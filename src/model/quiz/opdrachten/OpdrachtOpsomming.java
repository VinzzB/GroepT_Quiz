package model.quiz.opdrachten;

import java.util.Arrays;

import model.quiz.Leraar;
import model.quiz.Opdracht;


public class OpdrachtOpsomming extends Opdracht implements IValideerbaar {
	
	private boolean inJuisteVolgorde;
	
	public OpdrachtOpsomming(Leraar auteur, OpdrachtCategorie categorie, String vraag, String juisteAntwoord, boolean inJuisteVolgorde) {
		super(auteur, categorie, vraag, juisteAntwoord);
		this.inJuisteVolgorde = inJuisteVolgorde;
	}
	
	/**
	 * Sets the data back from a database.
	 * @param dbData
	 * String array fields:
	 * 0: Id
	 * 1: ClassType
	 * 2: Vraag
	 * 3: Antwoord
	 * 4: MaxAantalPogingen
	 * 5: MaxAntwoordTijd
	 * 6: Categorie
	 * 7: DatumReg.
	 * 8: Auteur
	 * *9*: in juiste volgorde
	 */
	public OpdrachtOpsomming(String[] dbData) {
		super(dbData);
		this.inJuisteVolgorde = Boolean.parseBoolean(dbData[9]);
	}
	
	@Override
	public boolean isValide(String antwoord) {
		try
		{
			String[] answer = antwoord.split(";");		
			return answer.length > 0;
		}
		catch(Exception e)
		{ return false; }
	}

	@Override
	public String getValideerTekst() {
		return "Typ je antwoorden " 
				+ (inJuisteVolgorde ? "in de juiste volgorde" : "achter elkaar") 
				+ " gescheiden door een ';' (zonder aanhalingstekens)";
	}

	@Override
	public boolean isJuisteAntwoord(String antwoord) {
		//split de antwoorden
		String[] antwArray = antwoord.split(";");
		String[] juistAntwArray = getJuisteAntwoord().split(";");
		//Komen de aantallen overeen?
		if (antwArray.length != juistAntwArray.length)
		{ return false; }
		//Antwoorden moeten niet in dezelfde volgorde gegeven worden? -> Sorteer beide lijsten.		
		if (!inJuisteVolgorde)
		{
			Arrays.sort(antwArray);
			Arrays.sort(juistAntwArray);
		}		
		// Controleer de juiste antwoorden tegenover de gegeven antwoorden
		for (int i = 0; i < juistAntwArray.length; i++) {
			if (!juistAntwArray[i].equals(antwArray[i]))
				return false;
		}		
		return true;		
	}
	
	public String[] getDataForDb() {
		String[] values = new String[10];
		super.fillDataArray(values);
		values[9] = Boolean.toString(inJuisteVolgorde);
		return values;
	}

	@Override
	public OpdrachtTypen getType() {
		// TODO Auto-generated method stub
		return OpdrachtTypen.OPSOMMING;
	}
}
