package view;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import java.awt.event.ActionEvent;

public class StartPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5298009830384385530L;
	/**
	 * Create the panel.
	 */
	private JComboBox<Entry<Integer, String>> cmbQuizen;	
    private	JButton btnSpelen;
	private JLabel lblNewLabel;
	private JComboBox<String> cmbLeerling;
	public StartPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.MIN_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblNewLabel = new JLabel("Leerling:");
		add(lblNewLabel, "4, 4, right, default");
		
		cmbLeerling = new JComboBox<String>();
		add(cmbLeerling, "6, 4, fill, default");
		
		JLabel lblSpeelQuiz = new JLabel("speel quiz:");
		lblSpeelQuiz.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSpeelQuiz, "4, 6, right, default");
		
		cmbQuizen = new JComboBox<Entry<Integer, String>>();
		add(cmbQuizen, "6, 6, fill, default");
		
		btnSpelen = new JButton("Spelen");

		add(btnSpelen, "6, 8");

	}
	public void fillQuizComboBox(Map<Integer, String> keuzes)
	{
		for (Entry<Integer, String> quiz : keuzes.entrySet()) {
			cmbQuizen.addItem(quiz);
		}
	}
	public void fillLeerlingComboBox(List<String> values)
	{
		for (String string : values) {
			cmbLeerling.addItem(string);	
		}		
	}
	
	@SuppressWarnings("unchecked")
	public Entry<Integer, String> getQuizKeuze()
	{
		Object obj = cmbQuizen.getSelectedItem();
		if (obj instanceof Entry)
			return (Entry<Integer, String>)obj;
		return null;
		
	}
	
	public void setQuizKeuze(Entry<Integer,String> quizKeuze)
	{
		cmbQuizen.setSelectedItem(quizKeuze);
	}
	
	public String getLeerling()
	{
		return (String)cmbLeerling.getSelectedItem();
	}
	public void setLeerling(String value)
	{
		cmbLeerling.setSelectedItem(value);
	}
	
	/**
	 * Adds an actionlistener from the controller
	 * @param speelQuizButton
	 */
	public void addButtonListener(ActionListener speelQuizButton)
	{
		btnSpelen.addActionListener(speelQuizButton);
	}
	

}
