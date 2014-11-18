package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.quiz.Leerling;
import model.quiz.Quiz;
import model.quiz.catalogi.Catalogi;
import view.Main;
import view.StartPanel;

public class StartController {

	private Main mainForm;
	private StartPanel panel; // = new StartPanel();
	
		
	
	public StartController(Main mainForm, StartPanel startPanel) {
		this.mainForm = mainForm;
		this.panel = startPanel;
		
		//event listeners
		panel.addButtonListener(new ButtonListener());
		
		//Vul Combobox met quizen
		fillCmbQuizen();
		fillCmbLeerling();
		//Add panel to cardView
		mainForm.addPanel(panel,"Start");		
	}
	
	private void fillCmbQuizen()
	{
		//Converteer naar integer / Strings.
		Map<Integer, String> entries = new HashMap<Integer, String>();
		
		for (Entry<Integer, Quiz> quizEntry : Catalogi.getInstance().getQuizCatalogus()) {
			entries.put(quizEntry.getKey(), quizEntry.getValue().toString());
		}
		panel.fillQuizComboBox(entries);
	}
	private void fillCmbLeerling()
	{
		List<String> values = new ArrayList<String>();
		//TODO: leerling
		values.add("Vincent");
		panel.fillLeerlingComboBox(values);
	}
	
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Entry<Integer,String> keuze = panel.getQuizKeuze();
			
			Quiz q = Catalogi.getInstance().getQuiz(keuze.getKey());	
			
			//TODO: leerling
			Leerling l = new Leerling("Vincent", 1);
			
			QuizController qc = new QuizController(q, mainForm);
			qc.speelQuiz(l);
		}		
	}
	
}
