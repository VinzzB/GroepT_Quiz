package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.quiz.Opdracht;
import view.BeheerOpdracht;
import view.Main;

public class BeheerOpdrachtController {

	private Main mainForm;
	private Opdracht opdracht;
	private BeheerOpdracht panel;
	public BeheerOpdrachtController(Main mainForm, Opdracht opdracht) {
		this.mainForm = mainForm;
		this.opdracht = opdracht;
		panel = new BeheerOpdracht();		
		panel.addButtonListener(new ButtonListener());
		panel.printOpdrachtData(opdracht.getType().toString(), opdracht.getVraag(), opdracht.getJuisteAntwoord());
	}
	
	public void showPanel()
	{
		mainForm.addPanel(panel, "BeheerOpdracht");
	}	
	public void closePanel()
	{
		mainForm.removePanel(panel);
		mainForm = null;
		opdracht = null;
		panel.removeAll();
		panel = null;
	}
	
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if (arg0.getSource() instanceof JButton)
			{
				JButton button = (JButton)arg0.getSource();
				if (button.equals(panel.getBtnOpslaan()))
				{
					//TODO change opdracht type?
					//TODO Fout bij opslaan vraag Meerkeuze / opsomming  tgv override getVraag()... 
					opdracht.setOpdracht(panel.getTxtVraag().getText(), panel.getTxtAntwoord().getText(), 0, 0, opdracht.getDatumRegistratie(), opdracht.getAuteur());
					closePanel();
				}
				else if (button.equals(panel.getBtnSluiten()))
				{
					closePanel();
				}
			}
		}
		
	}
}
