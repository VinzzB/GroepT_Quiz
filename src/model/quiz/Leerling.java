package model.quiz;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Leerling implements Iterable<QuizDeelname> {

	private String leerlingNaam;
	private int leerjaar;
	private Set<QuizDeelname> quizDeelnames = new HashSet<QuizDeelname>();
	@Override
	public Iterator<QuizDeelname> iterator() {
		return quizDeelnames.iterator();
	}

	public int getLeerjaar()
	{ return leerjaar; }
		
	public void AddQuizDeelname(QuizDeelname quizDeelname)
	{
		quizDeelnames.add(quizDeelname);
	}
	
	public void RemoveQuizDeelname(QuizDeelname quizDeelname)
	{
		quizDeelnames.remove(quizDeelname);
	}
	
	public Leerling(String naam, int leerjaar) {
		this.leerlingNaam = naam;
		this.leerjaar = leerjaar;
	}	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return leerlingNaam + " (" + leerjaar + " leerjaar)";
	}
}
