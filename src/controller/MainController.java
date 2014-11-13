package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;

import model.Catalogi;
import view.Main;

public class MainController {

	private Main mainForm;
	private Catalogi cat;
	/**
	 * The app starts here!
	 * @param args
	 */
	public static void main(String[] args)
	{
		new MainController();
	}
	
	public MainController() {
		mainForm = new Main();
		mainForm.addWindowListener(new WindowListener());
		cat = new Catalogi();
		cat.loadData();
		mainForm.addMenuListener(new MenuClickHandler());
		new StartController(mainForm, cat);
		mainForm.showMe();
	}	
	
	private class MenuClickHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent action) 
		{
			if (action.getSource() instanceof JMenuItem)
			{
				JMenuItem jItem = (JMenuItem)action.getSource();
				if(jItem.equals(mainForm.getMntmInstellingen())) //App instellingen (property file) -> restart required...
				{
					System.out.println("Instellingen");
				}
				else if(jItem.equals(mainForm.getMntmAfsluiten())) //afsluiten
				{
					close();
					System.out.println("Afsluiten");
				}
				else if(jItem.equals(mainForm.getMntmOpdrachten())) //beheer opdrachten
				{
					new BeheerOpdrachtenController(mainForm, cat);
				}
				else if(jItem.equals(mainForm.getMntmQuizenTesten())) //Beheer quizen
				{
					System.out.println("Quizen / testen");
				}
			}
		}		
	}	
	
	public void close()
	{
		//if 
		mainForm.setVisible(false);		
		mainForm = null;
		cat = null;
		System.exit(0);
	}
	
	private class WindowListener implements java.awt.event.WindowListener
	{
		@Override
		public void windowClosing(WindowEvent arg0) {
			close();
		}
		@Override public void windowActivated(WindowEvent arg0) {}
		@Override public void windowClosed(WindowEvent arg0) {}		
		@Override public void windowDeactivated(WindowEvent arg0) {}
		@Override public void windowDeiconified(WindowEvent arg0) {}
		@Override public void windowIconified(WindowEvent arg0) {}
		@Override public void windowOpened(WindowEvent arg0) {}		
	}
}
