module com.example.ip_2022 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ip_2022 to javafx.fxml;
    exports com.example.ip_2022;
}