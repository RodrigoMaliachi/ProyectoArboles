package modelo;

import java.io.File;

public class Test {
    public static void main(String[] args) {

        ArbolNombres arbolNombres;

        arbolNombres = createTree();

        if ( arbolNombres != null ) {
            System.out.println(arbolNombres);

            System.out.println( arbolNombres.busca("Jonatan Gonzalez Ahumada").indice );
        }
    }

    private static ArbolNombres createTree() {
        return doesFilesTreesExists() ? ArbolNombresSerializator.fromBinaryFile() : new ArbolNombres( Archivo.leerArchivoCSV() );
    }

    private static boolean doesFilesTreesExists(){
        return new File("src/", "nombres.arb").exists();
    }
}
