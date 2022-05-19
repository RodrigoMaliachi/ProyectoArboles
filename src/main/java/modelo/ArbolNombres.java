package modelo;

import arbolb.arbol_mas.ArbolException;
import arbolb.arbol_mas.DepositoArchivos;
import arbolb.Graduado;
import arbolb.arbol_mas.SerializadorException;
import java.util.ArrayList;
import java.util.List;

public class ArbolNombres {
    private final DepositoArchivos<String, Integer> nombres;

    public ArbolNombres() throws ArbolException {
        nombres = new DepositoArchivos<>("src/","nombres",100000);
    }

    public ArbolNombres(ArrayList<Graduado> lista) throws ArbolException {
        this();
        crearArbol(lista);
    }

    private void crearArbol(ArrayList<Graduado> lista) {
        lista.forEach(this::agregarNombre);
    }

    private void agregarNombre(Graduado g) {
        try {
            nombres.agregar(g.getNombre(), g.getIndice());
        } catch (ArbolException | SerializadorException e) {
            e.printStackTrace();
        }
    }

    private DepositoArchivos<String, Integer> getNombres() {
        return nombres;
    }

    public void listar() throws SerializadorException {
        List<Integer> lista = nombres.listar();
        lista.forEach(System.out::println);
    }
}
