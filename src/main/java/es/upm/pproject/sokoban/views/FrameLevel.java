package es.upm.pproject.sokoban.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.controllers.SokobanController;
import es.upm.pproject.sokoban.model.objects.GameObject;

public class FrameLevel extends JFrame {

	private static final long serialVersionUID = 1L;
	private SokobanController controller;
	private JLabel totalMovements;
	private JLabel levelMovements;
	private JPanel gamePanel;
	private JPanel scorePanel;
	private JPanel total;
	private JFrame options;

	public FrameLevel(SokobanController controller, String levelName, GameObject[][] map, int gameScore, int levelScore) {
		this.controller = controller;

		setTitle(levelName);
		setLocation(300, 60);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		total = new JPanel();
		total.setLayout(new BoxLayout(total, BoxLayout.X_AXIS));
		total.setPreferredSize(getMinimumSize());

		initialize(map, levelName, gameScore, levelScore);
		
		setVisible(true);
	}

	private void initialize(GameObject[][] map, String levelName, int gameScore, int levelScore) {
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				controller.onMove(e.getKeyChar());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//We need to include this method but we are not going to use it.
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//We need to include this method but we are not going to use it.
			}
		};

		gamePanel = new JPanel(new GridLayout(map.length, map[0].length));

		GridLayout grid = new GridLayout(4, 1);
		grid.setHgap(0);
		grid.setVgap(0);
		scorePanel = new JPanel(grid);

		initScore(levelName, gameScore, levelScore);
		initGame(map);
		gamePanel.setFocusable(true);
		gamePanel.addKeyListener(keyListener);

		total.add(gamePanel);
		total.add(scorePanel);
		add(total);
		
		setSize(getMinimumSize());
		setSize(new Dimension(getWidth() + map.length, getHeight() + map[0].length + 7));
		options = new FrameMenu(controller,this);
		options.setVisible(false);
	}

	private void initGame(GameObject[][] map) {
		gamePanel.setBackground(Color.LIGHT_GRAY);
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		flow.setHgap(0);
		for (int currentRow = 0; currentRow < map.length; currentRow++) {
			for (int currentColumn = 0; currentColumn < map[0].length; currentColumn++) {
				JPanel panel = new JPanel(flow);
				JLabel label = new JLabel(new ImageIcon(map[currentRow][currentColumn].getImage()));
				panel.add(label);
				gamePanel.add(panel);
			}
		}
	}

	private void initScore(String levelName, int gameScore, int levelScore) {
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 0, 0);

		JPanel scoreAuxPanel = new JPanel(flow);
		scoreAuxPanel.setBackground(Color.LIGHT_GRAY);
		
		JLabel levelNameLabel = new JLabel("  " + levelName);
		levelNameLabel.setForeground(Color.DARK_GRAY);
		scoreAuxPanel.add(levelNameLabel);
		
		scorePanel.add(scoreAuxPanel);

		// Level Score
		scoreAuxPanel = new JPanel(flow);
		scoreAuxPanel.setBackground(Color.LIGHT_GRAY);
		JLabel levelScoreLabel = new JLabel(" Level Score: ");
		levelScoreLabel.setForeground(Color.BLUE);
		scoreAuxPanel.add(levelScoreLabel);
		levelMovements = new JLabel();
		levelMovements.setText(Integer.toString(levelScore));
		levelMovements.setForeground(Color.DARK_GRAY);
		scoreAuxPanel.add(levelMovements);
		scorePanel.add(scoreAuxPanel);
		
		// Global Score
		scoreAuxPanel = new JPanel(flow);
		scoreAuxPanel.setBackground(Color.LIGHT_GRAY);
		JLabel gameScoreLabel = new JLabel(" Global Score: ");
		gameScoreLabel.setForeground(Color.BLUE);
		scoreAuxPanel.add(gameScoreLabel);
		totalMovements = new JLabel();
		totalMovements.setText(Integer.toString(gameScore));
		totalMovements.setForeground(Color.DARK_GRAY);
		scoreAuxPanel.add(totalMovements);
		scorePanel.add(scoreAuxPanel);
		
		// Buttons
		scoreAuxPanel = new JPanel(flow);
		scoreAuxPanel.setBackground(Color.LIGHT_GRAY);
		initButtons(scoreAuxPanel);
		scorePanel.add(scoreAuxPanel);	
	}

	private void initButtons(JPanel scoreAuxPanel) {
		Dimension dim = new Dimension(80, 30);
		
		JButton option = new JButton("Options");
		option.setFocusable(false);
		option.setPreferredSize(dim);
		scoreAuxPanel.add(option);
		option.addActionListener(event -> displayMenu());

		JButton undo = new JButton("Undo");
		undo.setFocusable(false);
		undo.setPreferredSize(dim);
		scoreAuxPanel.add(undo);
		undo.addActionListener(event -> controller.onUndo());

		JButton reset = new JButton("Reset");
		reset.setFocusable(false);
		reset.setPreferredSize(dim);
		scoreAuxPanel.add(reset);
		reset.addActionListener(event -> controller.onReset());
	}

	private void displayMenu() {
		if(!options.isVisible()) {
			options.setVisible(true);
		}
	}

	public void updateInterface(GameObject[][] map) {
		gamePanel.removeAll();
		initGame(map);
		revalidate();
		repaint();
	}

	public void updateScores(int gameScore, int levelScore) {
		totalMovements.setText(Integer.toString(gameScore));
		levelMovements.setText(Integer.toString(levelScore));
	}
}
