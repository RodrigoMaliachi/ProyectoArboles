package modelo;

import arbolb.Graduado;

import java.io.*;
import java.util.ArrayList;

public class Archivo {
    private String linea;
    private String partes[] = null;

    private ArrayList<Graduado> listaGraduado= new ArrayList<>();
    // leerArchivoCSV, lee la información del archivo y guarda los valores en "listaGraduado"
     public void leerArchivoCSV(String nombreArchivo){
        try{
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            while((linea = lector.readLine())!=null){
                partes = linea.split(",");
                //imprimirLinea();
                //System.out.println("prueba"+partes[3]+"\n");
                listaGraduado.add(new Graduado(Integer.parseInt(partes[0]),partes[1],partes[2],partes[3]));
                //System.out.println(Integer.parseInt(partes[0])+partes[1]+partes[2]+Integer.parseInt(partes[1]));
            }
            lector.close();
            linea = null;
            partes = null;
        }catch (Exception e){
            // No se encuentra el archivo
        }
    }

    public void imprimirLinea(){
        for (int i = 0; i<partes.length; i++){
            System.out.println(partes[i]+"  |  ");
        }
    }

    public ArrayList<Graduado> getListaGraduado(){
         return listaGraduado;
    }
}

