package view;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class speelQuiz extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1693897766434867041L;
	private JTextField txtAntwoord;
	private JTextField txtHint;
	private JTextField txtTijd;
	public JTextField getTxtAntwoord() {
		return txtAntwoord;
	}

	public JTextField getTxtHint() {
		return txtHint;
	}

	public JTextField getTxtTijd() {
		return txtTijd;
	}

	public JTextField getTxtNr() {
		return txtNr;
	}

	public JTextField getTxtPoging() {
		return txtPoging;
	}

	public JTextPane getTxtVraag() {
		return txtVraag;
	}

	public JButton getBtnAnnuleren() {
		return btnAnnuleren;
	}

	public JButton getBtnVolgende() {
		return btnVolgende;
	}

	private JTextField txtNr;
	private JTextField txtPoging;
	private	JTextPane txtVraag;
	private JButton btnAnnuleren;
	private JButton btnVolgende;

	/**
	 * Create the panel.
	 */
	public speelQuiz() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(57dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(22dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNr = new JLabel("Nr:");
		add(lblNr, "2, 2, right, default");
		
		txtNr = new JTextField();
		txtNr.setHorizontalAlignment(SwingConstants.CENTER);
		txtNr.setEditable(false);
		txtNr.setEnabled(false);
		add(txtNr, "4, 2, fill, default");
		txtNr.setColumns(10);
		
		JLabel lblPoging = new JLabel("Poging");
		add(lblPoging, "6, 2, right, default");
		
		txtPoging = new JTextField();
		txtPoging.setHorizontalAlignment(SwingConstants.CENTER);
		txtPoging.setEnabled(false);
		txtPoging.setEditable(false);
		add(txtPoging, "8, 2, fill, default");
		txtPoging.setColumns(10);
		
		JLabel lblVraag = new JLabel("Vraag:");
		add(lblVraag, "2, 4, right, default");
		
		txtVraag = new JTextPane();
		txtVraag.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtVraag.setEnabled(false);
		txtVraag.setEditable(false);
		add(txtVraag, "4, 4, 7, 1, default, fill");
		
		JLabel lblHint = new JLabel("Hint");
		add(lblHint, "2, 6, right, default");
		
		txtHint = new JTextField();
		txtHint.setEnabled(false);
		txtHint.setEditable(false);
		add(txtHint, "4, 6, 7, 1, fill, default");
		txtHint.setColumns(10);
		
		JLabel lblTijd = new JLabel("Tijd");
		add(lblTijd, "2, 8, right, default");
		
		txtTijd = new JTextField();
		txtTijd.setHorizontalAlignment(SwingConstants.CENTER);
		txtTijd.setEnabled(false);
		txtTijd.setEditable(false);
		add(txtTijd, "4, 8, fill, default");
		txtTijd.setColumns(10);
		
		JLabel lblSeconds = new JLabel("Seconds");
		add(lblSeconds, "6, 8");
		
		JLabel lblAntwoord = new JLabel("Antwoord:");
		add(lblAntwoord, "2, 10, right, default");
		
		txtAntwoord = new JTextField();
		add(txtAntwoord, "4, 10, 7, 1, fill, default");
		txtAntwoord.setColumns(10);
		
		btnAnnuleren = new JButton("Annuleren");
		add(btnAnnuleren, "4, 12");
		
		btnVolgende = new JButton("> Volgende");
		add(btnVolgende, "6, 12, 5, 1");

	}

	public String getAntwoord()
	{
		return txtAntwoord.getText();
	}
	
	public void setOpdracht(int nr, String vraag, String hint, int Poging)
	{
		txtNr.setText(Integer.toString(nr));
		txtVraag.setText(vraag);
		txtHint.setText(hint);
		txtPoging.setText(Integer.toString(Poging));
		txtAntwoord.setText(null);
		txtAntwoord.requestFocus();
		
	}
	public void setTijdRemaining(int tijdInSec)
	{
		txtTijd.setText(Integer.toString(tijdInSec));
	}
	
	public void addButtonListener(ActionListener listener)
	{
		btnAnnuleren.addActionListener(listener);
		btnVolgende.addActionListener(listener);		
	}

}
