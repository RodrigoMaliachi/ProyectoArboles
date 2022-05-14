package modelo;

public class Nodo {
    protected Object dato;
    protected Nodo siguiente;

    public Nodo(){

    }

    public Nodo(Object dato){
        this.dato = dato;
        this.siguiente = null;
    }

    public Nodo(Object dato,Nodo siguiente){
        this.dato = dato;
        this.siguiente = siguiente;
    }

    public Object getDato(){
        return this.dato;
    }

    public void setDato(Object dato){
        this.dato = dato;
    }

    public Nodo getSiguiente(){
        return this.siguiente;
    }

    public void setSiguiente(Nodo siguiente){
        this.siguiente = siguiente;
    }
}
