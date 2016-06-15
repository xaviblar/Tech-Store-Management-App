/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package articles;

import java.util.LinkedList;

/**
 *
 * @author Brandon
 */
public class listArticles {
    //lista para almacenar todos los articulos
    public static LinkedList<articles>list= new LinkedList();
    
    public listArticles() {
        
    }
    /*recibe un articulo
      lo inserta a la lista de articulos*/
     public static boolean insertArticle(articles nodeToFix){
         if(nodeToFix != null)
         {
             list.add(nodeToFix);
             return true;
         }
         else
         {
             return false;
         }
     } 
     
     /*
     recibe el codigo de un articulo y modifica los demas atributos
     con los parametros de entrada
     */
    public static boolean modifyArticle(String code, String newCode, String name, String description,int cost)
    {
        articles art = search(newCode);
        if(art == null && cost >= 0)
        {
            for (articles list1 : list) {
                if (list1.getCode().equals(code) == true) {
                    list1.setName(name);
                    list1.setDescription(description);
                    list1.setCost(cost);
                    list1.setCode(newCode);
                    return true;
                }
            } 
        }
        return false;
    }
    /*
    elimina un articulo de la lista de articulos
    recibiendo su codigo
    */
     public static void deleteArticle(String code){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCode().equals(code)){
                list.remove(i);
                return;
            }
        }   
    }
     
     public static String articleInfo(String code){
         articles article= search(code);{
         if(article==null){
             return null;
            }
         return article.toString();
        }
     }
     
     /*
      busca un articulo recibiendo el codigo
     */
     public static articles search(String code){
        for (articles list1 : list) {
            if (list1.getCode().equals(code)) {
                return list1;
            }
        }
         return null;
     }
     /*
         recibe el codigo del articulo para disminuir el stock
     */
    public static boolean sellArticle(String code,int cant){
        for (articles list1 : list) {
            if (list1.getCode().equals(code)) {
                if (cant <= ((toSellArticles) list1).getStock() && cant > 0) {
                    ((toSellArticles) list1).setStock(((toSellArticles) list1).getStock() - cant);
                    return true;
                } 
                else {
                    return false;
                }
            }
        }
        return false;
    }
    /*
    aunmenta el stock de un articulo especifico
    */
    public static void increaseStockArticle(String code,int cant){
        if(cant > 0)
        {
            for (articles list1 : list) {
                if (list1.getCode().equals(code)) {
                    ((toSellArticles) list1).increaseStock(cant);
                    return; 
                }
            }
        }
    }
    /*
    repara un articulo recibiendo el codigo
    pone el booleano de reparado en true
    */
    public static void fixArticle(String code){
        for (articles list1 : list) {
            if (list1.getCode().equals(code)) {
                ((toFixArticles) list1).setFixed(true);
                return;
            }
        }
    }
    /*
    verifica si un articulo es instancia de los articulos para reparar
    recibe un articulo
    retorna false si no es instancia y de lo contrario true
    */
    public boolean instanceToFix(articles art){
        return art instanceof toFixArticles;
    }
    /*
    imprime todos los articulos no reparados
    */
    public void printNotFixed(){
        for (articles list1 : list) {
            if (instanceToFix(list1)) {
                System.out.println(((toFixArticles) list1).toString());
            } else {
                System.out.println(((toSellArticles) list1).toString());
            }
        }
    }
    
    
  
    
}
