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
import modelo.Test;

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
    @FXML ComboBox<Integer> comboCal;
    @FXML Button botonBuscar;

    // Para actualizar datos de la tabla
    private ObservableList<Graduado> egresados;

    private ArrayList<Graduado> egresadosCompleto= new ArrayList<>();
    private final ArrayList<Graduado> egresadosDefault = new ArrayList<>();
    private final ArbolCalificacion calificaciones = new ArbolCalificacion();
    private final ArbolProfesiones profesiones = new ArbolProfesiones();

    private ArrayList<Integer> lista1=new ArrayList<>();
    private ArrayList<Integer> lista2=new ArrayList<>();


    @FXML
    public void filtrarPorNombre(){
        if(checkboxNombre.isSelected()){
            checkboxProf.setSelected(false);
            checkboxCal.setSelected(false);
            egresados.sort(new Comparator<Graduado>() {
                @Override
                public int compare(Graduado o1, Graduado o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });
        }else{
            egresados.clear();
            egresados.addAll(egresadosDefault);
        }
    }

    @FXML
    public void filtrarPorProfesion(){
        if(checkboxProf.isSelected()){
            checkboxNombre.setSelected(false);
            checkboxCal.setSelected(false);
            egresados.sort(new Comparator<Graduado>() {
                @Override
                public int compare(Graduado o1, Graduado o2) {
                    return o1.getProfesion().compareTo(o2.getProfesion());
                }
            });
        }else{
            egresados.clear();
            egresados.addAll(egresadosDefault);
        }
    }

    @FXML
    public void filtrarPorCalificacion(){
        if(checkboxCal.isSelected()){
            checkboxNombre.setSelected(false);
            checkboxProf.setSelected(false);
            egresados.sort(new Comparator<Graduado>() {
                @Override
                public int compare(Graduado o1, Graduado o2) {
                    return o1.getPromedio()-o2.getPromedio();
                }
            });
        }else{
            egresados.clear();
            egresados.addAll(egresadosDefault);
        }
    }

    @FXML
    public void seleccionNombre() {
        comboNombre.getSelectionModel().getSelectedItem();
        //nombres
    }

    @FXML
    public void seleccionProfesion(){
        try {
            if(comboProf.getSelectionModel().getSelectedItem()!="Profesion") {
                if (lista1.isEmpty()) {
                    lista1 = profesiones.buscar(comboProf.getSelectionModel().getSelectedItem());
                } else {
                    lista2 = profesiones.buscar(comboProf.getSelectionModel().getSelectedItem());
                }
            }
        } catch (SerializadorException e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    @FXML
    public void seleccionCalificacion(){
        try {
            if(comboCal.getSelectionModel().getSelectedItem()!=0){
                if (lista1.isEmpty()) {
                    lista1 = calificaciones.buscar(comboCal.getSelectionModel().getSelectedItem());
                } else {
                    lista2 = calificaciones.buscar(comboCal.getSelectionModel().getSelectedItem());
                }
            }
        } catch (SerializadorException e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    public void buscar() {
        if (!lista1.isEmpty()) {
            ArrayList<Integer> busqueda = new ArrayList<>();
            if (lista2.isEmpty()) {
                for (Integer i : lista1) {
                    System.out.println("Aquí estoyyy" + lista1);
                }
                mostrarBusqueda(lista1);
                lista1.clear();
            } else {
                for (int i = 0; i < lista1.size(); i++) {
                    for (int j = 0; j < lista2.size(); j++) {
                        if (lista1.get(i).equals(lista2.get(j))) {
                            busqueda.add(lista1.get(i));
                        }
                    }
                }
                System.out.println(busqueda);
                mostrarBusqueda(busqueda);
                lista1.clear();
                lista2.clear();
            }
        }
        comboProf.getSelectionModel().selectLast();
        comboCal.getSelectionModel().selectLast();
    }

    private void mostrarBusqueda(ArrayList<Integer> busqueda){
        egresados.clear();
        for(int j = 0; j<busqueda.size();j++){
            egresados.add(egresadosDefault.get(busqueda.get(j)-1));
        }
    }

    private void initializeTrees(){
        egresadosCompleto=Archivo.leerArchivoCSV();
        if(!Test.fileExists("profesiones")){
            try {
                profesiones.crearArbol(egresadosCompleto);
            } catch (SerializadorException | ArbolException e) {
                e.printStackTrace();
            }
        }
        if(!Test.fileExists("calificacion")){
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
        for(Graduado graduado:egresadosDefault){
            if(!listaN.contains(graduado.getProfesion())) {
                listaN.add(graduado.getNombre());
            }
        }

        comboNombre.getItems().addAll(listaN);
    }

    private void initializeComboboxProfesiones(){
        ArrayList<String> listaN = new ArrayList<>();
        for(Graduado graduado:egresadosDefault){
            if(!listaN.contains(graduado.getProfesion())) {
                listaN.add(graduado.getProfesion());
            }
        }
        listaN.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        listaN.add("Profesion");
        comboProf.getItems().addAll(listaN);
    }

    private void initializeComboboxCalificaciones(){
        ArrayList<Integer> listaN = new ArrayList<>();
        for(Graduado graduado:egresadosDefault){
            if(!listaN.contains(graduado.getPromedio())) {
                listaN.add(graduado.getPromedio());
            }
        }
        listaN.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        listaN.add(0);
        comboCal.getItems().addAll(listaN);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTrees();
        initializeTable();
        initializeComboboxNombres();
        initializeComboboxProfesiones();
        initializeComboboxCalificaciones();
    }

    private void imprimirDatosTable(ArrayList<Graduado> alumnos){
        egresados.clear();
        egresados.addAll(alumnos);
    }

    private static ArbolNombres createTree() {
      return doesFilesTreesExists() ? ArbolNombresSerializator.fromBinaryFile() : new ArbolNombres( Archivo.leerArchivoCSV() );
    }

    private static boolean doesFilesTreesExists(){
        return new File("src/", "nombres.arb").exists();
    }
}