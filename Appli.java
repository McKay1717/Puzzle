import control.ControlGroup;
import engine.Game;

class Appli {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Game g = new Game();
				ControlGroup control = new ControlGroup(g);
			}
		});
	}
}