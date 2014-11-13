package model;

import model.quiz.Opdracht;
import model.quiz.Quiz;
import model.quiz.QuizCatalogus;
import model.quiz.opdrachten.OpdrachtCatalogus;

public class Catalogi {

	private QuizCatalogus quizCat;
	private OpdrachtCatalogus opdrachtCat;
	
	public void loadData()
	{
		//laad opdrachten van db
		opdrachtCat = new OpdrachtCatalogus();
		opdrachtCat.LoadData(); //load from db			
		
		//Laad quizen en koppelingen van db
		quizCat = new QuizCatalogus();
		quizCat.loadData(opdrachtCat); //load from db		
	}
	
	public void saveData()
	{
		if (quizCat == null || opdrachtCat == null)
			throw new IllegalAccessError("Eén of meerdere data objecten werden niet geinitialisserd. Kan de gegevens niet opslaan.");
		opdrachtCat.saveData();
		quizCat.saveData(opdrachtCat);
	}
	public QuizCatalogus getQuizCatalogus() 
	{ return quizCat; }
	
	public Quiz getQuiz(int index)
	{ return quizCat.getQuiz(index); }

	public OpdrachtCatalogus getOpdrachtCatalogus() 
	{ return opdrachtCat; }
	
	public Opdracht getOpdracht(int index)
	{ return opdrachtCat.getOpdracht(index); }
}
