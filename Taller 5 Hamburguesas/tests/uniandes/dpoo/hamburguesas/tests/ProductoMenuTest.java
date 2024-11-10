package uniandes.dpoo.hamburguesas.tests;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

    private ProductoMenu producto1;

    @Before
    public void setUp() {
        producto1 = new ProductoMenu("corral", 14000);
    }

    @Test
    public void testGetNombre() {
        assertEquals("corral", producto1.getNombre());
    }

    @Test
    public void testGetPrecio() {
        assertEquals(14000, producto1.getPrecio());
    }

    @Test
    public void testToString() {
        String factura = producto1.generarTextoFactura();
        assertTrue(factura.contains("corral"));
        assertTrue(factura.contains("precio: 14000"));
    }
}
