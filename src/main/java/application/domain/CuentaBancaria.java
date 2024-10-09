package application.domain;

public abstract class CuentaBancaria implements Imprimible{
    Persona titular;
    Double saldo;
    String IBAN;

    public CuentaBancaria(Persona titular, String IBAN, Double saldo) {
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
}
