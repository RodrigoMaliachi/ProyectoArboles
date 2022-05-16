package arbolb;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Graduado implements Serializable, Comparable<Graduado> {
    private int indice;
    private String nombre;
    private String profesion;
    private int promedio;

    public Graduado(int indice, String nombre, String profesion, int promedio) {
        this.indice = indice;
        this.nombre = nombre;
        this.profesion = profesion;
        this.promedio = promedio;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public String toString(){
        return "Alumnos: " + nombre + " Profesion: " + profesion + " Con promedio: " + promedio;
    }

    public static Graduado parseGraduado(String[] data) {
        return new Graduado(
                Integer.parseInt( data[0] ),
                data[1],
                data[2],
                Integer.parseInt( data[3] )
        );
    }

    @Override
    public int compareTo(Graduado o) {
        return indice - o.indice;
    }
}
