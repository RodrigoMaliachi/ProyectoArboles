package modelo;

import java.io.*;
/**
 * Parte del proceso para generar el arbol de nombres
 *
 * @author Rodrigo Natali Jonatan Angelica
 */
public class ArbolNombresSerializator {

    private static final String FILE_PATH = "src/nombres.arb";

    public static boolean toBinaryFile(ArbolNombres tree) {
        try {
            ObjectOutputStream output = new ObjectOutputStream( new FileOutputStream( FILE_PATH ));
            output.writeObject(tree);
            output.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArbolNombres fromBinaryFile() {
        try {
            ObjectInputStream input = new ObjectInputStream( new FileInputStream( FILE_PATH ));
            ArbolNombres nombresTree = (ArbolNombres) input.readObject();
            input.close();
            return nombresTree;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
