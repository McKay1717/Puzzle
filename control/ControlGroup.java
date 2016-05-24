package control;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import engine.Game;
import ui.EcranDeJeux;
import ui.MenuPrincipal;

public class ControlGroup {

	private Game g;
	private ButtonMenuPrincipalListener buttonMenuPrincipalListener;
	private GameButtonListener buttonListener;
	private MenuPrincipal menuPrincipal;
	private EcranDeJeux e;
	private JFrame f;
	private ControlMenu controlMenu;
	private ControlTimer controlTimer;
	private Timer timer;

	public ControlGroup(Game g) {
		this.g = g;
		f = new JFrame("Puzzle");
		controlTimer = new ControlTimer(this);
		timer = new Timer(1000, controlTimer);
		buttonMenuPrincipalListener = new ButtonMenuPrincipalListener(this);
		menuPrincipal = new MenuPrincipal(buttonMenuPrincipalListener);
		buttonListener = new GameButtonListener(this);
		controlMenu = new ControlMenu(this);

		f.setContentPane(menuPrincipal);
		f.setSize(500, 250);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(true);
		f.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (e != null)
					buttonListener.UpdateIcon();
			}
		});
	}

	public Game getG() {
		return g;
	}

	public ButtonMenuPrincipalListener getButtonMenuPrincipalListener() {
		return buttonMenuPrincipalListener;
	}

	public GameButtonListener getButtonListener() {
		return buttonListener;
	}

	public MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}

	public EcranDeJeux getE() {
		return e;
	}

	public JFrame getF() {
		return f;
	}

	public void setE(EcranDeJeux e) {
		this.e = e;
	}

	public ControlMenu getControlMenu() {
		return controlMenu;
	}

	public Timer getTimer() {
		return timer;
	}

}
