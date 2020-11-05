package es.upm.pproject.sokoban.model.objects;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EmptySpace extends GameObject {

	public EmptySpace () throws IOException {
		super(ImageIO.read(new File("resources/images/EmptySpace.png")));
	}
	
	@Override
	public boolean isEmptySpace() {
		return true;
	}
}
