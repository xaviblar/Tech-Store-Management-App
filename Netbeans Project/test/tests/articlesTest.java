/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import articles.articles;
import articles.listArticles;
import articles.toFixArticles;
import articles.toSellArticles;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xavier
 */
public class articlesTest {

    toSellArticles artToSell;
    toFixArticles artToFix;

    public articlesTest() {
    }

    @Before
    public void setUp() {
        artToSell = new toSellArticles(2, "Codigo", "Nombre", "Descripcion");
        artToSell.setStock(1);
        artToFix = new toFixArticles("idOwner", "code", "name", "description", "nameOwner");
        listArticles.insertArticle(artToSell);
        listArticles.insertArticle(artToFix);
    }

    @After
    public void tearDown() {
        artToSell = null;
        artToFix = null;
        listArticles.list.clear();
    }

    /**
     * Test que verifica que un articulo es insertado correctamente
     */
    @Test
    public void testInsertArticleSuccessfully() {
        //Las inserciones se realizan en el setUp()
        int esperado = 2;
        int obtenido = listArticles.list.size();
        assertEquals("Error en la inserción", esperado, obtenido);
    }

    /**
     * Test que verifica que no se inserten objetos nulos
     */
    @Test
    public void testInsertArticleNull() {
        artToSell = null;
        listArticles.insertArticle(artToSell);
        int esperado = 2;
        int obtenido = listArticles.list.size();
        assertEquals("Se realiza la insercion de un objeto nulo", esperado, obtenido);
    }

    /**
     * Test que verifica que el metodo modificar articulo modifique de manera
     * correcta
     */
    @Test
    public void testModifyArticleSuccessfully() {
        listArticles.modifyArticle("Codigo", "NuevoCodigo", "NombreModificado", "DescripcionModificada", 5); // se modifica al articulo
        articles article = listArticles.search("NuevoCodigo");
        assertTrue("Error en la modificacion", article.getCode().equals("NuevoCodigo") && article.getDescription().equals("DescripcionModificada") && article
                .getName().equals("NombreModificado") && article.getCost() == 5);
    }

    /**
     * Test que verifica que no se modifique un articulo si el codigo ya existe
     */
    @Test
    public void testModifyArticleRepeatedCode() {
        toSellArticles artToSell2 = new toSellArticles(2, "CodigoRepetido", "Nombre", "Descripcion");
        listArticles.insertArticle(artToSell2);
        boolean result = listArticles.modifyArticle("Codigo", "CodigoRepetido", "NombreModificado", "DescripcionModificada", 1);
        assertFalse("Articulo fue modificado", result);
    }

    /**
     * Test que verifica que no se modifique un articulo si el precio dado es
     * negativo
     */
    @Test
    public void testModifyArticleNegativePrice() {
        boolean result = listArticles.modifyArticle("Codigo", "CodigoModificado", "NombreMod", "DescrpcionMod", -2);
        assertFalse("Articulo fue modificado", result);
    }

    /**
     * Test que verifica que el metodo de borrar articulo funcione correctamente
     */
    @Test
    public void testDeleteArticleSucessfully() {
        listArticles.deleteArticle("Codigo");
        articles result = listArticles.search("Codigo");
        assertEquals("Articulo no fue eliminado", result, null);
    }

    /**
     * Test que verifica que el metodo buscar retorne el objeto correcto
     */
    @Test
    public void testSearchArticleSucessfully() {
        articles result = listArticles.search("Codigo");
        assertTrue("Articulos no coinciden", result.equals(artToSell));
    }

    /**
     * Test que verifica que la funcion de vender articulo funcione de manera
     * correcta
     */
    @Test
    public void testSellArticleSuccessfully() {
        boolean result = listArticles.sellArticle("Codigo", 1);
        assertTrue("Metodo no proceso la venta", result);
    }

