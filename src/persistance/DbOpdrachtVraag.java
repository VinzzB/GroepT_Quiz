package persistance;

import model.quiz.Opdracht;
import model.quiz.opdrachten.OpdrachtTypen;

public class DbOpdrachtVraag extends DbOpdrachtBase {

	DbOpdrachtVraag(Opdracht opdracht) {
		super(opdracht);
	}

	public DbOpdrachtVraag(String[] dbData) {
		super(dbData);
	}

	@Override
	public OpdrachtTypen getType() {
		return OpdrachtTypen.VRAAG;
	}

	@Override
	public String[] asStringArray() {
		String[] dataRow = new String[9];
		super.fillStringArray(dataRow);
		return dataRow;
	}
}
