package model.quiz.status;

import model.quiz.*;

public class Afgewerkt extends Status {
	protected Afgewerkt() { /*Accessible as Static only*/ }

	@Override
	public void opengesteld(Quiz q) {
		if (q.getOpdrachten().size() > 0)
			super.opengesteld(q); //	q.setStatus(Status.opengesteld);	
	}
	
	@Override
	public void laatsteKans(Quiz q) {
		System.out.println("De quiz is nog niet opgengesteld!");
	}
	@Override
	public void afgesloten(Quiz q) {
		System.out.println("De quiz is nog niet opgengesteld!");		
	}

	@Override
	public Statussen get() {
		// TODO Auto-generated method stub
		return Statussen.Afgewerkt;
	}

	
}
