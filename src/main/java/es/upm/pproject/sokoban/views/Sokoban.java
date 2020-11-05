package es.upm.pproject.sokoban.views;

import java.awt.EventQueue;
import es.upm.pproject.sokoban.controllers.SokobanController;
import es.upm.pproject.sokoban.model.SokobanImpl;
import es.upm.pproject.sokoban.model.interfaces.SokobanService;

public class Sokoban {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SokobanService model = new SokobanImpl();
			SokobanController controller = new SokobanController(model);
			new FrameHome(controller);
			controller.initMusic();
		});
	}
}
