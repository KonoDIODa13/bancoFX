package application.controller;

import application.CRUD.Banco;
import application.domain.*;

import application.utils.AlertUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class controller {
    Banco banco = new Banco();

    public Text TInteres, TMantenimiento, TInteresDescubierto, TMaxDescubierto, TCantidad;
    @FXML
    private ToggleGroup tipoCuentaTG;
    @FXML
    TextField TFNombre, TFApellidos, TFDNI, TFIBAN, TFSaldo,
            TFInteres, TFMantenimiento, TFInteresDescubierto, TFMaxDescubierto,
            TFIBANBusqueda, TFCantidad;

    @FXML
    public Button BTNObtenerSaldo, BTNRetirar, BTNIngresar;

    @FXML
    public ListView LVCuentas;

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
        comprobacionesTextos(nombre, apellidos, DNI);
        boolean boolIBAN = compruebaIBAN(TFIBAN.getText());
        if (boolIBAN) {
            String IBAN = TFIBAN.getText();
            double saldo = compruebaDecimal(TFSaldo.getText(), "saldo");
            Persona titular = new Persona(nombre, apellidos, DNI);

            boolean compruebaInsertado = annadriTipoCuenta(titular, IBAN, saldo);

            if (compruebaInsertado) {
                AlertUtils.mostrarConfirmacion("Cuenta insertada con exito");
                LVCuentas.setItems(FXCollections.observableList(banco.listadoCuentas()));
                limpiarCampos(event);
            } else {
                AlertUtils.mostrarError("Error al insertar cuenta");
            }

        } else {
            AlertUtils.mostrarError("IBAN no correcto, recuede que tiene que ser ESNNNNNNNNNNNNNNNNNNNN");
        }
    }

    public boolean annadriTipoCuenta(Persona titular, String IBAN, double saldo) {
        boolean compruebaInsertado = false;
        switch (tipoCuenta) {
            case 1: //ahorro
                double interes = compruebaDecimal(TFInteres.getText(), "interés");
                CuentaAhorro cuentaAhorro = new CuentaAhorro(titular, IBAN, saldo, interes);
                if (banco.abrirCuenta(cuentaAhorro)) {
                    compruebaInsertado = true;
                }
                break;
            case 2: //personal
                double mantenimiento = compruebaDecimal(TFMantenimiento.getText(), "mantenimiento");
                CuentaPersonal cuentaPersonal = new CuentaPersonal(titular, IBAN, saldo, mantenimiento);
                if (banco.abrirCuenta(cuentaPersonal)) {
                    compruebaInsertado = true;
                }
                break;
            case 3: //empresa
                double interesDescubierto = compruebaDecimal(TFInteresDescubierto.getText(), "interes decubierto");
                double maxDescubierto = compruebaDecimal(TFMaxDescubierto.getText(), "max decubierto");

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
        if (compruebaIBAN(IBANBusqueda)) {
            CuentaBancaria cuentaBancaria = banco.informacionCuenta(IBANBusqueda);
            if (cuentaBancaria != null) {
                AlertUtils.mostrarConfirmacion("Cuenta encontrada");
                mostrarmovimientos();
            } else {
                AlertUtils.mostrarError("Cuenta no encontrada");
            }
        } else {
            AlertUtils.mostrarError("IBAN no correcto, recuede que tiene que ser ESNNNNNNNNNNNNNNNNNNNN");
        }
    }

    public void mostrarmovimientos() {
        TFCantidad.setDisable(false);
        BTNIngresar.setDisable(false);
        BTNRetirar.setDisable(false);
        BTNObtenerSaldo.setDisable(false);
        TCantidad.setDisable(false);
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
        }
        tipoCuentaTG.selectToggle(null);
        tipoCuenta = 0;
    }

    public void limpiarCamposOpciones(ActionEvent event) {
        TFIBANBusqueda.setText("");
        TFCantidad.setText("");
        TCantidad.setDisable(true);
        BTNRetirar.setDisable(true);
        BTNObtenerSaldo.setDisable(true);
        BTNIngresar.setDisable(true);
    }
    public Boolean compruebaIBAN(String IBAN) {
        String patronIBAN = "^ES\\d{20}$";
        Pattern patronCompilado = Pattern.compile(patronIBAN);
        Matcher matcher = patronCompilado.matcher(IBAN);
        return matcher.matches();

    }

    public void ingresar(ActionEvent event) {
        String IBAN = TFIBANBusqueda.getText();
        double cantidad = compruebaDecimal(TFCantidad.getText(), "cantidad");
        if (compruebaIBAN(IBAN)) {
            if (banco.ingresoCuenta(IBAN, cantidad)) {
                double saldo = banco.obtenerSaldo(IBAN);
                AlertUtils.mostrarConfirmacion("El saldo ha sido modificado con exito. El nuevo saldo es: " + saldo);
                TFCantidad.setText("");
            } else {
                AlertUtils.mostrarError("Error al ingresar dinero en la cuenta.");
            }
        } else {
            AlertUtils.mostrarError("IBAN no correcto, recuede que tiene que ser ESNNNNNNNNNNNNNNNNNNNN");
        }
    }

    public void retirar(ActionEvent event) {
        String IBAN = TFIBANBusqueda.getText();
        double cantidad = compruebaDecimal(TFCantidad.getText(), "cantidad");
        if (compruebaIBAN(IBAN)) {
            if (banco.retiradaCuenta(IBAN, cantidad)) {
                double saldo = banco.obtenerSaldo(IBAN);
                AlertUtils.mostrarConfirmacion("El saldo ha sido modificado con exito. El nuevo saldo es: " + saldo);
                TFCantidad.setText("");
            } else {
                AlertUtils.mostrarError("Error al retirar dinero en la cuenta.");
            }
        } else {
            AlertUtils.mostrarError("IBAN no correcto, recuede que tiene que ser ESNNNNNNNNNNNNNNNNNNNN");
        }
    }

    public void obtenerSaldo(ActionEvent event) {
        String IBAN = TFIBANBusqueda.getText();
        if (compruebaIBAN(IBAN)) {
            double saldo = banco.obtenerSaldo(IBAN);
            AlertUtils.mostrarConfirmacion("El Saldo de la cuenta es de: " + saldo);
        } else {
            AlertUtils.mostrarError("IBAN no correcto, recuede que tiene que ser ESNNNNNNNNNNNNNNNNNNNN");
        }
    }

    public void salir(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
        }
    }

    public double compruebaDecimal(String decimal, String tipo) {
        if (decimal.isEmpty()) {
            AlertUtils.mostrarError("No has rellenado nada en el campo " + tipo);
            return 0;
        }
        if (!decimal.matches("\\d+")) {
            AlertUtils.mostrarError("El campo de " + tipo + " no son solo números");
            return 0;
        }
        return Double.parseDouble(decimal);
    }

    public void compruebaTextos(String texto, String tipo) {
        if (texto.isEmpty()) {
            AlertUtils.mostrarError("No has rellenado nada en el campo " + tipo);
        }
    }

    public void comprobacionesTextos(String nombre, String apellidos, String DNI) {
        compruebaTextos(nombre, "nombre");
        compruebaTextos(apellidos, "apellidos");
        compruebaTextos(DNI, "DNI");
    }

}
