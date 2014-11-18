package persistance;

import model.quiz.Opdracht;
import model.quiz.opdrachten.OpdrachtMeerkeuze;
import model.quiz.opdrachten.OpdrachtOpsomming;
import model.quiz.opdrachten.OpdrachtTypen;

class DbOpdrachtFactory {

	public static DbOpdrachtBase getDbOpdracht(OpdrachtTypen typeOpdracht, String[] dataRow)
	{		
		switch (typeOpdracht) {
		case VRAAG: // "OpdrachtVraag":
			return new DbOpdrachtVraag(dataRow);		
		case MEERKEUZE: // "OpdrachtMeerkeuze":
			return new DbOpdrachtMeerkeuze(dataRow);
		case OPSOMMING: // "OpdrachtOpsomming":
			return new DbOpdrachtOpsomming(dataRow);
		default:
			return null;
		}		
	}
	
	public static DbOpdrachtBase getDbOpdracht(String typeOpdracht, String[] dataRow)
	{
		return getDbOpdracht(OpdrachtTypen.valueOf(typeOpdracht), dataRow);
	}
	
	public static DbOpdrachtBase getDbOpdracht(Opdracht opdracht)
	{
		// or evaluate as if(opdracht instanceOf OpdrachtVraag), ...
		switch (opdracht.getType()) {
		case VRAAG:
			return new DbOpdrachtVraag(opdracht);
		case MEERKEUZE:
			return new DbOpdrachtMeerkeuze((OpdrachtMeerkeuze)opdracht);
		case OPSOMMING:
			return new DbOpdrachtOpsomming((OpdrachtOpsomming)opdracht);
		default:
			return null;
		}
	}
}
