package persistance;

public class DbHandler {
	
//	private static volatile DbHandler instance;
//	
//	public synchronized static DbHandler getInstance()
//	{
//		if (instance == null)
//			synchronized (DbHandler.class) {
//				if (instance == null)
//					instance = new DbHandler();
//			}
//		
//		return instance;
//	}
//	
//	private DbHandler()
//	{
//		
//	}
	
	
	public IDbStrategy getCatalogus(DbStoreType storeType)
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
