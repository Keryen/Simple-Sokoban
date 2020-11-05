package es.upm.pproject.sokoban.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.controllers.SokobanController;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private SokobanController controller;
	JComboBox<String> combo;

	public FrameHome(SokobanController controller) {

		this.controller = controller;

		setTitle("PProject Sokoban");
		setSize(300, 275);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		initialize();

		setVisible(true);
	}

	private void initialize() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		panel.add(Box.createRigidArea(new Dimension(0, this.getWidth() / 8))); // To separate components
		
		JLabel label = new JLabel();
		label.setFont(new Font("Bazooka", Font.BOLD, 30));
		label.setText("SOKOBAN");
		label.setAlignmentX(CENTER_ALIGNMENT);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.add(label);
		
		panel.add(Box.createRigidArea(new Dimension(0, this.getWidth() / 10))); // To separate the text from the buttons	

		JButton newGame = new JButton("New Game");
		newGame.setAlignmentX(CENTER_ALIGNMENT);
		newGame.addActionListener(event -> createLevel());
		panel.add(newGame);
		
		JLabel labellvl = new JLabel();
		labellvl.setAlignmentX(CENTER_ALIGNMENT);
		labellvl.setText("or");
		panel.add(labellvl);
		
		JButton load = new JButton("Load Game");
		load.setAlignmentX(CENTER_ALIGNMENT);
		load.addActionListener(event -> displaySlots());		
		panel.add(load);
		
		JPanel aux = new JPanel(new FlowLayout(FlowLayout.LEFT));
		aux.setBackground(Color.LIGHT_GRAY);
		
		JLabel select = new JLabel ("Select your skin: ");
		aux.add(Box.createRigidArea(new Dimension(0, this.getWidth() / 6)));
		aux.add(select);
		
		String [] players = {"Default","Chocobo","Mario", "Red","Sans"};
		combo = new JComboBox<>(players);
		combo.setSelectedIndex(0);
		aux.add(combo);
		
		panel.add(aux);
		
		add(panel);
	}

	private void createLevel(){
		setVisible(false);
		controller.setTexture(combo.getSelectedIndex());
		controller.createLevel(0, true);
	}
	
	private void displaySlots() {
		setVisible(false);
		new FrameSlots(controller, "load", this);
	}
}
