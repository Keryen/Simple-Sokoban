package es.upm.pproject.sokoban.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogPopUp extends JDialog {

	private static final long serialVersionUID = 1L;

	public DialogPopUp (String title, String msg, Color color) {

		setTitle(title);
		if (title.equals("Sokoban Instructions")) {
			setSize (800, 300);
		}
		else {
			setSize(400, 150);
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		aux.setBackground(Color.LIGHT_GRAY);
		
		JLabel finish = new JLabel(msg);
		finish.setForeground(color);
		aux.add(finish);
		add(aux);
	
		aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		aux.setBackground(Color.LIGHT_GRAY);
		
		JButton close = new JButton("Close");
		close.setPreferredSize(new Dimension(150,40));
		close.addActionListener(event -> setVisible(false));
		aux.add(close);
		add(aux);
		
		setVisible(true);
	}
}
