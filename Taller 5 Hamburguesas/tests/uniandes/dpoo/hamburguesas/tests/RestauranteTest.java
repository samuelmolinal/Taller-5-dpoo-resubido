package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
	
	private Restaurante restaurante;
	
	
	@Before
	public void setUp() {
		restaurante = new Restaurante();
	}
	
	@Test
	public void testCargarIngredientes() throws IOException, IngredienteRepetidoException { 
		File archivoIngredientes = new File("data/ingredientes.txt");
		restaurante.cargarIngredientes(archivoIngredientes);
		assertFalse("Deben haber ingredientes cargados", restaurante.getIngredientes().isEmpty());
        assertTrue("Debería contener lechuga", restaurante.getIngredientes().stream().anyMatch(i -> i.getNombre().equals("lechuga")));
	}
	
	 @Test
	 public void testCargarMenu() throws IOException, ProductoRepetidoException {
	        File archivoMenu = new File("data/menu.txt");
	        restaurante.cargarMenu(archivoMenu);
	        assertFalse("Deben haber menus cargados", restaurante.getMenuBase().isEmpty());
	        assertTrue("Debería contener corral", restaurante.getMenuBase().stream().anyMatch(p -> p.getNombre().equals("corral")));
	    }
	 
	 @Test
	 public void testCargarCombos() throws IOException, ProductoRepetidoException, ProductoFaltanteException {
		 File archivoMenus = new File("data/menu.txt");
		 File archivoCombos = new File("data/combos.txt");
		 restaurante.cargarMenu(archivoMenus);
		 restaurante.cargarCombos(archivoCombos);
		 assertFalse("Deben haber combos cargados", restaurante.getMenuCombos().isEmpty());
		 assertTrue("Deberia tener combo corral", restaurante.getMenuCombos().stream().anyMatch(c -> c.getNombre().equals("combo corral")));
	 }
	 
	 @Test
	 public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
	        ArrayList<Producto> productos = new ArrayList<>();
	        productos.add(new ProductoMenu("corral", 14000));
	        restaurante.iniciarPedido("Samuel", "Calle 60", productos);
	        assertNotNull("Debe haber un pedido en curso", restaurante.getPedidoEnCurso());
	        assertEquals("Samuel", restaurante.getPedidoEnCurso().getNombreCliente());
	    }
	 
	 @Test
	 public void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
	        ArrayList<Producto> productos = new ArrayList<>();
	        productos.add(new ProductoMenu("corral", 14000));
	        restaurante.iniciarPedido("Samuel", "Calle 60", productos);
	        restaurante.cerrarYGuardarPedido();
	        assertNull("No debería haber un pedido en curso", restaurante.getPedidoEnCurso());
	        assertEquals("Debería haber un pedido en el historial", 1, restaurante.getPedidos().size());
	    }
	 
	 @Test
	 public void testGetPedidos() {
	        assertNotNull("La lista de pedidos no debería ser null", restaurante.getPedidos());
	        assertTrue("La lista de pedidos debería estar vacía inicialmente", restaurante.getPedidos().isEmpty());
	    }
	 
	 @Test
	 public void testGetMenuCombos() {
		 assertNotNull("El menu de combos no deberia ser null", restaurante.getMenuCombos());
	 }
	 
	 @Test
	 public void testGetMenuBase() {
		 assertNotNull("El menu base no deberia ser null", restaurante.getMenuBase());
	 }
	 
	 @Test
	 public void testGetIngredientes() {
		 assertNotNull("La lista de ingredientes no puede ser nula", restaurante.getIngredientes());
	 }
}
