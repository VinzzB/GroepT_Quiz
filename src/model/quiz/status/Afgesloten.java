package model.quiz.status;

import model.quiz.*;

public class Afgesloten extends Status {
	protected Afgesloten() { }
	
	@Override
	public Statussen get() {
		// TODO Auto-generated method stub
		return Statussen.Afgesloten;
	}

	@Override
	public void afgewerkt(Quiz q) {
		// TODO Auto-generated method stub
		super.afgewerkt(q);
	}

	@Override
	public void opengesteld(Quiz q) {
		// TODO Auto-generated method stub
		super.opengesteld(q);
	}

	@Override
	public void laatsteKans(Quiz q) {
		// TODO Auto-generated method stub
		super.laatsteKans(q);
	}

}
