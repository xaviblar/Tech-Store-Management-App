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
public class toSellArticles extends articles {
    private int price;
    private int stock;

    public toSellArticles(int price,String code, String name, String description) {
        super(code, name, description);
        this.price = price;
        this.stock=0;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if(stock >= 0)
        {
            this.stock = stock;
        }
    }
    
    public void increaseStock(int c)
    {
        this.stock+=c;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws Exception {
        if(price<0){
            throw new Exception("El precio debe ser positivo");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString()+"toSoldArticles{" + "price=" + price + ", stock=" + stock + '}';
    }

    @Override
    public String getOwnerId() {
        return null;
    }

    @Override
    public String getOwnerName() {
        return null;
    }

    @Override
    public String getObservations() {
        return null;
    }

    @Override
    public boolean isFixed() {
        return false;
    }

    
    
    

    
    
    
}
