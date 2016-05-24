package control;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import engine.Game;
import ui.EcranDeJeux;

public class ControlMenu implements ActionListener {

	Game g;
	JFrame f;
	ControlGroup group;

	public ControlMenu(ControlGroup group) {

		this.group = group;
		this.g = group.getG();
		this.f = group.getF();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		switch (item.getLabel()) {
		case "3x3":
			g.StartGame(3);
			g.mix(100);

			break;

		case "4x4":
			g.StartGame(4);
			g.mix(100);
			break;

		case "5x5":
			g.StartGame(5);
			g.mix(100);
			break;

		case "Meillieur Score":
			if(g.getBestScore()!= -1)
			{
			JOptionPane.showMessageDialog(null, "Meillieur score :\n" + g.getBestPlayer() + " : " + g.getBestScore());

			}else
			{
				JOptionPane.showMessageDialog(null, "Meillieur score :\nAucun");
			}
			break;
		}

		group.setE(new EcranDeJeux(g, group.getButtonListener(), group.getControlMenu()));
		f.setSize(500, 500);
		group.getE().setSize(f.getSize());
		;
		f.setContentPane(group.getE());
		group.getE().addBar();
		group.getE().callUpdater();

	}

}
