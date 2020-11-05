package es.upm.pproject.sokoban.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.controllers.SokobanController;

public class DialogNextLevel extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private SokobanController controller;

	public DialogNextLevel(SokobanController controller, String levelName, int currentLevel,int levelScore, int gameScore) {
		this.controller = controller;

		setTitle("Level complete!");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(5,1));
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		
		initialize(levelName, currentLevel, levelScore, gameScore);

		setVisible(true);
	}

	private void initialize(String levelName, int currentLevel, int levelScore, int gameScore) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel label = new JLabel("CONGRATULATIONS");
		label.setFont(new Font("Bazooka", Font.BOLD, 25));
		panel.add(label);
		add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.LIGHT_GRAY);
		JLabel level = new JLabel("You have completed: " + levelName);
		panel.add(level);
		add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.LIGHT_GRAY);
		JLabel score = new JLabel("Level Score: " + levelScore);
		panel.add(score);
		add(panel);

		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.LIGHT_GRAY);
		JLabel totalScore = new JLabel("Game Score: " + gameScore);
		panel.add(totalScore);
		add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.LIGHT_GRAY);
		JButton nextLevel = new JButton("Next Level");
		nextLevel.addActionListener(event -> nextLevel(currentLevel + 1));
		nextLevel.setPreferredSize(new Dimension(150,40));
		panel.add(nextLevel);
		add(panel);
		
	}

	private void nextLevel(int next) {
		controller.createLevel(next, false);
		setVisible(false);

	}
}

