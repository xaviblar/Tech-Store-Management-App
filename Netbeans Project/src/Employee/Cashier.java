/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

/**
 *
 * @author Xavier
 */
//Clase que hereda de la clase Employee, representa un cajero de la tienda
public class Cashier extends Employee {

    public Cashier(String Name, String birthDate, String Gender, String phoneNumber, String email, String address, String idNumber, String payment, int pph, int workHours) throws Exception {
        super(Name, birthDate, Gender, phoneNumber, email, address, idNumber, payment,pph,workHours);
    }
}
