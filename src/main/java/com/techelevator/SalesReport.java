package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class SalesReport {

	
	public void getSalesReport() {
		double potatoCrips = 0;
		double stackers = 0;
		double grainWaves = 0;
		int cloudPopcorn = 0;
		int moonpie = 0;
		int cowtales = 0;
		int wonkaBar = 0;
		int crunchie = 0;
		int skor = 0;
		int cola = 0;
		int drSalt = 0;
		int mountainMelter = 0;
		int heavy = 0;
		int dietCola = 0;
		int uChews = 0;
		int littleLeagueChew = 0;
		int chiclets = 0;
		int triplemint = 0;
		try {
		File salesReport = new File("sales_report");
		File logTxt = new File("Log.txt");
			Scanner salesScanner = new Scanner(logTxt); {
			while(salesScanner.hasNextLine()) {
				String line = salesScanner.nextLine();
				if(line.contains("Potato Crisps")) {
					potatoCrips++;
				}
					else if (line.contains("Stackers")) {
						stackers++;
					}
					else if(line.contains("Grain Waves")) {
						grainWaves++;
					}
					else if(line.contains("Cloud Popcorn")) {
						cloudPopcorn++;
					}
					else if(line.contains("Moonpie")) {
						moonpie++;
					}
					else if(line.contains("Cowtales")) {
						cowtales++;
					}
					else if(line.contains("Wonka Bar")) {
					wonkaBar++;
					}
					else if(line.contains("Crunchie")) {
					crunchie++;
					}
					else if(line.contains("Grain Waves")) {
						grainWaves++;
					}
					else if(line.contains("Skor")) {
					skor++;
					}
					else if(line.contains("Cola")) {
					cola++;
					}
					else if(line.contains("Dr. Salt")) {
					drSalt++;
					}
					else if(line.contains("Mountain Melter")) {
					mountainMelter++;
					}
					else if(line.contains("Heavy")) {
					heavy++;
					}
					else if(line.contains("Diet Cola")) {
					dietCola++;
					}
					else if(line.contains("U-Chews")) {
					uChews++;
					}
					else if(line.contains("Little League Chew")) {
					littleLeagueChew++;
					}
					else if(line.contains("Chiclets")) {
					chiclets++;
					}
					else if(line.contains("Triplemint")) {
					triplemint++;
					}
				
				PrintWriter salesPrint = new PrintWriter(salesReport);
				salesPrint.println("PotatoCrisps|" + potatoCrips);
				salesPrint.println("Stackers|" + stackers);
				salesPrint.println("Grain Waves|" + grainWaves);
				salesPrint.println("Cloud Popcorn|" + cloudPopcorn);
				salesPrint.println("Moonpie|" + moonpie);
				salesPrint.println("Cowtales|" + cowtales);
				salesPrint.println("Wonka Bar|" + wonkaBar);
				salesPrint.println("Crunchie|" + crunchie);
				salesPrint.println("Skor|" + skor);
				salesPrint.println("Cola|" + cola);
				salesPrint.println("Dr. Salt|" + drSalt);
				salesPrint.println("Mountain Melter|" + mountainMelter);
				salesPrint.println("Heavy|" + heavy);
				salesPrint.println("Diet Cola|" + dietCola);
				salesPrint.println("U-Chews|" + uChews);
				salesPrint.println("Little League Chew|" + littleLeagueChew);
				salesPrint.println("Chiclets|" + chiclets);
				salesPrint.println("Triplemint|" + triplemint);
				salesPrint.println("Total Sales = " + ((3.05 * potatoCrips) + (1.45* stackers) + (2.75 * grainWaves) + (3.65* cloudPopcorn) + (1.8* moonpie) + (1.5*cowtales) 
						+ (1.5 * wonkaBar) + (1.75*crunchie) + (1.25* cola) + (1.5*drSalt) + (1.5*mountainMelter) + (1.5*heavy) + (.85 * uChews) 
						+ (0.95*littleLeagueChew) + (0.75*chiclets) + (0.75* triplemint)));
				salesPrint.close();
				break;
		
				
			}
		
			}
		}
		catch (FileNotFoundException e) {
			e.getMessage();
		}
	}
}

		

