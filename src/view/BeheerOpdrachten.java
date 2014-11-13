package view;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JButton;

public class BeheerOpdrachten extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8712629275888779880L;
	private JTable table;
	private JButton btnClose;
	/**
	 * Create the panel.
	 */
	
	public BeheerOpdrachten() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("min:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		table = new JTable();

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		add(scrollPane, "4, 4, 3, 1, fill, fill");
		
		btnClose = new JButton("Sluiten");
		add(btnClose, "6, 6");

	}
	
	public void fillOpdrachtenTable(TableModel model)
	{
		table.setModel(model);
		TableColumn colModel_ID = table.getColumnModel().getColumn(0);
		colModel_ID.setMinWidth(0);
		//colModel_ID.setMaxWidth(0);
		//colModel_ID.setResizable(false);
	}
	
	public void addButtonListener(ActionListener listener)
	{
		btnClose.addActionListener(listener);
	}
	
	public void addTableMouseListener(MouseAdapter listener)
	{
		table.addMouseListener(listener);
	}


}
