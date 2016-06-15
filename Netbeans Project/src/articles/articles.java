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

// representa los articulos de la tienda
public abstract class articles {
    
    private String code;
    private String name;
    private String description;
    private int cost;
    
    public articles(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.cost=0;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public abstract String getOwnerId();
    public abstract String getOwnerName();
    public abstract String getObservations();
    public abstract boolean isFixed();
    @Override
    public String toString() {
        return "articles{" + "code=" + code + ", name=" + name + ", description=" + description + '}';
    } 
}
