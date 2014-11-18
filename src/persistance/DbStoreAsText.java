package persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.quiz.*;
import model.quiz.catalogi.OpdrachtCatalogus;
import model.quiz.opdrachten.OpdrachtFactory;
import model.quiz.status.Status;
import model.quiz.status.Statussen;

public class DbStoreAsText extends DbTextBase {

	private final String quizenPath = "./bin/persistance/data/quizen.dat";
	private final String opdrachtenPath = "./bin/persistance/data/opdrachten.dat";
	private final String quizOpdrachtenPath = "./bin/persistance/data/quizOpdrachten.dat";
	@Override
	public Map<Integer,Opdracht> leesOpdrachten() {
		// Read rows and parse into fields (as array)
		List<String[]> rows = readFile(opdrachtenPath);
		// Map the data with an index number
		Map<Integer,Opdracht> opdrachten = new HashMap<Integer, Opdracht>();		
		for (String[] row : rows) {		
			//Factory for DbData Object			
			DbOpdrachtBase Dbo = DbOpdrachtFactory.getDbOpdracht(row[1], row);
			//Factory for Opdracht object
			opdrachten.put(Integer.parseInt(row[0]), OpdrachtFactory.getOpdracht(Dbo));
		}
		return opdrachten;
	}

	@Override
	public Map<Integer,Quiz> leesQuizen(OpdrachtCatalogus opdrachtenCatalog) {
		// Read rows and parse into fields (as array)
		List<String[]> rows = readFile(quizenPath);
		//Get all Quiz Opdrachten at once
		List<DbQuizOpdracht> quizOpdrachten = leesQuizOpdrachten();
		//Create a new quizen list
		Map<Integer, Quiz> quizen = new HashMap<Integer, Quiz>();
		for (String[] row : rows) {
			//Parse data
			int id = Integer.parseInt(row[0]);
			DbQuiz dataRow = new DbQuiz(row);
			//rebuild Quiz (Status = inConstructie)
			Quiz q = new Quiz(dataRow);
			
			//Koppel opdrachten van huidige quiz (filters quizOpdrachten list)
			List<DbQuizOpdracht> currQuizOpdrachten = quizOpdrachten.stream().filter(p -> p.getQuizIndex() == id).collect(Collectors.toList());
			for (DbQuizOpdracht dbQo : currQuizOpdrachten) {
				Opdracht o = opdrachtenCatalog.getOpdracht(dbQo.getOpdrachtIndex());
				if (o != null)
				{ QuizOpdracht.koppelOpdrachtAanQuiz(q, o, dbQo.getMaxScore()); }
			}
			//set saved status
			q.setStatus(Status.get(Statussen.valueOf(row[5])));
			//add to quizen catalog
			quizen.put(id, q); //new Quiz(row));			
		}
		return quizen;
	}

	private List<DbQuizOpdracht> leesQuizOpdrachten() {
		List<String[]> rows = readFile(quizOpdrachtenPath);
		List<DbQuizOpdracht> values = new ArrayList<DbQuizOpdracht>();
		for (String[] row : rows) {
			values.add(new DbQuizOpdracht(row));
		}
		return values;
	}
	
	@Override
	public void schrijfOpdrachten(Map<Integer,Opdracht> opdrachten) {
		//schrijfBestand(opdrachtenPath, convertToStorableMap(opdrachten));
		//Schrijf opdrachten naar bestand
		List<String[]> opdrData = new ArrayList<String[]>();
		for (Entry<Integer, Opdracht> i : opdrachten.entrySet()) {
			//Fill DbData Object
			DbOpdrachtBase dbDataBase = DbOpdrachtFactory.getDbOpdracht(i.getValue());
			String[] dbRow = dbDataBase.asStringArray(); //fill data array
			dbRow[0] = Integer.toString(i.getKey()); //add key
			opdrData.add(dbRow);			
		}		
		writeFile(opdrachtenPath, opdrData);
	}

	@Override
	public void schrijfQuizen(Map<Integer,Quiz> quizen, OpdrachtCatalogus opdrachtenCatalog) {
		//Schrijf quizen naar bestand
		List<String[]> quizData = new ArrayList<String[]>();
		//Hold a list with gekoppelde opdrachten
		List<String[]> qoList = new ArrayList<String[]>();
		//iterate through quizen
		for (Entry<Integer, Quiz> i : quizen.entrySet()) {
			//get quiz data
			DbQuiz dataRow = new DbQuiz(i.getValue(), i.getKey());
			String[] dbRow = dataRow.asStringArray();
			quizData.add(dbRow);			
			//get gekoppelde opdrachten
			//TODO opsplitsen in aparte method??? ( lees/schrijfGekoppeldeQuizOpdrachten() --> Hoe status?) 
			for (QuizOpdracht qo : i.getValue().getQuizOpdrachten()) {
				Opdracht o = qo.getOpdracht();
				int idx = opdrachtenCatalog.getIndex(o);
				qoList.add(new String[] { i.getKey().toString() ,
										Integer.toString(idx) , 
										Integer.toString(qo.getMaxScore()) });
			}			
		}
		//Schrijf quizen naar bestand.
		writeFile(quizenPath, quizData);
		//Schrijf gekoppelde quiz opdrachten naar bestand	
		writeFile(quizOpdrachtenPath, qoList);
	}
}
