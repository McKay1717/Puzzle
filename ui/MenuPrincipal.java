package ui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ButtonMenuPrincipalListener;

public class MenuPrincipal extends JPanel {

	BoxLayout fl = new BoxLayout(this, BoxLayout.Y_AXIS);
	JButton button3 = new JButton("3x3");
	JButton button4 = new JButton("4x4");
	JButton button5 = new JButton("5x5");
	JLabel label = new JLabel("Puzzle");
	ButtonMenuPrincipalListener buttonMenuPrincipalListener;
	int fontSize = 50;

	public MenuPrincipal(ButtonMenuPrincipalListener listener) {

		buttonMenuPrincipalListener = listener;
		this.setLayout(fl);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setFont(new Font(label.getFont().getName(), fontSize, fontSize));
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		button3.setFont(new Font(button3.getFont().getName(), fontSize / 2, fontSize / 2));
		button3.addActionListener(buttonMenuPrincipalListener);
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		button4.setFont(new Font(button4.getFont().getName(), fontSize / 2, fontSize / 2));
		button4.addActionListener(buttonMenuPrincipalListener);
		button5.setAlignmentX(Component.CENTER_ALIGNMENT);
		button5.setFont(new Font(button5.getFont().getName(), fontSize / 2, fontSize / 2));
		button5.addActionListener(buttonMenuPrincipalListener);
		this.add(label);
		this.add(button3);
		this.add(button4);
		this.add(button5);
		this.setVisible(true);
	}

}
