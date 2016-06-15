/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bills;
import articles.toSellArticles;
import java.util.LinkedList;

/**
 *
 * @author Brandon
 */
/*
representa laas facturas de los articulos para vender
*/
public class BillToSale extends Bill {
    private LinkedList<toSellArticles> list=new LinkedList<>();
    public BillToSale(String clientName, String id, String idEmployee) {
        super(clientName, id, idEmployee);
    }

    @Override
    public LinkedList<toSellArticles> getList() {
        return list;
    }
}
