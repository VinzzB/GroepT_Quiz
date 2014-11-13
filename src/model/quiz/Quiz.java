package model.quiz;

import java.util.ArrayList;

import utils.Arrays;

import java.util.Iterator;
import java.util.List;

import model.quiz.status.*;
import utils.date.normal.Datum;

public class Quiz implements Iterable<QuizOpdracht>, Comparable<Quiz>
{
	
	//Metadata Members	
	private String onderwerp;
	private int[] leerjaren; //voor welke leerjaren geschikt (1-6)
	private boolean isTest; //Als Toets
	

	private boolean isUniekeDeelname; //Mag meerdere keren opgelost worden. indien test dan altijd uniekeDeelname
	private List <QuizOpdracht> quizOpdrachten; //alle quizopdrachten in deze quiz
	private List <QuizDeelname> quizDeelnames;  //alle deelnames in deze quiz(???)
	private Leraar auteur;
	private Datum datumRegistratie;
	private Status status = new InConstructie();//mogelijkheden: "in constructie", "Afgewerkt", "opengesteld","laatsteKans", "Afgesloten"	
	
	//CTORS
	public Quiz (String onderwerp){
		setOnderwerp(onderwerp);
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		quizDeelnames = new ArrayList<QuizDeelname>();
	}
	public Quiz (String onderwerp, int[] leerjaren, Leraar auteur, boolean isTest, boolean isUniekeDeelname){
		this(onderwerp);
		setLeerjaren(leerjaren);
		setAuteur(auteur);
		setIsTest(isTest);
		setIsUniekeDeelname(isUniekeDeelname);		
	}	

	/** Create a Quiz with properties from Db.
	 * 0: ID
	 * 1: Onderwerp
	 * 2: Leerjaren (comma seperated)
	 * 3: isTest
	 * 4: isUniekeDeelname
	 * 5: Status
	 * 6: DatumReg
	 * 7: Auteur
	 * @param fields
	 */
	public Quiz(String[] fields)
	{
		this(fields[1]);		
		//Parse leerjaren
		String[] tmpL = fields[2].split(",");
		this.leerjaren = new int[tmpL.length];
		for (int i = 0; i < tmpL.length; i++) 
		{ leerjaren[i] = Integer.parseInt(tmpL[i]); }
		this.isTest = Boolean.parseBoolean(fields[3]);
		this.isUniekeDeelname = Boolean.parseBoolean(fields[4]);
		this.setStatus(Status.get(Statussen.valueOf(fields[5])));
		this.datumRegistratie = new Datum(fields[6]);
		this.auteur = Leraar.valueOf(fields[7]);		
	}
	
	public String[] getDataForDb()
	{
		String[] values = new String[8]; //0 =empty!
		values[1] = this.onderwerp;
		values[2] = Arrays.Join(",", this.leerjaren);
		values[3] = Boolean.toString(this.isTest);
		values[4] = Boolean.toString(this.isUniekeDeelname);
		values[5] = status.get().name();
		values[6] = datumRegistratie.getEuropeanFormat();
		values[7] = auteur.name();
		return values;
	}
	
	//Properties set
	public void setOnderwerp(String onderwerp) { this.onderwerp = onderwerp; }
	public void setIsTest(boolean isTest) { this.isTest = isTest; }	
	public void setLeerjaren(int[] leerjaren) { this.leerjaren = leerjaren; }
	public void setDatumRegistratie(Datum datumRegistratie) 
	{ this.datumRegistratie = datumRegistratie; }
	public void setIsUniekeDeelname(boolean isUniekeDeelname) 
	{ this.isUniekeDeelname = isUniekeDeelname; }
	
	public void setLeerjaren(String leerjaren)
	{
		String[] tmpL = leerjaren.split(",");
		this.leerjaren = new int[tmpL.length];
		for (int i = 0; i < tmpL.length; i++) 
		{ this.leerjaren[i] = Integer.parseInt(tmpL[i]); }
	}
	
	public void setAuteur(Leraar auteur) {
		this.auteur = auteur;
	}
	public List<QuizOpdracht> getQuizOpdrachten() {
		ArrayList <QuizOpdracht> qo = new ArrayList <QuizOpdracht>();
		for (QuizOpdracht quizOpdracht : quizOpdrachten) {
			qo.add(quizOpdracht);
		}
		return qo;
	}
	
	public int getQuizOpdrachtenCount()
	{
		return quizOpdrachten.size();
	}
	
