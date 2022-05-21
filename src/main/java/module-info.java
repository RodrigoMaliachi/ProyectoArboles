module com.proyectoarboles.proyectoarboles {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.opencsv;
    requires java.desktop;

    opens com.proyectoarboles.proyectoarboles to javafx.fxml;
    exports com.proyectoarboles.proyectoarboles;
    exports arbolb;
}