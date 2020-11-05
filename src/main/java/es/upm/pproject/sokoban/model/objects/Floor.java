package es.upm.pproject.sokoban.model.objects;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Floor extends GameObject {

	public Floor () throws IOException {
		super(ImageIO.read(new File("resources/images/Floor.png")));
	}
	
	@Override
	public boolean isFloor() {
		return true;
	}
}
