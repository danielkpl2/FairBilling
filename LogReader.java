import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Reads the log file
 * @author Daniel Kasprowicz
 *
 */
public class LogReader {
	private ArrayList<ArrayList<String>> log;

	public LogReader(String fileName) throws FileNotFoundException{
		this(new BufferedReader(new FileReader(fileName)));
	}

	public LogReader(BufferedReader bufferedReader){
		try{
			log = new ArrayList<ArrayList<String>>();
			String line;
			int index = 0;
			while((line = bufferedReader.readLine()) != null){
				log.add(new ArrayList<String>());
				String[] lineArray = line.split(" ");
				if(lineArray.length != 3) continue; //skip lines in incorrect format
				String timeStamp = lineArray[0];
				SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
				try{
					dateFormat.parse(timeStamp); //tries to parse the timestamp, if the format is incorrect then the line is skipped
				}catch(ParseException pe){
					continue;			
				}
				for(int i = 0; i < lineArray.length; i++){
					//System.out.print(lineArray[i] + " ");
					log.get(index).add(lineArray[i]);
				}
				//System.out.println();
				index++;
			}
			bufferedReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public int getLogSize(){
		return log.size();
	}
	
	
	
	

}
