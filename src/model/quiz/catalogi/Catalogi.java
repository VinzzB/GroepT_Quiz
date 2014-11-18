package model.quiz.catalogi;

import model.quiz.Opdracht;
import model.quiz.Quiz;


public class Catalogi {
	
	private static volatile Catalogi uniqueInstance;
	//Singelton class
	public static synchronized Catalogi getInstance()
	{
		if (uniqueInstance == null)
			synchronized (Catalogi.class) {
				if (uniqueInstance == null)
				{
					uniqueInstance = new Catalogi();
					uniqueInstance.loadData();
				}
			}
		return uniqueInstance;
	}
	private Catalogi() { /* SINGLETON -> make class ctor private*/ }

	private QuizCatalogus quizCat;
	private OpdrachtCatalogus opdrachtCat;
	
	//called only once when initializing uniqueInstance
	private void loadData()
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
			throw new IllegalAccessError("Eén of meerdere data objecten werden niet geinitialiseerd. Kan de gegevens niet opslaan.");
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
