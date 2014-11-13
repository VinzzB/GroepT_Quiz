package model.quiz.leerling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeerlingCatalogus implements Iterable<Leerling> {

	private List<Leerling> leerlingen;
	
	public LeerlingCatalogus()
	{
		leerlingen = new ArrayList<Leerling>();
		//TODO is an hard coded list atm (replace with db data)		
		leerlingen.add(new Leerling("Vincent Bloemen", 2));
		leerlingen.add(new Leerling("Nathalie Mathieu", 2));
		leerlingen.add(new Leerling("Isaak", 2));
		leerlingen.add(new Leerling("Silvia", 2));
		leerlingen.add(new Leerling("Natalia", 2));
	}

	@Override
	public Iterator<Leerling> iterator() {
		return leerlingen.iterator();
	}
	
}
