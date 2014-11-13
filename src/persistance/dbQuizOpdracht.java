package persistance;

public class dbQuizOpdracht { // implements Storable {

	private int quizIndex;
	private int opdrachtIndex;
	private int maxScore;
	
	public dbQuizOpdracht(int quizIndex, int OpdrachtIndex, int maxScore ) {
		this.quizIndex = quizIndex;
		this.opdrachtIndex = OpdrachtIndex;
		this.maxScore = maxScore;
	}

	public int getQuizIndex() {
		return quizIndex;
	}

	public int getOpdrachtIndex() {
		return opdrachtIndex;
	}

	public int getMaxScore() {
		return maxScore;
	}

//	@Override
//	public String[] getDataForDb() {
//		// TODO Auto-generated method stub
//		String[] values = new String[3];
//		values[0] = quizIndex;
//		values[1] =
//		return null;
//	}
}
