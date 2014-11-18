package model.quiz.opdrachten;

import persistance.DbOpdrachtMeerkeuze;
import model.quiz.Leraar;
import model.quiz.Opdracht;

public class OpdrachtMeerkeuze extends Opdracht implements IValideerbaar {

	private String[] keuzen;
	
	public OpdrachtMeerkeuze(Leraar auteur, OpdrachtCategorie categorie, String vraag, String juisteAntwoord, String[] keuzen) {
		super(auteur, categorie, vraag, juisteAntwoord);
		this.keuzen = keuzen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + java.util.Arrays.hashCode(keuzen);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpdrachtMeerkeuze other = (OpdrachtMeerkeuze) obj;
		if (!java.util.Arrays.equals(keuzen, other.keuzen))
			return false;
		return true;
	}

	@Override
	public boolean isValide(String antwoord) {
		try		
		{
			int value = Integer.parseInt(antwoord); 
			return value > 0 && value <= keuzen.length;
		}
		catch(Exception e)
		{ return false;}
	}

	public String[] getKeuzen() {
		return keuzen;
	}

	@Override
	public String getValideerTekst() {
		return "Geef een nummer tussen 1 t/m " + keuzen.length;
	}	
	
	@Override
	public boolean isJuisteAntwoord(String antwoord) {	
		return getJuisteAntwoord().equals(antwoord); //String to string check (int to int is also possible)
	}
	/**
	 * Sets the data back from a database.
	 * @param dbData
	 */
	public OpdrachtMeerkeuze(DbOpdrachtMeerkeuze dbData) {
		super(dbData);
		this.keuzen = dbData.getKeuzen();
	}
	
//	@Override
//	public String getVraag() {
//		// TODO Auto-generated method stub
//		return super.getVraag() + "\r\n" + Arrays.FormatJoin("%d - %s\r\n", keuzen);
//	}

	@Override
	public OpdrachtTypen getType() {
		return OpdrachtTypen.MEERKEUZE;
	}
}
