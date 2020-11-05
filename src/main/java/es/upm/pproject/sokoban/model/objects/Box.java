package es.upm.pproject.sokoban.model.objects;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Box extends GameObject {
	
	private boolean isOnPoint;
	
	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.objects.Box");
	private static final String EXCEPTIONMSG = "An exception has ocurr";
	
	public Box () throws IOException {
		super(ImageIO.read(new File("resources/images/Box.png")));
		isOnPoint = false;
	}
	
	public boolean isOnGoalPosition() {
		return isOnPoint;
	}

	public void setOnGoalPosition(boolean onGoal) {
		isOnPoint = onGoal;
		try {
			File f;
			if (onGoal) {
				f = new File("resources/images/FinishedBox.png");
			} else {
				f = new File("resources/images/Box.png");
			} 
			this.setImage(ImageIO.read(f));
		}catch (IOException e) {
			LOGGER.log(Level.SEVERE, EXCEPTIONMSG, e);
		}
	}
	
	@Override
	public boolean isBox() {
		return true;
	}
}

