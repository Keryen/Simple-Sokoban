package es.upm.pproject.sokoban.model.objects;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Wall extends GameObject {

	public Wall () throws IOException {
		super(ImageIO.read(new File("resources/images/Wall.png")));
	}
	
	@Override
	public boolean isWall() {
		return true;
	}
}
