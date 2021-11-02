package fr.sgcib.tondeuse.entities;

import org.apache.commons.lang.Validate;

public class Pelouse {
	//Largeur de la pelouse
	private int width;
	//Longueur de la pelouse
	private int height;
	
	
	public Pelouse(){
	}
	
	public Pelouse(int width, int height){
		Validate.isTrue(width > 0, "Coordonnée sur les X doit etre positive");
		Validate.isTrue(height > 0, "Coordonnée sur les Y doit etre positive");

		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
