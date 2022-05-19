package modelo;

import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.SerializadorException;
import java.io.File;

public class Test {
    public static void main(String[] args) throws SerializadorException {

        ArbolNombres arbolNombres;

        arbolNombres = createTree();

        if ( arbolNombres != null )
            arbolNombres.listar();
    }

    private static ArbolNombres createTree() {
        try {
            return doesFilesTreesExists() ? new ArbolNombres() : new ArbolNombres( Archivo.leerArchivoCSV() );
        } catch (ArbolException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean doesFilesTreesExists(){
        File arb = new File("src/", "nombres.arb");
        File dat = new File("src/", "nombres.dat");

        if ( !arb.exists() || !dat.exists() ) {
            if ( arb.exists() )
                arb.delete();
            if ( dat.exists() )
                dat.delete();
            return false;
        }
        return true;
    }
}
