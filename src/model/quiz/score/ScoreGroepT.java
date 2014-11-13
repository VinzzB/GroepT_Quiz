package model.quiz.score;

import java.util.List;

import model.quiz.OpdrachtAntwoord;

public class ScoreGroepT implements IBerekening {

	/**
	 * @see model.quiz.score.IBerekening#getOpdrachtScore(OpdrachtAntwoord)
	 */
	@Override
	public double getOpdrachtScore(OpdrachtAntwoord a) {
		int maxTime = a.getOpdracht().getMaxAntwoordTijd();
		if (a.getOpdracht().isJuisteAntwoord(a.getLaatsteAntwoord()) 
			&& (maxTime == 0 || a.getAntwoordTijd() <= maxTime))
		{			
			if (a.getAantalPogingen() == 1)
				return a.getQuizOpdracht().getMaxScore() ; 
			if (a.getAantalPogingen() > 1)
				return a.getQuizOpdracht().getMaxScore() / 2;			
		}
		return 0;
	}

	/**
	 * @see model.quiz.score.IBerekening#getQuizScore(List)
	 */
	@Override
	public double getQuizScore(List<OpdrachtAntwoord> antwoorden) {
		double score = 0;
		for (OpdrachtAntwoord a : antwoorden) {
			score += getOpdrachtScore(a) / a.getQuizOpdracht().getMaxScore();
		}
		return Math.round((((double)score / antwoorden.size()) * getQuizMaxScore()));
	}

/**
 * @see model.quiz.score.IBerekening#getQuizMaxScore()
 */
	@Override
	public double getQuizMaxScore() {
		return 20; //Punten worden herleid naar 10 (afgerond naar geheel getal.)
	}
}
