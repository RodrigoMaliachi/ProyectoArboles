package modelo;

import arbolb.Graduado;
import com.opencsv.CSVReader;
import java.io.*;
import java.util.ArrayList;

public class Archivo {

    private static final String ROOT = "Egresados.txt";
    private static final ArrayList<Graduado> alumnos = new ArrayList<>();

    public static ArrayList<Graduado> leerArchivoCSV(){
        try {

            //Creamos una instancia del lector del CSV
            CSVReader reader = new CSVReader( new FileReader( ROOT ) );

            //Eliminamos los datos antes introducidos
            alumnos.clear();

            //Para cada registro del CSV, se agrega el dato a la lista de alumnos
            reader.forEach(Archivo::addToAlumnos);

            reader.close();

        } catch (Exception e) { e.printStackTrace(); }

        return alumnos;
    }

    private static void addToAlumnos(String[] data) {
        alumnos.add(Graduado.parseGraduado(data));
    }
}