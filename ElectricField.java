//Luke Jacobs
//Electric Field
//Object for Electric Field

public class ElectricField
{
	private int str;
	private String dir;
	public ElectricField()
	{
		str = 1;
		dir = "N";
	}
	public ElectricField(int str,String dir)
	{
		this.str = str;
		this.dir = dir.toUpperCase();
	}
	public int getStr()
	{
		return str;
	}
	public String getDir()
	{
		return dir;
	}
}