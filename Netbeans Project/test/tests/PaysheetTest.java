/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Employee.Cashier;
import Employee.Employee;
import Paysheets.Paysheet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;

/**
 *
 * @author Xavier
 */
public class PaysheetTest {

    Cashier emp;
    Cashier cashier;
    Cashier cashier2;

    public PaysheetTest() {
    }

    @Before
    public void setUp() throws Exception {
        emp = new Cashier("Xavier", "19/08/1993", "M", "8755-2695", "xaviblar@gmail.com", "CQ", "1", "Monthly", 1900, 8);
        cashier=new Cashier("","12/12/2015","","","bxc1938@gmail.com","","test","",1,2);
        Paysheet.addEmployee(emp);
    }

    @After
    public void tearDown() {
        emp = null;
        cashier =null;
        Paysheet.employeeList.clear();
    }

    /**
     * Test que verifica que el metodo de añadir empleado funciona correctamente
     */
    @Test
    public void testInsertEmployeeSuccessfully() {
        Employee emp2 = Mockito.mock(Employee.class);
        Paysheet.addEmployee(emp2);
        int esperado = 2;
        int obtenido = Paysheet.employeeList.size();
        assertEquals("Error en la inserción", esperado, obtenido);
    }

    /**
     * Test que verifica que el metodo de buscar un empleado retorna el objeto
     * correcto
     */
    @Test
    public void testSearchEmployeeSuccessfully() {

        Cashier result = (Cashier) Paysheet.getEmployee("1");
        assertTrue("No se ha retornado el objeto correcto", result.equals(emp));
    }

    /**
     * Test que verifica que el metodo de añadir horas extra a un empleado
     * funciona correctamente
     */
    @Test
    public void testAddExtraHoursSuccessfully() {

        Paysheet.addExtraHours("1", 2);
        Employee result = Paysheet.getEmployee("1");
        assertEquals("Resultado no es el esperado", result.getExtraHours(), 2);
    }

    /**
     * Test que verifica que el metodo de añadir horas extra a un empleado no
     * modifique el valor si la cantidad recibida es negativa
     */
    @Test
    public void testAddExtraHoursNegativeQuantity() {

        Paysheet.addExtraHours("1", -2);
        Employee result = Paysheet.getEmployee("1");
        assertEquals("Resultado no es el esperado", result.getExtraHours(), 0);
    }
    
    /**
     * Test of deleteEmployee method, of class Paysheet.
     */
    @Test
    public void testDeleteEmployee() {
        try {
            Paysheet.addEmployee(cashier);
            Paysheet.deleteEmployee("test");
            int size=Paysheet.employeeList.size();
            assertEquals("Error al eliminar el empleado",1,size);
        } catch (Exception ex) {
            Logger.getLogger(PaysheetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDeleteEmployeeNullValue() throws NoSuchMethodException, Exception {
       Paysheet.addEmployee(cashier);   
       Paysheet.deleteEmployee("test2");
       int size=Paysheet.employeeList.size();
       assertEquals("Error al eliminar el empleado",2,size);
    }
    
    @Test 
    public void TestModifyEmployeeSuccefully() throws Exception{
        Paysheet.addEmployee(cashier); 
        Paysheet.modifyEmployee("-","07/11/2015","-","-","bcv1938@gmail.com","-","test","-",1100,100);
        //se hace una nueva instancia con el objeto que se insetado que se va a modificar
        cashier2=(Cashier)Paysheet.search("test");
        assertTrue(cashier2.getAddress().equals("-") && cashier2.getDateOfBirth().
                equals("07/11/2015") && cashier2.getEmail().equals("bcv1938@gmail.com") && cashier2.
                        getPaymentPerHour()==1100 && cashier2.getWorkHours()==100 && 
                        cashier2.getGender().equals("-") && cashier2.getIdNumber().
                                equals("test") && cashier2.getName().equals("-")
                && cashier2.getPhoneNumber().equals("-") && cashier2.getPaymentType().equals("-"));
        
    }
    
    @Test
    public void TestSearchEmployeeSuccefully(){
        try {
            Paysheet.addEmployee(cashier);
            Cashier pruebaBusqueda= (Cashier)Paysheet.search(cashier.getIdNumber());    
            assertEquals("Error en el método search",cashier,pruebaBusqueda);
        } catch (Exception ex) {
            Logger.getLogger(PaysheetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testSearchEmployeeNull(){
        try {
            Paysheet.addEmployee(cashier);
            Cashier pruebaBusqueda=(Cashier)Paysheet.search("ID incorrecto");
            assertEquals("Error en el método search",null,pruebaBusqueda);
        } catch (Exception ex) {
            Logger.getLogger(PaysheetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of modifyEmployee method, of class Paysheet.
     */
    @Test
    public void testModifyEmployeeNegativeValues() {
        try{
            Paysheet.addEmployee(cashier);
            try{
                doThrow(ArithmeticException.class).when(Paysheet.class);
                Paysheet.modifyEmployee("--","--","--","--","--","--","test","--",-4,-4);
                fail("Ingreso de horas menores que cero");
            }
            catch(ArithmeticException exception ){
                assertEquals(exception.getMessage(),("Ingreso de horas menores que cero"));
            }
        }
        catch(Exception ex ){
            Logger.getLogger(PaysheetTest.class.getName()).log(Level.SEVERE, null, ex);
        }              
    }
    
     /**
     * Prueba que cuando se solicita la información de un empleado
     * mediante el numero de cedula
     * este muestra el valor esperado
     */
    @Test
    public void testInfoEmployeeSuccefully(){
        Paysheet.addEmployee(cashier);
        Paysheet.addEmployee(cashier2);
        String esperado="Employee{\n" + "Name=" + cashier.getName() + ", phoneNumber=" + cashier.getPhoneNumber() + ", email=" + cashier.getEmail() + ", idNumber=" + cashier.getIdNumber() + '}';
        String obtenido=Paysheet.infoEmployee(cashier.getIdNumber());
        assertEquals(esperado,obtenido);
    }
    
    /**
     * prueba para verificar que si el usuario no existe no muestra nada
     */
    @Test
    public void testInfoEmployeeNull(){
        Paysheet.addEmployee(cashier);
        Paysheet.addEmployee(cashier2);
        String obtenido ;
        obtenido=Paysheet.infoEmployee("");  
        assertEquals(null,obtenido);
    }
}
