import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;


public class User {
	private String name;
	private ArrayList<LocalTime> sessionStarts;
	private ArrayList<LocalTime> sessionEnds;
	private ArrayList<ArrayList<LocalTime[]>> sessionPairs;

	public User(String name, LocalTime timestamp, String status){
		this.name = name;
		sessionStarts = new ArrayList<LocalTime>();
		sessionEnds = new ArrayList<LocalTime>();
		sessionPairs = new ArrayList<ArrayList<LocalTime[]>>();
		addSessionStatus(timestamp, status);
	}
	
	
	protected void enumerateSessionPairs(){
		CircularArrayList<LocalTime> startList = new CircularArrayList<LocalTime>();
		CircularArrayList<LocalTime> endList = new CircularArrayList<LocalTime>();
		startList.addAll(sessionStarts);
		endList.addAll(sessionEnds);
		
		ArrayList<ArrayList<LocalTime[]>> sessions = sessionPairs;
		
		for(int i = 0; i < startList.size(); i++){
			endList.setIndex(i);				
			Iterator itStart = startList.iterator();
			Iterator itEnd = endList.iterator();
			sessions.add(new ArrayList<LocalTime[]>());
			while(itStart.hasNext() && itEnd.hasNext()){
				LocalTime startTime = (LocalTime) itStart.next();
				LocalTime endTime = (LocalTime) itEnd.next();
				
				if(endTime.isBefore(startTime)){
					sessions.remove(sessions.size()-1); 
					startList.setIndex(0); //without this, the start times would continue the loop
					break;
				}
				sessions.get(sessions.size()-1).add(new LocalTime[]{startTime, endTime});
			}	
		}
	}
	
	/**
	 * @return the sessionPairs
	 */
	public ArrayList<ArrayList<LocalTime[]>> getSessionPairs() {
		return sessionPairs;
	}



	/**
	 * @param sessionPairs the sessionPairs to set
	 */
	public void setSessionPairs(ArrayList<ArrayList<LocalTime[]>> sessionPairs) {
		this.sessionPairs = sessionPairs;
	}

	public void addSession(LocalTime timestamp, String status){
		addSessionStatus(timestamp, status);
	}
	
	private void addSessionStatus(LocalTime timestamp, String status){
		if(status.equals("Start")){
			sessionStarts.add(timestamp);
		}else if(status.equals("End")){
			sessionEnds.add(timestamp);
		}
	}
	
	public String toString(){
		return "starts: " + sessionStarts + "\nends: " + sessionEnds;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the sessionStarts
	 */
	public ArrayList<LocalTime> getSessionStarts() {
		return sessionStarts;
	}



	/**
	 * @param sessionStarts the sessionStarts to set
	 */
	public void setSessionStarts(ArrayList<LocalTime> sessionStarts) {
		this.sessionStarts = sessionStarts;
	}



	/**
	 * @return the sessionEnds
	 */
	public ArrayList<LocalTime> getSessionEnds() {
		return sessionEnds;
	}


	/**
	 * @param sessionEnds the sessionEnds to set
	 */
	public void setSessionEnds(ArrayList<LocalTime> sessionEnds) {
		this.sessionEnds = sessionEnds;
	}
}
