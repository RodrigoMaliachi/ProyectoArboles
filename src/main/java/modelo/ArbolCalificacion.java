package modelo;

import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.DepositoArchivos;
import arbolb.Graduado;
import arbolb.arbol_mas.SerializadorException;

import java.util.ArrayList;
import java.util.List;

public class ArbolCalificacion {
    private DepositoArchivos<Integer, ArrayList<Integer>> cal;

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
            if(cal.exists(cadena)){
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

    public void listar() throws SerializadorException {
        List<ArrayList<Integer>> lista = cal.listar();
        System.out.println("\nLista");
        lista.stream().forEach((pru) -> {
            System.out.println(pru);
        });
    }
}