	//Properties get
	public Leraar getAuteur() { return auteur; }	
	public Datum getDatumRegistratie() { return datumRegistratie; }
	public int[] getLeerjaren() { return (int[])leerjaren.clone(); }
	public String getOnderwerp() { return onderwerp; }	
	public boolean getIsTest() { return isTest; }	
	public boolean getIsUniekeDeelname() { return isUniekeDeelname || isTest; }	
	
	public ArrayList<QuizDeelname> getAllDeelnamen()
	{
		ArrayList <QuizDeelname> qd = new ArrayList <QuizDeelname>();
		for (QuizDeelname d : quizDeelnames){
			qd.add(d);
		}
		return qd; //non editable list.
	}	
	
	/**
	 * Haalt alle gekoppelde opdrachten in deze quiz op.
	 * @return
	 */
	public ArrayList <Opdracht> getOpdrachten(){
		ArrayList <Opdracht> opdrachten = new ArrayList <Opdracht>();
		for (QuizOpdracht quizOpdracht : quizOpdrachten){
			opdrachten.add(quizOpdracht.getOpdracht());
		}
		return opdrachten; //non editable list.
	}
	
	public QuizOpdracht getOpdracht(int volgnr){
		return quizOpdrachten.get(volgnr-1);
	}	
	
	protected boolean isValidLeerjaar(int leerjaar)
	{ 
		for (int i = 0; i < leerjaren.length; i++) {
			if (leerjaren[i] == leerjaar)
			{ return true; }
		}
		return false; // leerjaren.contains(leerjaar); 
	}	
	
	//State methods
	public void setAfgewerkt() { status.afgewerkt(this); }	
	public void setOpengesteld() { status.opengesteld(this); }
	public void setLaatsteKans() { status.laatsteKans(this); }
	public void setInConstructie() {status.inConstructie(this); }
	
	public void setStatus(Status status) {this.status = status; }		
	public Status getStatus() { return status; }
	
	//Methods voor bi-directionele communicatie met opdrachten en deelnames
	protected void addQuizDeelname(QuizDeelname qd)
	{ quizDeelnames.add(qd); }
	
	protected void removeQuizDeelname(QuizDeelname qd)
	{ quizDeelnames.remove(qd); }
	
	protected void addQuizOpdracht(QuizOpdracht quizOpdracht)
	{ 
		//TODO: implement status method allowKoppelOpdracht		
		if (status.get() != Statussen.InConstructie)
			throw new IllegalArgumentException("De Quiz kan enkel bewerkt worden als de status op 'In Constructie' staat. huidige status: " + status.get().getOmschrijving());
		if (quizOpdrachten.contains(quizOpdracht))
			throw new IllegalArgumentException("De opgegeven opdracht is al toegewezen aan deze quiz.");
		quizOpdrachten.add(quizOpdracht); }
	
	protected void removeQuizOpdracht(QuizOpdracht quizOpdracht)
	{ quizOpdrachten.remove(quizOpdracht); }
	
	
	
	@Override
	public Iterator<QuizOpdracht> iterator() {
		return quizOpdrachten.iterator();
	}
	@Override
	public int compareTo(Quiz o) {
		// TODO Auto-generated method stub
		return toString().compareTo(o.toString());
	}	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return onderwerp + " (leerjaren: " + Arrays.Join(",", leerjaren) + ") ontworpen door " + auteur.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime
				* result
				+ ((datumRegistratie == null) ? 0 : datumRegistratie.hashCode());
		result = prime * result + (isTest ? 1231 : 1237);
		result = prime * result + (isUniekeDeelname ? 1231 : 1237);
		result = prime * result + java.util.Arrays.hashCode(leerjaren);
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
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
		Quiz other = (Quiz) obj;
		if (auteur != other.auteur)
			return false;
		if (datumRegistratie == null) {
			if (other.datumRegistratie != null)
				return false;
		} else if (!datumRegistratie.equals(other.datumRegistratie))
			return false;
		if (isTest != other.isTest)
			return false;
		if (isUniekeDeelname != other.isUniekeDeelname)
			return false;
		if (!java.util.Arrays.equals(leerjaren, other.leerjaren))
			return false;
		if (onderwerp == null) {
			if (other.onderwerp != null)
				return false;
		} else if (!onderwerp.equals(other.onderwerp))
			return false;
		return true;
	}
}

