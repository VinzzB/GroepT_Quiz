package persistance;

import java.util.Map;

import model.quiz.*;
import model.quiz.catalogi.OpdrachtCatalogus;

public class DbStoreAsSql implements IDbStrategy {

	@Override
	public Map<Integer, Opdracht> leesOpdrachten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Quiz> leesQuizen(OpdrachtCatalogus opdrachtenCatalog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void schrijfOpdrachten(Map<Integer, Opdracht> opdrachten) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schrijfQuizen(Map<Integer, Quiz> quizen,
			OpdrachtCatalogus opdrachtenCatalog) {
		// TODO Auto-generated method stub
		
	}
}
