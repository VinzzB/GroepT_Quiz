package model.quiz;

public enum Leraar {

	FoxP("Patrick", "Fox");
	
	private final String voornaam;
	private final String achternaam;
	
	Leraar(String pVoornaam, String pAchernaam)
	{
		voornaam = pVoornaam;
		achternaam = pAchernaam;
	}
	
	public String getVoornaam()
	{
		return voornaam;
	}
	public String getAchternaam()
	{
		return achternaam;
	}
	
	public String getName()
	{
		return voornaam + " " + achternaam;
	}
	
	@Override
	public String toString() {	
		return getName();
	}
}
