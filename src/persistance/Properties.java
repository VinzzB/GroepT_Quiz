package persistance;
import java.io.IOException;

import model.quiz.score.ScoreTypen;


public class Properties {
	private final String scoreTypeString = "scoreStrategyType";
	private final String dbStoreString = "dbStore";
	
	private volatile static Properties uniqueInstance;
	
	public synchronized static Properties getInstance()
	{
		if (uniqueInstance == null)
			synchronized (Properties.class) {
				if (uniqueInstance == null)
					uniqueInstance = new Properties();
			}
		return uniqueInstance;
	}
	
	private ScoreTypen scoreStrategyType;
	private DbStoreType dbStore;
	private Properties() 
	{
		java.util.Properties props = new java.util.Properties();
		try {
			props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			//Load Defaults
		}
		//get Score strategy
		if(props.containsKey(scoreTypeString)) 
		{ scoreStrategyType = ScoreTypen.valueOf(props.getProperty(scoreTypeString)); }
		else //default
		{ scoreStrategyType = ScoreTypen.GroepT; }
		
		//get dbStore type
		if(props.containsKey(dbStoreString)) 
		{ dbStore = DbStoreType.valueOf(props.getProperty(dbStoreString)); }
		else //default
		{ dbStore = DbStoreType.TextFile; }		
	}
	public ScoreTypen getScoreStrategy() {
		return scoreStrategyType;
	}
	public DbStoreType getDbStore() {
		return dbStore;
	}
	
	
}
