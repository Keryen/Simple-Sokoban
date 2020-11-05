package es.upm.pproject.sokoban.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.controllers.SokobanController;

public class FrameMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private SokobanController controller;
	private JFrame levelView; 
	private JFrame slotsView;
	
	public FrameMenu(SokobanController controller, JFrame levelView) {
		this.controller = controller;
		this.levelView = levelView; 
		
		setTitle("Options");
		setSize(230, 230);
		setLocationRelativeTo(levelView);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLayout(new GridLayout(5, 1));

		initialize();
		
		setVisible(true);
	}

	public void initialize() {
		Dimension dim = new Dimension(75, 30);
		
		JPanel aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		aux.setBackground(Color.LIGHT_GRAY);
		JButton help = new JButton("Help");
		help.setPreferredSize(dim);
		aux.add(help);
		help.addActionListener(event -> controller.readInstructions());
		add(aux);

		aux = new JPanel(new FlowLayout());
		aux.setBackground(Color.LIGHT_GRAY);
		JButton load = new JButton("Load");
		load.setPreferredSize(dim);
		aux.add(load);
		load.addActionListener(event -> displaySlots("load"));

		JButton save = new JButton("Save");
		save.setPreferredSize(dim);
		aux.add(save);
		save.addActionListener(event -> displaySlots("save"));
		add(aux);
		
		dim = new Dimension(110,30);
		
		aux = new JPanel(new FlowLayout());
		aux.setBackground(Color.LIGHT_GRAY);
		JButton close = new JButton("Main Menu");
		close.setPreferredSize(dim);
		aux.add(close);
		close.addActionListener(event -> mainMenu());
		add(aux);
		
		aux = new JPanel(new FlowLayout());
		aux.setBackground(Color.LIGHT_GRAY);
		JButton closeMenu = new JButton("Close menu");
		closeMenu.setPreferredSize(dim);
		aux.add(closeMenu);
		closeMenu.addActionListener(event -> close());
		add(aux);
		
		aux = new JPanel(new FlowLayout());
		aux.setBackground(Color.LIGHT_GRAY);
		JButton quit = new JButton("Quit game");
		quit.setPreferredSize(dim);
		aux.add(quit);
		quit.addActionListener(event -> quit());
		add(aux);
	}

	private void displaySlots(String action) {
		slotsView = new FrameSlots(controller, action, this);
		setVisible(false);
	}

	private void mainMenu() {
		controller.deleteLevelView();
		
		if(slotsView!=null)
			slotsView.setVisible(false);
		levelView.setVisible(false);
		setVisible(false);
		new FrameHome(controller);
		
	}

	private void close() {
		setVisible(false);
	}
	
	private void quit() {
		System.exit(0);
	}

}
