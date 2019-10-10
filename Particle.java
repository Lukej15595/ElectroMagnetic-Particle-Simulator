// Luke Jacobs
// Particle
// Particle class

public class Particle
{
	private int charge, pNum;
	private double vi,vx,vy;
	private int xPos, yPos;
	private final double MASS = Math.pow(10,-29);
	private final double micro = Math.pow(10,-6);

	public Particle()//Luke Jacobs
	{
		charge = 0;
		vi = 0;
	}

	public Particle(int charge)//Luke Jacobs
	{
		this.charge = charge;
		xPos = 0;
		yPos = 0;
		vi = 0;
	}
	public Particle(int charge, int xPos, int yPos)//Luke Jacobs
	{
		this.charge = charge;
		this.xPos = xPos;
		this.yPos = yPos;
		vi = 0;
	}
	public Particle(int charge,double velocity)//Luke Jacobs
	{
		this.charge = charge;
		vi = velocity;
	}
	public int getNum()//Luke Jacobs
	{
		return pNum;
	}
	public int getCharge()//Luke Jacobs
	{
		return charge;
	}
	public double getVi()//Luke Jacobs
	{
		return vi;
	}
	public int getXPos()//Collin Brooks
	{
		return xPos;
	}
	public int getYPos()//Collin Brooks
	{
		return yPos;
	}
	public int getYVelocity()
	{
		return (int)vy;
	}
	public void setYVelocity(double Fnet)
	{
		vy += accel(Fnet);
	}
	public void setXVelocity(double Fnet)
	{
		vx += accel(Fnet);
	}
	public int getXVelocity()
	{
		return (int)vx;
	}
	public void setLoc(int xPos, int yPos)//Collin Brooks
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}
	public double accel(double Fnet)//Luke Jacobs
	{

		return Fnet*Math.pow(10,-24)/(MASS);
	}
	public double electricForce(int charge1,int charge2, int d)//Luke Jacobs
	{
		double chr1 = charge1*micro;
		double chr2 = charge2*micro;
		double numerator = Math.abs((9*(Math.pow(10,9)))*(chr1*chr2));
		return numerator/Math.pow(d,2);
	}
	public double EFieldForce(double electricField)//Luke Jacobs
	{
		double chr = charge*micro;
		return chr*electricField;
	}
	public String EFieldForceDir(String dir)//Luke Jacobs
	{
		if(charge > 0)
		{
			return dir.toUpperCase();
		}
		else if(dir.toUpperCase().equals("N"))
		{
			return "S";
		}
		else if(dir.toUpperCase().equals("S"))
		{
			return "N";
		}
		else if(dir.toUpperCase().equals("W"))
		{
			return "E";
		}
		else if(dir.toUpperCase().equals("E"))
		{
			return "W";
		}
		return "X";
	}
}