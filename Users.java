import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Users {
	private Map<String, User> users;
	
	
	@SuppressWarnings("unchecked")
	public Users(SessionLog log){
		users = new HashMap<String, User>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		//ArrayList<ArrayList<String>> l = (ArrayList<ArrayList<String>>)(log).getLog();
		
		LocalTime earliestTimestamp = LocalTime.parse(log.getLog().get(0).get(0), formatter);
		LocalTime latestTimestamp = LocalTime.parse(log.getLog().get(log.getLog().size()-1).get(0), formatter);
		
		//System.out.println(earliestTimestamp + " " + latestTimestamp);
		
		for(ArrayList<String> entry: (ArrayList<ArrayList<String>>)(log).getLog()){
			
			
			LocalTime timestamp = LocalTime.parse(entry.get(0), formatter);
			
			addSession(entry.get(1), timestamp, entry.get(2));
		}
		
		checkOrphanedSessions(); 
	}
	
	private void checkOrphanedSessions() {
		for(Map.Entry<String, User> user: users.entrySet()){
			for(LocalTime sessionStart: user.getValue().getSessionStarts()){
				
			}
		}
		
	}

	private void addSession(String username, LocalTime timestamp, String status){
		if(users.containsKey(username)){
			users.get(username).addSession(timestamp, status);
		}else{
			users.put(username, new User(username, timestamp, status));
		}
	}
	
	public String toString(){
		String s = "";
		for(Map.Entry<String, User> user: users.entrySet()){
			s += user.getKey() + ": " + user.getValue() + "\n";
		}
		return s;
		
	}

}
