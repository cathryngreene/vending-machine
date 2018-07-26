package com.techelevator;

public class Balance {

	private double currentBalance;
	
	public Balance(){
		currentBalance = 0;
	}

	public double addToBalance(double money) {
		currentBalance += money;
		return currentBalance;
	}
	
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}
	
	
	
}
