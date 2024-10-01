package application.domain;

public class CuentaPersonal extends CuentaCorriente {
    Double mantenimiento;

    public CuentaPersonal(Persona titular, String IBAN, Double saldo, Double mantenimiento) {
        super(titular, IBAN, saldo);
        this.mantenimiento = mantenimiento;
    }

    public Double getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(Double mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    @Override
    public String toString() {
        return "CuentaPersonal{" +
                "mantenimiento=" + mantenimiento +
                '}';
    }
}
