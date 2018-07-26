package com.techelevator;

public class Product {
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private String name;
	private double price;
	private String type;
	private int quantity;
	
	public Product(String name, double price, String type, int quantity) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public String getSound() {
		String sound = "";
		String chip = "Crunch Crunch, Yum!";
		String candy = "Munch Munch, Yum!";
		String drink = "Glug Glug, Yum!";
		String gum = "Chew Chew, Yum!";
		if (type.equals("Chip")) {
			sound = chip;
		}
		if(type.equals("Candy")) {
			sound = candy;
		}
		if(type.equals("Drink")) {
			sound = drink;
		}
		if(type.equals("Gum")) {
			sound = gum;
		}
		return sound;
	}
	
}
