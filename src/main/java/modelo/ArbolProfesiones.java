package modelo;

import arbolb.Graduado;
import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.DepositoArchivos;
import arbolb.arbol_mas.Elemento;
import arbolb.arbol_mas.SerializadorException;
import com.proyectoarboles.proyectoarboles.Graduado2;

import java.util.ArrayList;
import java.util.List;

public class ArbolProfesiones {
    private DepositoArchivos<String, ArrayList<Integer>> profesion;

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

    public ArrayList<Integer> buscar(String prof) throws SerializadorException {
        return profesion.get(prof);
    }

    public List<Elemento> getIndices() throws SerializadorException{
        return profesion.indices();
    }

    public void listar() throws SerializadorException {
        List<ArrayList<Integer>> lista = profesion.listar();
        System.out.println("\nLista");
        lista.stream().forEach((grad) -> {
            System.out.println(grad);
        });
    }
}