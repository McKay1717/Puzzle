package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import engine.Game;
import ui.EcranDeJeux;

public class ButtonMenuPrincipalListener implements ActionListener {

	Game g;
	JFrame f;
	ControlGroup group;
	public ButtonMenuPrincipalListener(ControlGroup group) {

		
		this.group = group;
		this.g = group.getG();
		this.f = group.getF();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton src = (JButton) arg0.getSource();
		switch (src.getLabel()) {
		case "3x3":
			g.StartGame(3);
			
			break;

		case "4x4":
			g.StartGame(4);
			break;

		case "5x5":
			g.StartGame(5);
			break;

		default:
			break;
		}
		g.mixTab(100);
		group.setE(new EcranDeJeux(g, group.getButtonListener(), group.getControlMenu()));
		f.setSize(500, 500);
		group.getE().setSize(f.getSize());;
		f.setContentPane(group.getE());
		group.getE().addBar();
		group.getE().callUpdater();
		
	}

}
