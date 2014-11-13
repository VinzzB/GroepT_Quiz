package model.quiz;

import java.util.ArrayList;
import java.util.List;

import model.quiz.opdrachten.OpdrachtCategorie;
import model.quiz.opdrachten.OpdrachtTypen;
import utils.date.normal.Datum;

public abstract class Opdracht implements Comparable<Opdracht> {
	
	//Metadata members
	/** The UniqueID for this opdracht object. (being set in the CTOR's) */
	private String vraag;
	private String juisteAntwoord;
	private int maxAantalPogingen = 1;
	private int maxAntwoordTijd = 0; //in seconds (0 = onbepaald)
	private List<QuizOpdracht> quizOpdrachten; //gekoppeld aan QuizOpdrachten (maxScore)
	private OpdrachtCategorie categorie;
	private Datum datumRegistratie = new Datum();
	private Leraar auteur;

	
	public void fillDataArray( String[] dataArray) {
		dataArray[1] = getClass().getSimpleName();
		dataArray[2] = this.vraag;
		dataArray[3] = this.juisteAntwoord;
		dataArray[4] = Integer.toString(this.maxAantalPogingen);
		dataArray[5] = Integer.toString(this.maxAntwoordTijd);
		dataArray[6] = this.categorie.name();
		dataArray[7] = this.datumRegistratie.getEuropeanFormat();
		dataArray[8] = this.auteur.name();
	}

	//Must inherit Methods
	public abstract boolean isJuisteAntwoord(String antwoord);	
	public abstract String[] getDataForDb();
	public abstract OpdrachtTypen getType();
	
	//Properties
	public Leraar getAuteur() { return auteur; }	
	public Datum getDatumRegistratie() { return datumRegistratie; }	
	public OpdrachtCategorie getCategorie() { return categorie; }	
	public String getVraag() { return vraag; }
	public String getJuisteAntwoord() { return juisteAntwoord; }
	public int getMaxAantalPogingen() { return maxAantalPogingen; }
	public int getMaxAntwoordTijd() { return maxAntwoordTijd; }
	
	//CTORS
	protected Opdracht() {
		quizOpdrachten = new ArrayList<QuizOpdracht>();				
	}

	public Opdracht (Leraar auteur, OpdrachtCategorie categorie, String vraag, String juisteAntwoord){
		this();				
		this.auteur = auteur;
		this.categorie = categorie;
		this.vraag = vraag;
		this.juisteAntwoord = juisteAntwoord;			
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
	public Opdracht(String[] dbData) {
		this();
		//Set data for this object			
		this.vraag = dbData[2];
		this.juisteAntwoord = dbData[3];
		this.maxAantalPogingen = Integer.parseInt(dbData[4]);
		this.maxAntwoordTijd = Integer.parseInt(dbData[5]);
		this.categorie = OpdrachtCategorie.valueOf(dbData[6]);		
		this.datumRegistratie = new Datum(dbData[7]);
		this.auteur = Leraar.valueOf(dbData[8]);
	}
	
//	protected void startOpdracht()
//	{
//		if (maxAntwoordTijd > 0)
//		{ /* Activate timer */ }
//	}
	
			
	//Methods for bi-directional communication
	protected void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht){
		quizOpdrachten.add(quizOpdracht);		
	}
	
	protected void verwijderQuizOpdracht(QuizOpdracht quizOpdracht){
		quizOpdrachten.remove(quizOpdracht);
	}
	
	//Overridden Methods
	
	@Override
	public String toString(){
		return vraag+" ("+juisteAntwoord+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((juisteAntwoord == null) ? 0 : juisteAntwoord.hashCode());
		result = prime * result + maxAantalPogingen;
		result = prime * result + maxAntwoordTijd;
		result = prime * result
				+ ((quizOpdrachten == null) ? 0 : quizOpdrachten.hashCode());
		result = prime * result + ((vraag == null) ? 0 : vraag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opdracht other = (Opdracht) obj;
		if (juisteAntwoord == null) {
			if (other.juisteAntwoord != null)
				return false;
		} else if (!juisteAntwoord.equals(other.juisteAntwoord))
			return false;
		if (maxAantalPogingen != other.maxAantalPogingen)
			return false;
		if (maxAntwoordTijd != other.maxAntwoordTijd)
			return false;		
		if (vraag == null) {
			if (other.vraag != null)
				return false;
		} else if (!vraag.equals(other.vraag))
			return false;
		return true;
	}

	@Override
	public int compareTo(Opdracht arg) {				
		return toString().compareTo(arg.toString());
	}
}
