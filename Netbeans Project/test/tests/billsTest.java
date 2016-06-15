/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import articles.toSellArticles;
import bills.Bill;
import bills.BillToFix;
import bills.BillToSale;
import bills.listBills;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Xavier
 */
@RunWith(PowerMockRunner.class)
public class billsTest {

    BillToSale billToSell;
    BillToFix billToFix;
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String actualDate = sdf.format(date);
    
    public billsTest() {
    }
    
    @Before
    public void setUp() {
        billToSell = new BillToSale("ClientName", "BillID", "EmployeeID");
        billToFix = new BillToFix("Observations", 1000, "ClientName", "BillID2", "EmployeeID");        
        listBills.insertBill(billToSell);
        listBills.insertBill(billToFix);
    }
    
    @After
    public void tearDown() {
        billToSell = null;
        billToFix = null;
        listBills.list.clear();
    }

    /**
     * Test que verifica que una factura es insertada correctamente
     */
    @Test
    public void testInsertBillSuccessfully() {
        //Las Inserciones se realizan en el setUp()
        int esperado = 2;
        int obtenido = listBills.list.size();
        assertEquals("Error en la inserción", esperado, obtenido);
    }

    /**
     * Test que verifica que no se inserten objetos nulos
     */
    @Test
    public void testInsertBillNull() {
        billToSell = null;
        listBills.insertBill(billToSell);
        int esperado = 2;
        int obtenido = listBills.list.size();
        assertEquals("Se realiza la insercion de un objeto nulo", esperado, obtenido);
    }

    /**
     * Test que verifica que no se inserten facturas con codigo repetido
     */
    @Test
    public void testInsertBillRepeatedCode() {
        BillToSale billToSell2 = new BillToSale("ClientName", "BillID", "EmployeeID");
        listBills.insertBill(billToSell2);
        int esperado = 2;
        int obtenido = listBills.list.size();
        assertEquals("Se realiza la insercion de un objeto nulo", esperado, obtenido);
    }

    /**
     * Test que verifica que el metodo que aumenta el monto total de una factura
     * funcione correctamente
     */
    @Test
    public void testPlusTotalSuccessfully() {
        billToSell.plusTotal(500);
        assertEquals("Error en el metodo", 500, billToSell.getAmount());
    }

    /**
     * Test que verifica que no se modifique el monto de una factura si el monto
     * dado es negativo
     */
    @Test
    public void testPlusTotalNegativeQuantity() {
        billToSell.plusTotal(-500);
        assertEquals("Error en el metodo", 0, billToSell.getAmount());
    }

    /**
     * Test que verifica que el metodo de borrar factura funcione correctamente
     */
    @Test
    public void testDeleteBillSucessfully() {
        listBills.deleteBill("BillID");
        Bill result = listBills.search("BillID");
        assertEquals("Articulo no fue eliminado", result, null);
    }

    /**
     * Test que verifica que el metodo buscar retorne el objeto correcto
     */
    @Test
    public void testSearchBillSucessfully() {
        Bill result = listBills.search("BillID");
        assertTrue("Articulos no coinciden", result.equals(billToSell));
    }

    /**
     * Test que verifica que la funcion de insertar un articulo a una factura
     * funcione de manera correcta
     */
    @Test
    public void testInsertArticleToBillSuccessfully() {
        toSellArticles art = Mockito.mock(toSellArticles.class);
        listBills.insertArticle(art, "BillID");
        BillToSale bill = (BillToSale) listBills.search("BillID");
        int result = bill.getList().size();
        assertEquals("Articulo no fue insertado correctamente", 1, result);
    }

    /**
     * Test que verifica que la funcion de insertar un articulo a una factura
     * funcione de manera correcta
     */
    @Test
    public void testDeleteArticleToBillSuccessfully() {
        toSellArticles art = Mockito.mock(toSellArticles.class);
        listBills.insertArticle(art, "BillID");
        listBills.deleteArticle(art, "BillID");
        BillToSale bill = (BillToSale) listBills.search("BillID");
        int result = bill.getList().size();
        assertEquals("Articulo no fue eliminado correctamente", 0, result);
    }

    /**
     * Test que verifica que la funcion de insertar un articulo a una factura
     * funcione de manera correcta
     */
    @Test
    public void testCheckTotalAfterDeletingArticle() {
        toSellArticles art = Mockito.mock(toSellArticles.class);
        listBills.insertArticle(art, "BillID");
        listBills.deleteArticle(art, "BillID");
        BillToSale bill = (BillToSale) listBills.search("BillID");
        int result = bill.getAmount();
        assertEquals("El total no se modifico correctamente", 0, result);
    }

    /**
     * Test que verifica que el metodo que retorna el historial de venta por
     * fecha retorne la informacion correcta
     */
    @Test
    public void testSalesHistoryByDateSuccessfull() {
        BillToSale bill = new BillToSale("ClientName", "BillID3", "EmployeeID");
        listBills.insertBill(bill);
        ArrayList<BillToSale> arr = listBills.salesBillHistoryByDateArray(actualDate);
        assertTrue("Resultado no es el esperado", (arr.get(0).equals(billToSell) && arr.get(1).equals(bill)));
    }

    /**
     * Test que verifica que el metodo que retorna el historial de ventas
     * retorne la informacion correcta
     */
    @Test
    public void testSalesHistorySuccessfull() {
        BillToSale bill = new BillToSale("ClientName", "BillID3", "EmployeeID");
        Date date = new Date();
        Calendar c = Calendar.getInstance();        
        c.setTime(date);        
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        bill.setDate(date);
        listBills.insertBill(bill);
        ArrayList<BillToSale> arr = listBills.allSalesHistoryArray();
        assertTrue("Resultado no es el esperado", (arr.get(0).equals(billToSell) && arr.get(1).equals(bill)));
    }

