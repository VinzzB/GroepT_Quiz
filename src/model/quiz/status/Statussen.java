package model.quiz.status;
/**
 * De statussen die een Quiz kan hebben
 * @author bloemevi
 *
 */
public enum Statussen {
	
	/**
	 * Een leraar kan de quiz bewerken. De quiz is niet beschikbaar voor leerlingen.  
	 */
	InConstructie("In constructie"),  
	/**
	 * De quiz is afgewerkt maar nog niet zichtbaar voor de leerlingen
	 */
	Afgewerkt("Afgewerkt"), 
	/**
	 * De quiz kan gestart worden door de leerlingen.
	 */
	Opengesteld("Opengesteld"), 
	/**
	 * Het is de laatste kans om deze quiz te spelen.
	 */
	LaatsteKans("Laatste kans"), 
	/**
	 * De quiz is afgesloten en bijgevolg niet meer beschikbaar voor de leerlingen.
	 */
	Afgesloten("Afgesloten");
	
	private final String Omschrijving;
	
	private Statussen(String omschrijving) 
	{
		this.Omschrijving = omschrijving;
	}
	
	public String getOmschrijving()
	{
		return Omschrijving;
	}
}
