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

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    TextField TFNombre, TFApellidos, TFDNI, TFIBAN, TFSaldo,
            TFInteres, TFMantenimiento, TFInteresDescubierto, TFMaxDescubierto,
            TFIBANBusqueda;

    int tipoCuenta = 0;

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

        tipoCuenta = 1;
    }

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

        tipoCuenta = 2;
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

        tipoCuenta = 3;
    }


    public void abrirCuenta(ActionEvent event) {
        String nombre = TFNombre.getText();
        String apellidos = TFApellidos.getText();
        String DNI = TFDNI.getText();
        boolean boolIBAN = compruebaIBAN(TFIBAN.getText());
        if (boolIBAN) {
            String IBAN = TFIBAN.getText();
            double saldo = Double.parseDouble(TFSaldo.getText());
            Persona titular = new Persona(nombre, apellidos, DNI);

            boolean compruebaInsertado = annadriTipoCuenta(titular, IBAN, saldo);

            if (compruebaInsertado) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Cuenta insertada con exito");
                alerta.show();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR, "Error al insertar cuenta");
                alerta.show();
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "IBAN no correcto, recuede que teiene que ser ESNNNNNNNNNNNNNNNNNNNN");
            alerta.show();
            limpiarCampos(event);
        }
    }

    public boolean annadriTipoCuenta(Persona titular, String IBAN, double saldo) {
        boolean compruebaInsertado = false;
        switch (tipoCuenta) {
            case 1: //ahorro
                double interes = Double.parseDouble(TFInteres.getText());
                CuentaAhorro cuentaAhorro = new CuentaAhorro(titular, IBAN, saldo, interes);
                if (banco.abrirCuenta(cuentaAhorro)) {
                    compruebaInsertado = true;
                }
                break;
            case 2: //personal
                double mantenimiento = Double.parseDouble(TFMantenimiento.getText());
                CuentaPersonal cuentaPersonal = new CuentaPersonal(titular, IBAN, saldo, mantenimiento);
                if (banco.abrirCuenta(cuentaPersonal)) {
                    compruebaInsertado = true;
                }
                break;
            case 3:
                double interesDescubierto = Double.parseDouble(TFInteresDescubierto.getText()), maxDescubierto = Double.parseDouble(TFMaxDescubierto.getText());

                CuentaEmpresa cuentaEmpresa = new CuentaEmpresa(titular, IBAN, saldo, interesDescubierto, maxDescubierto);
                if (banco.abrirCuenta(cuentaEmpresa)) {
                    compruebaInsertado = true;
                }
                break;
        }
        return compruebaInsertado;
    }

    public void buscarCuenta(ActionEvent event) {
        String IBANBusqueda = TFIBANBusqueda.getText();
        CuentaBancaria cuentaBancaria = banco.informacionCuenta(IBANBusqueda);
        if (cuentaBancaria != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Cuenta encontrada");
            alerta.show();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Cuenta no encontrada");
            alerta.show();
        }
    }

    public void limpiarCampos(ActionEvent event) {
        TFNombre.setText("");
        TFApellidos.setText("");
        TFDNI.setText("");
        TFIBAN.setText("");
        TFSaldo.setText("");

        switch (tipoCuenta) {
            case 1:
                TInteres.setVisible(false);
                TFInteres.setDisable(true);
                TFInteres.setVisible(false);
                TFInteres.setText("");
                break;
            case 2:
                TMantenimiento.setVisible(false);
                TFMantenimiento.setDisable(true);
                TFMantenimiento.setVisible(false);
                TFMantenimiento.setText("");
                break;
            case 3:
                TInteresDescubierto.setVisible(false);
                TFInteresDescubierto.setDisable(true);
                TFInteresDescubierto.setVisible(false);
                TFInteresDescubierto.setText("");

                TMaxDescubierto.setVisible(false);
                TFMaxDescubierto.setDisable(true);
                TFMaxDescubierto.setVisible(false);
                TFMaxDescubierto.setText("");
                break;
            default:
                System.out.println("usted no deberia de estar aqui");
        }
        tipoCuentaTG.selectToggle(null);
        tipoCuenta = 0;
    }

    public void salir(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
        }
    }

    public Boolean compruebaIBAN(String IBAN) {
        String patronIBAN = "^ES\\d{20}$";
        Pattern patronCompilado = Pattern.compile(patronIBAN);
        Matcher matcher = patronCompilado.matcher(IBAN);
        return matcher.matches();

    }
}
