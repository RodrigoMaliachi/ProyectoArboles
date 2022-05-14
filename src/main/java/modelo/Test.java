package modelo;

import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.SerializadorException;

public class Test {
    public static void main(String[] args) throws ArbolException, SerializadorException {
        Archivo archi= new Archivo();
        archi.leerArchivoCSV("Egresadostxt.txt");

        ArbolCalificacion arbolCalificacion = new ArbolCalificacion();
        arbolCalificacion.crearArbol(archi.getListaGraduado());
        //arbolCalificacion.listar();
    }
}
