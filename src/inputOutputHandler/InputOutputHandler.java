package inputOutputHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import activity.Activity;
import agency.TravelAgency;
import destination.Destination;
import travelPackage.TravelPackage;
import traveller.Passenger;
import traveller.Membership;

public class InputOutputHandler implements UserInput {

	private TravelAgency travelPartner;
	
	public InputOutputHandler(TravelAgency agency) {
		this.travelPartner=agency;
	}
	
	public void showPrompt() {
		System.out.println("Press 1 to add a travel package");
		System.out.println("Press 2 to add a passenger");
		System.out.println("Press 3 to add a destination to a package");
		System.out.println("Press 4 to add an activity to destination");
		System.out.println("Press 5 to add a passenger to a travel package");
		System.out.println("Press 6 to add a passenger to an activity");
		System.out.println("Press 7 to print itinerary of a travel package");
		System.out.println("Press 8 to print passenger list of travel package");
		System.out.println("Press 9 to print the details of a passenger");
		System.out.println("Press 10 to print activities with available seats");
		System.out.println("Press 11 to exit the program");
	}
	
	public void addPackage(Scanner sc) {
		System.out.println("Enter details of the package to be created");
		try {
			System.out.println("Enter package name");
			String packageName= sc.next();
			if(travelPartner.isPackageNamePresent(packageName)) {
				System.out.println("Package name already taken");
				return;
			}
			System.out.println("Enter capacity of package");
			int capacity=sc.nextInt();
			if(capacity<=0) {
				throw new Exception("Illegal capacity value");
			}
			TravelPackage pack= new TravelPackage(packageName,capacity);
			travelPartner.addPackage(pack);
		}catch (Exception e) {
			System.out.println(e);
			System.out.println("Error in creating package");
		}
		
	}
	
	public void addPassenger(Scanner sc) {
		System.out.println("Enter passenger details to add:");
		try {
			System.out.println("Enter the name of the passenger");
			String name=sc.next();
			System.out.println("Enter the passenger number");
			String passengerNumber=sc.next();
			if(travelPartner.isPassengerPresent(passengerNumber)) {
				System.out.println("Passenger number is already taken");
				return;
			}
			System.out.println("Enter the member type");
			System.out.println("Press 1 for Standard");
			System.out.println("Press 2 for Gold");
			System.out.println("Press 3 for Premium");
			int memberType=sc.nextInt();
			if(memberType!=1 && memberType!=2 && memberType!=3) {
				System.out.println("Invalid  value");
				throw new Exception("Invalid  member type");
			}
			Passenger pass;
			if(memberType==3)
				pass=Passenger.generatePremiumPassenger(name, passengerNumber);
			else {
				double balance=sc.nextDouble();
				if(balance<0) {
					throw new Exception("Illegal value for balance");
				}
				if(memberType==2)
					pass=new Passenger(name,passengerNumber,Membership.type.GOLD,balance);
				else
					pass=new Passenger(name,passengerNumber,Membership.type.STANDARD,balance);
			}
			travelPartner.AddPassenger(pass);
			System.out.println("New passenger added");
		}catch (Exception e) {
			System.out.println(e);
			System.out.println("Error in creating passenger");
		}
	}
	
	public void addDestination(Scanner sc) {
		System.out.println("Enter the destination details to add into a package");
		try {
			System.out.println("Enter package name");
			String packageName=sc.next();
			TravelPackage pack=travelPartner.getTravelPackageByName(packageName);
			if(pack==null) {
				throw new Exception("Package name not found");
			}
			System.out.println("Enter destination name");
			String destName=sc.next();
			Destination dest=new Destination(destName);
			pack.addDestination(dest);
			System.out.println("Destination added successfully");
		}catch (Exception e) {
			System.out.println(e);
			return;
		}
	}
	
	public void addPassengerToPackage(Scanner sc) {
		System.out.println("Enter package details to add passenger");
		try {
			System.out.println("Enter package name");
			String name=sc.next();
			TravelPackage travelPackage=travelPartner.getTravelPackageByName(name);
			System.out.println("Enter passenger name");
			String passengerName=sc.next();
			Passenger passenger=travelPartner.getPassengerByName(passengerName);
			travelPackage.addPassenger(passenger);
			System.out.println("Passenger added successfully");
		}catch(Exception e){
			System.out.println(e);
			return;
		}
	}
	
