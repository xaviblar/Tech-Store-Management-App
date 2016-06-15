/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package articles;

/**
 *
 * @author Brandon
 */
/*
atributos demas aparte de los que hereda de la classe articles
*/
public class toFixArticles extends articles{
    private boolean fixed=false;
    private String idOwner;
    private String ownerName;
    private String observations;
    
    public toFixArticles(String idOwner, String code, String name, String description,String nameOwner) {
        super(code, name, description);
        this.idOwner = idOwner;
        this.ownerName=nameOwner;
    }
 
    @Override
    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    @Override
    public String getOwnerId() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    @Override
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    
    @Override
    public String toString() {
        return super.toString()+"toFixArticles{"  + ", fixed=" + fixed + ", idOwner=" + idOwner + '}';
    }   
}
