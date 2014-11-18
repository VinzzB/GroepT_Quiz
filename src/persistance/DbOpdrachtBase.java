package persistance;
	import model.quiz.Leraar;
import model.quiz.Opdracht;
import model.quiz.opdrachten.OpdrachtCategorie;
import model.quiz.opdrachten.OpdrachtTypen;
import utils.date.normal.Datum;
public abstract class DbOpdrachtBase {

		private String vraag;
		private String juisteAntwoord;
		private int maxAantalPogingen;
		private int maxAntwoordTijd;
	//	private List<QuizOpdracht> quizOpdrachten;
		private OpdrachtCategorie categorie;
		private Datum datumRegistratie;
		private Leraar auteur;

		public abstract OpdrachtTypen getType();
		public abstract String[] asStringArray();		
		
		protected void fillStringArray(String[] data)
		{
			if (data != null && data.length >= 8)
			{
				data[1] = getType().name();
				data[2] = getVraag();
				data[3] = getJuisteAntwoord();
				data[4] = Integer.toString(getMaxAantalPogingen());
				data[5] = Integer.toString(getMaxAntwoordTijd());
				data[6] = getCategorie().name();
				data[7] = getDatumRegistratie().getEuropeanFormat();
				data[8] = getAuteur().name();
			}
		}
		
		DbOpdrachtBase(String[] dataRow)
		{
			this.vraag = dataRow[2];
			this.juisteAntwoord = dataRow[3];
			this.maxAantalPogingen = Integer.parseInt(dataRow[4]);
			this.maxAntwoordTijd = Integer.parseInt(dataRow[5]);
			this.categorie = OpdrachtCategorie.valueOf(dataRow[6]);		
			this.datumRegistratie = new Datum(dataRow[7]);
			this.auteur = Leraar.valueOf(dataRow[8]);
		}
		
		DbOpdrachtBase(Opdracht opdracht) {
			this.vraag = opdracht.getVraag();
			this.juisteAntwoord = opdracht.getJuisteAntwoord();
			this.maxAntwoordTijd = opdracht.getMaxAntwoordTijd();
			this.categorie = opdracht.getCategorie();
			this.datumRegistratie = opdracht.getDatumRegistratie();
			this.auteur = opdracht.getAuteur();
		}

		public String getVraag() {
			return vraag;
		}

//		public void setVraag(String vraag) {
//			this.vraag = vraag;
//		}

		public String getJuisteAntwoord() {
			return juisteAntwoord;
		}

//		public void setJuisteAntwoord(String juisteAntwoord) {
//			this.juisteAntwoord = juisteAntwoord;
//		}

		public int getMaxAantalPogingen() {
			return maxAantalPogingen;
		}
//
//		public void setMaxAantalPogingen(int maxAantalPogingen) {
//			this.maxAantalPogingen = maxAantalPogingen;
//		}

		public int getMaxAntwoordTijd() {
			return maxAntwoordTijd;
		}
//
//		public void setMaxAntwoordTijd(int maxAntwoordTijd) {
//			this.maxAntwoordTijd = maxAntwoordTijd;
//		}

//		public List<QuizOpdracht> getQuizOpdrachten() {
//			return quizOpdrachten;
//		}
//
//		public void setQuizOpdrachten(List<QuizOpdracht> quizOpdrachten) {
//			this.quizOpdrachten = quizOpdrachten;
//		}

		public OpdrachtCategorie getCategorie() {
			return categorie;
		}
//
//		public void setCategorie(OpdrachtCategorie categorie) {
//			this.categorie = categorie;
//		}

		public Datum getDatumRegistratie() {
			return datumRegistratie;
		}
//
//		public void setDatumRegistratie(Datum datumRegistratie) {
//			this.datumRegistratie = datumRegistratie;
//		}

		public Leraar getAuteur() {
			return auteur;
		}
//
//		public void setAuteur(Leraar auteur) {
//			this.auteur = auteur;
//		}
	
}
