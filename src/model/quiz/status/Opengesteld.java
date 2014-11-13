package model.quiz.status;

import model.quiz.*;

public class Opengesteld extends Status {
	protected Opengesteld() { }
	
	@Override
	public void inConstructie(Quiz q) {	
		if (q.getAllDeelnamen().size() == 0)
			super.inConstructie(q);
	}
	
	@Override
	public void afgewerkt(Quiz q) {
		
		System.out.println("Deze quiz is al opengesteld!");
	}

	@Override
	public void laatsteKans(Quiz q) {
		super.laatsteKans(q);
		
	}

	@Override
	public void afgesloten(Quiz q) {
		super.afgesloten(q);
	}

	@Override
	public Statussen get() {
		// TODO Auto-generated method stub
		return Statussen.Opengesteld;
	}

	@Override
	public boolean kanDeelnemen() {
		// TODO Auto-generated method stub
		return true;
	}
}
