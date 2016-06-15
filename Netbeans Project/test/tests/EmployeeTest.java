/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Employee.Cashier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xavier
 */
public class EmployeeTest {
    
    public EmployeeTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testEmailFormat(){
        try{
            Cashier cashier=new Cashier("","","","","","","","",2,2);
            fail("Formato de correo incorrecto");
        }
        catch(Exception e){
            assertEquals(e.getMessage(),"Formato de correo incorrecto");
        }
    }
    @Test
    public void testDateFormat(){
        try{
            Cashier cashier=new Cashier("","s","","","bxc1938@gmail.com","","","",2,2);
            fail("Formato de fecha correcto");
        }
        catch(Exception e){
            assertEquals(e.getMessage(),"Formato de fecha incorrecto");
        }
    }
    @Test
    public void testPPHGreaterThanZero(){
        try{
            Cashier cashier=new Cashier("","12/12/2015","","","bxc1938@gmail.com","","","",-1,2);
            fail("Pago por hora mayor que cero");
        }
        catch(Exception e){
            assertEquals(e.getMessage(),"Pago por hora menor que cero");
        }
    }
    
}
