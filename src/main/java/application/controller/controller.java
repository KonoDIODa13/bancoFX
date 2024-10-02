package application.controller;

import application.CRUD.Banco;
import application.domain.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class controller {
    @FXML
    private ToggleGroup tipoCuenta;
    @FXML
    private RadioButton RBAhorro;
    @FXML
    private RadioButton RBPersonal;
    @FXML
    private RadioButton RBEmpresa;
    @FXML
    TextField TFNombre, TFApellidos, TFDNI, TFIBAN, TFSaldo, TFInteres, TFMantenimiento, TFInteresDescubierto, TFMaxDescubierto;

    public void mostrarCuentaPersonal(ActionEvent event){
           }


    public void mostrarCuentaEmpresa(ActionEvent event) {
    }

    public void mostrarCuentaAhorro(ActionEvent event) {
        TFInteres.setDisable(false);
        TFMantenimiento.setDisable(true);
        TFInteresDescubierto.setDisable(true);
        TFMaxDescubierto.setDisable(true);
    }
}
