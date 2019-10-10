//Luke Jacobs
// JPanParticle
// JPanel formatted for particle
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class JPanParticle extends JPanel
{
	private JLabel label, lblCharge;
	private int index, charge;
	public JPanParticle()
	{
		setLayout(new BorderLayout());
		label = new JLabel("Particle "+(index));
		add(label,BorderLayout.NORTH);
		setBackground(Color.RED);
		setOpaque(true);
		setSize(154,50);
		setVisible(true);
	}
	public JPanParticle(int ind,int charge)
	{
		index = ind;
		this.charge = charge;
		setLayout(new BorderLayout());
		label = new JLabel("Particle "+(index));
		lblCharge = new JLabel("Charge: "+charge+" µC");
		add(label,BorderLayout.NORTH);
		add(lblCharge);
		color();
		setBorder(BorderFactory.createLineBorder(Color.black));
		setOpaque(true);
		setSize(154,50);
		setVisible(true);
	}
	public void color()
	{
		if(index == 1)
		{
			setBackground(Color.RED);
		}
		else if(index == 2)
		{
			setBackground(Color.YELLOW);
		}
	}
	public String getName()
	{
		return label.getText();
	}

}