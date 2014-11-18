package model.quiz.score;

import persistance.Properties;

public class ScoreStrategy {

	//the volatile keyword ensures that multiple thread handle uniqueInstance var correctly
	//when it is being initialized to the Singelton instance
	private volatile static IBerekening uniqueInstance;
	public static synchronized IBerekening getInstance()
	{
		if(uniqueInstance == null)
		{
			synchronized (ScoreStrategy.class) {
				if(uniqueInstance == null)					
				{ uniqueInstance = getBerekening(Properties.getInstance().getScoreStrategy()); }
			}
		}
		return uniqueInstance;
	}
	
	private ScoreStrategy() {};
	private static IBerekening getBerekening(ScoreTypen scoreType )
	{
		switch (scoreType) {
		case GroepT:
			return new ScoreGroepT();
		default:
			return null;
		}
	}
}
