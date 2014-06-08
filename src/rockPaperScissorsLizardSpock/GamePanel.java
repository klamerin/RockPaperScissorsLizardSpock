package rockPaperScissorsLizardSpock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	String[] choices = { "rock", "paper", "scissors", "lizard", "Spock" };
	String[] fileNames = { "img//rock.jpg", "img//paper.jpg", "img//scissors.jpg",
			"img//lizard.jpg", "img//Spock.jpg" };

	Signs paper;
	Signs rock;
	Signs scissors;
	Signs lizard;
	Signs spock;
	
	JButton exit;

	JLabel result;
	JLabel sheldonScore;
	JLabel yourScore;
	JLabel sheldonWin;

	Random rnd = new Random();
	int playerWins = 0;
	int compWins = 0;
	int ties = 0;
	String pChoice;
	
	public GamePanel() {

		setLayout(null);

		exit = new JButton("EXIT");
		exit.setLocation(700, 500);
		exit.setSize(70, 30);
		exit.setOpaque(false);
		exit.setVisible(true);
		exit.addActionListener(new exitListener());
		add(exit);

		rock = new Signs(10, choices[0], fileNames[0]);
		rock.addActionListener(new rockListener());
		add(rock);

		paper = new Signs(170, choices[1], fileNames[1]);
		paper.addActionListener(new paperListener());
		add(paper);

		scissors = new Signs(330, choices[2], fileNames[2]);
		scissors.addActionListener(new scissorsListener());
		add(scissors);

		lizard = new Signs(490, choices[3], fileNames[3]);
		lizard.addActionListener(new lizardListener());
		add(lizard);

		spock = new Signs(650, choices[4], fileNames[4]);
		spock.addActionListener(new spockListener());
		add(spock);

		result = new JLabel();
		result.setSize(700, 30);
		result.setLocation(50, 400);
		result.setOpaque(false);
		result.setVisible(true);
		result.setHorizontalTextPosition(JLabel.CENTER);
		result.setFont(new Font("Serif", Font.BOLD, 30));
		result.setForeground(Color.WHITE);
		add(result);

		yourScore = new JLabel();
		yourScore.setSize(180, 15);
		yourScore.setLocation(50, 500);
		yourScore.setOpaque(false);
		yourScore.setVisible(true);
		yourScore.setFont(new Font("Serif", Font.BOLD, 15));
		yourScore.setForeground(Color.WHITE);
		add(yourScore);

		sheldonScore = new JLabel();
		sheldonScore.setSize(180, 15);
		sheldonScore.setLocation(50, 530);
		sheldonScore.setOpaque(false);
		sheldonScore.setVisible(true);
		sheldonScore.setFont(new Font("Serif", Font.BOLD, 15));
		sheldonScore.setForeground(Color.WHITE);
		add(sheldonScore);

		sheldonWin = new JLabel();
		sheldonWin.setSize(810, 600);
		sheldonWin.setLocation(0, 0);
		ImageIcon sheldonWinIcon = new ImageIcon("img//sheldonWins.jpg");
		sheldonWin.setIcon(sheldonWinIcon);
		sheldonWin.setOpaque(true);
		sheldonWin.setVisible(false);
		add(sheldonWin);

		playSound();

	}

	// draw background
	public void paintComponent(Graphics g) {
		Image background = new ImageIcon("img//bazinga.jpg").getImage();
		g.drawImage(background, 0, 0, this);
	}

	//set info label method that displays game results and comments
	public void setText(String sign) {
		result.setText(sign);
	}
	
	//set score labels method
	public void setScore() {
		yourScore.setText("Player score: " + playerWins);
		sheldonScore.setText("Sheldon's score: " + compWins);
	}

	

	// exit the the game if "exit" is submitted and calculate winner
	private class exitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (playerWins > compWins) {
				setText("You WON, CHEATER!");
			} else if (playerWins < compWins) {
				sheldonWin.setVisible(true);
				paper.setVisible(false);
				rock.setVisible(false);
				scissors.setVisible(false);
				lizard.setVisible(false);
				spock.setVisible(false);
				sheldonScore.setVisible(false);
				yourScore.setVisible(false);
				result.setVisible(false);
				exit.setVisible(false);
			} else {
				setText("The result is equal!");
			}

		}
	}
	
	//set JButton listeners for each button/sign
	private class rockListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pChoice = rock.name;
			play();
		}
	}

	private class paperListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pChoice = paper.name;
			play();
		}
	}

	private class scissorsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pChoice = scissors.name;
			play();
		}
	}

	private class lizardListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pChoice = lizard.name;
			play();
		}
	}

	private class spockListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pChoice = spock.name;
			play();
		}
	}

	//set background music
	public void playSound() {
		String file = "img//The Big Bang Theory - Full.wav";
		File soundFile;
		Clip clip;
		AudioInputStream audioIn;
		soundFile = new File(file);
		try {
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	//set game logic
	public void play() {
		
		// generate random number between 0 and 4
		int randNumb = rnd.nextInt(5); 
		String cChoice = choices[randNumb];

		// check for bad input or tie
		if (!(pChoice.equals("rock")) && !(pChoice.equals("paper"))
				&& !(pChoice.equals("scissors")) && !(pChoice.equals("lizard"))
				&& !(pChoice.equals("Spock")) && !(pChoice.equals("exit"))) {
			setText("Invalid player input.");
		} else if (pChoice.equals(cChoice)) {
			ties++;
			setText("It's a tie!");
		}

		// check all variations if player chooses rock
		else if (pChoice.equals("rock")) {
			if (cChoice.equals("scissors")) {
				playerWins++;
				setScore();
				setText("Rock crushes scissors. You win!!");
			} else if (cChoice.equals("lizard")) {
				playerWins++;
				setScore();
				setText("Rock crushes lizard. You win!!");
			} else if (cChoice.equals("paper")) {
				compWins++;
				setScore();
				setText("Paper covers rock. Sheldon wins!!");
			} else if (cChoice.equals("Spock")) {
				compWins++;
				setScore();
				setText("Spock vaporizes rock. Sheldon wins!!");
			}

			// check all variations if player chooses paper
		} else if (pChoice.equals("paper")) {
			if (cChoice.equals("rock")) {
				playerWins++;
				setScore();
				setText("Paper covers rock. You win!!");
			} else if (cChoice.equals("Spock")) {
				playerWins++;
				setScore();
				setText("Paper disproves Spock. You win!!");
			} else if (cChoice.equals("scissors")) {
				compWins++;
				setScore();
				setText("Scissors cut paper. Sheldon wins!!");
			} else if (cChoice.equals("lizard")) {
				compWins++;
				setScore();
				setText("Lizard eats paper. Sheldon wins!!");
			}

			// check all variations if player chooses scissors
		} else if (pChoice.equals("scissors")) {
			if (cChoice.equals("paper")) {
				playerWins++;
				setScore();
				setText("Scissors cut paper. You win!!");
			} else if (cChoice.equals("lizard")) {
				playerWins++;
				setScore();
				setText("Scissors decapitate lizard. You win!!");
			} else if (cChoice.equals("rock")) {
				compWins++;
				setScore();
				setText("Rock crushes scissors. Sheldon wins!!");
			} else if (cChoice.equals("Spock")) {
				compWins++;
				setScore();
				setText("Spock smashes scissors. Sheldon wins!!");
			}

			// check all variations if player chooses lizard
		} else if (pChoice.equals("lizard")) {
			if (cChoice.equals("paper")) {
				playerWins++;
				setScore();
				setText("Lizard eats paper. You win!!");
			} else if (cChoice.equals("Spock")) {
				playerWins++;
				setScore();
				setText("Lizard poisons Spock. You win!!");
			} else if (cChoice.equals("rock")) {
				compWins++;
				setScore();
				setText("Rock crushes lizard. Sheldon wins!!");
			} else if (cChoice.equals("scissors")) {
				compWins++;
				setScore();
				setText("Scissors decapitate lizard. Sheldon wins!!");
			}

			// check all variations if player chooses Spock
		} else if (pChoice.equals("Spock")) {
			if (cChoice.equals("rock")) {
				playerWins++;
				setScore();
				setText("Spock vaporizes rock. You win!!");
			} else if (cChoice.equals("scissors")) {
				playerWins++;
				setScore();
				setText("Spock smashes scissors. You win!!");
			} else if (cChoice.equals("paper")) {
				compWins++;
				setScore();
				setText("Paper disproves Spock. Sheldon wins!!");
			} else if (cChoice.equals("lizard")) {
				compWins++;
				setScore();
				setText("Lizard poisons Spock. Sheldon wins!!");
			}
		}

	}
}
