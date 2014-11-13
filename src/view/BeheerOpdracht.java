package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import model.quiz.opdrachten.OpdrachtTypen; //TODO
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;

public class BeheerOpdracht extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4576969072450809500L;
	private JTextPane txtVraag;
	private JTextField txtAntwoord;
	private JComboBox<OpdrachtTypen> cmbType;
	private JButton btnOpslaan;
	private JButton btnSluiten;

	public JTextPane getTxtVraag() {
		return txtVraag;
	}

	public JTextField getTxtAntwoord() {
		return txtAntwoord;
	}

	public JComboBox<OpdrachtTypen> getCmbType() {
		return cmbType;
	}

	public JButton getBtnOpslaan() {
		return btnOpslaan;
	}

	public JButton getBtnSluiten() {
		return btnSluiten;
	}

	/**
	 * Create the panel.
	 */
	public BeheerOpdracht() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:max(53dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(93dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:max(58dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblType, "2, 4, right, default");
		
		cmbType = new JComboBox<OpdrachtTypen>();
		cmbType.setModel(new DefaultComboBoxModel<OpdrachtTypen>(OpdrachtTypen.values()));
		add(cmbType, "4, 4, 3, 1, fill, default");
		
		JLabel lblVraag = new JLabel("Vraag");
		lblVraag.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblVraag, "2, 6, right, default");
		
		txtVraag = new JTextPane();
		add(txtVraag, "4, 6, 3, 1, fill, default");
		
		btnOpslaan = new JButton("Opslaan");
		
		JLabel lblAntwoord = new JLabel("Antwoord");
		lblAntwoord.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblAntwoord, "2, 8, right, default");
		
		txtAntwoord = new JTextField();
		add(txtAntwoord, "4, 8, 3, 1, fill, default");
		txtAntwoord.setColumns(10);
		add(btnOpslaan, "4, 10");
		
		btnSluiten = new JButton("Sluiten");
		add(btnSluiten, "6, 10");

	}

	public void printOpdrachtData(String type, String vraag, String antwoord)
	{
		cmbType.setSelectedItem(type);
		txtVraag.setText(vraag);
		txtAntwoord.setText(antwoord);		
	}
	
	public void addButtonListener(ActionListener listener)
	{
		btnOpslaan.addActionListener(listener);
		btnSluiten.addActionListener(listener);
	}
	
}
