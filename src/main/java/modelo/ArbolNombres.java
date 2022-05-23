package modelo;

import arbolb.Graduado;
import ArbolAVL.ArbolAVL;
import ArbolAVL.VerticeArbolBinario;
import java.util.Collection;

public class ArbolNombres extends ArbolAVL<String> {

    public class VerticeNombre extends VerticeAVL {

        protected int indice;

        /**
         * Constructor único que recibe un elemento.
         *
         * @param elemento el elemento del vértice.
         */
        public VerticeNombre(String elemento) {
            super(elemento);
        }

        public int getIndice() {
            return indice;
        }
    }

    public ArbolNombres() {
        super();
    }

    public ArbolNombres(Collection<Graduado> collection) {
        collection.forEach(this::agrega);
        ArbolNombresSerializator.toBinaryFile(this);
    }

    @Override
    protected Vertice nuevoVertice(String elemento) {
        return new VerticeNombre(elemento);
    }

    public void agrega(Graduado g) {
        agrega(g.getNombre());
        verticeNombre( busca(g.getNombre()) ).indice = g.getIndice();
    }

    @Override
    public VerticeNombre busca(String elemento) {
        return verticeNombre( super.busca(elemento) );
    }

    private VerticeNombre verticeNombre(VerticeArbolBinario<String> vertice) {
        return (VerticeNombre) vertice;
    }
}
