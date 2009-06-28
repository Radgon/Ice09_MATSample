package com.commons;


public class Scopetest {
	
	private int creation;

	public Scopetest() {
		creation = (int)System.nanoTime();
	}

	public int uniqueCode() {
		return creation;
	}
	
}