	public void addActivityToDestination(Scanner sc) {
		System.out.println("Enter the activity details to add to a destination");
		try {
			System.out.println("Enter package name");
			String packageName=sc.next();
			System.out.println("Enter Destination name");
			String destinationName=sc.next();
			Destination destination=travelPartner.getDestinationByName(packageName,destinationName);
			System.out.println("Enter activity name");
			String activityName=sc.next();
			System.out.println("Enter activity description");
			String activityDescription=sc.next();
			System.out.println("Enter activity Cost");
			double activityCost=sc.nextDouble();
			if(activityCost<0) {
				throw new Error("Illegal cost of activity");
			}
			System.out.println("Enter activity Capacity");
			int activityCapacity=sc.nextInt();
			if(activityCapacity<0) {
				throw new Error("Illegal capacity of activity");
			}
			Activity activity=new Activity(activityName, activityDescription, activityCost, activityCapacity, destination);
			travelPartner.addActivity(activity);
			destination.addActivities(activity);
		}catch (Exception e) {
			System.out.println(e);
			return;
		}
	}
	
	public void addPassengerToActivity(Scanner sc) {
		System.out.println("Enter details to add Passenger to activity");
		try {
			System.out.println("Enter the number of the passenger");
			String passengerNumber=sc.next();
			Passenger passenger = travelPartner.getPassengerByNumber(passengerNumber);
			System.out.println("Enter activity name");
			String activityName=sc.next();
			Activity activity=travelPartner.getActivityByName(activityName);
			if(activity==null || passenger == null)
				throw new Exception("Invalid details entered");
			activity.addPassenger(passenger);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void printItinerary(Scanner sc) {
		try {
			System.out.println("Enter package name");
			String packageName=sc.next();
			TravelPackage travelPackage=travelPartner.getTravelPackageByName(packageName);
			if(travelPackage == null)
				throw new Exception("Package name invalid");
			System.out.println("Package name: "+travelPackage.getName());
			for(Destination destination: travelPackage.getDestinations()) {
				System.out.println("Destination name: "+destination.getName());
				for(Activity activity: destination.getActivities()) {
					System.out.println("Activity name: "+activity.getName());
					System.out.println("Cost: "+activity.getCost());
					System.out.println("Capacity: "+activity.getCapacity());
					System.out.println("Description: "+activity.getDescription());
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void printPassengerListForPackage(Scanner sc) {
		try {
			System.out.println("Enter Package name");
			String packageName=sc.next();
			TravelPackage travelPackage =travelPartner.getTravelPackageByName(packageName);
			if(travelPackage == null)
				throw new Exception("travel package name is invalid");
			System.out.println("Capacity: "+travelPackage.getPassengerCapacity());
			System.out.println("Currently enrolled: "+travelPackage.getCurrentStrength());
			ArrayList<Passenger> passengers=travelPackage.getPassengerList();
			for(Passenger passenger:passengers) {
				System.out.println("Name: "+passenger.getName());
				System.out.println("Number: "+passenger.getPassengerNo());
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void printPassengerDetails(Scanner sc) {
		try {
			System.out.println("Enter passenger number");
			String passengerNumber=sc.next();
			Passenger passenger= travelPartner.getPassengerByNumber(passengerNumber);
			System.out.println("Name: "+passenger.getName());
			System.out.println("Number: "+passenger.getPassengerNo());
			if(!passenger.isPremiumMember())
				System.out.println("Balance: "+passenger.getBalance());
			for(Activity activity: passenger.getActivities()) {
				System.out.println("Activity name: "+activity.getName());
				System.out.println("At destination: "+activity.getSpot().getName());
				System.out.println("Price paid: "+activity.getPrice(passenger.getMemberType()));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void printAvailableActivities() {
		for(Activity activity:travelPartner.getActivitiesMap().values()) {
			if(activity.hasAvailableSlots()) {
				System.out.println("Activity: "+activity.getName());
				System.out.println("Available slots: "+activity.getAvailableSlots());
			}
		}
	}
	
	public void processInput() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:/Users/Utkarsh/Desktop/TravelAgency.txt"));
		while(scanner.hasNextLine()) {
			showPrompt();
			if(scanner.hasNextInt()) {
				int flag=scanner.nextInt();
				switch(flag) {
				case 1:{
					addPackage(scanner);
					break;
				}
				case 2:{
					addPassenger(scanner);
					break;
				}
				case 3:{
					addDestination(scanner);
					break;
				}
				case 4:{
					addActivityToDestination(scanner);
					break;
				}
				case 5:{
					addPassengerToPackage(scanner);
					break;
				}
				case 6:{
					addPassengerToActivity(scanner);
					break;
				}
				case 7:{
					printItinerary(scanner);
					break;
				}
				case 8:{
					printPassengerListForPackage(scanner);
					break;
				}
				case 9:{
					printPassengerDetails(scanner);
					break;
				}
				case 10:{
					printAvailableActivities();
					break;
				}
				case 11:{
					System.out.println("Thanks for choosing us");
					return;
				}
				default:{
					System.out.println("Invalid selection. Choose again");
				}
				}
			}else {
				System.out.println("The input was unexpected");
				return;
			}
		}
	}
}
