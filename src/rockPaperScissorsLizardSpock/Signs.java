package rockPaperScissorsLizardSpock;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Signs extends JButton {

	public String name;

	public Signs(int x, String name, String fileName) {
		this.name = name;
		setLocation(x, 30);
		setSize(150, 150);
		ImageIcon buttonIcon = new ImageIcon(fileName);
		setIcon(buttonIcon);
		setOpaque(true);
		setVisible(true);
	}
}
