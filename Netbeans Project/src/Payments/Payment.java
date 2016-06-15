
package Payments;

import Employee.Employee;
import java.util.Date;

/**
 *
 * @author Xavier
 */
//Clase cada uno de los pagos realizados a los empleados
public class Payment {
    
    private Date date;
    private Employee emp;
    private double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public double getAmount() {
        return amount;
    }

    public void setMonto(double monto) throws Exception {
        if(monto>0){
           this.amount = monto;
        }
        else{
            throw new Exception("El monto debe ser mayor que cero");
        }
    }

    public Payment() {
    }

    public Payment(Date date, Employee emp, double monto) throws Exception {
        this.date = date;
        this.emp = emp;
        this.amount = monto;
        if(emp==null){
            throw new Exception("El empleado no debe ser nulo");
        }
    }
}
