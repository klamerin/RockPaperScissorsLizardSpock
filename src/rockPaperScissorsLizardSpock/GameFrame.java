package rockPaperScissorsLizardSpock;

import javax.swing.JFrame;

public class GameFrame {
	public static void main(String[] args) {

		JFrame gameFrame = new JFrame();
		gameFrame.setSize(810, 600);
		gameFrame.setTitle("Rock-Paper-Scissors-Lizard-Spock");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		GamePanel gamePanel = new GamePanel();
		gameFrame.add(gamePanel);
		gameFrame.setVisible(true);
	}
}
