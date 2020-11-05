package es.upm.pproject.sokoban.model.objects;

import java.awt.image.BufferedImage;


public class GameObject {

	private BufferedImage image;

	public GameObject (BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public boolean isPlayer() {
		return false;
	}
	
	public boolean isBox() {
		return false;
	}
	
	public boolean isEmptySpace() {
		return false;
	}
	
	public boolean isFloor() {
		return false;
	}
	
	public boolean isGoalPosition() {
		return false;
	}
	
	public boolean isWall() {
		return false;
	}
}
