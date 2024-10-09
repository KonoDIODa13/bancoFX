package application.domain;
//Extiendo de CuentaBancaria
public class CuentaAhorro extends CuentaBancaria {
    Double interes;

    public CuentaAhorro(Persona titular, String IBAN, Double saldo, Double interes) {
        super(titular, IBAN, saldo);
        this.interes = interes;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    @Override
    public String imprimir() {
        return "titular[" + titular.imprimir() + "], saldo=" + saldo + ", IBAN= " + IBAN + "e interes=" + interes;
    }
}
