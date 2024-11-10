package uniandes.dpoo.hamburguesas.mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static ArrayList<Combo> combos = new ArrayList<>();
	private static ArrayList<Ingrediente> ingredientes= new ArrayList<>();
	private static ArrayList<ProductoMenu> productos = new ArrayList<>();
	
	

	public ArrayList<Combo> getCombos() {
		return combos;
	}

	public void setCombos(ArrayList<Combo> combos) {
		this.combos = combos;
	}



	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}



	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}



	public static void main (String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/lc161/Downloads/Taller 5 Hamburguesas/data/combos.txt"))){
			String linea;
			while((linea = br.readLine()) != null) {
				String[] lista = linea.split(";");
				ArrayList<ProductoMenu> productosMenu = new ArrayList<>();
				for(int i = 2; i < lista.length; i++) {
					String nombreProducto = lista[i];
					try(BufferedReader br2 = new BufferedReader(new FileReader("C:/Users/lc161/Downloads/Taller 5 Hamburguesas/data/menu.txt"))){
						String lineaMenu;
						String[] lineaMenuEncontrado;
						while((lineaMenu = br2.readLine()) != null) {
							String[] lista2 = lineaMenu.split(";");
							if(lista2[0].equals(nombreProducto)) {
								lineaMenuEncontrado = lista2;
								ProductoMenu productoMenu = new ProductoMenu(nombreProducto, Integer.parseInt(lineaMenuEncontrado[1]));
								productosMenu.add(productoMenu);
								productos.add(productoMenu);
							}
						}
					}catch (IOException e) {
						e.printStackTrace();
					}
				
				}
				Combo combo = new Combo(lista[0], Double.parseDouble(lista[1].substring(0, lista[1].length()-1)) / 10, productosMenu);
				Main.combos.add(combo);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/lc161/Downloads/Taller 5 Hamburguesas/data/ingredientes.txt"))){
			String linea;
			while((linea = br.readLine()) != null) {
				String[] lista = linea.split(";");
				Main.ingredientes.add(new Ingrediente(lista[0], Integer.parseInt(lista[1])));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		boolean continuar = true;
		Scanner scanner = new Scanner(System.in);
		while(continuar) {
			System.out.println("1. Mostrar menu");
			System.out.println("2. Iniciar nuevo pedido");
			System.out.println("3. Cerrar un pedido y guardar la factura");
			System.out.println("4. Agregar un elemento a un pedido, incluyendo ajustes");
			System.out.println("5. Consultar la información de un pedido dado su identificador");
			System.out.println("6. Salir de la aplicacion");
			System.out.print("Ingrese la opcion que desee: ");
			int opcion = scanner.nextInt();
			if(opcion == 1) {
				System.out.println("Estos son los combos disponibles: ");
				for(Combo combo: Main.combos) {
					System.out.println(" ");
					System.out.println("Nombre del combo: " + combo.getNombre());
					System.out.println("Precio: " + combo.getPrecio());
					System.out.println("Descuento: " + combo.getDescuento());
					for(ProductoMenu items: combo.getItems()) {
						System.out.println("Items por combo: " + items);
					}
					
				}
				
				System.out.println("Estos son los ingredientes disponibles: ");
				for(Ingrediente ingrediente: Main.ingredientes) {
					System.out.println(" ");
					System.out.println("Ingrediente: " + ingrediente.getNombre());
					System.out.println("Precio: " + ingrediente.getCostoAdicional());
				}
				
				System.out.println("Estos son los productos del menu disponibles: ");
				for(ProductoMenu productos: Main.productos) {
					System.out.println(" ");
					System.out.println("Nombre del producto: " + productos.getNombre());
					System.out.println("Precio: " + productos.getPrecio());
					
				}
			}
			else if (opcion == 2) {
				return;
			}
			
			else if (opcion == 3) {
				return;
			}
			
			else if (opcion == 4) {
				return;
			}
			
			else if (opcion == 5) {
				return;
			}
			
			else if (opcion == 6) {
				continuar = false;
			}
			
		}
		scanner.close();
	}
	
}
