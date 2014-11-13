package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import model.Catalogi;
import utils.OpdrachtenTableModel;
import view.BeheerOpdrachten;
import view.Main;

public class BeheerOpdrachtenController {

	private Main mainForm;
	private Catalogi cat;
	private BeheerOpdrachten panel;
	public BeheerOpdrachtenController(Main mainForm, Catalogi cat) {
		this.mainForm = mainForm;
		this.cat = cat;		
		panel = new BeheerOpdrachten();
		panel.fillOpdrachtenTable(new OpdrachtenTableModel(cat.getOpdrachtCatalogus().getOpdrachten()));
		panel.addButtonListener(new ButtonListener());
		panel.addTableMouseListener(new TableMouseListener());
		mainForm.addPanel(panel, "beheerOpdrachten");
	}
	
	public void closePanel()
	{
		mainForm.removePanel(panel);
		mainForm = null;
		cat = null;
		panel = null;
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			closePanel();
		}
		
	}	
	
	private class TableMouseListener extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent arg0) {
			if (arg0.getClickCount() == 2)
			{
				JTable table = (JTable)arg0.getSource();
				int rowIdx = table.rowAtPoint(arg0.getPoint());
				BeheerOpdrachtController b = new BeheerOpdrachtController(mainForm, cat.getOpdracht((int)table.getValueAt(rowIdx, 0)));
				b.showPanel();
			}
		}
	}
	
	
}
