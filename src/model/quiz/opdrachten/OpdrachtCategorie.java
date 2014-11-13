package model.quiz.opdrachten;

public enum OpdrachtCategorie {

	rekenen("Rekenen"),
	NL_Taal("Nederlandse Taal"),
	FR_Taal("Franse Taal"),
	Alg_Ken("Algemene Kennis");
	
	private final String omschrijving;
	
	private OpdrachtCategorie(String pOmschrijving) {
		omschrijving = pOmschrijving;
	}
	
	public String getOmschrijving()
	{ return omschrijving;}		
	
}
