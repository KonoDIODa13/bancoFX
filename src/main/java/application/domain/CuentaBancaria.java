package application.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CuentaBancaria {
    Persona titular;
    Double saldo;
    String IBAN;

    public CuentaBancaria(Persona titular, String IBAN, Double saldo) {
        if (!compruebaIban(IBAN)) return;
        this.titular = titular;
        this.IBAN = IBAN;
        this.saldo = saldo;
    }

    public Persona getTitular() {
        return titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public boolean ingresar(Double ingreso) {
        saldo += ingreso;
        return true;
    }

    public boolean retirar(Double retirada) {
        if (retirada > saldo) return false;
        saldo -= retirada;
        return true;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "titular=" + titular +
                ", saldo=" + saldo +
                ", IBAN='" + IBAN + '\'' +
                '}';
    }

    private boolean compruebaIban(String IBAN) {
        String patronIBAN = "^ES\\d{20}$";
        Pattern patronCompilado = Pattern.compile(patronIBAN);
        Matcher matcher = patronCompilado.matcher(IBAN);
        return matcher.matches();
    }
}
