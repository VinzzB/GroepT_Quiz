package view;

import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.CardLayout;

public class Main extends JFrame { // implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9059099498669029713L;

	JMenuItem mntmInstellingen = new JMenuItem("Instellingen");
	JMenuItem mntmAfsluiten = new JMenuItem("Afsluiten");
	JMenuItem mntmOpdrachten = new JMenuItem("Opdrachten");
	JMenuItem mntmQuizenTesten = new JMenuItem("Quizen - Testen");
	public Main()
	{
		super("Quiz Game");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //via windowlistener
		
		setUp();
	}
	
	private void setUp()
	{
		//Create menubar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
						
		//Create Menu - Bestand
		JMenu mnBestand = new JMenu("Bestand");
		menuBar.add(mnBestand);
		mnBestand.add(mntmInstellingen);		
		mnBestand.add(mntmAfsluiten);			
		//Create Menu - Beheer
		JMenu mnNewMenu = new JMenu("Beheer");
		menuBar.add(mnNewMenu);
		mnNewMenu.add(mntmOpdrachten);		
		mnNewMenu.add(mntmQuizenTesten);		
		//cardLayout = new CardLayout(0,0);
		getContentPane().setLayout(new CardLayout(0,0));
		
	
	}
	
	public void showMe()
	{
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 400);
		setVisible(true);
	}
	
//	public static void main(String[] args)
//	{
//		Main t = new Main();
//		t.showMe();	
//	}

	
	public void addPanel(JPanel panel, String panelName)
	{
		CardLayout card = ((CardLayout)getContentPane().getLayout());
		getContentPane().add(panel,panelName);
	    card.show(getContentPane(), panelName);
	}
	
	public void removePanel(JPanel panel)
	{
		//haal vorig jpanel op en maak zichtbaar
		CardLayout card = ((CardLayout)getContentPane().getLayout());
		card.previous(getContentPane());
		//remove panel
		getContentPane().remove(panel);		
	}
	
	public JMenuItem getMntmInstellingen() {
		return mntmInstellingen;
	}

	public JMenuItem getMntmAfsluiten() {
		return mntmAfsluiten;
	}

	public JMenuItem getMntmOpdrachten() {
		return mntmOpdrachten;
	}

	public JMenuItem getMntmQuizenTesten() {
		return mntmQuizenTesten;
	}

	public void addMenuListener(ActionListener listener)
	{
		mntmInstellingen.addActionListener(listener);
		mntmAfsluiten.addActionListener(listener);		
		mntmOpdrachten.addActionListener(listener);
		mntmQuizenTesten.addActionListener(listener);
	}
	
	
	public void displayErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}
}
