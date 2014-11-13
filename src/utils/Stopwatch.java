package utils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
	
	private class Task extends TimerTask
	{
		@Override
		public void run() { elapsedSeconds++; }
	}
		
	private int elapsedSeconds = 0;
	private Timer timer;
	
	public Stopwatch() { }
	
	public void Start()
	{
		timer = new Timer();
		timer.schedule(new Task(), 1000, 1000); //repeat every sec
	}
	
	public void Stop()
	{
		if (timer != null) { timer.cancel(); }
		timer = null;
	}
	
	public int getSeconds()
	{ return elapsedSeconds; }
	
	@Override
	public String toString() {
		return Integer.toString(elapsedSeconds);
	}
	//test
	public static void main(String[] args)
	{
		Stopwatch a = new Stopwatch();
		a.Start();
		System.out.print("Timer Started (press any key to stop...)");	
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		a.Stop();
		System.out.print("Stopped @ " + a.elapsedSeconds);
		
	}
}
