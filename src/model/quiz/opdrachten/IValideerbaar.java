package model.quiz.opdrachten;

public interface IValideerbaar {

	boolean isValide(String antwoord);
	String getValideerTekst();
}
