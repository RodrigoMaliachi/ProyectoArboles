package modelo;

import arbolb.arbol_mas.DepositoArchivos;
import arbolb.arbol_mas.SerializadorException;
import java.io.File;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws SerializadorException {

        DepositoArchivos<String, ArrayList<Integer>> arbolNombres;

        if ( fileExists() ) {
            arbolNombres = ArbolNombres.getNombresTree();
        } else {
            arbolNombres = ArbolNombres.crearArbol(Archivo.leerArchivoCSV());
        }

        arbolNombres.listar();
    }

    private static boolean fileExists(){
        File arb = new File("src/", "nombres.arb");
        File dat = new File("src/", "nombres.dat");

        if ( !arb.exists() || !dat.exists() ) {
            arb.delete();
            dat.delete();
            return false;
        }
        return true;
    }
}
