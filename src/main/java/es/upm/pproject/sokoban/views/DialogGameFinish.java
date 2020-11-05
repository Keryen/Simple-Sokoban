package es.upm.pproject.sokoban.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.controllers.SokobanController;

public class DialogGameFinish extends JDialog {

	private static final long serialVersionUID = 1L;
	private SokobanController controller;

	public DialogGameFinish(SokobanController controller, int gameScore) {

		this.controller = controller;

		setTitle("Game finished");
		setSize(350, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		aux.setBackground(Color.LIGHT_GRAY);
		
		JLabel finish = new JLabel("CONGRATULATIONS, YOU HAVE FINISHED THE GAME!");
		aux.add(finish);
		add(aux);
	
		aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		aux.setBackground(Color.LIGHT_GRAY);
		
		JLabel gameScoreLabel = new JLabel(" Global Score: ");
		gameScoreLabel.setForeground(Color.BLUE);
		aux.add(gameScoreLabel);
		JLabel totalMovements = new JLabel();
		totalMovements.setText(Integer.toString(gameScore));
		totalMovements.setForeground(Color.DARK_GRAY);
		aux.add(totalMovements);
		add(aux);
		
		aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		aux.setBackground(Color.LIGHT_GRAY);
		
		JButton close = new JButton("Main Menu");
		close.setPreferredSize(new Dimension(150,40));
		close.addActionListener(event -> mainMenu());
		aux.add(close);
		
		add(aux);
		
		setVisible(true);
	}
	
	private void mainMenu() {
		controller.deleteLevelView();
		
		setVisible(false);
		new FrameHome(controller);
	}
}