    /**
     * Test que verifica que el metodo que retorna el historial de ventas por
     * fecha retorne null dada una fecha invalida
     */
    @Test
    public void testSalesHistoryByDateInvalidDate() {
        ArrayList<BillToSale> arr = listBills.salesBillHistoryByDateArray("30/12/2015");
        assertEquals("Transaccion no retorna null", arr, null);
    }

    /**
     * Test que verifica que el metodo que retorna el historial de ventas por
     * fecha lance una excepcion dado un formato de fecha invalido
     */
    @Test
    public void testSalesHistoryByDateParseException() {
        PowerMockito.mockStatic(listBills.class);
        PowerMockito.doThrow(new ParseException("", 1)).when(listBills.class);
        listBills.salesBillHistoryByDateArray("nanan");
    }

    /**
     * Test que verifica que el metodo que retorna el historial de reparaciones
     * por fecha lance una excepcion dado un formato de fecha invalido
     */
    @Test
    public void testRepairsHistoryByDateParseException() {
        PowerMockito.mockStatic(listBills.class);
        PowerMockito.doThrow(new ParseException("", 1)).when(listBills.class);
        listBills.repairsBillHistoryByDateArray("nanan");
    }

    /**
     * Test que verifica que el metodo que retorna el historial de reparaciones
     * por fecha retorne la informacion correcta
     */
    @Test
    public void testRepairHistoryByDateSuccessfull() {
        BillToFix bill = new BillToFix("Observations", 1000, "ClientName", "BillID3", "EmployeeID");        
        listBills.insertBill(bill);
        ArrayList<BillToFix> arr = listBills.repairsBillHistoryByDateArray(actualDate);
        assertTrue("Resultado no es el esperado", (arr.get(0).equals(billToFix) && arr.get(1).equals(bill)));
    }

    /**
     * Test que verifica que el metodo que retorna el historial de reparaciones
     * retorne la informacion correcta
     */
    @Test
    public void testRepairsHistorySuccessfull() {
        BillToFix bill = new BillToFix("Observations", 1000, "ClientName", "BillID3", "EmployeeID");
        Date date = new Date();
        Calendar c = Calendar.getInstance();        
        c.setTime(date);        
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        bill.setDate(date);
        listBills.insertBill(bill);
        ArrayList<BillToFix> arr = listBills.allRepairsHistoryArray();
        assertTrue("Resultado no es el esperado", (arr.get(0).equals(billToFix) && arr.get(1).equals(bill)));
    }

    /**
     * Test que verifica que el metodo que retorna el historial de reparaciones
     * por fecha retorne null dada una fecha invalida
     */
    @Test
    public void testRepairsHistoryByDateInvalidDate() {
        ArrayList<BillToFix> arr = listBills.repairsBillHistoryByDateArray("12/12/2015");
        assertEquals("Transaccion no retorna null", arr, null);
    }

    /**
     * Test que verifica que no se le asigne una cantidad negativa al saldo de
     * una factura
     */
    @Test
    public void testSetAmountNegativeAmount() {
        billToSell.setAmount(-500);
        int result = billToSell.getAmount();
        assertEquals("Transaccion no retorna null", result, 0);
    }

    /**
     * Test que verifica que el metodo que calcula los montos acumulados retorna
     * informacion correcta
     */
    @Test
    public void testAccumulatedAmountsSuccesfull() {
        BillToSale bill2 = new BillToSale("ClientName", "BillID4", "EmployeeID");
        bill2.plusTotal(500);
        listBills.insertBill(bill2);
        
        Date initialDate = new Date();
        Date finalDate = new Date();
        Calendar c = Calendar.getInstance();        
        c.setTime(finalDate);        
        c.add(Calendar.DATE, 1);
        finalDate = c.getTime();
        int arr[] = listBills.accumulatedAmounts(initialDate, finalDate);
        assertTrue("Valores retornados no son correctos", (arr[0] == 500 && arr[1] == 1000));
    }

    /**
     * Test que verifica que el metodo que calcula los montos acumulados retorna
     * null si la fecha inicial es mayor a la final
     */
    @Test
    public void testAccumulatedAmountsInitDateGreaterThanFinalDate() {
        
        Date initialDate = new Date();
        Date finalDate = new Date();
        Calendar c = Calendar.getInstance();        
        c.setTime(initialDate);        
        c.add(Calendar.DATE, 1);
        initialDate = c.getTime();
        int arr[] = listBills.accumulatedAmounts(initialDate, finalDate);
        assertArrayEquals("No se retorna null", arr, null);
    }

    /**
     * Test que verifica que el metodo que calcula los montos acumulados retorna
     * null si la fecha inicial es mayor a la actual
     */
    @Test
    public void testAccumulatedAmountsInitDateGreaterThanActualDate() {
        Date initialDate = new Date();
        Date finalDate = new Date();
        Calendar c = Calendar.getInstance();        
        c.setTime(initialDate);        
        c.add(Calendar.DATE, 1);
        initialDate = c.getTime();
        c.setTime(finalDate);        
        c.add(Calendar.DATE, 1);
        finalDate = c.getTime();
        int arr[] = listBills.accumulatedAmounts(initialDate, finalDate);
        assertArrayEquals("No se retorna null", arr, null);
    }
}
