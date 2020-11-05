package es.upm.pproject.sokoban.model.objects;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GoalPosition extends GameObject {

	public GoalPosition () throws IOException {
		super(ImageIO.read(new File("resources/images/GoalPosition.png")));
	}
	
	@Override
	public boolean isGoalPosition() {
		return true;
	}
}
