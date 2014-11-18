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
	private JTextField textField;
	private JLabel lblAuteur;
	private JTextField textField_1;
	private JLabel lblHint;
	private JLabel lblNewLabel;
	private JTextField textField_2;
	private JPanel subPanel;

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
//	public String getVraag()
//	{ return txtVraag.getText(); }
//	
//	public String getJuistAntwoord()
//	{ return txtAntwoord.getText(); }
	

	/**
	 * Create the panel.
	 */
	public BeheerOpdracht() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:max(53dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(90dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(88dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:max(38dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(55dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblType, "2, 4, right, default");
		
		cmbType = new JComboBox<OpdrachtTypen>();
		cmbType.setModel(new DefaultComboBoxModel<OpdrachtTypen>(OpdrachtTypen.values()));
		add(cmbType, "6, 4, 3, 1, fill, default");
		
		JLabel lblVraag = new JLabel("Vraag");
		lblVraag.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblVraag, "2, 6, right, default");
		
		txtVraag = new JTextPane();
		add(txtVraag, "4, 6, 5, 1, fill, default");
		
		subPanel = new JPanel();
		subPanel.setBorder(null);
		add(subPanel, "2, 8, 7, 1, fill, fill");
		
		JLabel lblAntwoord = new JLabel("Antwoord");
		lblAntwoord.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblAntwoord, "2, 10, right, default");
		
		txtAntwoord = new JTextField();
		add(txtAntwoord, "4, 10, 5, 1, fill, default");
		txtAntwoord.setColumns(10);
		
		lblHint = new JLabel("Hint");
		add(lblHint, "2, 12, right, default");
		
		textField = new JTextField();
		add(textField, "4, 12, 5, 1, fill, default");
		textField.setColumns(10);
		
		lblAuteur = new JLabel("Max Pogingen");
		add(lblAuteur, "2, 14, right, default");
		
		textField_1 = new JTextField();
		add(textField_1, "4, 14, fill, default");
		textField_1.setColumns(10);
		
		lblNewLabel = new JLabel("Max Antwoordtijd");
		add(lblNewLabel, "6, 14, right, default");
		
		textField_2 = new JTextField();
		add(textField_2, "8, 14, fill, default");
		textField_2.setColumns(10);
		
		btnOpslaan = new JButton("Opslaan");
		add(btnOpslaan, "4, 16, 3, 1");
		
		btnSluiten = new JButton("Sluiten");
		add(btnSluiten, "8, 16");

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
