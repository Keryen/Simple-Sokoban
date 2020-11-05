package es.upm.pproject.sokoban.model.objects;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends GameObject {

	private int i;
	private int j;
	private boolean isOnGoalPosition;
	
	public Player (String texture) throws IOException {
		super(ImageIO.read(new File("resources/images/"+texture+".png")));
		isOnGoalPosition = false;
	}
	
	public boolean isOnGoalPosition() {
		return isOnGoalPosition;
	}

	public void setOnGoalPosition(boolean onGoalPosition) {
		isOnGoalPosition = onGoalPosition;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	public void setJ(int j) {
		this.j = j;
	}
	
	@Override
	public boolean isPlayer() {
		return true;
	}
}
