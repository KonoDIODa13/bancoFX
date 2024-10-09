package application.CRUD;

import application.domain.CuentaBancaria;
import application.domain.Imprimible;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    // Instancio el arrayList de cuentas
    ArrayList<CuentaBancaria> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    /*
     inserto la cuenta pasada por paramentro.
     compruebo si la cuenta que paso existe ya o si el IBAN es igual a uno ya existente.
     utilizo el stream para recorrer los arrays con el metodo de anyMatch que busca cualquier (cuenta en este caso)
     que coincida el IBAN con el IBAN de la cuenta pasada por parametro. En caso de que no la encuentre, la inserta.
     */
    public boolean abrirCuenta(CuentaBancaria cuentaBancaria) {
        if (cuentas.contains(cuentaBancaria)) return false;
        if (cuentas.stream().anyMatch(cuenta -> cuenta.getIBAN().equalsIgnoreCase(cuentaBancaria.getIBAN())))
            return false;
        cuentas.add(cuentaBancaria);
        return true;
    }

    // metodo utilizado para mostrar con la interfaz imprimible (no el toString()) en el ListView.
    public List<String> listadoCuentas() {
        List<String> cuentasToString = new ArrayList<>();
        for (CuentaBancaria cuentaBancaria : cuentas) {
            cuentasToString.add(cuentaBancaria.imprimir());
        }
        return cuentasToString;
    }

    // busco en el arraylist si existe una cuenta con el IBAN pasado por el parametro.
    public CuentaBancaria informacionCuenta(String IBAN) {
        return cuentas.stream()
                .filter(cuentaBancaria -> cuentaBancaria.getIBAN().equalsIgnoreCase(IBAN))
                .findFirst()
                .orElse(null);
    }

    // primero busco la cuenta (para saber si existe para realizar cambios) en caso de que si, ingresamos la cantidad a ingresar
    public boolean ingresoCuenta(String IBAN, Double ingreso) {
        CuentaBancaria cuenta = informacionCuenta(IBAN);
        if (cuenta == null) return false;
        if (ingreso == 0) return false;
        return cuenta.ingresar(ingreso);
    }

    // al igual que el ingresar, pero con retirar.
    public boolean retiradaCuenta(String IBAN, Double retirada) {
        CuentaBancaria cuenta = informacionCuenta(IBAN);
        if (cuenta == null) return false;
        if (retirada == 0) return false;
        return cuenta.retirar(retirada);
    }

    public Double obtenerSaldo(String IBAN) {
        CuentaBancaria cuenta = informacionCuenta(IBAN);
        return cuenta.getSaldo();
    }
}
