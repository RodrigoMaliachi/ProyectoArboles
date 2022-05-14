package arbolb;

import java.io.Serializable;

public class Graduado implements Serializable {
    private int indice;
    private String nombre;
    private String promedio;
    private String profesion;

    public  Graduado(int indice,String nombre, String profesion, String promedio){
        this.nombre = nombre;
        this.promedio = promedio;
        this.profesion = profesion;
        this.indice = indice;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    //------------------------------
    public String getPromedio(){
        return promedio;
    }

    public void setPromedio(String promedio){
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
