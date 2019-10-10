//Collin Brooks & Luke Jacobs
//ParticleGUI
// GUI runner for electromagnetic fields
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ParticleGUI extends JFrame implements MouseListener,MouseMotionListener
{
	//worked on by Luke Jacobs
	private ButtonListener listener;
	private JPanel panel1,panel2,panel3,panel4;
	private JTabbedPane tPane;
	private JButton btnParticle,btnEField, btnStart, btnStop, btnReset, btnExit;
	private ImageIcon icoRed,icoYellow;
	private JLabel particle;
	private ArrayList<Particle> particleList;
	private ArrayList<JPanParticle> JPanelList;
	private ArrayList<JLabel> lblParticleList;
	private ArrayList<Boolean> particleSelector;//Worked on by Collin Brooks
	private Timer time;
	private TimerListener tListen;
	private int pNum;
	private ElectricField e;
	public ParticleGUI()//Worked on by Luke Jacobs
	{
		super("Particle Simulator");
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		tListen = new TimerListener();
		time = new Timer(33,tListen);
		listener = new ButtonListener();
		tPane = new JTabbedPane();
		btnParticle = new JButton("Create Particle");
		btnEField = new JButton("Create Electric Field");
		btnStart = new JButton("Start Simulator");
		btnStop = new JButton("Stop Simulator");
		btnStop.setEnabled(false);
		btnReset = new JButton("RESET");
		btnReset.setEnabled(false);
		btnExit = new JButton("Exit");
		icoRed = new ImageIcon("red.png");
		icoYellow = new ImageIcon("yellow.png");
		particle = new JLabel(icoRed);
		e = new ElectricField(0,"N");
		//mouse listeners
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		//button listeners
		btnParticle.addActionListener(listener);
		btnEField.addActionListener(listener);
		btnStart.addActionListener(listener);
		btnStop.addActionListener(listener);
		btnReset.addActionListener(listener);
		btnExit.addActionListener(listener);
		//panels
		panel1 = new JPanel();
		panel1.setLayout(null);
        panel1.setBackground(Color.BLACK);
        panel1.setOpaque(true);
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5,1));
		panel2.add(btnStart);
		panel2.add(btnStop);
		panel2.add(btnReset);
		panel2.add(new JLabel(""));
		panel2.add(btnExit);
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(5,1));
		panel4 = new JPanel();
		panel4.setLayout(new GridLayout(5,1));
		panel4.add(btnEField);
		//adding to contentpane and panels
		panel3.add(btnParticle);
		tPane.add("Menu",panel2);
		tPane.add("Particles",panel3);
		tPane.add("ElectricFields",panel4);
		cp.add(panel1, BorderLayout.CENTER);
		cp.add(tPane, BorderLayout.EAST);
		//sets
		setSize(1200,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//ArrayLists
		particleList = new <Particle>ArrayList();
		JPanelList = new <JPanParticle>ArrayList();
		lblParticleList = new <JLabel>ArrayList();
		particleSelector = new <Boolean>ArrayList();//c

		//pNum
		pNum = 1;
	}
	public static void main (String[] args)
	{
		ParticleGUI foo = new ParticleGUI();
	}
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			velocity();
			movement();
		}
	}
	public int getXDis()
	{
		Particle p1 = particleList.get(0);
		Particle p2 = particleList.get(1);
		return p1.getXPos() - p2.getXPos();
	}
	public int getYDis()
	{
		Particle p1 = particleList.get(0);
		Particle p2 = particleList.get(1);
		return p1.getYPos() - p2.getYPos();
	}
	public void movement()//Worked on by Collin Brooks and Luke Jacobs
	{
		for(int i = 0;i < particleList.size();i++)
		{
			particleList.get(i).setLoc(particleList.get(i).getXPos() + particleList.get(i).getXVelocity(),particleList.get(i).getYPos() + particleList.get(i).getYVelocity());
			lblParticleList.get(i).setLocation(particleList.get(i).getXPos(),particleList.get(i).getYPos());
		}
	}
	public void velocity()//Worked on by Collin Brooks and Luke Jacobs
	{
		for(int i = 0;i < particleList.size();i++)
		{
			if(e.getStr() > 0)//just EField force on particle
			{
				if(e.getDir().equals("E"))
					particleList.get(i).setXVelocity(particleList.get(i).EFieldForce(e.getStr()));
				else if(e.getDir().equals("W"))
					particleList.get(i).setXVelocity(-1*(particleList.get(i).EFieldForce(e.getStr())));
				else if(e.getDir().equals("S"))
					particleList.get(i).setYVelocity(particleList.get(i).EFieldForce(e.getStr()));
				else if(e.getDir().equals("N"))
					particleList.get(i).setYVelocity(-1*(particleList.get(i).EFieldForce(e.getStr())));
			}
		}
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)//Worked on by Luke Jacobs
		{
			btnReset.setEnabled(true);
			Object source = event.getSource();
			String[] possibleValues = {"Particle 1","Particle 2"};
			if(source == btnExit)
				System.exit(0);
			if(source == btnStart)//Worked on by Collin Brooks
			{
				btnStop.setEnabled(true);
				time.start();
				btnStart.setEnabled(false);
			}
			if(source == btnStop)//Worked on by Collin Brooks
			{
				btnStart.setEnabled(true);
				time.stop();
				btnStop.setEnabled(false);
			}
			if(source == btnParticle)//Worked on by Luke Jacobs
			{
				String inp = JOptionPane.showInputDialog(new Frame(),"Enter the charge of your particle in micro coloumbs");
				if(inp != null && pNum != 3)
				{
					particleList.add(new Particle(Integer.parseInt(inp),pNum));
					particleList.get(particleList.size()-1).setLoc(412,331);
					JPanelList.add(new JPanParticle(pNum,particleList.get(particleList.size()-1).getCharge()));
					if(pNum == 2)
					{
						lblParticleList.add(new JLabel(icoYellow));
					}
					else
					{
						lblParticleList.add(new JLabel(icoRed));
					}
					particleSelector.add(false);//c
					lblParticleList.get(particleList.size()-1).setBounds(412,331,60,60);
					panel1.add(lblParticleList.get(lblParticleList.size()-1));
					panel3.add(JPanelList.get(JPanelList.size()-1));
					panel1.repaint();
					panel3.validate();
					panel3.repaint();
					pNum++;
					if(pNum == 3)
						btnParticle.setEnabled(false);
				}

			}
			else if(source == btnEField)//Worked on by Luke Jacobs and Collin Brooks
			{
				String inp = JOptionPane.showInputDialog(new Frame(),"Be sure you added all particles to simulator before adding an E-Field\nEnter the strength and direction of the Electric Field\nEXAMPLE: 20E");
				if(inp != null)
				{
					int s = Integer.parseInt(inp.substring(0,inp.length()-1));
					String d = inp.substring(inp.length()-1);
					e = new ElectricField(s,d);
					JLabel j = new JLabel(new ImageIcon(e.getDir() + ".png"));
					j.setBounds(0,0,1000,650);
					panel1.add(j);
					panel1.repaint();
					panel4.add(new JLabel("Electric Field Strength: "+e.getStr()+ "N/C"));
					panel4.add(new JLabel("Electric Field direction: "+e.getDir()));
					double str = 0;
					String dir = "";
					for(int i = 0;i < particleList.size();i++)
					{
						str = particleList.get(i).EFieldForce(e.getStr());
						dir = particleList.get(i).EFieldForceDir(e.getDir());
						//panel4.add(new JLabel("The force exerted on the particle "+particleList.get(i).getNum()+" is "+String.format("%.2f",str)+"N"+dir));
					}
				}
			}
			if(source == btnReset)//Worked on by Collin Brooks and Luke Jacobs
			{
				time.stop();
				pNum = 1;
				e = new ElectricField(0,"N");
				panel4.removeAll();
				panel4.add(btnEField);
				panel1.removeAll();
				panel1.repaint();
				panel3.removeAll();
				particleList.clear();
				JPanelList.clear();
				lblParticleList.clear();
				panel3.add(btnParticle);
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				btnReset.setEnabled(false);
				btnParticle.setEnabled(true);
			}
		}
	}
	public void mouseClicked(MouseEvent e)
	{

	}
	public void	mouseEntered(MouseEvent e)
	{

	}
	public void	mouseExited(MouseEvent e)
	{

	}
	public void	mousePressed(MouseEvent e)//Worked on by Collin Brooks
	{
		int dis = 25;
		for(int i = 0; i < lblParticleList.size(); i++)
		{
			if(lblParticleList.get(i).getX()-e.getX()+40 < dis && lblParticleList.get(i).getX()-e.getX()+40 > -dis && lblParticleList.get(i).getY()-e.getY()+55 < dis && lblParticleList.get(i).getY()-e.getY()+55 > -dis)
			{
				particleSelector.set(i,true);
				break;
			}
		}
	}
	public void	mouseReleased(MouseEvent e)//Worked on by Collin Brooks
	{
		for(int i = 0; i < particleSelector.size(); i++)
		{
			particleSelector.set(i,false);
		}
	}
	public void mouseDragged(MouseEvent e)//Worked on by Collin Brooks
	{
		for(int i = 0; i < lblParticleList.size(); i++)
		{
			if(particleSelector.get(i) == true)
			{
				lblParticleList.get(i).setLocation(e.getX()-40,e.getY()-55);
				particleList.get(particleList.size()-1).setLoc(e.getX()-40,e.getY()-55);
				panel1.repaint();
			}
		}
	}
	public void mouseMoved(MouseEvent e)
	{

	}
}
