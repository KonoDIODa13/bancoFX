package application.domain;

public class CuentaEmpresa extends CuentaCorriente{
    Double interesDescubierto;
    Double MaxDescubierto;

    public CuentaEmpresa(Persona titular, String IBAN, Double saldo, Double interesDescubierto, Double maxDescubierto) {
        super(titular, IBAN, saldo);
        this.interesDescubierto = interesDescubierto;
        MaxDescubierto = maxDescubierto;
    }

    public Double getInteresDescubierto() {
        return interesDescubierto;
    }

    public Double getMaxDescubierto() {
        return MaxDescubierto;
    }

    public void setInteresDescubierto(Double interesDescubierto) {
        this.interesDescubierto = interesDescubierto;
    }

    public void setMaxDescubierto(Double maxDescubierto) {
        MaxDescubierto = maxDescubierto;
    }

    @Override
    public String toString() {
        return "CuentaEmpresa{" +
                "interesDescubierto=" + interesDescubierto +
                ", MaxDescubierto=" + MaxDescubierto +
                '}';
    }
}
