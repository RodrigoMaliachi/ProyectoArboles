package modelo;

import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.DepositoArchivos;
import arbolb.Graduado;
import arbolb.arbol_mas.SerializadorException;

import java.util.ArrayList;
import java.util.List;

public class ArbolCalificacion {
    private DepositoArchivos<String, ListaSimple> calificaciones;
    private DepositoArchivos<String, ArrayList<Integer>> cal;

    public ArbolCalificacion(){
        try {
            calificaciones = new DepositoArchivos("src/","calificaciones",1000);
            cal =new DepositoArchivos("src/","caliiones",1000);
        } catch (ArbolException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearArbol(ArrayList<Graduado> listaa) throws SerializadorException, ArbolException {

        for (Graduado graduado : listaa) {
            String cadena = graduado.getPromedio();
            if(cal.exists(cadena)){
              ArrayList<Integer> leerGraduado = cal.get(graduado.getPromedio());
              leerGraduado.add(graduado.getIndice());
                System.out.println("e"+graduado.getIndice()+leerGraduado);
            }else{
              System.out.println("p"+graduado.getIndice());
              //ListaSimple aux = new ListaSimple();
              String p = graduado.getPromedio();
              //this.calificaciones.agregar(p,aux);
                ArrayList<Integer> pp = new ArrayList<>();
                pp.add(graduado.getIndice());
           //   calificaciones.agregar(graduado.getPromedio(),  new ListaSimple());
                cal.agregar(graduado.getPromedio(),pp);
                System.out.println("p"+graduado.getIndice());
            }
        }
            /*
            ArrayList leerVehiculo= calificaciones.get("90");
            String y = graduado.getPromedio();
            boolean x = calificaciones.exists(y);
            System.out.println(x + "  (" + y);
            */
        }


        //if(calificaciones.exists(graduado.getPromedio())){
        //  ArrayList leerGraduado = calificaciones.get(graduado.getPromedio());
        //leerGraduado.add(graduado.getIndice());

        //}else{
        //  System.out.println("p"+graduado.getIndice());
        //ArrayList aux = new ArrayList<>();
        //aux.add(graduado.getIndice());
        //calificaciones.agregar(graduado.getPromedio(), aux);
        //}
        //}


    public void listar() throws SerializadorException {
        List<ArrayList<Integer>> lista = cal.listar();
        System.out.println("\nLista");
        lista.stream().forEach((pru) -> {
            System.out.println(pru);
        });
    }
}
