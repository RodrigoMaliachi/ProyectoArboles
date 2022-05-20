package com.proyectoarboles.proyectoarboles;

import arbolb.Graduado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Archivo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ControladorVistaArboles implements Initializable{
    @FXML private CheckBox checkboxNombre;
    @FXML private CheckBox checkboxProf;
    @FXML private CheckBox checkboxCal;
    @FXML private TableView<Graduado2> tablaEgresados;
    @FXML private TableColumn<Graduado2,String> columnaNombre;
    @FXML private TableColumn<Graduado2,String> columnaProf;
    @FXML private TableColumn<Graduado2,Integer> columnaCal;
    @FXML ComboBox<String> comboNombre;
    @FXML ComboBox<String> comboProf;
    @FXML ComboBox<String> comboCal;
    @FXML Button botonBuscar;

    // Para actualizar datos de la tabla
    private ObservableList<Graduado2> egresados;

    private ArrayList<Graduado2> egresadosDefault = new ArrayList<>();


    @FXML
    public void filtrarPorNombre(){
        if(checkboxNombre.isSelected()){
            checkboxProf.setSelected(false);
            checkboxCal.setSelected(false);
            egresados.sort(new Comparator<Graduado2>() {
                @Override
                public int compare(Graduado2 o1, Graduado2 o2) {
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
            egresados.sort(new Comparator<Graduado2>() {
                @Override
                public int compare(Graduado2 o1, Graduado2 o2) {
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
            egresados.sort(new Comparator<Graduado2>() {
                @Override
                public int compare(Graduado2 o1, Graduado2 o2) {
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

    }

    @FXML
    public void seleccionProfesion(){

    }

    @FXML
    public void seleccionCalificacion(){

    }

    @FXML
    public void buscar(){
        //columnaCal.setSortable(true);
        //columnaCal.setSortType(TableColumn.SortType.DESCENDING);
    }

    private void initializeTable(){
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaProf.setCellValueFactory(new PropertyValueFactory<>("profesion"));
        columnaCal.setCellValueFactory(new PropertyValueFactory<>("promedio"));

        egresados = FXCollections.observableArrayList();
        tablaEgresados.setItems(egresados);
        imprimirDatosTable();
    }



    private void initializeComboboxNombres(){
        ArrayList<String> listaN = new ArrayList<>();
        listaN.add("Andrea"); listaN.add("Alma"); listaN.add("Jonatan"); listaN.add("Rodrigo"); listaN.add("");
        comboNombre.getItems().addAll(listaN);
    }

    private void initializeComboboxProfesiones(){
        ArrayList<String> listaN = new ArrayList<>();
        listaN.add("Andrea"); listaN.add("Alma"); listaN.add("Jonatan"); listaN.add("Rodrigo");
        comboProf.getItems().addAll(listaN);
    }

    private void initializeComboboxCalificaciones(){
        ArrayList<String> listaN = new ArrayList<>();
        listaN.add("Andrea"); listaN.add("Alma"); listaN.add("Jonatan"); listaN.add("Rodrigo");
        comboCal.getItems().addAll(listaN);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        initializeComboboxNombres();
        initializeComboboxProfesiones();
        initializeComboboxCalificaciones();
    }

    private void imprimirDatosTable(){
        egresados.clear();
        ArrayList<Graduado> alumnos = new ArrayList<>();
        alumnos = Archivo.leerArchivoCSV();

        for (Graduado graduado : alumnos) {
            egresados.add(new Graduado2(graduado.getNombre(),graduado.getProfesion(),graduado.getPromedio()));
        }

        llenarDefault(egresados);
    }

    private void llenarDefault(ObservableList<Graduado2> egresados){
        for(Graduado2 graduado : egresados){
            egresadosDefault.add(graduado);
        }
    }
}