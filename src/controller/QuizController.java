package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.quiz.*;
import model.quiz.leerling.Leerling;
import model.quiz.score.ScoreStrategy;
import view.Main;
import view.speelQuiz;

public class QuizController {

	private Main mainForm;	
	//Catalogi catalogi = new Catalogi();
	private	Quiz quiz;
	private int currOpdrachtIdx;
	private speelQuiz panel = new speelQuiz();
	private QuizDeelname deelname;
	private OpdrachtAntwoord antwoord;
	public QuizController(Quiz q, Main view) {
			quiz = q;
			mainForm = view;	
			panel.addButtonListener(new ButtonListener());
	}
	

	public void speelQuiz(Leerling l)
	{
		if (deelname == null)
		{
			//init deelname
			deelname = new QuizDeelname(l, quiz);
			//add quiz panel
			mainForm.addPanel(panel,"SpeelQuiz");
			//print first opdracht
			printVolgendeOpdracht();
		}
		
	}
	private void printOpdracht(int volgNr)
	{		
		QuizOpdracht qo = quiz.getOpdracht(volgNr);
		antwoord = OpdrachtAntwoord.koppelQuizOpdrachtAanDeelname(qo, deelname);				
		panel.setOpdracht(currOpdrachtIdx, qo.getOpdracht().getVraag(), "", qo.getAntwoorden().size());	
		antwoord.StartOpdracht();
	}
	
	private void printVolgendeOpdracht()
	{
		if (currOpdrachtIdx < quiz.getQuizOpdrachtenCount())
		{	printOpdracht(++currOpdrachtIdx); }
		else
		{			
			mainForm.removePanel(panel);
			
			mainForm.displayErrorMessage("De quiz is afgelopen.\r\nScore: " + deelname.getScore() + "/" + ScoreStrategy.getInstance().getQuizMaxScore());
		}
	}
	
	public void closePanel()
	{
		mainForm.removePanel(panel);
		quiz = null;
		panel = null;
		deelname = null;
		antwoord = null;
		mainForm = null;
	}
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getSource() instanceof JButton)
			{
				JButton pressedButton = (JButton)arg0.getSource();
				if (pressedButton.equals(panel.getBtnVolgende()))
				{
					try {
						
						antwoord.geefAntwoord(panel.getAntwoord());
						printVolgendeOpdracht();
												
					} catch (Exception e) {
						mainForm.displayErrorMessage(e.getMessage());		
						printOpdracht(currOpdrachtIdx); //reset
					}					
				}
				else if (pressedButton.equals(panel.getBtnAnnuleren()))
				{					
					//TODO cancel quiz!?
					mainForm.removePanel(panel);
				}
			}
		}
		
	}
//	
//	
//	public static void main(String[] args)
//	{
//		//QuizController c = new QuizController();
//		//c.getOpdrachtData();
//	}
}
