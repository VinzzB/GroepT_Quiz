package model.quiz.score;

import java.util.List;

import model.quiz.OpdrachtAntwoord;

public interface IBerekening {

	/**
	 * Een strategy om de score van één opdracht te berekenen
	 * @param antwoord Het antwoord dat werd gegeven op de opdracht.
	 * @return De behaalde score op de opdracht. (komma getallen toegelaten)
	 */
	double getOpdrachtScore(OpdrachtAntwoord antwoord);
	
	/**
	 * Een strategy om de score van een quiz te berekenen. (Per deelname)
	 * @param antwoorden Geeft een lijst met antwoorden uit één quiz en van één deelname.
	 * @return De behaalde score van een deelname op een quiz. ( maximum te behalen = getQuizMaxScore() )
	 * (komma getallen toegelaten) 
	 */
	double getQuizScore(List<OpdrachtAntwoord> antwoorden);
	
	/**
	 * De maximum te behalen score op een quiz. De behaalde punten per opdracht in een quiz worden herleid naar dit getal.
	 * @return (komma getallen toegelaten)
	 */
	double getQuizMaxScore();
}
