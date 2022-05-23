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

public class ControladorVistaArboles implements Initializable{
    @FXML private CheckBox checkboxNombre;
    @FXML private CheckBox checkboxProf;
    @FXML private CheckBox checkboxCal;
    @FXML private TableView<Graduado> tablaEgresados;
    @FXML private TableColumn<Graduado,String> columnaNombre;
    @FXML private TableColumn<Graduado,String> columnaProf;
    @FXML private TableColumn<Graduado,Integer> columnaCal;
    @FXML ComboBox<String> comboNombre;
    @FXML ComboBox<String> comboProf;
    @FXML ComboBox<String> comboCal;
    @FXML Button botonBuscar;

    // Para actualizar datos de la tabla
    private ObservableList<Graduado> egresados;

    private ArrayList<Graduado> egresadosCompleto= new ArrayList<>();

    private final ArbolNombres nombres = createTree();
    private final ArbolProfesiones profesiones = new ArbolProfesiones();
    private final ArbolCalificacion calificaciones = new ArbolCalificacion();

    private ArrayList<Integer> lista1=new ArrayList<>();
    private ArrayList<Integer> lista2=new ArrayList<>();
    private int indiceNombre = -1;


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

    private void mostrarBusqueda(ArrayList<Integer> busqueda){
        egresados.clear();
        for (Integer integer : busqueda) {
            egresados.add(egresadosCompleto.get(integer - 1));
        }
    }

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

    private void initializeTable(){
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaProf.setCellValueFactory(new PropertyValueFactory<>("profesion"));
        columnaCal.setCellValueFactory(new PropertyValueFactory<>("promedio"));

        egresados = FXCollections.observableArrayList();
        tablaEgresados.setItems(egresados);
        imprimirDatosTable(egresadosCompleto);
    }

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

    private void initializeComboboxProfesiones() throws SerializadorException {
        ArrayList<String> listaN = new ArrayList<>();
        listaN.add("Profesion");
        for(int i=0;i<profesiones.getIndices().size();i++){
            listaN.add((String) profesiones.getIndices().get(i).getClave());
        }
        comboProf.getItems().addAll(listaN);
    }

    private void initializeComboboxCalificaciones() throws SerializadorException {
        ArrayList<String> listaN = new ArrayList<>();
        listaN.add("Calificacion");
        for(int i=60;i<=100;i++){
            listaN.add(String.valueOf(i));
        }
        comboCal.getItems().addAll(listaN);
    }

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

    private void imprimirDatosTable(ArrayList<Graduado> alumnos){
        egresados.clear();
        egresados.addAll(alumnos);
    }

    private static ArbolNombres createTree() {
      return fileExists("nombres.arb") ? ArbolNombresSerializator.fromBinaryFile() : new ArbolNombres( Archivo.leerArchivoCSV() );
    }

    private static boolean fileExists(String nombre){
        return new File("src/", nombre).exists();
    }
}