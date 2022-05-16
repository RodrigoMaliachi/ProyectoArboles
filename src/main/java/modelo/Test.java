package modelo;

import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.SerializadorException;

import java.io.File;

public class Test {
    public static void main(String[] args) throws ArbolException, SerializadorException {

        ArbolCalificacion arbolCalificacion;

        if ( fileExists() ) {
            arbolCalificacion = new ArbolCalificacion();
        } else {
            arbolCalificacion = new ArbolCalificacion();
            arbolCalificacion.crearArbol(Archivo.leerArchivoCSV());
        }

        arbolCalificacion.listar();
    }

    private static boolean fileExists(){
        File arb = new File("src/", "caliiones.arb");
        File dat = new File("src/", "caliiones.dat");

        if ( !arb.exists() || !dat.exists() ) {
            arb.delete();
            dat.delete();
            return false;
        }
        return true;
    }
}
