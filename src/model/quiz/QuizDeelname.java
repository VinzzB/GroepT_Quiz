package model.quiz;

import java.util.ArrayList;
import java.util.List;

import model.quiz.score.ScoreStrategy;
import utils.date.normal.Datum;

public class QuizDeelname {

	private Datum datumDeelname = new Datum();
	private Leerling leerling;	
	private Quiz quiz;
	private List<OpdrachtAntwoord> antwoorden;
	public QuizDeelname(Leerling l, Quiz q) throws IllegalArgumentException, IllegalStateException
	{
		if (l == null)
			throw new IllegalArgumentException("Leerling mag niet leeg zijn!");
		if (q == null)
			throw new IllegalArgumentException("Quiz mag niet leeg zijn!");
		if (!q.getStatus().kanDeelnemen())						
			throw new IllegalArgumentException("De Quiz is niet opengesteld voor leerlingen!");			
		if (!q.isValidLeerjaar(l.getLeerjaar()))
			throw new IllegalStateException("De opgegeven leerling mag niet deelnemen aan deze quiz.");	
		quiz = q;
		leerling = l;
		//Koppel
		q.addQuizDeelname(this);
		l.AddQuizDeelname(this);
		antwoorden = new ArrayList<OpdrachtAntwoord>();
		//this.datumDeelname = new Datum();
	}
	
	public QuizDeelname(Leerling l, Quiz q, Datum datumDeelname) throws IllegalArgumentException
	{
		this(l, q);
		//koppelLeerlingAanQuiz(l, q);
		this.datumDeelname = datumDeelname;
	}
	
	public void koppelAntwoordAanQuizDeelname(OpdrachtAntwoord oa)
	{
		antwoorden.add(oa);
		
	}
	public void ontkoppelAntwoordvanQuizDeelname(OpdrachtAntwoord oa)
	{
		antwoorden.remove(oa);
	}
	
	public double getScore()
	{
		return ScoreStrategy.getInstance().getQuizScore(antwoorden);
	}
		
	public Quiz getQuiz()
	{ return quiz; }
	
	public Datum getDatumDeelname()
	{ return datumDeelname; }
	
	public Leerling getLeering()
	{ return leerling; }

	protected void OntkoppelLeerlingVanQuiz() throws IllegalStateException
	{
		if (antwoorden.size() > 0)
			throw new IllegalStateException("Kan de leerling niet meer ontkoppelen van deze quiz."); //TODO: check if nodig...
		leerling.RemoveQuizDeelname(this);
		quiz.removeQuizDeelname(this);
	}
	
//	public static QuizDeelname koppelLeerlingAanQuiz(Leerling l, Quiz q) throws IllegalArgumentException
//	{		
//		QuizDeelname d = new QuizDeelname(l,q);
//		q.addQuizDeelname(d);
//		l.AddQuizDeelname(d);
//		return d;
//	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return leerling + " speelde op " + datumDeelname.getEuropeanFormat() + " quiz " + quiz + ". Behaalde score: " + getScore();
	}
}
