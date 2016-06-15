/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bills;

import articles.toSellArticles;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Brandon
 */
/*
 representa las facturas con us repsectivos atributos
 */
public abstract class Bill {

    private int amount;
    private String clientName;
    private String id;
    private String idEmployee;
    Date date;

    public Bill(String clientName, String id, String idEmployee) {
        this.amount = 0;
        this.clientName = clientName;
        this.id = id;
        this.idEmployee = idEmployee;
        this.date = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void plusTotal(int amount) {
        if (amount > 0) {
            this.amount += amount;
        }
    }

    public void lessTotal(int amount) {
        if (amount > 0) {
            this.amount -= amount;
        }
    }

    public void setAmount(int amount) {
        if (amount >= 0) {
            this.amount = amount;
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIDEmployee() {
        return idEmployee;
    }

    public void setIDEmployee(String nameEmployed) {
        this.idEmployee = nameEmployed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract LinkedList<toSellArticles> getList();
}
