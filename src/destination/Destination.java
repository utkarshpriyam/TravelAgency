package destination;

import java.util.ArrayList;

import activity.Activity;

public class Destination {
	String name;
	ArrayList<Activity> activities;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}
	
	public void addActivities(Activity activity) {
		activities.add(activity);
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public Destination(String name) {
		this.name=name;
		this.activities = new ArrayList<Activity>();
	}
	
	public Destination(String name, ArrayList<Activity> acts) {
		this.name = name;
		this.activities= acts;
	}
	
}
