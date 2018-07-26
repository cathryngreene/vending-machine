package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items"; // Define a constant
																									// for the menu text
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase"; // Define a constant for menu text
	private static final String MAIN_MENU_OPTION_EXIT = "Exit"; // Define a constant for menu text
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, // Define array with menu
																						// choices
			MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_OPTION_FEED_MONEY = "Add money to your balance"; // Define a constant for the
																							// menu text
	private static final String PURCHASE_OPTION_SELECT_PRODUCT = "Select your product"; // Define a constant for menu
																						// text
	private static final String PURCHASE_OPTION_FINISH = "Finish your transaction"; // Define a constant for menu text
	private static final String[] PURCHASE_OPTIONS = { PURCHASE_OPTION_FEED_MONEY, PURCHASE_OPTION_SELECT_PRODUCT,
			PURCHASE_OPTION_FINISH };

	private Menu menu;
	Inventory test = new Inventory();
	
	
	



	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {		
		test.loadInventory();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS); // To use the menu, invoke the
																					// getChoiceFromOptions() method
																					// with array of options

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				test.displayInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				SecondaryMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				return;
				// exit program - this is where our code goes
			}
		}

	}
	
	public void SecondaryMenu() {
		Balance balance = new Balance();
		ArrayList<Product> customerSelections = new ArrayList<>();
		int quantityRemaining;
		double currentBalance = 0;
		File auditTracker = new File("Log.txt");
		SalesReport salesFile = new SalesReport();
		while (true) {
			String stepTwo = (String) menu.getChoiceFromOptions(PURCHASE_OPTIONS);

			if (stepTwo.equals(PURCHASE_OPTION_FEED_MONEY)) {
				System.out.println("**Please add your money to the machine**");
				Scanner keyboard = new Scanner(System.in);
				Double inputMoney = keyboard.nextDouble();
				System.out.println("You have added $" + inputMoney);
				currentBalance = balance.getCurrentBalance();
				currentBalance += inputMoney;
				balance.setCurrentBalance(currentBalance);
				System.out.println("Your total balance is: $" + String.format("%.2f", balance.getCurrentBalance()));

				try (PrintWriter diskFile = new PrintWriter(new FileWriter(auditTracker, true))) {
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
					diskFile.print(timeStamp + " FEED MONEY    $" + inputMoney + "   $" + currentBalance + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (stepTwo.equals(PURCHASE_OPTION_SELECT_PRODUCT)) {
				boolean isValid = false;
				System.out.println("**Please select an item**");
				Scanner keyboard = new Scanner(System.in);
				String selection = keyboard.nextLine();
				System.out.println("You have entered " + selection);
				Set<String> keys = test.getInventoryMap().keySet();
				for (String elem : keys) {
					if (elem.equals(selection)) {
						if (test.getInventoryMap().get(elem).getQuantity() >= 1) {
							if (balance.getCurrentBalance() >= test.getInventoryMap().get(elem).getPrice()) {
								double startingBalance = currentBalance;
								currentBalance -= test.getInventoryMap().get(elem).getPrice();
								balance.setCurrentBalance(currentBalance);
								quantityRemaining = test.getInventoryMap().get(elem).getQuantity() - 1;
								test.getInventoryMap().get(elem).setQuantity(quantityRemaining);
								customerSelections.add(test.getInventoryMap().get(elem));
								System.out.println("You purchased " + test.getInventoryMap().get(elem).getName());
								System.out.println("You have: $" + currentBalance + " remaining");
								isValid = true;
								try (PrintWriter diskFile = new PrintWriter(new FileWriter(auditTracker, true))) {
									String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
											.format(Calendar.getInstance().getTime());
									diskFile.print(timeStamp + " " + test.getInventoryMap().get(elem).getName() + "  "
											+ elem + "   $" + startingBalance + "   $" + currentBalance + "\n");
								} catch (Exception e) {
									e.printStackTrace();
								}
								break;
							} else {
								System.out.println(
										"Sorry, you don't have enough money, please insert more money to finish transaction");
							}
						} else {
							System.out.println("Sorry, this product is SOLD OUT");
						}
					}
				}
				if (isValid == false) {
					System.out.println("Not a valid selection"); // we have tried this in several locations, but
																	// program is not printing this line when
																	// applicable
				}
			} else if (stepTwo.equals(PURCHASE_OPTION_FINISH)) {
				double startingBalance = currentBalance;
				int quarterCount = 0;
				int dimeCount = 0;
				int nickelCount = 0;
				currentBalance = currentBalance * 100;
				while (currentBalance >= 5) {
					if (currentBalance >= 25) {
						quarterCount++;
						currentBalance -= 25;
					} else if (currentBalance >= 10) {
						dimeCount++;
						currentBalance -= 10;
					} else if (currentBalance >= 5) {
						nickelCount++;
						currentBalance -= 5;
					}
				}
				System.out.println("Your change is " + quarterCount + " quarters, " + dimeCount + " dimes, and "
						+ nickelCount + " nickels.");
				System.out.println("Please collect your change!");
				currentBalance = 0;
				balance.setCurrentBalance(currentBalance);
				System.out.println("Your Current Balance is " + currentBalance);
				for (Product elem : customerSelections) {
					System.out.println(elem.getSound());
				}

				try (PrintWriter diskFile = new PrintWriter(new FileWriter(auditTracker, true))) {
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
					diskFile.printf(timeStamp + " GIVE CHANGE   $" + startingBalance + "    $" + currentBalance + "\n");
				} catch (Exception e) {
					e.printStackTrace();

					
					
					customerSelections.clear();
				}break;
			}
			salesFile.getSalesReport();
		}

	}

	public static void main(String[] args) throws FileNotFoundException {

		Menu menu = new Menu(System.in, System.out); // Instantiate the menu object (input source & output source)
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
