package uniandes.dpoo.hamburguesas.tests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private Combo combo;
	
	
	@Before
	public void setUp() {
		ArrayList<ProductoMenu> items = new ArrayList<>();
		items.add(new ProductoMenu("corral", 14000));
		items.add(new ProductoMenu("corral queso", 16000));
		items.add(new ProductoMenu("corral pollo", 15000));
		combo = new Combo("corral", 0.1, items);
	}
	
	@Test
    public void testGetNombre() {
        assertEquals("corral", combo.getNombre());
    }
	
	@Test
	public void testGetPrecio() {
		double suma = combo.getItems().get(0).getPrecio() + combo.getItems().get(1).getPrecio() + combo.getItems().get(2).getPrecio();
		assertEquals(suma - suma* 0.1, combo.getPrecio());
	}
	
	@Test
	public void testGenerarFactura() {
        String factura = combo.generarTextoFactura();
        assertTrue(factura.contains("Combo corral"));
        assertTrue(factura.contains(" Descuento: 0.1"));
        assertTrue(factura.contains("            40500"));
        
	}

}
