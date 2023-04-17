import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class Animation extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Image slonce, ziemia, mars, jowisz, neptun, ksiezyc;
	private Thread animThreat;
	private int x1 = 680, x2, x3, x4, x5, x6, y1 = 350, y2, y3, y4, y5, y6;
	double a1 = 0.5, a2 = 0.4, a3 = 0.3, a4 = 0.2, a5 = 0, a6 = 0.1;

	private final int refreshRate = 30;
	private boolean isPaused = false, orbity = true;

	public Animation() {
		setBackground(Color.BLACK);
		setDoubleBuffered(true);

		try {
			slonce = ImageIO.read(this.getClass().getResource("slonce.png"));
			ziemia = ImageIO.read(this.getClass().getResource("ziemia.png"));
			mars = ImageIO.read(this.getClass().getResource("mars.png"));
			jowisz = ImageIO.read(this.getClass().getResource("jupiter.png"));
			neptun = ImageIO.read(this.getClass().getResource("neptune.png"));
			ksiezyc = ImageIO.read(this.getClass().getResource("ksiezyc.png"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		animThreat = new Thread(this);
		animThreat.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(slonce, x1, y1, this);
		g2.drawImage(ziemia, x2, y2, this);
		g2.drawImage(mars, x3, y3, this);
		g2.drawImage(jowisz, x4, y4, this);
		g2.drawImage(neptun, x5, y5, this);
		g2.drawImage(ksiezyc, x6, y6, this);

		g2.setColor(Color.WHITE);
		if (orbity) {
			g2.drawOval(x1 - 50, y1 - 50, 320, 320);
			g2.drawOval(x1 - 130, y1 - 130, 465, 465);
			g2.drawOval(x1 - 230, y1 - 230, 765, 765);
			g2.drawOval(x1 - 375, y1 - 375, 975, 975);
			g2.drawOval(x2 - 15, y2 - 15, 100, 100);
		}

		g.dispose();

	}

	public void movement() {

		a1 += 0.1 + a5;

		int x = 80;
		int y = x;

		x2 = (int) (x1 + x + 150 * Math.cos(a1));
		y2 = (int) (y1 + y + 150 * Math.sin(a1));

		a2 += 0.05 + a5;

		x3 = (int) (x1 + x + 220 * Math.cos(a2));
		y3 = (int) (y1 + y + 220 * Math.sin(a2));

		a3 += 0.03 + a5;

		x4 = (int) (x1 + x + 370 * Math.cos(a3));
		y4 = (int) (y1 + y + 370 * Math.sin(a3));

		a4 += 0.02 + a5;

		x5 = (int) (x1 + x + 500 * Math.cos(a4));
		y5 = (int) (y1 + y + 500 * Math.sin(a4));

		a6 += 0.01 + a5;

		x6 = (int) (x2 + 15 + 50 * Math.cos(a6));
		y6 = (int) (y2 + 15 + 50 * Math.sin(a6));

	}

	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {

			while (isPaused) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					System.out.println("interrupted");
				}
			}

			movement();
			repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = refreshRate - timeDiff;

			if (sleep < 0)
				sleep = 2;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}

			beforeTime = System.currentTimeMillis();
		}
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			isPaused = !isPaused;
		}

		if (key == KeyEvent.VK_F1) {
			a5 += 0.01;
		}
		if (key == KeyEvent.VK_F2) {
			a5 -= 0.01;

		}

		if (key == KeyEvent.VK_F3) {
			orbity = !orbity;
		}

		if (key == KeyEvent.VK_RIGHT) {
			x1 += 5;
		}
		if (key == KeyEvent.VK_LEFT) {
			x1 -= 5;
		}

		if (key == KeyEvent.VK_UP) {
			y1 -= 5;
		}

		if (key == KeyEvent.VK_DOWN) {
			y1 += 5;
		}

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

}