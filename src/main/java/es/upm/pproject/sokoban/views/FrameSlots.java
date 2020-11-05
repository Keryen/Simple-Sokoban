package es.upm.pproject.sokoban.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.controllers.SokobanController;

public class FrameSlots extends JFrame {

	private static final long serialVersionUID = 1L;
	private SokobanController controller;
	private JFrame previousFrame;
	
	public FrameSlots(SokobanController controller, String action, JFrame previousFrame) {
		this.controller = controller;
		this.previousFrame = previousFrame;
		
		setTitle("Slots");
		setSize(250, 225);
		setLocationRelativeTo(previousFrame);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLayout(new GridLayout(5, 1));

		initialize(action);

		setVisible(true);
	}

	private void initialize(String action) {
		
		JPanel aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel labelAct = new JLabel("Select wich slot you want to " + action);
		aux.add(labelAct);
		add(aux);

		Dimension dim = new Dimension(75, 30);
		
		aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton slot1 = new JButton("Slot 1");
		slot1.setPreferredSize(dim);
		aux.add(slot1);
		slot1.addActionListener(event -> doAction(action, 1));
		add(aux);

		aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton slot2 = new JButton("Slot 2");
		slot2.setPreferredSize(dim);
		aux.add(slot2);
		slot2.addActionListener(event -> doAction(action, 2));
		add(aux);

		aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton slot3 = new JButton("Slot 3");
		slot3.setPreferredSize(dim);
		aux.add(slot3);
		slot3.addActionListener(event -> doAction(action, 3));
		add(aux);
		
		aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton exit = new JButton("Exit");
		exit.setPreferredSize(dim);
		aux.add(exit);
		exit.addActionListener(event -> close());
		add(aux);
	}

	private void doAction(String action, int slot) {
		switch (action) {
		case "load":
			end();
			controller.loadGame(slot);
			break;
		case "save":
			end();
			controller.saveGame(slot);
			break;
		default:
		}
	}
	
	private void close() {
		setVisible(false);
		previousFrame.setVisible(true);	
	}
	
	private void end() {
		setVisible(false);
		previousFrame.setVisible(false);
	}
}
