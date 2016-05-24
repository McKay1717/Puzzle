package control;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import engine.Game;
import ui.EcranDeJeux;

public class GameButtonListener implements ActionListener {
	Game g;
	JFrame f;
	ControlGroup group;
	int x = -1;
	int y = -1;

	public GameButtonListener(ControlGroup group) {

		this.group = group;
		this.g = group.getG();
		this.f = group.getF();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton button = (JButton) arg0.getSource();
		int buttonID = Integer.parseInt(button.getActionCommand());
		int[] result = g.search(buttonID);
		if (buttonID != 0) {
			x = result[0];
			y = result[1];
		} else if (x != -1) {
			int tmpX = x - result[0];
			int tmpY = y - result[1];
			if (tmpX == 0 && tmpY == 1) {
				g.move(3);
			}
			if (tmpX == 0 && tmpY == -1) {
				g.move(1);
			}
			if (tmpX == -1 && tmpY == 0) {
				g.move(0);
			}
			if (tmpX == 1 && tmpY == 0) {
				g.move(2);
			}

		}
		JButton[][] buttons = group.getE().getButtons();
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j].setActionCommand(g.getPuzzle()[i][j] + "");
			}
		}
		UpdateIcon();
		group.getE().getScoreLabel().setText(g.getScore()+"");
		group.getE().repaint();
		if (g.isSolved()) {
			this.getTimer().stop();
			if (g.getScore() > g.getBestScore()) {

				String nom = null;

				if (g.getBestScore() != -1) {
					nom = JOptionPane.showInputDialog(null, "Vous avez battu le meillieur score précédent : "
							+ g.getBestScore() + " par " + g.getBestPlayer(), "Nouveau Record",
							JOptionPane.QUESTION_MESSAGE);
				} else {
					nom = JOptionPane.showInputDialog(null, "Vous avez réalisé le 1er record !", "Nouveau Record",
							JOptionPane.QUESTION_MESSAGE);
				}
				g.addScore(nom);

			}
			group.getF().setContentPane(group.getMenuPrincipal());
			group.getF().repaint();
			group.getF().setJMenuBar(null);
		}
	}

	public void UpdateIcon() {

		EcranDeJeux e = group.getE();
		try {

			BufferedImage[] imgs = g.loadImage(e.getWidth(), e.getHeight());
			JButton[][] buttons = group.getE().getButtons();
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[i].length; j++) {
					buttons[i][j].setIcon(null);
					if (g.getPuzzle()[i][j] == 0) {
						buttons[i][j].setOpaque(false);
						buttons[i][j].setContentAreaFilled(false);
						buttons[i][j].setBorderPainted(false);
					}
				}
			}
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[i].length; j++) {

					int pos = Integer.parseInt(buttons[i][j].getActionCommand());
					if (pos != 0)
						buttons[i][j]
								.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgs[pos].getSource())));
				}
			}

		} catch (IOException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}

	}
	public Timer getTimer()
	{
		return group.getTimer();
	}

}
