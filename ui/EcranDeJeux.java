package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.ControlMenu;
import control.GameButtonListener;
import engine.Game;

public class EcranDeJeux extends JPanel {
	private JPanel grille = new JPanel();
	private JPanel barre_de_gauche = new JPanel();
	private BorderLayout layout = new BorderLayout();
	private GridLayout gridLayout;
	private JButton[][] buttons;
	private Game game;
	private GameButtonListener gameButtonListener;
	private JMenuBar menuBar = new JMenuBar();

	private JMenu option = new JMenu("Options");

	private JMenuItem x3 = new JMenuItem("3x3");

	private JMenuItem x4 = new JMenuItem("4x4");

	private JMenuItem x5 = new JMenuItem("5x5");

	private JMenuItem score = new JMenuItem("Meillieur Score");

	private JLabel scoreLabel;

	public EcranDeJeux(Game game, GameButtonListener buttonListener, ControlMenu menu) {
		this.game = game;
		scoreLabel = new JLabel("" + game.getScore());
		barre_de_gauche.setLayout(new BoxLayout(barre_de_gauche, BoxLayout.Y_AXIS));
		gameButtonListener = buttonListener;
		int size = this.game.getSize();
		buttons = new JButton[size][size];
		gridLayout = new GridLayout(size, size, 10, 10);
		this.setLayout(layout);
		grille.setLayout(gridLayout);
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {

				buttons[i][j] = new JButton();

				buttons[i][j].setActionCommand(game.getGameTab()[i][j] + "");
				buttons[i][j].addActionListener(gameButtonListener);
				buttons[i][j].setBorder(BorderFactory.createEmptyBorder());
				grille.add(buttons[i][j]);
			}
		}
		grille.setVisible(true);
		option.add(x3);
		option.add(x4);
		option.add(x5);
		option.addSeparator();
		option.add(score);
		menuBar.add(option);
		x3.addActionListener(menu);
		x4.addActionListener(menu);
		x5.addActionListener(menu);
		score.addActionListener(menu);
		barre_de_gauche.add(new JLabel("Score  :"));
		barre_de_gauche.add(scoreLabel);
		this.add(grille, BorderLayout.CENTER);
		this.add(barre_de_gauche, BorderLayout.WEST);
		gameButtonListener.getTimer().start();
	}

	public JButton[][] getButtons() {
		return buttons;
	}

	public void callUpdater() {
		gameButtonListener.UpdateIcon();

	}

	public void addBar() {
		((JFrame) SwingUtilities.getWindowAncestor(this)).setJMenuBar(menuBar);
	}

	public JLabel getScoreLabel() {
		return scoreLabel;
	}

	public void setScoreLabel(JLabel scoreLabel) {
		this.scoreLabel = scoreLabel;
	}
}
