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
repesenta las facturas de las facturas de los articulos para reparar
*/
public class BillToFix extends Bill{
    private String repairObservations;


    public BillToFix(String ObservationsOfRepair, int amount, String clientName, String id, String idEmployee) {
        super(clientName, id, idEmployee);
        this.repairObservations = ObservationsOfRepair;
        super.plusTotal(amount);
    }

    public String getRepairObservations() {
        return repairObservations;
    }

    public void setRepairObservations(String damage) {
        this.repairObservations = damage;
    }
    
    @Override
    public LinkedList<toSellArticles> getList() {
        return null;
    }
}
