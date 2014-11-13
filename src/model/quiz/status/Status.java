package model.quiz.status;

import model.quiz.*;

public abstract class Status {	
	//Static methods
	static final Status afgewerkt = new Afgewerkt();
	static final Status inConstructie = new InConstructie();
	static final Status opengesteld = new Opengesteld();
	static final Status laatsteKans = new LaatsteKans();
	static final Status Afgesloten = new Afgesloten();
		
	/**
	 * Converteert een enum van het type Statussen naar een State object
	 * @param statusType
	 * @return
	 */
	public static Status get(Statussen statusType)
	{
		switch (statusType) {
		case Afgewerkt: return afgewerkt;
		case InConstructie: return inConstructie;
		case Opengesteld: return opengesteld;
		case LaatsteKans: return laatsteKans;
		case Afgesloten: return Afgesloten;
		default: return null;
		}
	}
	
	//Abstract Methods
	/**
	 * Haalt het bijhorende enum type op van het huidig State object.
	 * @return Geeft het bijhorende enumeration type terug.
	 */
	public abstract Statussen get(); //exposes the enum value 
	/**
	 * Kan een leerling deelnemen aan de quiz?
	 * @return
	 */
	public boolean kanDeelnemen() { return false; }
	//public void KoppelOpdracht() { }
	
	//delegates
	//public boolean deelnemen(Quiz q, Leerling l)
	public void inConstructie(Quiz q) { q.setStatus(inConstructie); }
	public void afgewerkt(Quiz q) { q.setStatus(afgewerkt); }
	public void opengesteld(Quiz q) { q.setStatus(opengesteld); }
	public void laatsteKans(Quiz q) { q.setStatus(laatsteKans); }	
	public void afgesloten(Quiz q) { q.setStatus(Afgesloten); }//TODO Close mag via normale method?	
	
	
}
