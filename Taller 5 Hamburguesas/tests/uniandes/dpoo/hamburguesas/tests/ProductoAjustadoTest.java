package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

public class ProductoAjustadoTest {

    private ProductoAjustado productoAjustado;
    private Ingrediente queso;

    @Before
    public void setUp() {
        ProductoMenu producto = new ProductoMenu("corral", 14000);
        productoAjustado = new ProductoAjustado(producto);
        queso = new Ingrediente("queso mozzarella", 2500);
        
    }

    @Test
    public void testAgregarIngrediente() {
        productoAjustado.agregarIngrediente(queso);
        assertEquals("queso mozzarella", productoAjustado.getAgregados().get(productoAjustado.getAgregados().size()-1).getNombre());
    }

    @Test
    public void testEliminarIngrediente() {
        productoAjustado.eliminarIngrediente(queso);
        assertEquals("queso mozzarella", productoAjustado.getEliminados().get(productoAjustado.getEliminados().size()-1).getNombre());
    }
    
    @Test
    public void testGetPrecio() {
    	productoAjustado.agregarIngrediente(queso);
    	productoAjustado.eliminarIngrediente(new Ingrediente("tomate", 1000));
    	assertEquals(16500, productoAjustado.getPrecio(), 16500);
    }
    
    @Test
    public void testToString() {
    	productoAjustado.agregarIngrediente(queso);
    	productoAjustado.eliminarIngrediente(new Ingrediente("tomate", 1000));
        String factura = productoAjustado.generarTextoFactura();
        assertTrue(factura.contains("corral    14000"));
        assertTrue(factura.contains("Total: 16500"));
        assertTrue(factura.contains("    -tomate"));
        assertTrue(factura.contains("    +queso"));
        assertTrue(factura.contains("                2500"));
    }
    
    
    
    
}
