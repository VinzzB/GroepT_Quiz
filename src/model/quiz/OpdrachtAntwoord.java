package model.quiz;

import javax.naming.OperationNotSupportedException;

import model.quiz.opdrachten.IValideerbaar;
import model.quiz.score.ScoreStrategy;
import utils.Stopwatch;

public class OpdrachtAntwoord {
	private	QuizDeelname deelname;
	private QuizOpdracht quizOpdracht;
	private String laatsteAntwoord;
	private int aantalPogingen;
	private Stopwatch antwoordTijd;
	
	private OpdrachtAntwoord(QuizOpdracht qo, QuizDeelname qd)
	{
		deelname = qd;
		quizOpdracht = qo;		
		
	}
	
	public QuizDeelname getDeelname() { return deelname; }
	public QuizOpdracht getQuizOpdracht() { return quizOpdracht; }
	public Opdracht getOpdracht() { return quizOpdracht.getOpdracht(); }
	public String getLaatsteAntwoord() { return laatsteAntwoord; }
	public int getAantalPogingen() { return aantalPogingen; }

	public double getScore() { 
		return ScoreStrategy.getInstance().getOpdrachtScore(this); 
	} 

	public int getAntwoordTijd() {
		return antwoordTijd == null ? 0 : antwoordTijd.getSeconds();
	}
	
	public void StartOpdracht() { antwoordTijd = new Stopwatch(); antwoordTijd.Start(); }	
	public void StopOpdracht() { antwoordTijd.Stop(); }
	
	/**
	 * Geef een antwoord op de opdracht. Hiermee wordt de tijd gestopt. 
	 * Bij meerdere antwoorden kan de tijd voortgezet worden door {@link #StartOpdracht()} opnieuw aan te roepen.
	 * @param antwoord
	 * @throws IllegalArgumentException
	 * @throws OperationNotSupportedException
	 */
	public void geefAntwoord(String antwoord) throws IllegalArgumentException, OperationNotSupportedException
	{
		validate(antwoord);
		antwoordTijd.Stop(); //TODO: keep running bij meerdere antwoorden? -> Create a stop Method? of startOpdracht() opnieuw aanroepen?		
		if (aantalPogingen > quizOpdracht.getOpdracht().getMaxAantalPogingen())
			throw new OperationNotSupportedException("Er kan geen antwoord meer gegeven worden op deze opdracht");
		int maxTime = quizOpdracht.getOpdracht().getMaxAntwoordTijd();
		if (maxTime > 0 && antwoordTijd.getSeconds() > maxTime)
			throw new OperationNotSupportedException("De tijd is om. Er mag geen antwoord meer gegeven worden.");
		aantalPogingen++;
		laatsteAntwoord = antwoord;				
	}
	private void validate(String antwoord)
	{
		if (antwoord == null || antwoord == "")
			throw new IllegalArgumentException("Het antwoord mag niet leeg zijn."); //of wel?
		//Can Validate answer? then validate first!
		if (getOpdracht() instanceof IValideerbaar)
		{
			IValideerbaar validator = ((IValideerbaar)getOpdracht());
			if (!validator.isValide(antwoord))
				throw new IllegalArgumentException(validator.getValideerTekst());
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s antwoordde '%s' in %d poging(en) en in %s second(en). SCORE: %.1f/%d",
							deelname.getLeering(), 
							laatsteAntwoord ,
							aantalPogingen ,
							antwoordTijd ,
							getScore() ,
							quizOpdracht.getMaxScore());
	}
	
	public static OpdrachtAntwoord koppelQuizOpdrachtAanDeelname(QuizOpdracht qo, QuizDeelname qd)
	{
		OpdrachtAntwoord oa = new OpdrachtAntwoord(qo, qd);
		qo.koppelAntwoordAanQuizOprdacht(oa);
		qd.koppelAntwoordAanQuizDeelname(oa);
		return oa;
	}
	
	
}
