/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

/**
 *
 * @author Xavier
 */
//Clase que hereda de la clase employee, representa un tecnico de la tienda
public class Maintainer extends Employee{

    public Maintainer(String Nombre, String FechaNac, String Sexo, String numTelefono, String email, String Direccion, String numCedula, String payment, int pph, int workHours) throws Exception {
        super(Nombre, FechaNac, Sexo, numTelefono, email, Direccion, numCedula, payment, pph, workHours);
    }
    
}
