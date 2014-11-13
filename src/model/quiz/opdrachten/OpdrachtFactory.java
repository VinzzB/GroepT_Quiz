package model.quiz.opdrachten;

import model.quiz.*;

public class OpdrachtFactory {

	public static Opdracht getOpdracht(OpdrachtTypen opdrachtType, String[] dbData)
	{		
		switch (opdrachtType) {
		case VRAAG: // "OpdrachtVraag":
			return new OpdrachtVraag(dbData);		
		case MEERKEUZE: // "OpdrachtMeerkeuze":
			return new OpdrachtMeerkeuze(dbData);
		case OPSOMMING: // "OpdrachtOpsomming":
			return new OpdrachtOpsomming(dbData);
		default:
			return null;
		}		
	}
	public static Opdracht getOpdracht(String opdrachtType, String[] dbData)
	{
		OpdrachtTypen type = OpdrachtTypen.valueOf(opdrachtType);
		return getOpdracht(type, dbData);
	}
	
}
