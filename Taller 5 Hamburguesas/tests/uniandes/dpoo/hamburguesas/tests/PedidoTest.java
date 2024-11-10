package uniandes.dpoo.hamburguesas.tests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
public class PedidoTest {
	
	private Pedido pedido;
	private ProductoMenu nuevoProducto1;
	private ProductoMenu nuevoProducto2;
	
	@Before
	public void setUp() {
		ArrayList<Producto> productos = new ArrayList<>();
		nuevoProducto1 = new ProductoMenu("mexicana", 22000);
		nuevoProducto2 = new ProductoMenu("corralita", 13000);
		productos.add(nuevoProducto1);
		productos.add(nuevoProducto2);
		pedido = new Pedido("Samuel", "Calle 60", productos);
		
		

		
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Samuel", pedido.getNombreCliente());
	}
	
	@Test
	public void testGetDireccionCliente() {
		assertEquals("Calle 60", pedido.getDireccionCliente());
	}
	
	@Test
	public void testGetIdPedido() {
		int id = pedido.getIdPedido();
		assertEquals(id, pedido.getIdPedido());
		
	}
	
	@Test
	public void testAgregarProducto() {
		ProductoMenu nuevoProducto1 = new ProductoMenu("mexicana", 22000);
		pedido.agregarProducto(nuevoProducto1);
		assertTrue(pedido.getProductos().contains(nuevoProducto1));
	}
	
	@Test
	public void testGetPrecioNetoPedido() {
		double suma = pedido.getProductos().get(0).getPrecio() + pedido.getProductos().get(1).getPrecio();
		assertEquals(suma, pedido.getPrecioNetoPedido(), suma);
	}
	
	@Test
	public void testGetPrecioIVAPedido() {
		double suma = pedido.getProductos().get(0).getPrecio() + pedido.getProductos().get(1).getPrecio();
		double precioIVA = suma * 0.19;
		assertEquals(precioIVA, pedido.getPrecioIVAPedido(), precioIVA);
	}
	
		
	@Test
	public void testGenerarTextoFaxtura() {
		String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Cliente: Samuel"));
        assertTrue(factura.contains("Dirección: Calle 60"));
        assertTrue(factura.contains("Precio Neto:  35000"));
        assertTrue(factura.contains("IVA:          6650"));
        assertTrue(factura.contains("Precio Total: 41650"));
	}
	
	@Test
    public void testGuardarFactura() {
        File archivo = new File("factura_test.txt");
        try {
            pedido.guardarFactura(archivo);
            assertTrue(archivo.exists());
        } catch (FileNotFoundException e) {
            fail("No se pudo guardar la factura.");
        } finally {
            archivo.delete();
        }
    }
}
