package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import engine.Game;

public class ControlTimer implements ActionListener {

	Game g;
	JFrame f;
	ControlGroup group;

	public ControlTimer(ControlGroup group) {

		this.group = group;
		this.g = group.getG();
		this.f = group.getF();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		g.increaseTime();
		f.repaint();
		group.getE().getScoreLabel().setText(g.getScore() + "");

	}

}
