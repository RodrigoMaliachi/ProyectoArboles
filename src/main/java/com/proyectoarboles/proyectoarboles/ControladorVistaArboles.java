package com.proyectoarboles.proyectoarboles;

import arbolb.Graduado;
import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.SerializadorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.*;
import modelo.ArbolCalificacion;
import modelo.ArbolProfesiones;
import modelo.Archivo;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
/**
 * Es el controlador para la interfaz
 *
 * @author Rodrigo Natali Jonatan Angelica
 */
public class ControladorVistaArboles implements Initializable{
    @FXML private CheckBox checkboxNombre;
    @FXML private CheckBox checkboxProf;
    @FXML private CheckBox checkboxCal;
    @FXML private TableView<Graduado> tablaEgresados;
    @FXML private TableColumn<Graduado,String> columnaNombre;
    @FXML private TableColumn<Graduado,String> columnaProf;
    @FXML private TableColumn<Graduado,Integer> columnaCal;
    /**
     * Contenedor de todos los nombre
     */
    @FXML ComboBox<String> comboNombre;
    /**
     * Contenedor de todas las profesiones
     */
    @FXML ComboBox<String> comboProf;
    /**
     * Contenedor de todas las calificaciones
     */
    @FXML ComboBox<String> comboCal;
    @FXML Button botonBuscar;

    /**
     * Para actualizar los datos de la tabla
     */
    private ObservableList<Graduado> egresados;
    /**
     * Contiene la informacion del archivo CSV
     */
    private ArrayList<Graduado> egresadosCompleto= new ArrayList<>();

    private final ArbolNombres nombres = createTree();
    private final ArbolProfesiones profesiones = new ArbolProfesiones();
    private final ArbolCalificacion calificaciones = new ArbolCalificacion();
    /**
     * Listas para el proceso de los filtros
     */
    private ArrayList<Integer> lista1=new ArrayList<>();
    private ArrayList<Integer> lista2=new ArrayList<>();
    private int indiceNombre = -1;

    /**
     * Ordenar por nombres
     */
    @FXML
    public void filtrarPorNombre(){
        if(checkboxNombre.isSelected()){
            checkboxProf.setSelected(false);
            checkboxCal.setSelected(false);
            egresados.sort(Comparator.comparing(Graduado::getNombre));
        } else {
            egresados.clear();
            egresados.addAll(egresadosCompleto);
        }
    }

    /**
     * Ordenar por profesion
     */
    @FXML
    public void filtrarPorProfesion(){
        if ( checkboxProf.isSelected() ) {
            checkboxNombre.setSelected(false);
            checkboxCal.setSelected(false);
            egresados.sort(Comparator.comparing(Graduado::getProfesion));
        } else {
            egresados.clear();
            egresados.addAll(egresadosCompleto);
        }
    }

    /**
     * Ordenar por calificacion
     */
    @FXML
    public void filtrarPorCalificacion(){
        if ( checkboxCal.isSelected() ) {
            checkboxNombre.setSelected(false);
            checkboxProf.setSelected(false);
            egresados.sort(Comparator.comparingInt(Graduado::getPromedio));
        } else {
            egresados.clear();
            egresados.addAll(egresadosCompleto);
        }
    }
    @FXML
    public void seleccionNombre() {
        String nombre = comboNombre.getValue();
        indiceNombre = !nombre.equals("Nombre") ? nombres.busca(nombre).getIndice() : -1;
    }

