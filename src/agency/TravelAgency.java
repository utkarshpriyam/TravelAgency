package agency;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import activity.Activity;
import destination.Destination;
import travelPackage.TravelPackage;
import traveller.Passenger;

public class TravelAgency {
	ArrayList<Passenger> passengers;
	ArrayList<TravelPackage> packages;
	Map<String, Activity> activitiesMap;
	
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public ArrayList<TravelPackage> getPackages() {
		return packages;
	}

	public void setPackages(ArrayList<TravelPackage> packages) {
		this.packages = packages;
	}

	public TravelAgency() {
		passengers= new ArrayList<Passenger>();
		packages=new ArrayList<TravelPackage>();
		activitiesMap=new HashMap<>(); 
	}
	
	public void addActivity(Activity activity) {
		activitiesMap.put(activity.getName(),activity);
	}
	
	
	
	public Map<String, Activity> getActivitiesMap() {
		return activitiesMap;
	}

	public void setActivitiesMap(Map<String, Activity> activitiesMap) {
		this.activitiesMap = activitiesMap;
	}

	public Activity getActivityByName(String activityName) {
		return activitiesMap.get(activityName);
	}
	
	public TravelPackage getTravelPackageByName(String name) {
		for(TravelPackage pack: packages) {
			if(name.equals(pack.getName()))
				return pack;
		}
		return null;
	}
	
	public Passenger getPassengerByNumber(String passengerNumber) {
		return passengers.stream().filter(passenger->passenger.getPassengerNo().equals(passengerNumber)).findAny().get();
	}
	
	public Destination getDestinationByName(String packageName,String destinationName) {
		TravelPackage travelPackage=getTravelPackageByName(packageName);
		if(travelPackage==null)
			return null;
		return travelPackage.getDestinationByName(destinationName);
	}
	
	public void AddPassenger(Passenger pass) {
		passengers.add(pass);
	}
	
	public void addPackage(TravelPackage pack) {
		packages.add(pack);
	}
	
	public boolean isPassengerPresent(String passNumber) {
		for(Passenger pass: passengers) {
			if(passNumber.equals(pass.getPassengerNo()))
				return true;
		}
		return false;
	}
	
	public boolean isPackageNamePresent(String name) {
		for(TravelPackage pack:packages) {
			if(name.equals(pack.getName()))
				return true;
		}
		return false;
	}

	public Passenger getPassengerByName(String passengerName) {
		// TODO Auto-generated method stub
		return passengers.stream().filter(passenger->passenger.getName().equals(passengerName)).findAny().get();
	}
}
