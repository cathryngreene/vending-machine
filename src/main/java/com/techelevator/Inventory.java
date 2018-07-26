package com.techelevator;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Predicate;

import org.mockito.asm.tree.TryCatchBlockNode;

public class Inventory {
	
	protected Map<String, Product> inventoryMap = new TreeMap<>();

	public Map<String, Product> loadInventory() {
		File inventoryList = new File("vendingmachine.csv");
		try {
			Scanner reader = new Scanner(inventoryList);
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] parts = line.split("\\|");
				inventoryMap.put(parts[0], new Product(parts[1], Double.parseDouble(parts[2]), parts[3], 5));
			}

		} catch (FileNotFoundException e) {
			System.out.println("File error: " + e.getMessage());
		}
		return inventoryMap;

	}
	

	public Map<String, Product> getInventoryMap() {
		return inventoryMap;
	}

	
	

	public void displayInventory() {
		Set<String> keys = getInventoryMap().keySet();
		for(String slot : keys) {
			
			System.out.println(slot + " 	|	" + getInventoryMap().get(slot).getName()+ "	 " + " 		|			$" + getInventoryMap().get(slot).getPrice() + "  	|   " + getInventoryMap().get(slot).getQuantity() + " pieces");
		}
	}
	
//	public void selectProduct(String selection) {
//		ArrayList<Product> customerSelections = new ArrayList<>(); 
//		Set<String> keys = loadInventory().keySet();
//		for(String elem: keys) {
//			if(elem.equals(selection)) 
//				customerSelections.add(loadInventory().get(elem));
//			}
//		}
}

	

