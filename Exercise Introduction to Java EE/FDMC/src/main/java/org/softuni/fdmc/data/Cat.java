package org.softuni.fdmc.data;

public class Cat {
	
	private String name;
	private String breed;
	private String color;
	private int numberOfLegs;
	
	public Cat(String name, String breed, String color, int numberOfLegs) {
		this.setName(name);
		this.setBreed(breed);
		this.setColor(color);
		this.setNumberOfLegs(numberOfLegs);
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getBreed() {
		return breed;
	}
	
	private void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getColor() {
		return color;
	}
	
	private void setColor(String color) {
		this.color = color;
	}
	
	public int getNumberOfLegs() {
		return numberOfLegs;
	}
	
	private void setNumberOfLegs(int numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}
}
