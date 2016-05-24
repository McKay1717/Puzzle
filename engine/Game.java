package engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class Game {

	private int[][] gameTab;
	private int size, x, y;
	private final String imageName = "img2.png";
	private int bestScore = -1;
	private String bestPlayer = "";
	private final int score = 500000;
	private int moveCount = 0;
	private int numberOfSecond = 0;
	private ArrayList<Integer> scoreList = new ArrayList<Integer>();
	private ArrayList<String> playerList = new ArrayList<String>();

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isSolved() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (gameTab[j][i] == 0)
					continue;
				if (gameTab[j][i] != 1 + j * size + i)
					return false;
			}
		}
		return true;
	}

	public void mixTab(int numberofMove) {
		while (numberofMove > 0) {
			int tmp =  (int) Math.floor(Math.random() * 4);
			if (move(tmp))
				numberofMove--;
		}
		moveCount = 0;
	}

	public boolean move(int dir) {
		int tmp;
		switch (dir) {
		// Haut
		case 0: {
			if (y < 1)
				return false;
			tmp = gameTab[y][x];
			gameTab[y][x] = gameTab[y - 1][x];
			gameTab[y - 1][x] = tmp;
			y--;
			break;
		}
		// Gauche
		case 1: {
			if (x < 1)
				return false;
			tmp = gameTab[y][x];
			gameTab[y][x] = gameTab[y][x - 1];
			gameTab[y][x - 1] = tmp;
			x--;
			break;
		}
		// Arriére
		case 2: {
			if (y > size - 2)
				return false;
			tmp = gameTab[y][x];
			gameTab[y][x] = gameTab[y + 1][x];
			gameTab[y + 1][x] = tmp;
			y++;
			break;
		}
		// Droite
		case 3: {
			if (x > size - 2)
				return false;
			tmp = gameTab[y][x];
			gameTab[y][x] = gameTab[y][x + 1];
			gameTab[y][x + 1] = tmp;
			x++;
			break;
		}
		}
		moveCount++;
		return true;
	}

	public void StartGame(int size) {
		setSize(size);
		gameTab = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				gameTab[j][i] = 1 + j * size + i;
			}

		}
		x = size - 1;
		y = size - 1;
		gameTab[x][y] = 0;
	}

	public int[][] getGameTab() {
		return gameTab;
	}

	public int[] search(int id) {
		for (int i = 0; i < gameTab.length; i++) {
			for (int j = 0; j < gameTab[i].length; j++) {
				if (gameTab[i][j] == id)
					return new int[] { i, j };
			}
		}
		return null;
	}

	public String getImageName() {
		return imageName;
	}

	public BufferedImage[] loadImage(int w, int h) throws IOException {
		BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/" + getImageName()));
		Image toolkitImage = image.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
		int width = toolkitImage.getWidth(null);
		int height = toolkitImage.getHeight(null);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.drawImage(toolkitImage, 0, 0, null);
		g.dispose();
		int chunkWidth = image.getWidth() / getSize();

		int chunkHeight = (image.getHeight() + 10) / getSize();
		int count = 0;
		BufferedImage imgs[] = new BufferedImage[getSize() * getSize()];
		for (int x = 0; x < getSize(); x++) {
			for (int y = 0; y < getSize(); y++) {
				// Initialize the image array with image chunks
				imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

				// draws the image chunk
				Graphics2D gr = imgs[count++].createGraphics();
				gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x,
						chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
				gr.dispose();
			}
		}
		return imgs;
	}

	public void readScore() throws IOException {
		BufferedReader br = null;
		int tmp = -1;
		String player = "";
		String line;

		br = new BufferedReader(new FileReader(getSize() + "score.txt"));

		while ((line = br.readLine()) != null) {
			int tempScore = Integer.parseInt(line.split(":")[0]);
			String tempAuteur = line.split(":")[1];
			scoreList.add(tempScore);
			playerList.add(tempAuteur);
			if (tempScore > tmp) {
				tmp = tempScore;
				player = tempAuteur;
			}
		}
		bestScore = tmp;
		bestPlayer = player;

	}

	public void writeToFile() throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter(getSize() + "score.txt"));
		Iterator<String> player = playerList.iterator();
		Iterator<Integer> scoreI = scoreList.iterator();

		while (player.hasNext() && scoreI.hasNext()) {
			br.write(scoreI.next() + ":" + player.next());
		}
		br.close();
	}

	public int getBestScore() {

		try {
			readScore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bestScore;
	}

	public String getBestPlayer() {

		try {
			readScore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bestPlayer;
	}

	public int getScore() {
		return (score - numberOfSecond - moveCount);
	}

	public int getNumberOfSecond() {
		return numberOfSecond;
	}

	public void setNumberOfSecond(int numberOfSecond) {
		this.numberOfSecond = numberOfSecond;
	}

	public ArrayList<Integer> getScoreList() {
		return scoreList;
	}

	public ArrayList<String> getPlayerList() {
		return playerList;
	}

	public void addScore(String nom) {
		playerList.add(nom);
		scoreList.add(score);
		try {
			writeToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void increaseTime() {
		numberOfSecond++;
	}
}
