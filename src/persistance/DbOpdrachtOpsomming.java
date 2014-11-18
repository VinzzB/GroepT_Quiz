package persistance;

import model.quiz.opdrachten.OpdrachtOpsomming;
import model.quiz.opdrachten.OpdrachtTypen;

public class DbOpdrachtOpsomming extends DbOpdrachtBase {

	boolean inJuisteVolgorde;
	
	DbOpdrachtOpsomming(String[] dataRow) {
		super(dataRow);
		inJuisteVolgorde = Boolean.getBoolean(dataRow[9]);
	}

	public DbOpdrachtOpsomming(OpdrachtOpsomming opdracht) {
		super(opdracht);
		inJuisteVolgorde = opdracht.getInJuisteVolgorde();
	}

	public boolean getInJuisteVolgorde() {
		return inJuisteVolgorde;
	}

	@Override
	public OpdrachtTypen getType() {
		return OpdrachtTypen.OPSOMMING;
	}

	@Override
	public String[] asStringArray() {
		String[] dataRow = new String[10];
		super.fillStringArray(dataRow);
		dataRow[9] = Boolean.toString(inJuisteVolgorde);
		return dataRow;
	}
}
