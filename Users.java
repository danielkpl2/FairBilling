import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Users {
	private Map<String, User> users;
	private LocalTime earliestTimestamp;
	private LocalTime latestTimestamp;
	
	@SuppressWarnings("unchecked")
	public Users(SessionLog log){
		users = new HashMap<String, User>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		//ArrayList<ArrayList<String>> l = (ArrayList<ArrayList<String>>)(log).getLog();
		
		earliestTimestamp = LocalTime.parse(log.getLog().get(0).get(0), formatter);
		latestTimestamp = LocalTime.parse(log.getLog().get(log.getLog().size()-1).get(0), formatter);
		
		//System.out.println(earliestTimestamp + " " + latestTimestamp);
		
		for(ArrayList<String> entry: log.getLog()){
			
			
			LocalTime timestamp = LocalTime.parse(entry.get(0), formatter);
			
			addSession(entry.get(1), timestamp, entry.get(2));
		}
		
		checkOrphanedSessions();
		enumerateSessionPairs();
	}
	
	private void enumerateSessionPairs() {
		for(Map.Entry<String, User> user: users.entrySet()){
			CircularArrayList<LocalTime> startList = new CircularArrayList<LocalTime>();
			CircularArrayList<LocalTime> endList = new CircularArrayList<LocalTime>();
			startList.addAll(user.getValue().getSessionStarts());
			endList.addAll(user.getValue().getSessionEnds());
			 
			
			//LocalTime [][][][] sessions = new LocalTime[startList.size()][startList.size()][endList.size()][2];
			LocalTime [][][][] sessions = new LocalTime[10][10][10][10];
			
			for(int i = 0; i < startList.size(); i++){
				startList.setIndex(i);
				endList.setIndex(i);
				for(LocalTime startTime: startList){
					int j = startList.getIndex();
					for(LocalTime endTime: endList){
						int k = endList.getIndex();
						//sessions[j][k] = new ArrayList
						System.out.println(startTime + " - " + endTime);
						sessions[i][j][k][0] = startTime;
						//System.out.println(sessions[i][j][k][0]);
						sessions[i][j][k][1] = endTime;
						//System.out.println(sessions);
					}
					System.out.println();
				}
				System.out.println();
			}
			
			//System.out.print(startList);
		}
		
		
		
		
	}

	private void checkOrphanedSessions() {
		for(Map.Entry<String, User> user: users.entrySet()){
			//check that the session pair counts are right, correct if not
			int count;
			if((count = user.getValue().getSessionEnds().size() - user.getValue().getSessionStarts().size()) != 0){
				for(int i = 0; i < count; i++){
					user.getValue().getSessionStarts().add(0, earliestTimestamp);
				}
			}else if((count = user.getValue().getSessionStarts().size() - user.getValue().getSessionEnds().size()) != 0){
				for(int i = 0; i < count; i++){
					user.getValue().getSessionEnds().add(latestTimestamp);
				}
			}
			//check that each session start has at least one possible end
			for(LocalTime sessionStart: user.getValue().getSessionStarts()){
				boolean found = false;
				for(LocalTime sessionEnd: user.getValue().getSessionEnds()){
					if(sessionStart.isBefore(sessionEnd)){
						found = true;
						break;
					}
				}
				if(!found){
					user.getValue().getSessionEnds().add(latestTimestamp);
				}
			}
			
			//check that each session end has at least one possible start
			for(LocalTime sessionEnd: user.getValue().getSessionEnds()){
				boolean found = false;
				for(LocalTime sessionStart: user.getValue().getSessionStarts()){
					if(sessionEnd.isAfter(sessionStart)){
						found = true;
						break;
					}
				}
				if(!found){
					user.getValue().getSessionStarts().add(0, earliestTimestamp);
				}
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
