package modelo;

import arbolb.Graduado;
import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.DepositoArchivos;
import arbolb.arbol_mas.SerializadorException;

import java.util.*;

public class ArbolNombres {

    private static final String SRC_PATH = "src/";
    private static final String TREE_NAME = "nombres";
    private static final NameTree nombresTree;

    static{
        try {
            nombresTree = new NameTree(SRC_PATH, TREE_NAME, 1000);
        } catch (ArbolException e) {
            throw new RuntimeException(e);
        }
    }

    public static NameTree crearArbol(ArrayList<Graduado> alumnos) {
        alumnos.forEach(ArbolNombres::addNameToTree);
        return nombresTree;
    }

    private static void addNameToTree(Graduado graduado){
        splitName( graduado.getNombre() ).forEach( name -> {

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
        });
    }

    private static List<String> splitName(String name) {
        name = name.toLowerCase();
        return Arrays.stream(name.split(" ")).toList();
    }

    private static class NameTree extends DepositoArchivos<String, ArrayList<Integer>> {

        public NameTree(String path, String nombreArchivo, int separacion) throws ArbolException {
            super(path, nombreArchivo, separacion);
        }

        public Integer[] search(String nombre) {
            Set<Integer> indices = new HashSet<>();
            splitName(nombre).forEach(name -> {
                try {
                    if ( exists(name) ) {
                        if (indices.isEmpty()) {
                            indices.addAll( get(name) );
                        } else {
                            indices.retainAll( get(name) );
                        }
                    }
                } catch (SerializadorException e) {
                    e.printStackTrace();
                }
            });

            return indices.toArray(new Integer[0]);
        }
    }
}