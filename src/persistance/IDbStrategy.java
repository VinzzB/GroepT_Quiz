package persistance;

import java.util.Map;

import model.quiz.*;
import model.quiz.catalogi.OpdrachtCatalogus;

public interface IDbStrategy {
	/* 
	 * Class DbHandler
	 * GetCatalogus()
	 * SetCaltalogus()
	 * 
	 * Interface = IDbStrategy
	 * 
	 * setDbStrategy(IDbStrategy dbs)
	*  - LeesOpdracht
	*  - LeesQuizen
	*  - LeesQuizOpdrachten --> Koppel Opdracht aan quiz
	*  ----> ABSTRACTE CLASSE VOOR LEES & SCHRIJF ALGORITME (Template patroon)
	*/
	//********************
	//1ste manier
	//********************
	Map<Integer,Opdracht> leesOpdrachten();
	Map<Integer,Quiz> leesQuizen(OpdrachtCatalogus opdrachtenCatalog);
	//List<dbQuizOpdracht> leesQuizOpdrachten();
	
	void schrijfOpdrachten(Map<Integer,Opdracht> opdrachten);
	void schrijfQuizen(Map<Integer,Quiz> quizen, OpdrachtCatalogus opdrachtenCatalog);
	//void schrijfQuizOpdrachten(List<Quiz> quizen);
	
//	//********************
//	//2DE MANIER
//	//********************
//	<T> T getData(dbDataTables dbTable);
//	<T> void setData(T data);
	
}
