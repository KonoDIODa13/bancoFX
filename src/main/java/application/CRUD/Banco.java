package application.CRUD;

import application.domain.CuentaBancaria;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    ArrayList<CuentaBancaria> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public boolean abrirCuenta(CuentaBancaria cuentaBancaria) {
        if (cuentas.contains(cuentaBancaria)) return false;
        if (cuentas.stream().anyMatch(cuenta -> cuenta.getIBAN().equalsIgnoreCase(cuentaBancaria.getIBAN())))
            return false;
        cuentas.add(cuentaBancaria);
        return true;
    }

    public List<CuentaBancaria> listadoCuentas() {
        return List.copyOf(cuentas);
    }

    public CuentaBancaria informacionCuenta(String IBAN) {
        return cuentas.stream()
                .filter(cuentaBancaria -> cuentaBancaria.getIBAN().equalsIgnoreCase(IBAN))
                .findFirst()
                .orElse(null);
    }

    public boolean ingresoCuenta(String IBAN, Double ingreso) {
        CuentaBancaria cuenta = informacionCuenta(IBAN);
        if (cuenta == null) return false;
        return cuenta.ingresar(ingreso);
    }

    public boolean retiradaCuenta(String IBAN, Double ingreso) {
        CuentaBancaria cuenta = informacionCuenta(IBAN);
        if (cuenta == null) return false;
        return cuenta.retirar(ingreso);
    }

    public Double obtenerSaldo(String IBAN) {
        CuentaBancaria cuenta = informacionCuenta(IBAN);
        return cuenta.getSaldo();
    }
}
