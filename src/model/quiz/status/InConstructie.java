package model.quiz.status;

import model.quiz.*;


//"in constructie", "Afgewerkt", "opengesteld","laatsteKans", "Afgesloten"
public class InConstructie extends Status {

	@Override
	public void afgewerkt(Quiz q) {
		//in constructie en bevat opdrachten
		if (q.getOpdrachten().size() > 0)
			q.setStatus(Status.afgewerkt);	
	}

	@Override
	public void opengesteld(Quiz q) {
		if (q.getOpdrachten().size() > 0)
			q.setStatus(Status.opengesteld);	
	}

	@Override
	public void laatsteKans(Quiz q) {
		System.out.println("De quiz is nog niet opengesteld!");
	}

	@Override
	public void afgesloten(Quiz q) {
		System.out.println("De quiz is nog niet opengesteld!");		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Quiz onder constructie";
	}

	@Override
	public Statussen get() {
		// TODO Auto-generated method stub
		return Statussen.InConstructie;
	}

}
