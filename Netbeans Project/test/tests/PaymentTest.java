/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Employee.Employee;
import Employee.Maintainer;
import Payments.Payment;
import Payments.paymentHistory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Xavier
 */
@RunWith(PowerMockRunner.class)
public class PaymentTest {
    Employee emp;
    Payment pay;
    Date date;
    Maintainer maintainer;
    Maintainer maintainer2;
    
    public PaymentTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        emp = Mockito.mock(Employee.class);
        date = new Date();
        pay = new Payment(date, emp, 5000);
        paymentHistory.addPayment(pay);
        maintainer = mock(Maintainer.class);
        maintainer.setIdNumber("prueba");
        maintainer.setName("Brandon");
        maintainer2 = mock(Maintainer.class);
        maintainer2.setIdNumber("prueba2");
        maintainer2.setName("Xavier");
    }
    
    @After
    public void tearDown() {
        paymentHistory.paymentList.clear();
    }
    
    /**
     * Test que verifica que el metodo que retorna el historial de pagos retorne la informacion correcta
     * @throws java.lang.Exception
     */
    @Test
    public void testPaymentHistorySuccessfull() throws Exception
    {
        Payment pay2 = new Payment(date, emp, 10000);
        paymentHistory.addPayment(pay2);
        ArrayList<Payment> arr = paymentHistory.AllPaymentHistoryArray();
        assertTrue("Resultado no es el esperado", (arr.get(0).equals(pay) && arr.get(1).equals(pay2)));
    }
    
    /**
     * Test que verifica que se lance una excepcion en caso de intentar asignar un monto negativo a un pago
     * @throws java.lang.Exception
     */
    @Test(expected=Exception.class)
    public void testSetMonto() throws Exception {
        Payment payment;
        payment = Mockito.mock(Payment.class);
        double monto = -1;
        Mockito.doThrow(new Exception("El monto debe ser mayor que cero")).when(payment).setMonto(monto);
        payment.setMonto(monto);
        
    }
    
    /**
     * Test que verifica que un pago no puede crearse con un empleado nulo
     * @throws Exception 
     */
    @Test
    public void testNullEmployee() throws Exception{
        
        try{
            Payment pay2 = new Payment(date,null,1200);
            fail("Empleado diferente de nulo");
        }
        catch(Exception e){
            assertEquals(e.getMessage(),"El empleado no debe ser nulo");
        }
    }
    
    /**
     * Test que verifica el correcto funcionamiento de añadir un pago
     */
    @Test
    public void testAddPaymentSuccesfully() throws Exception {
        Payment payment = new Payment();
        payment.setEmp(maintainer);
        payment.setMonto(500);
        
        Date date = new Date(2015, 11, 07);
        payment.setDate(date);
        paymentHistory.addPayment(payment);
        int obtenido = paymentHistory.paymentList.size();
        assertEquals("Error en el método", 2, obtenido);
    }

    @Test
    /**
     * *
     * Si el pago es null no lo inserta
     */
    public void testAddPaymentNull() {
        Payment payment = null;
        paymentHistory.addPayment(payment);
        int obtenido = paymentHistory.paymentList.size();
        assertEquals("Error en el método", 1, obtenido);
    }

    /**
     * Test of paymentHistoryByDate method, of class paymentHistory. se hacen 2
     * instancian 2 pagos con sus atributos y luego se compara el resultado
     * obtenido con el esperado
     */
    @Test
    public void testPaymentHistoryByDateSuccefully() throws Exception {
        String esperado;
        Payment payment = new Payment();
        payment.setEmp(maintainer);
        payment.setMonto(500);
        
      
        Date date = new Date(2015, 11, 07);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        payment.setDate(date);
        paymentHistory.addPayment(payment);

        esperado = ("-------------Payment history for " + sdf.format(date) + " -------------\n");
        esperado += ("Employee: " + payment.getEmp().getName());
        esperado += (" Salary paid: " + Double.toString(payment.getAmount()) + "\n");
        
        Payment payment2 = new Payment();
        payment2.setEmp(maintainer2);
        payment2.setMonto(600);
        Date date2 = new Date(2015, 11, 07);
        payment2.setDate(date2);
        paymentHistory.addPayment(payment2);
        esperado += ("Employee: " + payment2.getEmp().getName());
        esperado += (" Salary paid: " + Double.toString(payment2.getAmount()) + "\n");

        esperado += ("\n--------------------------End--------------------------");
        String obtenido = paymentHistory.paymentHistoryByDate(date);
        assertEquals(esperado, obtenido);
    }

    /**
     * Test of paymentHistoryByDate method, of class paymentHistory. se hacen 2
     * instancian 2 pagos con sus atributos y luego se compara el resultado
     * obtenido con el esperado
     */
    @Test
    public void testPaymentHistoryByDateNoResults() throws Exception {
        String esperado = null;
        Payment payment = new Payment();
        payment.setEmp(maintainer);
        payment.setMonto(500);
       
        Date date = new Date(2015, 11, 07);
        Date date2 = new Date(2015, 11, 9);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        payment.setDate(date);
        paymentHistory.addPayment(payment);
        String obtenido = paymentHistory.paymentHistoryByDate(date2);
        assertEquals(esperado, obtenido);
    }

    /**
     * Comprueba que el método imprime todos los pagos de manera correcta
     */
    @Test
    public void testAllPaymentHistorySuccefully() throws Exception {
        paymentHistory.paymentList.clear();
        String esperado;
        Date date = new Date(2015, 11, 07);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Payment payment = new Payment();
        payment.setEmp(maintainer);
        payment.setMonto(500);
        payment.setDate(date);
        paymentHistory.addPayment(payment);
        
        Payment payment2 = new Payment();
        payment2.setEmp(maintainer2);
        payment2.setMonto(600);
        Date date2 = new Date(2015, 10, 9);
        payment2.setDate(date2);
        paymentHistory.addPayment(payment2);

        esperado = ("-------------All can be fixed payment history-------------\n");

        esperado += ("Employee: " + payment.getEmp().getName());
        esperado += (" Salary paid: " + Double.toString(payment.getAmount()));
        esperado += (" Date: " + sdf.format(payment.getDate()).toString() + "\n");

        esperado += ("Employee: " + payment2.getEmp().getName());
        esperado += (" Salary paid: " + Double.toString(payment2.getAmount()));
        esperado += (" Date: " + sdf.format(payment2.getDate()).toString() + "\n");
        
        esperado+=("\n--------------------------End--------------------------");
        
        String obtenido=paymentHistory.AllPaymentHistory();
        assertEquals(esperado,obtenido);

    }
    /**
     * comprueba que si no existen pagos no muestra ningun resultado
    */
    @Test
    public void testAllPaymentHistoryNull() {
        //la lista de pagos se encuentra vacía
        paymentHistory.paymentList.clear();
        String obtenido = paymentHistory.AllPaymentHistory();
        assertEquals(null,obtenido);
    }

}
