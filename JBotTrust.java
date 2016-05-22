import java.awt.BorderLayout;
import javax.swing.*;

public class JBotTrust
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Bot Trust Simulation");
		frame.setLayout(new BorderLayout());
		frame.setSize(1165,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		Simulation.firstTest();
		
		Simulation sim = new Simulation();
		frame.add(sim, BorderLayout.CENTER);		
		frame.setVisible(true);
		 
		sim.start();
	}

}
