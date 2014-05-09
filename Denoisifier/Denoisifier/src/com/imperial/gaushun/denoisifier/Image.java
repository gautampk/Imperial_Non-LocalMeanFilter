package com.imperial.gaushun.denoisifier;

import java.util.ArrayList;
import java.io.*;

public class Image {
	public static void main(String[] args) {
		Console console = System.console();
		String planet = console.readLine("\nEnter your favourite planet: ");
		ArrayList planetList = new ArrayList();
		planetList.add(planet);
		planetList.add("Gliese 581 c");
		System.out.println("\nTwo cool planets: " + planetList);
	}
}