package model.quiz.opdrachten;

import persistance.*;
import model.quiz.*;

public class OpdrachtFactory {

	public static Opdracht getOpdracht(DbOpdrachtBase dbData)
	{		
		switch (dbData.getType()) {
		case VRAAG: // "OpdrachtVraag":
			return new OpdrachtVraag(dbData);		
		case MEERKEUZE: // "OpdrachtMeerkeuze":
			return new OpdrachtMeerkeuze((DbOpdrachtMeerkeuze)dbData);
		case OPSOMMING: // "OpdrachtOpsomming":
			return new OpdrachtOpsomming((DbOpdrachtOpsomming)dbData);
		default:
			return null;
		}		
	}
//	public static Opdracht getOpdracht(String opdrachtType, DbOpdrachtBase dbData)
//	{
//		OpdrachtTypen type = OpdrachtTypen.valueOf(opdrachtType);
//		return getOpdracht(type, dbData);
//	}
	
}
