import java.time.LocalTime;
import java.util.ArrayList;


public class User {
	private String name;
	private ArrayList<LocalTime> sessionStarts;
	private ArrayList<LocalTime> sessionEnds;
	//private Date timeStamp;
	//private Session sessions;

	public User(String name, LocalTime timestamp, String status){
		this.name = name;
		sessionStarts = new ArrayList<LocalTime>();
		sessionEnds = new ArrayList<LocalTime>();
		addSessionStatus(timestamp, status);
		
		//System.out.println(name + " " + timestamp);
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

}
