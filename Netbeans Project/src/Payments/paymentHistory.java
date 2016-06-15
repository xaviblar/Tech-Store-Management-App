/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Payments;
import GUI.AllCanBeFixed;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xavier
 */
//Clase que contiene una lista de pagos, cada vez que se realiza un pago a un empleado, ese pago es guardado en la lista de manera que funciona como un historial de pagos 
public class paymentHistory 
{
    public static LinkedList<Payment> paymentList = new LinkedList<>();
    
    public static void addPayment ( Payment pay)
    {
        if(pay!=null){
            paymentList.add(pay);
        }   
    }
    
    //Funcion que recibe una fecha y retorna un String con la informacion de todos los pagos realizados en ese fecha
    public static String paymentHistoryByDate(Date date)
    {
        String history;
        boolean empty=true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfPayment = null;
        history=("-------------Payment history for " + sdf.format(date) + " -------------\n");
        Payment pay;
        for(int i=0;i<paymentHistory.paymentList.size();i++)
        {
            String newstring = sdf.format(paymentList.get(i).getDate());
            try {
                dateOfPayment=sdf.parse(newstring);
            } catch (ParseException ex) {
                Logger.getLogger(paymentHistory.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(date);
            System.out.println(dateOfPayment);
            if (dateOfPayment.toString().equals(date.toString())) 
            {
                empty=false;
                pay = paymentList.get(i);
                history += ("Employee: " + pay.getEmp().getName());
                history += (" Salary paid: " + Double.toString(pay.getAmount())+"\n");
            }
        }
        history+=("\n--------------------------End--------------------------");
        if(empty==true)
        {
            return null;
        }
        else
        {
            return history;
        }
    }
    
    //Funcion que retorna un String con la informacion de todos los pagos realizados a los empleados
    public static String AllPaymentHistory()
    {
        String history;
        boolean empty=true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        history=("-------------All can be fixed payment history-------------\n");
        Payment pay;
        for(int i=0;i<paymentHistory.paymentList.size();i++)
        {
                empty=false;
                pay = paymentList.get(i);
                history += ("Employee: " + pay.getEmp().getName());
                history += (" Salary paid: " + Double.toString(pay.getAmount()));
                history += (" Date: "+sdf.format(pay.getDate()).toString()+"\n");
        }
        history+=("\n--------------------------End--------------------------");
        if(empty==true)
        {
            return null;
        }
        else
        {
            return history;
        }
    }
    
    public static ArrayList<Payment> AllPaymentHistoryArray()
    {
        ArrayList<Payment> arr = new ArrayList();
        for (Payment paymentList1 : paymentHistory.paymentList) {
            arr.add(paymentList1);
        }
        return arr;
    }
}
