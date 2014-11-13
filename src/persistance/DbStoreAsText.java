package persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import utils.date.normal.Datum;
import model.quiz.*;
import model.quiz.opdrachten.OpdrachtCatalogus;
import model.quiz.opdrachten.OpdrachtFactory;
import model.quiz.status.Status;
import model.quiz.status.Statussen;

public class DbStoreAsText extends DbTextBase implements IDbStrategy {

	private final String quizenPath = "./bin/persistance/data/quizen.dat";
	private final String opdrachtenPath = "./bin/persistance/data/opdrachten.dat";
	private final String quizOpdrachtenPath = "./bin/persistance/data/quizOpdrachten.dat";
	@Override
	public Map<Integer,Opdracht> leesOpdrachten() {
		// TODO Auto-generated method stub
		List<String[]> rows = readFile(opdrachtenPath);
		//Map the data with an index number
		Map<Integer,Opdracht> opdrachten = new HashMap<Integer, Opdracht>();		
		for (String[] row : rows) {
			//Factory!			
			opdrachten.put(Integer.parseInt(row[0]), OpdrachtFactory.getOpdracht(row[1], row));
		}
		return opdrachten;
	}

	@Override
	public Map<Integer,Quiz> leesQuizen(OpdrachtCatalogus opdrachtenCatalog) {
		// TODO Auto-generated method stub
		List<String[]> rows = readFile(quizenPath);
		List<dbQuizOpdracht> quizOpdrachten = leesQuizOpdrachten();
		
		Map<Integer, Quiz> quizen = new HashMap<Integer, Quiz>();
		for (String[] row : rows) {
			//rebuild Quiz (Status = inConstructie)
			int id = Integer.parseInt(row[0]);
			Quiz q = new Quiz(row[1]);			
			q.setLeerjaren(row[2]);
			q.setIsTest(Boolean.parseBoolean(row[3]));
			q.setIsUniekeDeelname(Boolean.parseBoolean(row[4]));
			q.setDatumRegistratie(new Datum(row[6]));
			q.setAuteur(Leraar.valueOf(row[7]));
			
			//Koppel opdrachten van huidige quiz (filters quizOpdrachten list)
			List<dbQuizOpdracht> currQuizOpdrachten = quizOpdrachten.stream().filter(p -> p.getQuizIndex() == id).collect(Collectors.toList());
			for (dbQuizOpdracht dbQo : currQuizOpdrachten) {
				Opdracht o = opdrachtenCatalog.getOpdracht(dbQo.getOpdrachtIndex());
				if (o != null)
				{ QuizOpdracht.koppelOpdrachtAanQuiz(q, o, dbQo.getMaxScore()); }
			}
			//zet saved status
			q.setStatus(Status.get(Statussen.valueOf(row[5])));
			//add to quizen catalog
			quizen.put(id, q); //new Quiz(row));			
		}
		return quizen;
	}

	private List<dbQuizOpdracht> leesQuizOpdrachten() {
		List<String[]> rows = readFile(quizOpdrachtenPath);
		List<dbQuizOpdracht> values = new ArrayList<dbQuizOpdracht>();
		for (String[] row : rows) {
			values.add(new dbQuizOpdracht(Integer.parseInt(row[0]), 
										  Integer.parseInt(row[1]), 
										  Integer.parseInt(row[2])));
		}
		return values;
	}
	
	@Override
	public void schrijfOpdrachten(Map<Integer,Opdracht> opdrachten) {
		//schrijfBestand(opdrachtenPath, convertToStorableMap(opdrachten));
		//Schrijf opdrachten naar bestand
		List<String[]> opdrData = new ArrayList<String[]>();
		for (Entry<Integer, Opdracht> i : opdrachten.entrySet()) {
			String[] dbRow = i.getValue().getDataForDb();
			dbRow[0] = Integer.toString(i.getKey());
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
			String[] dbRow = i.getValue().getDataForDb();
			dbRow[0] = Integer.toString(i.getKey());
			quizData.add(dbRow);			
			//get gekoppelde opdrachten
			for (QuizOpdracht qo : i.getValue().getQuizOpdrachten()) {
				Opdracht o = qo.getOpdracht();
				int idx = opdrachtenCatalog.getIndex(o);
				qoList.add(new String[] { i.getKey().toString() ,
										Integer.toString(idx) , 
										Integer.toString(qo.getMaxScore()) });
			}			
		}
		
		writeFile(quizenPath, quizData);
		//Schrijf gekoppelde quizen naar bestand	
		writeFile(quizOpdrachtenPath, qoList);
	}
	
}
