package persistance;

import utils.Arrays;
import model.quiz.opdrachten.OpdrachtMeerkeuze;
import model.quiz.opdrachten.OpdrachtTypen;

public class DbOpdrachtMeerkeuze extends DbOpdrachtBase {
	
	private	String[] keuzen;
	private final String Delimiter = ";";
	DbOpdrachtMeerkeuze(String[] dataRow) {
		super(dataRow);
		keuzen = dataRow[9].split(Delimiter);
	}
	public DbOpdrachtMeerkeuze(OpdrachtMeerkeuze opdracht) {
		super(opdracht);
		keuzen = opdracht.getKeuzen();
	}
	public String[] getKeuzen() {
		return keuzen;
	}
	@Override
	public OpdrachtTypen getType() {
		return OpdrachtTypen.MEERKEUZE;
	}
	@Override
	public String[] asStringArray() {
		String[] dataRow = new String[10];
		super.fillStringArray(dataRow);
		dataRow[9] = Arrays.Join(Delimiter, keuzen);
		return dataRow;
	}

}
