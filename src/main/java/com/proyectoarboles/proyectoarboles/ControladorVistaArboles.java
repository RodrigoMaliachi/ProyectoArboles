package com.proyectoarboles.proyectoarboles;

import arbolb.Graduado;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorVistaArboles implements Initializable{
    @FXML private CheckBox checkboxNombre;
    @FXML private CheckBox checkboxProf;
    @FXML private CheckBox checkboxCal;
    @FXML private TableView<Graduado> tablaEgresados;
    @FXML private TableColumn<Graduado,String> columnaNombre;
    @FXML private TableColumn<Graduado,String> columnaProf;
    @FXML private TableColumn<Graduado,String> columnaCal;
    ObservableList<Graduado> egresados;
    @FXML ComboBox<String> comboNombre;
    @FXML ComboBox<String> comboProf;
    @FXML ComboBox<String> comboCal;
    @FXML Button botonBuscar;

    @FXML
    public void filtrarPorNombre(){
        checkboxProf.setSelected(false);
        checkboxCal.setSelected(false);
    }

    @FXML
    public void filtrarPorProfesion(){
        checkboxNombre.setSelected(false);
        checkboxCal.setSelected(false);
    }

    @FXML
    public void filtrarPorCalificacion(){
        checkboxProf.setSelected(false);
        checkboxNombre.setSelected(false);
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

    }

    private void initializeTable(){

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
}
