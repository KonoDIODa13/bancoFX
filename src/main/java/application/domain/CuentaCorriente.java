package application.domain;
// extiendo de CuentaBancaria
public abstract class CuentaCorriente extends CuentaBancaria {

    public CuentaCorriente(Persona titular, String IBAN, Double saldo) {
        super(titular, IBAN, saldo);
    }
}
