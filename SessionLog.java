import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Reads the log file
 * @author Daniel Kasprowicz
 *
 */
public class SessionLog {
	private ArrayList<ArrayList<String>> log;

	public SessionLog(String fileName) throws FileNotFoundException{
		this(new BufferedReader(new FileReader(fileName)));
	}

	public SessionLog(BufferedReader bufferedReader){
		try{
			log = new ArrayList<ArrayList<String>>();
			String line;
			int index = 0;
			while((line = bufferedReader.readLine()) != null){

				if(!validate(line)) continue;
				
				String[] lineArray = line.split(" ");
				log.add(new ArrayList<String>());
				for(int i = 0; i < lineArray.length; i++){
					log.get(index).add(lineArray[i]);
				}
				index++;
			}
			bufferedReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Validates the log entry format
	 * @param line a log entry
	 * @return true if the format is valid, false if not
	 */
	private static boolean validate(String line){
		String[] lineArray = line.split(" ");
		
		if(lineArray.length != 3) return false; //skip lines in incorrect format
		
		//http://stackoverflow.com/questions/25873636/regex-pattern-for-exactly-hhmmss-time-string
		if(!lineArray[0].matches("(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)")) return false;

		if(!lineArray[2].equals("Start") && !lineArray[2].equals("End")) return false;
		
		return true;
	}
	
	public int getLogSize(){
		return log.size();
	}
	
	public ArrayList<ArrayList<String>> getLog(){
		return log;
	}
		
	public String toString(){
		String s = "";
		for(ArrayList<String> l: log){
			s += l + "\n";
		}
		return s;
	}
}
