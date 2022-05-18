package modelo;

import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.SerializadorException;

import java.io.File;

public class Test {
    public static void main(String[] args) throws ArbolException, SerializadorException {

        ArbolCalificacion arbolCalificacion;
        ArbolProfesiones arbolProfesiones;

        if ( fileExists("calificacion") ) {
            arbolCalificacion = new ArbolCalificacion();
            //arbolProfesiones = new ArbolProfesiones();
        } else {
            arbolCalificacion = new ArbolCalificacion();
            arbolCalificacion.crearArbol(Archivo.leerArchivoCSV());
           // arbolProfesiones = new ArbolProfesiones();
           // arbolProfesiones.crearArbol(Archivo.leerArchivoCSV());
        }

        arbolCalificacion.listar();
        //arbolProfesiones.listar();
    }

    private static boolean fileExists(String nombreArchivo ){
        File arb = new File("src/", nombreArchivo+".arb");
        File dat = new File("src/", nombreArchivo+".dat");

        if ( !arb.exists() || !dat.exists() ) {
            arb.delete();
            dat.delete();
            return false;
        }
        return true;
    }
}
