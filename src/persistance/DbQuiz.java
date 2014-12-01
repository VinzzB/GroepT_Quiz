package persistance;

import model.quiz.Leraar;
import model.quiz.Quiz;
import model.quiz.status.Statussen;
import utils.Arrays;
import utils.date.normal.Datum;

public class DbQuiz {
	int id;
	String onderwerp;
	Datum datumRegistratie;
	Leraar auteur;
	boolean isTest;
	boolean isUniekeDeelname;
	int[] leerjaren;
	Statussen status;
	//List<DbQuizOpdracht> quizOpdrachten;
	
	/**
	 * Maakt een dbQuizData object van gegevens afkomstig uit een Text database
	 * @param dataRow Een String array als waarden voor de velden in ��n quiz.
	 */
	DbQuiz(String[] dataRow)
	{
		this.id = Integer.parseInt(dataRow[0]);
		this.onderwerp = dataRow[1];		
		setLeerjaren(dataRow[2]);		
		this.isTest = Boolean.parseBoolean(dataRow[3]);
		this.isUniekeDeelname = Boolean.parseBoolean(dataRow[4]);
		this.datumRegistratie = new Datum(dataRow[6]);
		this.auteur = Leraar.valueOf(dataRow[7]);			
		this.status = Statussen.valueOf(dataRow[5]);
	}
	
	DbQuiz(Quiz q, int id)
	{
		this.id = id;
		this.onderwerp = q.getOnderwerp();
		this.leerjaren = q.getLeerjaren();
		this.isTest = q.getIsTest();
		this.isUniekeDeelname = q.getIsUniekeDeelname();
		this.status = q.getStatus().get();
		this.datumRegistratie = q.getDatumRegistratie();
		this.auteur = q.getAuteur();
	}
		
	/**
	 * Maakt een dbQuizData object van gegevens afkomstig uit een MySql database
	 * @param dataRow TODO! probably an incorrect param
	 */
	DbQuiz(Object[] dataRow) {
	
	}
	
	public String getOnderwerp() {
		return onderwerp;
	}
	public Datum getDatumRegistratie() {
		return datumRegistratie;
	}
	public Leraar getAuteur() {
		return auteur;
	}
	public boolean isTest() {
		return isTest;
	}
	public boolean isUniekeDeelname() {
		return isUniekeDeelname;
	}
	public int[] getLeerjaren() {
		return leerjaren;
	}
	public Statussen getStatus() {
		return status;
	}

	public String[] asStringArray()
	{
		String[] data = new String[8];
		data[0] = Integer.toString(id);
		data[1] = getOnderwerp();
		data[2] = Arrays.Join(",", getLeerjaren());
		data[3] = Boolean.toString(isTest());
		data[4] = Boolean.toString(isUniekeDeelname());
		data[5] = getStatus().name();
		data[6] = getDatumRegistratie().getEuropeanFormat();
		data[7] = getAuteur().name();
		return data;
				
	}
	
	private void setLeerjaren(String leerjaren)
	{
		String[] tmpL = leerjaren.split(","); //split param value
		this.leerjaren = new int[tmpL.length]; //allocate array
		for (int i = 0; i < tmpL.length; i++)  //iterate and fill array
		{ this.leerjaren[i] = Integer.parseInt(tmpL[i]); }
	}
	
}