    /**
     * Test que verifica que el metodo de vender articulo no procese una venta
     * con una cantidad mayor al stock actual
     */
    @Test
    public void testSellArticleInsufficientQuantity() {
        boolean result = listArticles.sellArticle("Codigo", 2);
        assertFalse("Metodo proceso la venta", result);
    }

    /**
     * Test que verifica que el metodo de vender articulo no procese una venta
     * con una cantidad negativa
     */
    @Test
    public void testSellArticleNegativeQuantity() {
        boolean result = listArticles.sellArticle("Codigo", -2);
        assertFalse("Metodo proceso la venta", result);
    }
    
    /**
     * Test que verifica que el stock de un articulo no sea modificado luego de una venta fallida
     */
    @Test
    public void testSellArticleFailCheckStock() {
        listArticles.sellArticle("Codigo", -2);
        toSellArticles art = (toSellArticles) listArticles.search("Codigo");
        assertEquals("Stock fue modificado", art.getStock(),1);
    }

    /**
     * Test que verifica que el stock disminuya al vender un articulo
     */
    @Test
    public void testVerifyStockAfterSuccessfulSale() {
        listArticles.sellArticle("Codigo", 1);
        toSellArticles art = (toSellArticles) listArticles.search("Codigo");
        assertEquals("Inventario no actualizado", art.getStock(), 0);
    }

    /**
     * Test que verifica que el stock se mantenga igual al intentar procesar una
     * venta invalida
     */
    @Test
    public void testVerifyStockAfterFailedSale() {
        listArticles.sellArticle("Codigo", -2);
        toSellArticles art = (toSellArticles) listArticles.search("Codigo");
        assertEquals("Stock fue modificado", art.getStock(), 1);
    }

    /**
     * Test que verifica que el metodo de aumentar stock de un articulo funcione
     * de manera correcta
     */
    @Test
    public void testIncreaseStockSuccessfully() {
        listArticles.increaseStockArticle("Codigo", 1);
        toSellArticles result = (toSellArticles) listArticles.search("Codigo");
        assertEquals("Stock no fue aumentado", result.getStock(), 2);
    }

    /**
     * Test que verifica que el metodo de aumentar stock no procese el cambio si
     * la cantidad dada es negativa
     */
    @Test
    public void testIncreaseStockNegativeQuantity() {
        listArticles.increaseStockArticle("Codigo", -2);
        toSellArticles result = (toSellArticles) listArticles.search("Codigo");
        assertEquals("Stock fue modificado", result.getStock(), 1);
    }

    /**
     * Test que verifica que el metodo set stock no procese el cambio si la
     * cantidad dada es negativa
     */
    @Test
    public void testSetStockNegativeQuantity() {
        artToSell.setStock(-2);
        assertEquals("Stock fue modificado", artToSell.getStock(), 1);
    }

    /**
     * Test que verifica que el metodo de reparar articulo funcione de manera
     * correcta
     */
    @Test
    public void testRepairArticleSuccessfully() {
        listArticles.fixArticle("code");
        toFixArticles result = (toFixArticles) listArticles.search("code");
        assertTrue("Estado de articulo no cambio a reparado", result.isFixed());
    }
    
    @Test
    public void testInfoArticleSuccefully(){
        articles artToFix2 = new toFixArticles("Prueba","Insercion","Exitosa","","");
        articles article = artToFix2;
        listArticles.insertArticle(article);
        String esperado = article.toString();
        String obtenido = listArticles.articleInfo("Insercion");
        assertEquals(esperado,obtenido);
    }
    
    @Test
    public void testInfoArticleNull(){
        listArticles.insertArticle(artToFix);
        String obtenido=listArticles.articleInfo("codigo incorrecto");
        assertEquals(null,obtenido);    
    }
    
    /**
     * Test of setPrice method, of class toSoldArticles.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetPrice() throws Exception {
        toSellArticles tsa= new toSellArticles(1,"","","");
        try {
            tsa.setPrice(-100);
            fail("El numero dado fue positivo");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(),"El precio debe ser positivo");
        }
        
    }
}
