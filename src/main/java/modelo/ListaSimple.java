package modelo;

public class ListaSimple {
    protected  Nodo inicio;
    protected  Nodo ultimo;
    public void insertarFinal (Object dato){
        if(vacio()){
            inicio = ultimo = new Nodo(dato);
        }else{
            Nodo temp = new Nodo(dato);
            ultimo.setSiguiente(temp);
            ultimo = temp;
        }
    }
    public boolean vacio(){
        return inicio == null;
    }

    public void imprimir(){
        Nodo actual = inicio;
        while(actual != null){
            if(actual==ultimo){
                System.out.println(actual.getDato());
            }else{
                System.out.print(actual.getDato()+"->");
            }
            actual = actual.getSiguiente();
        }
    }
}
