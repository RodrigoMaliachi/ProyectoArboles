package modelo;

import arbolb.Graduado;
import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.DepositoArchivos;
import arbolb.arbol_mas.SerializadorException;

import java.util.*;

public class ArbolNombres {

    private static final String SRC_PATH = "src/";
    private static final String TREE_NAME = "nombres";
    private static final DepositoArchivos<String, ArrayList<Integer>> nombresTree;

    static{
        try {
            nombresTree = new DepositoArchivos<>(SRC_PATH, TREE_NAME, 1000);
        } catch (ArbolException e) {
            throw new RuntimeException(e);
        }
    }

    public static DepositoArchivos<String, ArrayList<Integer>> crearArbol(ArrayList<Graduado> alumnos) {
        alumnos.forEach(ArbolNombres::addNameToTree);
        return nombresTree;
    }

    private static void addNameToTree(Graduado graduado){
        String name = graduado.getNombre();

        ArrayList<Integer> indices;

        try {
            if ( nombresTree.exists( name ) ) {
                indices = nombresTree.get( name );
                indices.add( graduado.getIndice() );

                nombresTree.modificar(
                        name,
                        indices
                );
            } else {
                indices = new ArrayList<>();
                indices.add( graduado.getIndice() );

                nombresTree.agregar(
                        name,
                        indices
                );
            }
        } catch (SerializadorException | ArbolException e) {
            e.printStackTrace();
        }
    }

    public static DepositoArchivos<String, ArrayList<Integer>> getNombresTree(){
        return nombresTree;
    }
}