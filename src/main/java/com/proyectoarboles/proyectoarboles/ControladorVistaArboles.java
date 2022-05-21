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
import modelo.ArbolCalificacion;
import modelo.ArbolProfesiones;
import modelo.Archivo;
import modelo.Test;

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
    @FXML ComboBox<Integer> comboCal;
    @FXML Button botonBuscar;

    // Para actualizar datos de la tabla
    private ObservableList<Graduado2> egresados;

    private ArrayList<Graduado> egresadosCompleto= new ArrayList<>();
    private ArrayList<Graduado2> egresadosDefault = new ArrayList<>();
    private ArbolCalificacion calificaciones = new ArbolCalificacion();
    private ArbolProfesiones profesiones = new ArbolProfesiones();

    private ArrayList<Integer> lista1=new ArrayList<>();
    private ArrayList<Integer> lista2=new ArrayList<>();


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
        comboNombre.getSelectionModel().getSelectedItem();
        //nombres
    }

    @FXML
    public void seleccionProfesion(){
        try {
            if(lista1.isEmpty()){
                lista1=profesiones.buscar(comboProf.getSelectionModel().getSelectedItem());
            }else {
                lista2 = profesiones.buscar(comboProf.getSelectionModel().getSelectedItem());
            }
        } catch (SerializadorException e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    @FXML
    public void seleccionCalificacion(){
        try {
            if(lista1.isEmpty()){
                lista1=calificaciones.buscar(comboCal.getSelectionModel().getSelectedItem());
            }else{
                lista2=calificaciones.buscar(comboCal.getSelectionModel().getSelectedItem());
            }

        } catch (SerializadorException e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    @FXML
    public void buscar() {
        if(!lista1.isEmpty()) {
            ArrayList<Graduado> busqueda = new ArrayList<>();
            if (lista2.isEmpty()) {
                int j = 0;
                for (Integer i : lista1) {
                    busqueda.add(egresadosCompleto.get(lista1.get(j)-1));
                    j++;
                    System.out.println("Aquí estoyyy"+ lista1);
                }
                mostrarBusqueda(busqueda);
                lista1.clear();
            } else {
                for(int i=0;i< lista1.size();i++){
                    for(int j=0;j< lista2.size();j++){
                        if(lista1.get(i).equals(lista2.get(j))){
                            busqueda.add(egresadosCompleto.get(lista1.get(i)));
                        }
                    }
                }
                System.out.println("Aquí estoy");
                mostrarBusqueda(busqueda);
                lista1.clear();
                lista2.clear();
            }
        }
    }

    private void mostrarBusqueda(ArrayList<Graduado> busqueda){
        egresados.clear();
        int i=0;
        System.out.println("Legoo aqui");
        System.out.println("Prbando aquí longituf");
        for(Graduado2 graduado:egresadosDefault){
            if(graduado.getNombre().equals(busqueda.get(i).getNombre())&& i<busqueda.size()-1) {
                egresados.add(graduado);
                i++;
            }
        }

    }

    private void initializeTrees(){
        egresadosCompleto=Archivo.leerArchivoCSV();
        if(!Test.fileExists("profesiones")){
            try {
                profesiones.crearArbol(egresadosCompleto);
            } catch (SerializadorException e) {
                e.printStackTrace();
            } catch (ArbolException e) {
                e.printStackTrace();
            }
        }
        if(!Test.fileExists("calificacion")){
            try {
                calificaciones.crearArbol(egresadosCompleto);
            } catch (SerializadorException e) {
                e.printStackTrace();
            } catch (ArbolException e) {
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
        for(Graduado2 graduado:egresadosDefault){
            if(!listaN.contains(graduado.getProfesion())) {
                listaN.add(graduado.getNombre());
            }
        }
        comboNombre.getItems().addAll(listaN);
    }

    private void initializeComboboxProfesiones(){
        ArrayList<String> listaN = new ArrayList<>();
        for(Graduado2 graduado:egresadosDefault){
            if(!listaN.contains(graduado.getProfesion())) {
                listaN.add(graduado.getProfesion());
            }
        }
        comboProf.getItems().addAll(listaN);
    }

    private void initializeComboboxCalificaciones(){
        ArrayList<Integer> listaN = new ArrayList<>();
        for(Graduado2 graduado:egresadosDefault){
            if(!listaN.contains(graduado.getPromedio())) {
                listaN.add(graduado.getPromedio());
            }
        }
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