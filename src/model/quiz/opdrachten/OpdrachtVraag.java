package model.quiz.opdrachten;

import model.quiz.Leraar;
import model.quiz.Opdracht;

public class OpdrachtVraag extends Opdracht {

	/**
	 * Creert een nieuwe opdracht van het type Vraag / antwoord.
	 * @param auteur
	 * @param categorie
	 * @param vraag
	 * @param juisteAntwoord
	 */
	public OpdrachtVraag(Leraar auteur, OpdrachtCategorie categorie,
			String vraag, String juisteAntwoord) {
		super(auteur, categorie, vraag, juisteAntwoord);
		// TODO Auto-generated constructor stub
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
	 */
	public OpdrachtVraag(String[] dbData) {
		super(dbData);
		
	}

	public OpdrachtVraag() {
		super();
	}
	
	@Override
	public boolean isJuisteAntwoord(String antwoord) {
		return getJuisteAntwoord().equals(antwoord);
		
	}

	public String[] getDataForDb() {
		String[] values = new String[9];
		super.fillDataArray(values);
		return values;
	}

	@Override
	public OpdrachtTypen getType() {
		// TODO Auto-generated method stub
		return OpdrachtTypen.VRAAG;
	}
}
