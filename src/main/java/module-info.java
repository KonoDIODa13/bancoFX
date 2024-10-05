module application.banco {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens application.banco to javafx.fxml;
    exports application.banco;
    exports application.controller;
    opens application.controller to javafx.fxml;
}