    /**
     * Guardar los indices de los graduados de la profesion seleccionada en alguna de las listas
     */
    @FXML
    public void seleccionProfesion(){
        try {
            if(!comboProf.getSelectionModel().getSelectedItem().equals("Profesion")) {
                if (lista1.isEmpty()) {
                    lista1 = profesiones.buscar(comboProf.getSelectionModel().getSelectedItem());
                } else {
                    lista2 = profesiones.buscar(comboProf.getSelectionModel().getSelectedItem());
                }
            }
        } catch (SerializadorException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Guardar los indices de los graduados de la calificacion seleccionada en alguna de las listas
     */
    @FXML
    public void seleccionCalificacion(){
        try {
            int opc = Integer.parseInt(comboCal.getValue());
                if (lista1.isEmpty()) {
                    lista1 = calificaciones.buscar(opc);
                } else {
                    lista2 = calificaciones.buscar(opc);
                }
        } catch (SerializadorException e) {
            e.printStackTrace();
        } catch (NumberFormatException ignored){}
    }

    /**
     * La funcion principal para filtrar los datos.
     * Se verifica si es por calificacion y profesion.
     * Se verifica si es por nombre.
     */
    public void buscar() {
        if ( indiceNombre != -1 ) {
            lista1.clear();
            lista1.add(indiceNombre);
            mostrarBusqueda(lista1);
        } else if (!lista1.isEmpty()) {
            if (lista2.isEmpty()) {
                mostrarBusqueda(lista1);
            } else {
                lista1.retainAll(lista2);
                mostrarBusqueda(lista1);
            }
        }
        indiceNombre = -1;
        lista1.clear();
        lista2.clear();
        comboNombre.setValue("Nombre");
        comboProf.getSelectionModel().selectFirst();
        comboCal.getSelectionModel().selectFirst();
    }

    /**
     * Muestra el resultado del filtro en la interfaz
     * @param busqueda es la lista con los resultados obtenidos en la funcion buscar
     */
    private void mostrarBusqueda(ArrayList<Integer> busqueda){
        egresados.clear();
        for (Integer integer : busqueda) {
            egresados.add(egresadosCompleto.get(integer - 1));
        }
    }

    /**
     * Crea los arboles de nombre y prefesiones
     */
    private void initializeTrees(){
        egresadosCompleto=Archivo.leerArchivoCSV();
        if(!fileExists("profesiones.arb") || !fileExists("profesiones.dat")){
            try {
                profesiones.crearArbol(egresadosCompleto);
            } catch (SerializadorException | ArbolException e) {
                e.printStackTrace();
            }
        }
        if(!fileExists("calificacion.arb") || !fileExists("calificacion.dat")){
            try {
                calificaciones.crearArbol(egresadosCompleto);
            } catch (SerializadorException | ArbolException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Se inicializa la tabla e imprime los 624 valores
     */
    private void initializeTable(){
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaProf.setCellValueFactory(new PropertyValueFactory<>("profesion"));
        columnaCal.setCellValueFactory(new PropertyValueFactory<>("promedio"));

        egresados = FXCollections.observableArrayList();
        tablaEgresados.setItems(egresados);
        imprimirDatosTable(egresadosCompleto);
    }

    /**
     * Se agregan los nombres al comboBox de nombres
     */
    private void initializeComboboxNombres(){
        ArrayList<String> listaN = new ArrayList<>();
        listaN.add("Nombre");
        for(Graduado graduado:egresadosCompleto){
            if(!listaN.contains(graduado.getProfesion())) {
                listaN.add(graduado.getNombre());
            }
        }

        comboNombre.getItems().addAll(listaN);
    }

    /**
     * Se agregan las profesiones al comboBox.
     * Si hay 30 profesiones, estas 30 se agregan para que se puedan utilizar para filtrar
     */
    private void initializeComboboxProfesiones() throws SerializadorException {
        ArrayList<String> listaN = new ArrayList<>();
        listaN.add("Profesion");
        for(int i=0;i<profesiones.getIndices().size();i++){
            listaN.add((String) profesiones.getIndices().get(i).getClave());
        }
        comboProf.getItems().addAll(listaN);
    }

    /**
     * Se agregan las calificaciones al comboBox
     */
    private void initializeComboboxCalificaciones() throws SerializadorException {
        ArrayList<String> listaN = new ArrayList<>();
        listaN.add("Calificacion");
        for(int i=60;i<=100;i++){
            listaN.add(String.valueOf(i));
        }
        comboCal.getItems().addAll(listaN);
    }

    /**
     * Esta funcion es la primera que se ejecuta al ejecutar el main.
     * No es necesario llamarla
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTrees();
        initializeTable();
        initializeComboboxNombres();
        try {
            initializeComboboxProfesiones();
        } catch (SerializadorException e) {
            throw new RuntimeException(e);
        }
        try {
            initializeComboboxCalificaciones();
        } catch (SerializadorException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Para imprimir los datos en la tabla, se llama en initializeTable()
     * @param alumnos se le pasa egresadosCompletos que es llenado en initializeTrees()
     */
    private void imprimirDatosTable(ArrayList<Graduado> alumnos){
        egresados.clear();
        egresados.addAll(alumnos);
    }

    private static ArbolNombres createTree() {
      return fileExists("nombres.arb") ? ArbolNombresSerializator.fromBinaryFile() : new ArbolNombres( Archivo.leerArchivoCSV() );
    }

    /**
     * Verifica la existencias de los archivos .arb y .dat
     * @param nombre nombre del archivo con extensión
     * @return false o true segun la exitencia del archivo
     */
    private static boolean fileExists(String nombre){
        return new File("src/", nombre).exists();
    }
}