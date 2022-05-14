package modelo;

import java.io.Serializable;

public class Graduado implements Serializable {
    private int indice;
    private String nombre;
    private int promedio;
    private String profesion;

    public  Graduado(String nombre, int promedio, String profesion){
        this.nombre = nombre;
        this.promedio = promedio;
        this.profesion = profesion;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    //------------------------------
    public int getPromedio(){
        return promedio;
    }

    public void setPromedio(int promedio){
        this.promedio = promedio;
    }
    //-------------------------------
    public String getProfesion(){
        return profesion;
    }

    public void setProfesion(String profesion){
        this.profesion = profesion;
    }

    //-------------------------------
    public int getIndice(){
        return indice;
    }

    public void setIndice(int indice){
        this.indice = indice;
    }

    //-------------------------------
    public String toString(){
        return "Alumnos: "+this.nombre+" Profesion: "+this.profesion+" Con promedio: "+this.promedio;
    }

}
