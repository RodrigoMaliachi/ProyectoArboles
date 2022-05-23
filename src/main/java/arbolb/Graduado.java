package arbolb;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Representa la informacion que se desea guardar en un formato de graduado
 *
 * @author Rodrigo Natali Jonatan Angelica
 */
@SuppressWarnings("unused")
public class Graduado implements Serializable, Comparable<Graduado> {
    /**
     * Representa la clave de busqueda
     */
    private final IntegerProperty indice = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty profesion = new SimpleStringProperty();
    private final IntegerProperty promedio = new SimpleIntegerProperty();

    public Graduado(int indice, String nombre, String profesion, int promedio) {
        this.indice.set(indice);
        this.nombre.set(nombre);
        this.profesion.set(profesion);
        this.promedio.set(promedio);
    }

    public IntegerProperty indiceProperty() {
        return indice;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty profesionProperty() {
        return profesion;
    }

    public IntegerProperty promedioProperty() {
        return promedio;
    }

    public int getIndice() {
        return indice.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getProfesion() {
        return profesion.get();
    }

    public int getPromedio() {
        return promedio.get();
    }

    public String toString(){
        return "Alumnos: " + nombre + " Profesion: " + profesion + " Con promedio: " + promedio;
    }

    public static Graduado parseGraduado(String[] data) {
        return new Graduado(
                Integer.parseInt( data[0] ),
                data[1],
                data[2],
                Integer.parseInt( data[3] )
        );
    }

    @Override
    public int compareTo(Graduado o) {
        return indice.get() - o.indice.get();
    }
}
