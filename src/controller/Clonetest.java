package controller;

import utils.date.normal.Datum;

public class Clonetest implements Cloneable {

	public String s = "test"; //Strings are also primitive through Char Strings. -> https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html 
	public int i = 12;
	public float fl = 10.0001f;
	public Datum date = new Datum(); //Shallow when super.clone() is called. Create deep clone yourself...
			
			
	
	@Override
	public Clonetest clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		Clonetest t = (Clonetest)super.clone();
		t.date = new Datum(this.date);
		return t;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Date: " + date + " String: " + s + " Int: " + i;
	}
	public String getS()
	{
		return s;
	}
	
	public static void main(String[] args)
	{
		//String[] s = new String[] {"een","twee"};
			try {
					//Create object
					Clonetest st1 = new Clonetest();
					//Deep Clone object
					Clonetest st2 = st1.clone();					
					//Test deep clone
					st2.i = 14;
					st2.s = "Dit is een super.clone test";
					
					st2.date.veranderDatum(10);
					
						
					System.out.println("1: " + st1);
					System.out.println("2: " + st2);
					
					//String test
					String s = "test"; //
					String ss = new String("test"); //new string object
					System.out.println("s = " + s.substring(0));
					System.out.println("ss==s.toUpperCase()" + (ss == s.toUpperCase())); //FALSE
					System.out.println("s==s.toUpperCase().toLowerCase()" + (s == s.toUpperCase().toLowerCase())); //FALSE
					System.out.println("'TEST'==s.toUpperCase()" + ("TEST" == s.toUpperCase())); //FALSE
					System.out.println("s.toUpperCase()=='TEST'" + (s.toUpperCase() == "TEST")); //FALSE
					System.out.println("s.toUpperCase().intern()=='TEST'" + (s.toUpperCase().intern() == "TEST")); //TRUE -> Check from pool
				
					
					
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
