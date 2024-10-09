package application.domain;
//Extiendo de CuentaCorriente
public class CuentaEmpresa extends CuentaCorriente {
    Double interesDescubierto;
    Double maxDescubierto;

    public CuentaEmpresa(Persona titular, String IBAN, Double saldo, Double interesDescubierto, Double maxDescubierto) {
        super(titular, IBAN, saldo);
        this.interesDescubierto = interesDescubierto;
        this.maxDescubierto = maxDescubierto;
    }

    public Double getInteresDescubierto() {
        return interesDescubierto;
    }

    public Double getMaxDescubierto() {
        return maxDescubierto;
    }

    public void setInteresDescubierto(Double interesDescubierto) {
        this.interesDescubierto = interesDescubierto;
    }

    public void setMaxDescubierto(Double maxDescubierto) {
        this.maxDescubierto = maxDescubierto;
    }

    @Override
    public String imprimir() {
        return "titular[" + titular.imprimir() + "], saldo=" + saldo + ", IBAN= " + IBAN + ", interes decubierto=" + interesDescubierto + " y maximo decubierto=" + maxDescubierto;

    }
}
