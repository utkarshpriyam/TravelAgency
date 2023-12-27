package traveller;

import java.util.ArrayList;

import activity.Activity;
import traveller.Membership.type;

public class Passenger implements Traveller{
	private String name;
	private String passengerNo;
	private Membership.type memberType;
	private ArrayList<Activity> activities;
	private double balance;
	
	
	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Passenger(String name, String passnum, Membership.type mType, double balance) {
		this.name=name;
		this.passengerNo=passnum;
		this.memberType=mType;
		this.balance=balance;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassengerNo() {
		return passengerNo;
	}


	public void setPassengerNo(String passengerNo) {
		this.passengerNo = passengerNo;
	}


	public Membership.type getMemberType() {
		return memberType;
	}
	
	public boolean isPremiumMember() {
		return memberType==type.PREMIUM;
	}

	public void setMemberType(Membership.type memberType) {
		this.memberType = memberType;
	}


	public ArrayList<Activity> getActivities() {
		return activities;
	}


	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public static Passenger generatePremiumPassenger(String name,String passnum) {
		// TODO Auto-generated constructor stub
		return new Passenger(name,passnum,Membership.type.PREMIUM,Double.MAX_VALUE);
	}
	
}
