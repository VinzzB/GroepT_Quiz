package model.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizOpdracht implements Comparable<QuizOpdracht> {
	//Meta
	private Quiz quiz;
	private Opdracht opdracht;
	private int maxScore;
	//Temporary dataMembers
	private List<OpdrachtAntwoord> antwoorden;
	//Ctor
	private QuizOpdracht (Quiz quiz, Opdracht opdracht, int maxScore){
		this.quiz = quiz;
		this.opdracht = opdracht;
		this.maxScore = maxScore;	
		antwoorden = new ArrayList<OpdrachtAntwoord>();
	}
	
	/** Hiermee koppel je een Opdracht aan een quiz en geef je de maximum te behalen score op (enkel voor deze quiz).
	 * @param quiz De quiz waar de opdracht aan gekoppeld moet worden.
	 * @param opdracht De opdracht die aan de quiz gekoppeld moet worden.
	 * @param maxScore De maximum te behalen score voor deze opdracht in de opgegeven quiz.
	 */
	public static void koppelOpdrachtAanQuiz(
                                             Quiz quiz, Opdracht opdracht, int maxScore){
		//Create QuizOpdracht Object 
		QuizOpdracht quizOpdracht = new QuizOpdracht(quiz,opdracht,maxScore);
		//Koppel QuizOpdracht aan opgegeven Quiz en Opdracht.
		quiz.addQuizOpdracht(quizOpdracht);
		opdracht.voegQuizOpdrachtToe(quizOpdracht);				
	}
	
	public void ontKoppelOpdrachtVanQuiz(){
		quiz.removeQuizOpdracht(this);
		opdracht.verwijderQuizOpdracht(this);
	}
	//Methods
	
	public void koppelAntwoordAanQuizOprdacht(OpdrachtAntwoord oa)
	{
		antwoorden.add(oa);
		
	}
	public void ontkoppelAntwoordvanQuizOpdracht(OpdrachtAntwoord oa)
	{
		antwoorden.remove(oa);
	}
	
	public List<OpdrachtAntwoord> getAntwoorden()
	{
		return antwoorden; //TODO: Deep copy
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public Opdracht getOpdracht() {
		return opdracht;
	}	
	
	public int getMaxScore()
	{
		return maxScore;
	}

	@Override
	public String toString() {
		return quiz + " - " + opdracht;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((antwoorden == null) ? 0 : antwoorden.hashCode());
		result = prime * result + maxScore;
		result = prime * result
				+ ((opdracht == null) ? 0 : opdracht.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizOpdracht other = (QuizOpdracht) obj;
		if (maxScore != other.maxScore)
			return false;
		if (opdracht == null) {
			if (other.opdracht != null)
				return false;
		} else if (!opdracht.equals(other.opdracht))
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}

	@Override
	public int compareTo(QuizOpdracht o) {
		return toString().compareTo(o.toString());
	}
}

