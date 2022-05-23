package modelo;

import arbolb.Graduado;
import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.DepositoArchivos;
import arbolb.arbol_mas.Elemento;
import arbolb.arbol_mas.SerializadorException;

import java.util.ArrayList;
import java.util.List;
/**
 * El objetivo es tener los metodos y atributos necesarios para crear el arbol de profesiones
 *
 * @author Rodrigo Natali Jonatan Angelica
 */
public class ArbolProfesiones {
    private DepositoArchivos<String, ArrayList<Integer>> profesion;

    /**
     * Constructor de la clase.
     * Se crea un objecto de tipo "DepositoArchivo". Al constructor se le pasa el nombre de los archivos .dat y .arb
     * Tambien se le pasa la separacion, representando el limite de datos almacenables en los archivos
     */
    public ArbolProfesiones(){
        try {
            profesion =new DepositoArchivos("src/","profesiones",100000);
        } catch (ArbolException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearArbol(ArrayList<Graduado> listaa) throws SerializadorException, ArbolException {
        for (Graduado graduado : listaa) {
            String cadena = graduado.getProfesion();

            if(profesion.exists(cadena)){

                int aux2 = graduado.getIndice(); // El inidice que se agregará
                ArrayList<Integer> aux3 = new ArrayList<>();  // La lista actualizada
                aux3 = profesion.get(graduado.getProfesion());
                aux3.add(aux2);   //Se agrega el nuevo indice
                profesion.modificar(cadena,aux3);   //Se actualizan los datos

            }else{
                String p = graduado.getProfesion();  //La clave del arbol
                ArrayList<Integer> pp = new ArrayList<>();  //Se crea el arreglo que guardará el primer elemento
                pp.add(graduado.getIndice());   //Se agrega el primer indice
                profesion.agregar(graduado.getProfesion(),pp);  // Se agrega la información al arbol
            }
        }
    }

    /**
     * Busca un elementos en el arbol dependiendo la clave
     * @param prof es la clave, por ejemplo "Arquitecto"
     * @return retorna la lista de indices guardados en la clave
     */
    public ArrayList<Integer> buscar(String prof) throws SerializadorException {
        return profesion.get(prof);
    }

    /**
     * Regresa las profesiones que se guardaron
     * @return lista de las profesiones guardadas
     */
    public List<Elemento> getIndices() throws SerializadorException{
        return profesion.indices();
    }

}