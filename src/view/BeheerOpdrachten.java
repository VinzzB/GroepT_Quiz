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
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import utils.OpdrachtenTableModel;

public class BeheerOpdrachten extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8712629275888779880L;
	private JTable table;
	private JButton btnClose;
	private JButton btnAdd;
	private JButton btnEdit;
	private final JButton btnDel = new JButton("Del");
	/**
	 * Create the panel.
	 */
	
	public BeheerOpdrachten() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(67dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(66dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(81dlu;min)"),
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
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		add(scrollPane, "4, 4, 7, 1, fill, fill");
		add(btnDel, "4, 6");
		
		btnEdit = new JButton("Edit");
		add(btnEdit, "6, 6");
		
		btnAdd = new JButton("Add");
		add(btnAdd, "8, 6");
		
		btnClose = new JButton("Sluiten");
		add(btnClose, "10, 6");

	}
	
	public void fillOpdrachtenTable(TableModel model)
	{
		table.setModel(model);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		TableColumn colModel_ID = table.getColumnModel().getColumn(0);
		colModel_ID.setMinWidth(0);
		colModel_ID.setMaxWidth(0);
		colModel_ID.setResizable(false);
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
