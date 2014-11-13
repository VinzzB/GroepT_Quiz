package model.quiz.status;

public class LaatsteKans extends Status {
	protected LaatsteKans() { }
	
	@Override
	public Statussen get() {
		// TODO Auto-generated method stub
		return Statussen.LaatsteKans;
	}
	
	@Override
	public boolean kanDeelnemen() {
		// TODO Auto-generated method stub
		return true;
	}
}
