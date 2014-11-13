package controller;
import java.util.List;
import java.util.Random;

import javax.naming.OperationNotSupportedException;

import model.Catalogi;
import model.quiz.*;
import model.quiz.leerling.Leerling;
public class QuizTestController {
	
	public static void main(String[] args) {		
		try
		{
			Catalogi catalogi = new Catalogi();
			catalogi.loadData();
//		//laad opdrachten van db
//		OpdrachtCatalogus opdrachten = new OpdrachtCatalogus();
//		opdrachten.LoadData(); //load from db			
//		
//		//Laad quizen en koppelingen van db
//		QuizCatalogus quizen = new QuizCatalogus();
//		quizen.loadData(opdrachten); //load from db		
		
		//TODO Init leerlingen catalogus
		//create leerling
		Leerling leerling1 = new Leerling("Vincent", 2);
		
		//Create a new Quiz		
//		Quiz quiz = new Quiz("Hoofdsteden Europa",new int[] {1,2,3}, Leraar.FoxP);
//		quiz.setDatumRegistratie(new Datum(2,11,2014));
		//Add to catalog
//		quizen.add(quiz);
		Quiz quiz = catalogi.getQuiz(1);
		
		//QuizOpdracht qo = quiz.getQuizOpdrachten().get(2);
		//qo.ontKoppelOpdrachtVanQuiz();
		
////Add new opdrachten to catalog
//		Opdracht opdracht1 = new OpdrachtVraag(Leraar.FoxP, OpdrachtCategorie.Alg_Ken,
//                        "Wat is de hoofdstad van Franrijk?","Parijs");
//		opdrachten.AddOpdracht(opdracht1);
	 // Opdracht opdracht2 = new OpdrachtVraag(Leraar.FoxP, OpdrachtCategorie.Alg_Ken,
     //                  "Wat is de hoofdstad van Spanje?","Madrid");
	 //	opdrachten.AddOpdracht(opdracht2);		
////ADD MEERKEUZE
//		OpdrachtMeerkeuze newOpdracht = new OpdrachtMeerkeuze(Leraar.FoxP, 
//												OpdrachtCategorie.Alg_Ken, 
//												"Wat is de hoofdstad van België?", "1", 
//												new String[] { "Brussel","Antwerpen","Leuven"});
////ADD OPSOMMING
//		OpdrachtOpsomming newOpdracht = new OpdrachtOpsomming(Leraar.FoxP,
//										OpdrachtCategorie.Alg_Ken,
//										"Som onze aangrenzende buurlanden chronologisch op.",
//										"Duitsland;Frankrijk;Luxemburg;Nederland",true);
//		
//		opdrachten.AddOpdracht(newOpdracht);
//		quizen.getQuiz(2).setInConstructie();
//		QuizOpdracht.koppelOpdrachtAanQuiz(quizen.getQuiz(2), newOpdracht, 2); //from db catalog
		//
		//Koppel opdrachten aan Quiz
		//QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdrachten.getOpdracht(3), 2); //from db catalog
		//QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht1, 2); //newly made opdracht
		
		//Zet Quiz status naar Opengesteld.
		//quizen.getQuiz(2).setOpengesteld();

		//koppel leerling aan quiz en speel!		
		SpeelQuiz(leerling1,quiz);			
		//Speel zelfde Quiz nog eens door zelfde leerling 
		SpeelQuiz(leerling1,quiz);		
		//create new leerling en speel quiz
		Leerling leerling2 = new Leerling("Nathalie", 3);		
		SpeelQuiz(leerling2,quiz);				
		//RESULTATEN QUIZ 1
		PrintResultatenQuiz(quiz);
		//OPEN ANDERE QUIZ
		quiz = catalogi.getQuiz(2);
	//	quiz.setOpengesteld();
		//SPEEL met leerling1
		SpeelQuiz(leerling1,quiz);
		PrintResultatenQuiz(quiz);
		
		//Close app = Save data...
		catalogi.saveData();
//		opdrachten.saveData();
//		quizen.saveData(opdrachten);
		
		
//		//get opdrachten test
//		System.out.println();
//		System.out.println(quiz.getOpdrachten());
//		
//		QuizOpdracht quizOpdracht = quiz.getOpdracht(1);
//		quizOpdracht.ontKoppelOpdrachtVanQuiz();
//		
//		System.out.println("1 ontkoppeld:" + quiz.getOpdrachten());
		}
		catch (Exception ex)
		{ System.out.println("FOUT: " + ex.getMessage()); }
	}
	
	private static void PrintResultatenQuiz(Quiz q)
	{
		System.out.println("RESULTATEN:");
		System.out.println("-----------------------------------------------------");
		for (QuizOpdracht qo : q) {
			System.out.println(qo.getOpdracht());
			List<OpdrachtAntwoord> antwoorden = qo.getAntwoorden();
			for (OpdrachtAntwoord a : antwoorden) {
				boolean juist = qo.getOpdracht().isJuisteAntwoord(a.getLaatsteAntwoord());
				System.out.println(a + (juist ? " - IS JUIST!":" - IS FOUT!"));
			}						
		}	
		for (QuizDeelname qd : q.getAllDeelnamen())
		{
			System.out.println(qd);
		}
		System.out.println("-----------------------------------------------------");
	}
	
	private static void SpeelQuiz(Leerling l, Quiz q)
	{
		System.out.println(l + " speelt de quiz: " + q.getOnderwerp());
		QuizDeelname deelname = new	QuizDeelname(l, q);
		Random r = new Random();
		//Start Quiz
		for (QuizOpdracht quizOpdracht : q) {
			
			//Create an answer object
			OpdrachtAntwoord oa = OpdrachtAntwoord.koppelQuizOpdrachtAanDeelname(quizOpdracht , deelname);
			//Show Question & Start
			System.out.print("Question: " + quizOpdracht.getOpdracht().getVraag());			
			oa.StartOpdracht(); //start the opdracht (kan in getOpdracht method van opdrachtAntwoord.?..)
			
			//Give an answer after some sec...
			String answer = quizOpdracht.getOpdracht().getJuisteAntwoord() + (r.nextInt(2)==0 ? "" : "Wrong");
			try {
				Thread.sleep(r.nextInt(10000));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(" -> " + answer );
			try 
			{			
				oa.geefAntwoord(answer);	
			} catch (Exception e) {
				System.out.println(e.getMessage());
				answer =quizOpdracht.getOpdracht().getJuisteAntwoord();
				System.out.println(" -> " + answer);
				try {
					oa.geefAntwoord(answer);
				} catch (IllegalArgumentException
						| OperationNotSupportedException e1) {
					System.out.println(e.getMessage());
				}
			}				
		}
	}
	
}