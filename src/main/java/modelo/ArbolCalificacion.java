package modelo;

import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.DepositoArchivos;
import arbolb.Graduado;
import arbolb.arbol_mas.Elemento;
import arbolb.arbol_mas.SerializadorException;

import java.util.ArrayList;
import java.util.List;
/**
 * El objetivo es tener los metodos y atributos necesarios para crear el arbol de calificaciones
 *
 * @author Rodrigo Natali Jonatan Angelica
 */
public class ArbolCalificacion {
    private DepositoArchivos<Integer, ArrayList<Integer>> cal;

    /**
     * Constructor de la clase.
     * Se crea un objecto de tipo "DepositoArchivo". Al constructor se le pasa el nombre de los archivos .dat y .arb
     * Tambien se le pasa la separacion, representando el limite de datos almacenables en los archivos
     */
    public ArbolCalificacion(){
        try {
            cal =new DepositoArchivos("src/","calificacion",1000);
        } catch (ArbolException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearArbol(ArrayList<Graduado> listaa) throws SerializadorException, ArbolException {
        for (Graduado graduado : listaa) {
            int cadena = graduado.getPromedio();
            if(cal.exists(cadena)){   // Ya se guardó una calificación igual a la que se acaba de ingresar
                int aux2 = graduado.getIndice(); // El inidice que se agregará
                ArrayList<Integer> aux3 = new ArrayList<>();  // La lista actualizada
                aux3 = cal.get(graduado.getPromedio());
                aux3.add(aux2);   //Se agrega el nuevo indice
               cal.modificar(cadena,aux3);   //Se actualizan los datos
            }else{
                int p = graduado.getPromedio();  //La clave del arbol
                ArrayList<Integer> pp = new ArrayList<>();  //Se crea el arreglo que guardará el primer elemento
                pp.add(graduado.getIndice());   //Se agrega el primer indice
                cal.agregar(graduado.getPromedio(),pp);  // Se agrega la información al arbol
            }
        }
    }

    /**
     * Busca un elementos en el arbol dependiendo la clave
     * @param calificacion es la clave, por ejemplo "90"
     * @return retorna la lista de indices guardados en la clave
     */
    public ArrayList<Integer> buscar(int calificacion) throws SerializadorException {
        return cal.get(calificacion);
    }

}
