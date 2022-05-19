package com.proyectoarboles.proyectoarboles;

public class Graduado2 {

        private String nombre;
        private String profesion;
        private int promedio;


        public Graduado2( String nombre, String profesion, int promedio) {

            this.nombre = nombre;
            this.profesion = profesion;
            this.promedio = promedio;
        }


        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getProfesion() {
            return profesion;
        }

        public void setProfesion(String profesion) {
            this.profesion = profesion;
        }

        public int getPromedio() {
            return promedio;
        }

        public void setPromedio(int promedio) {
            this.promedio = promedio;
        }

}
