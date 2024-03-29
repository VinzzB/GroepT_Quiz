package model.quiz.catalogi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import model.quiz.Quiz;
import persistance.DbHandler;

public class QuizCatalogus implements Iterable<Entry<Integer, Quiz>> {

	private Map<Integer, Quiz> quizen;
	private int lastID = 0;
	@Override
	public Iterator<Entry<Integer, Quiz>> iterator() { return quizen.entrySet().iterator(); }	
	
	QuizCatalogus() { quizen = new HashMap<Integer, Quiz>(); }
	
	public void add(Quiz q) throws Exception	
	{
		
		if(quizen.containsValue(q))
			throw new Exception("De opgegeven quiz staat al in de catalogus!");
		quizen.put(++lastID, q); 
	}
	
	public void remove(Quiz q)	{ quizen.remove(q); }
	
	public Quiz getQuiz(int index)	{ return quizen.get(index); }
	
	public Collection<Quiz> getQuizen() {return quizen.values(); }
	
	public int count() { return quizen.size(); }
	
	void loadData(OpdrachtCatalogus opdrachtenCatalog)
	{		
		quizen = DbHandler.getInstance().leesQuizen(opdrachtenCatalog);			
		lastID = getLastIndexNumber();		
	}
		
	/**
	 * Slaat de data op in een databank.
	 * @param opdrachtenCatalog De opdrachten catalogus om de koppeling tussen quizen en opdrachten op te slaan. 
	 */
	void saveData(OpdrachtCatalogus opdrachtenCatalog)
	{		
		DbHandler.getInstance().schrijfQuizen(quizen,opdrachtenCatalog);
	}

	private int getLastIndexNumber()
	{
		int value = 0;
		for (int key : quizen.keySet()) {
			if (value < key) { value = key;}
		}
		return value;
	}
}
