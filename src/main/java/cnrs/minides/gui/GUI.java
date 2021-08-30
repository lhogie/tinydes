package cnrs.minides.gui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class GUI extends JPanel
{
	JComponent systemView = new JLabel("system");
	JSlider slider = new JSlider();
	
	public GUI()
	{
		super(new BorderLayout());
		
		add(BorderLayout.CENTER, systemView);
		add(BorderLayout.SOUTH, slider);
	}
	
}
