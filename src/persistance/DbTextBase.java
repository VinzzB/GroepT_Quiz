package persistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import utils.Arrays;
/**
 * Abstracte classe om data in tekstbestanden om te zetten en omgekeerd.
 * @author bloemevi
 *
 */
public abstract class DbTextBase implements IDbStrategy {

	final String delimiter = "\t "; //Tab + keycode ALT_0160 (= Empty char != Space)' -> Avoids split errors when user used tabs
	/**
	 * Reads a whole file and parses the lines as rows. Each row gets separated into fields (as a String array). 
	 * @param FilePath The path to read from
	 * @return A list of rows. Each row contains an array of type String that holds the field values of that row.
	 */
	protected List<String[]> readFile(String pFilePath)
	{		
			List<String[]> data = new ArrayList<String[]>();	
			try (Scanner s = new Scanner(new File(pFilePath))) 
			{
				while (s.hasNext())
				{
					String currLine = s.nextLine();
					if (currLine != null && currLine != "")
					{
						String[] fields = currLine.split(delimiter); 
						data.add(fields);								
					}
				}
			}
			catch(FileNotFoundException e)
			{ System.out.println("Kan het bestand niet vinden op de opgegeven locatie: " + pFilePath); }			
			return data;
	}
	
	protected boolean writeFile(String pFilePath, List<String[]> data)
	{		
			try (PrintWriter print = new PrintWriter(new File(pFilePath))) 
			{				
				for (String[] row : data) {
					StringBuilder currLine = new StringBuilder();
					currLine.append(Arrays.Join(delimiter, row)); // ArrayToStringRow(row));
					print.println(currLine);
				}
				return true;
			}
			catch(FileNotFoundException e)
			{ System.out.println("Kan het bestand niet vinden op de opgegeven locatie: " + pFilePath); }			
			return false;
	}
//	protected boolean schrijfBestand(String pFilePath, Map<Integer,Storable> dataObjects) {
//		//List<String[]> lines = new ArrayList<String[]>();
//		try (PrintWriter print = new PrintWriter(new File(pFilePath))) 
//		{
//			for (Entry<Integer, Storable> i : dataObjects.entrySet()) {
//				//Get values from Storable object
//				String[] row = i.getValue().getDataForDb();
//				//Add index value
//				row[0] = i.getKey().toString();
//				//Transform data to one line
//				String line = Arrays.Join(delimiter, row);
//				//print line to file
//				print.println(line);
//			}
//			return true;
//		}
//		catch(FileNotFoundException e)
//		{ System.out.println("Kan het bestand niet vinden op de opgegeven locatie: " + pFilePath); }			
//		return false;
//		//writeFile(opdrachtenPath, lines);
//	}
	
//	/**
//	 * Converteert een Map<Integer,Object> naar een Map<Integer,Storable>. 
//	 * De values in de Map moeten de Storable interface implementeren.
//	 * @param values een Map<Integer,SomeStorableObject> boject
//	 * @return een Map<Integer,Storable> object
//	 */
//	protected <T> Map<Integer,Storable> convertToStorableMap(Map<Integer,T> values)
//	{
//		Map<Integer, Storable> data = new HashMap<Integer, Storable>();
//		for (Entry<Integer, T> value : values.entrySet()) {
//			if (value.getValue() instanceof Storable)
//			data.put(value.getKey(), (Storable) value.getValue());
//		}	
//		return data;
//	}
//	
//	private String ArrayToStringRow(String[] row)
//	{
//		StringBuilder line = new StringBuilder();
//		for (int i = 0; i < row.length; i++) {
//			line.append(row[i]);
//			if (i != row.length)
//			{ line.append(delimiter); }
//		}
//		return line.toString();
//	}
}
