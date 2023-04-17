import javax.swing.*;
import java.awt.*;


public class AppFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public AppFrame() {
		setTitle("Animation");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setSize(screenSize.width,screenSize.height);
		//setSize(1400,800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initGUI();
	}
	
	public void initGUI() {
		Animation animArea = new Animation();
		add(animArea);
		this.addKeyListener(animArea);
	}
}
