package application.controller;

import application.CRUD.Banco;
import application.domain.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class controller {
    Banco banco = new Banco();

    public Text TInteres, TMantenimiento, TInteresDescubierto, TMaxDescubierto;
    @FXML
    private ToggleGroup tipoCuentaTG;
    @FXML
    private RadioButton RBAhorro;
    @FXML
    private RadioButton RBPersonal;
    @FXML
    private RadioButton RBEmpresa;
    @FXML
    TextField TFNombre, TFApellidos, TFDNI, TFIBAN, TFSaldo, TFInteres, TFMantenimiento, TFInteresDescubierto, TFMaxDescubierto;

    public void mostrarCuentaPersonal(ActionEvent event) {
        TInteres.setVisible(false);
        TFInteres.setDisable(true);
        TFInteres.setVisible(false);

        TMantenimiento.setVisible(true);
        TFMantenimiento.setDisable(false);
        TFMantenimiento.setVisible(true);

        TInteresDescubierto.setVisible(false);
        TFInteresDescubierto.setDisable(true);
        TFInteresDescubierto.setVisible(false);

        TMaxDescubierto.setVisible(false);
        TFMaxDescubierto.setDisable(true);
        TFMaxDescubierto.setVisible(false);
    }


    public void mostrarCuentaEmpresa(ActionEvent event) {
        TInteres.setVisible(false);
        TFInteres.setDisable(true);
        TFInteres.setVisible(false);

        TMantenimiento.setVisible(false);
        TFMantenimiento.setDisable(true);
        TFMantenimiento.setVisible(false);

        TInteresDescubierto.setVisible(true);
        TFInteresDescubierto.setDisable(false);
        TFInteresDescubierto.setVisible(true);

        TMaxDescubierto.setVisible(true);
        TFMaxDescubierto.setDisable(false);
        TFMaxDescubierto.setVisible(true);
    }

    public void mostrarCuentaAhorro(ActionEvent event) {
        TInteres.setVisible(true);
        TFInteres.setDisable(false);
        TFInteres.setVisible(true);

        TMantenimiento.setVisible(false);
        TFMantenimiento.setDisable(true);
        TFMantenimiento.setVisible(false);

        TInteresDescubierto.setVisible(false);
        TFInteresDescubierto.setDisable(true);
        TFInteresDescubierto.setVisible(false);

        TMaxDescubierto.setVisible(false);
        TFMaxDescubierto.setDisable(true);
        TFMaxDescubierto.setVisible(false);
    }

    public void abrirCuenta(ActionEvent event) {
        String nombre = TFNombre.getText(), apellidos = TFApellidos.getText(), DNI = TFDNI.getText(), IBAN = TFIBAN.getText();
        double saldo = Double.parseDouble(TFSaldo.getText()), interes = Double.parseDouble(TFInteres.getText());
        Persona titular = new Persona(nombre, apellidos, DNI);
        CuentaAhorro cuenta = new CuentaAhorro(titular, IBAN, saldo, interes);
        if(banco.abrirCuenta(cuenta)){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Cuenta insertada con exito");
            alerta.show();
        }else{
            Alert alerta= new Alert(Alert.AlertType.ERROR, "Error al insertar cuenta");
            alerta.show();
        }
    }
}
