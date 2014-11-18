package persistance;

public class DbHandler {
	
	private static volatile IDbStrategy instance;
	
	public synchronized static IDbStrategy getInstance()
	{
		if (instance == null)
			synchronized (DbHandler.class) {
				if (instance == null)
					instance = new DbHandler().getCatalogus(Properties.getInstance().getDbStore());
			}		
		return instance;
	}
	
	private DbHandler()  { /* SINGLETON*/ }	
	
	public IDbStrategy getCatalogus(DbStoreType storeType) //--> Public method only usable in this class. 
	{
		switch (storeType) {
		case TextFile:
			return new DbStoreAsText();
		case MySQL:
			return new DbStoreAsSql();
		default:
			return null;
		}		
	} 
}
