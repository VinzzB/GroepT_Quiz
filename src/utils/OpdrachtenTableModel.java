package utils;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import model.quiz.Opdracht;

public class OpdrachtenTableModel extends AbstractTableModel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 237469763811134532L;

	public OpdrachtenTableModel(Map<Integer, Opdracht> data) {
		// TODO Auto-generated constructor stub
//		this.data = new Object[data.size()][];			
//		int ctr = 0;
//		for (Entry<Integer, Opdracht> entry : data.entrySet()) {
//			this.data[ctr++] = new Object[] { entry.getKey(), entry.getValue().toString() };
//			//this.data.put(entry.getKey(), entry.getValue().toString());
//			
//		}
		this.data = data;				
	}
	
	private Map<Integer, Opdracht> data;
	
	private String[] columns = { "Id","Vraag" };
	//private Object[][] data;
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
		
	@Override
	public Class<?> getColumnClass(int arg0) {
		return getValueAt(0, arg0).getClass(); // super.getColumnClass(arg0);
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columns[arg0].toString(); // super.getColumnName(arg0);
	}

	
	@Override
	public Object getValueAt(int arg0, int arg1) {
			int idx = 0;
			for (Entry<Integer,Opdracht> entry : data.entrySet()) 
			{
				if (idx == arg0)
					if (arg1 == 0)
						return entry.getKey();
					else
						return entry.getValue();
				idx++;
			} 
		return null;
	}
	
}
