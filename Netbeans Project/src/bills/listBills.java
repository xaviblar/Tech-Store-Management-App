/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bills;

import articles.articles;
import articles.toSellArticles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandon
 */
public class listBills {

    public static LinkedList<Bill> list = new LinkedList();

    //inserta una factura a la lista recibiendo el nodo
    public static void insertBill(Bill node) {
        if (node != null) {
            for(Bill bill : list)
            {
                if(bill.getId().equals(node.getId()))
                {
                    return;
                }
            }
            list.add(node);
        }
    }

    /*
     recibe el codigo de la factura y la elimina
     */
    public static void deleteBill(String code) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(code)) {
                list.remove(i);
                return;
            }
        }
    }
    /*
     recibe el codigo y busca una factura
     retorna la factura
     */

    public static Bill search(String code) {
        for (Bill list1 : list) {
            if (list1.getId().equals(code)) {
                return list1;
            }
        }
        return null;
    }

    /*
     revisa si es la factura es de los articulos par reparar o vender
     retorna true si es para reparar y false de lo contrario
     */
    public boolean instanceToFix(Bill bill) {
        return bill instanceof BillToFix;
    }

    /*
     inserta articulos a la factura
     y aument el monto de l afactura con cada articulo
     */
    public static void insertArticle(toSellArticles article, String codeB) {
        listBills.search(codeB).getList().add(article);
        listBills.search(codeB).plusTotal(article.getPrice());
    }
    
    public static void deleteArticle(toSellArticles article, String codeB) {
        listBills.search(codeB).getList().remove(article);
        listBills.search(codeB).lessTotal(article.getPrice());
    }
    
    /*
     metodo que recibe una fecha y busca las facturas en esa fecha
     */
    public static String salesBillHistoryByDate(Date date) {
        String history;
        boolean empty = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBill = null;
        history = ("-------------Sale Bills history for " + sdf.format(date) + " -------------\n");
        Bill mBill;
        for (int i = 0; i < list.size(); i++) {
            mBill = list.get(i);
            if (mBill instanceof BillToSale) {
                String newstring = sdf.format(list.get(i).getDate());
                try {
                    dateOfBill = sdf.parse(newstring);
                } catch (ParseException ex) {
                    Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (dateOfBill.toString().equals(date.toString())) {
                    empty = false;
                    history += ("Sale ID: " + mBill.getId());
                    history += (" Client Name: " + mBill.getClientName());
                    history += (" Employee ID: " + mBill.getIDEmployee());
                    history += (" Total: " + mBill.getAmount() + "\n");
                }
            }
        }
        history += ("\n--------------------------End--------------------------");
        if (empty == true) {
            return null;
        } else {
            return history;
        }
    }

    public static ArrayList<BillToSale> salesBillHistoryByDateArray(String dateString) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateString);
            if (date.after(today)) {
                return null;
            }
        } catch (ParseException ex) {
            Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        ArrayList<BillToSale> arr = new ArrayList();
        Date dateOfBill = null;
        for (Bill list1 : list) {
            if (list1 instanceof BillToSale) {
                String newstring = sdf.format(list1.getDate());
                try {
                    dateOfBill = sdf.parse(newstring);
                } catch (ParseException ex) {
                    Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (dateOfBill.toString().equals(date.toString())) {
                    arr.add((BillToSale) list1);
                }
            }
        }
        return arr;
    }

    /*
     muestra todas las ventas
     */
    public static String allSalesHistory() {
        String history;
        boolean empty = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBill = null;
        history = ("-------------Sale Bills history-------------\n");
        Bill mBill;
        for (int i = 0; i < list.size(); i++) {
            mBill = list.get(i);
            if (mBill instanceof BillToSale) {
                String newstring = sdf.format(list.get(i).getDate());
                try {
                    dateOfBill = sdf.parse(newstring);
                } catch (ParseException ex) {
                    Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
                }
                empty = false;
                history += ("Sale ID: " + mBill.getId());
                history += (" Date: ") + dateOfBill;
                history += (" Client Name: " + mBill.getClientName());
                history += (" Employee ID: " + mBill.getIDEmployee());
                history += (" Total: " + mBill.getAmount() + "\n");
            }
        }
        history += ("\n--------------------------End--------------------------");
        if (empty == true) {
            return null;
        } else {
            return history;
        }
    }

    /*
     muestra todas las ventas
     */
    public static ArrayList<BillToSale> allSalesHistoryArray() {
        ArrayList<BillToSale> arr = new ArrayList();
        for (Bill list1 : list) {
            if (list1 instanceof BillToSale) {
                arr.add((BillToSale) list1);
            }
        }
        return arr;
    }

    //muestra las reparaciones de una fecha en especifico
    public static String repairsBillHistoryByDate(Date date) {
        String history;
        boolean empty = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBill = null;
        history = ("-------------Repair Bills history for " + sdf.format(date) + " -------------\n");
        Bill mBill;
        for (int i = 0; i < list.size(); i++) {
            mBill = list.get(i);
            if (mBill instanceof BillToFix) {
                String newstring = sdf.format(list.get(i).getDate());
                try {
                    dateOfBill = sdf.parse(newstring);
                } catch (ParseException ex) {
                    Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (dateOfBill.toString().equals(date.toString())) {
                    empty = false;
                    history += ("Repair ID: " + mBill.getId());
                    history += (" Client Name: " + mBill.getClientName());
                    history += (" Employee ID: " + mBill.getIDEmployee());
                    history += (" Repair Observations: " + ((BillToFix) mBill).getRepairObservations());
                    history += (" Total: " + mBill.getAmount() + "\n");
                }
            }
        }
        history += ("\n--------------------------End--------------------------");
        if (empty == true) {
            return null;
        } else {
            return history;
        }
    }

    public static ArrayList<BillToFix> repairsBillHistoryByDateArray(String dateString) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateString);
            if (date.after(today)) {
                return null;
            }
        } catch (ParseException ex) {
            Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  
        ArrayList<BillToFix> arr = new ArrayList();

        Date dateOfBill = null;
        for (Bill list1 : list) {
            if (list1 instanceof BillToFix) {
                String newstring = sdf.format(list1.getDate());
                try {
                    dateOfBill = sdf.parse(newstring);
                } catch (ParseException ex) {
                    Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (dateOfBill.toString().equals(date.toString())) {
                    arr.add((BillToFix) list1);
                }
            }
        }
        return arr;
    }

    //muetra todas las reparaciones
    public static String allRepairsHistory() {
        String history;
        boolean empty = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBill = null;
        history = ("-------------Repair Bills history-------------\n");
        Bill mBill;
        for (int i = 0; i < list.size(); i++) {
            mBill = list.get(i);
            if (mBill instanceof BillToFix) {
                String newstring = sdf.format(list.get(i).getDate());
                try {
                    dateOfBill = sdf.parse(newstring);
                } catch (ParseException ex) {
                    Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
                }
                empty = false;
                history += ("Repair ID: " + mBill.getId());
                history += (" Date: ") + dateOfBill;
                history += (" Client Name: " + mBill.getClientName());
                history += (" Employee ID: " + mBill.getIDEmployee());
                history += (" Total: " + mBill.getAmount() + "\n");
            }
        }
        history += ("\n--------------------------End--------------------------");
        if (empty == true) {
            return null;
        } else {
            return history;
        }
    }

    public static ArrayList<BillToFix> allRepairsHistoryArray() {
        ArrayList<BillToFix> arr = new ArrayList();
        for (Bill list1 : list) {
            if (list1 instanceof BillToFix) {
                arr.add((BillToFix) list1);
            }
        }
        return arr;
    }

    //muestra montos acumulados en rango de fechas
    public static int[] accumulatedAmounts(Date initialDate, Date finalDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        String newstring2 = sdf.format(initialDate);
        String newstring3 = sdf.format(finalDate);
        String newstring4 = sdf.format(today);
        try {
            initialDate = sdf.parse(newstring2);
            finalDate = sdf.parse(newstring3);
            today = sdf.parse(newstring4);
        } catch (ParseException ex) {
            Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (initialDate.after(finalDate) || initialDate.after(today)) {
            return null;
        }

        int salesAmount = 0;
        int repairsAmount = 0;

        Date dateOfBill = null;
        Bill mBill;
        for (int i = 0; i < list.size(); i++) {
            mBill = list.get(i);
            String newstring = sdf.format(list.get(i).getDate());

            try {
                dateOfBill = sdf.parse(newstring);

            } catch (ParseException ex) {
                Logger.getLogger(listBills.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((dateOfBill.after(initialDate) || dateOfBill.toString().equals(initialDate.toString())) && (dateOfBill.before(finalDate)) || dateOfBill.toString().equals(finalDate.toString())) {
                if (mBill instanceof BillToFix) {
                    repairsAmount += mBill.getAmount();
                } else if (mBill instanceof BillToSale) {
                    salesAmount += mBill.getAmount();
                }
            }
        }
        int[] values = new int[2];
        values[0] = salesAmount;
        values[1] = repairsAmount;
        return values;
    }
}
