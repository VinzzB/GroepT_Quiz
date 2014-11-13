package model.quiz.opdrachten;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import model.Properties;
import model.quiz.Opdracht;
import persistance.*;

public class OpdrachtCatalogus implements Iterable<Entry<Integer,Opdracht>> {
	private static int lastID = 0;
	private Map<Integer, Opdracht> opdrachten;
	/**
	 * Initializes the Opdracht catalog.
	 */
	public OpdrachtCatalogus() {
		opdrachten = new HashMap<Integer,Opdracht>();					
	}
	
	@Override
	public Iterator<Entry<Integer,Opdracht>> iterator() {
		return opdrachten.entrySet().iterator();
	}

	/**
	 * Voegt een opdracht toe aan de catalogus.
	 * @param o
	 * @throws Exception
	 */
	public void AddOpdracht(Opdracht o) throws Exception
	{
		if(opdrachten.containsValue(o))
			throw new Exception("De opgegeven opdracht staat al in de catalogus!");
		opdrachten.put(++lastID,o);
	}	
	
	/**
	 * Laad de opdrachten data uit een databank
	 */
	public void LoadData()
	{
		IDbStrategy db = new DbHandler().getCatalogus(Properties.getInstance().getDbStore());
		opdrachten = db.leesOpdrachten();
		lastID = getLastIndexNumber();
	}
	
	/**
	 * Slaat de opdrachten op in een databank.
	 */
	public void saveData()
	{
		IDbStrategy db = new DbHandler().getCatalogus(Properties.getInstance().getDbStore());
		db.schrijfOpdrachten(opdrachten);
	}
	
	/**
	 * Haalt een opdracht uit de opdrachtencatalogus
	 * @param index
	 * @return
	 */
	public Opdracht getOpdracht(int index)
	{ return opdrachten.get(index); }
	
	public Map<Integer, Opdracht> getOpdrachten()
	{ return opdrachten; }
	
	public int getIndex(Opdracht opdracht)
	{
		if (opdrachten.containsValue(opdracht))
		{
			for (Entry<Integer, Opdracht> item : opdrachten.entrySet()) {
				if (item.getValue().equals(opdracht))
				{ return item.getKey(); }
			}
		}
		return -1; //not found
	}
	
	/**
	 * Geeft het aantal opdrachten in de catalogus weer.
	 * @return aantal opdrachten  in catalogus
	 */
	public int Count()
	{ return opdrachten.size(); }
	
	private int getLastIndexNumber()
	{
		int value = 0;
		for (int key : opdrachten.keySet()) {
			if (value < key) { value = key;}
		}
		return value;
	}
}
