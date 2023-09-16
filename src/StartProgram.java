import java.util.List;
import java.util.Scanner;
import controller.CalendarItemHelper;
import model.CalendarItem;

/**
 * @author Owennn - osherrmann
 * CIS175 - Fall 2023
 * Sep 15, 2023
 */

public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static CalendarItemHelper cih = new CalendarItemHelper();
	
	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter an event name: ");
		String event = in.nextLine();
		System.out.print("Enter an date in MM/DD/YYYY format: ");
		String date = in.nextLine();
		String[] dateArray = date.split("/");
		int month, day, year;
		month = Integer.parseInt(dateArray[0]);
		day = Integer.parseInt(dateArray[1]);
		year = Integer.parseInt(dateArray[2]);
		CalendarItem toAdd = new CalendarItem(month, day, year, event);
		cih.insertItem(toAdd);
	}
	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter an event name: ");
		String event = in.nextLine();
		System.out.print("Enter an date in MM/DD/YYYY format: ");
		String date = in.nextLine();
		String[] dateArray = date.split("/");
		int month, day, year;
		month = Integer.parseInt(dateArray[0]);
		day = Integer.parseInt(dateArray[1]);
		year = Integer.parseInt(dateArray[2]);
		CalendarItem toDelete = new CalendarItem(month, day, year, event);
		cih.deleteItem(toDelete);
	}
	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Event");
		System.out.println("2 : Search by Date");
		int searchBy = in.nextInt();
		in.nextLine();
		List<CalendarItem> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the Event name: ");
			String eventName = in.nextLine();
			foundItems = cih.searchForItemByEvent(eventName);
		} else {
			System.out.print("Enter an date in MM/DD/YYYY format: ");
			String date = in.nextLine();
			String[] dateArray = date.split("/");
			int month, day, year;
			month = Integer.parseInt(dateArray[0]);
			day = Integer.parseInt(dateArray[1]);
			year = Integer.parseInt(dateArray[2]);
			foundItems = cih.searchForItemByDate(day, month, year);
		}
		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
		for (CalendarItem l : foundItems) {
			System.out.println(l.getId() + " : " + l.toString());
		}
		System.out.print("Which ID to edit: ");
		int idToEdit = in.nextInt();
		CalendarItem toEdit = cih.searchForItemById(idToEdit);
		System.out.println("Retrieved " + toEdit.returnEventDetails());
		System.out.println("1 : Update Event");
		System.out.println("2 : Update Date");
		int update = in.nextInt();
		in.nextLine();
		if (update == 1) {
			System.out.print("New Event: ");
			String newEvent = in.nextLine();
			toEdit.setEvent(newEvent);
		} else if (update == 2) {
			System.out.print("New date (MM/DD/YYYY): ");
			String date = in.nextLine();
			String[] dateArray = date.split("/");
			toEdit.setMonth(Integer.parseInt(dateArray[0]));
			toEdit.setDay(Integer.parseInt(dateArray[1]));
			toEdit.setYear(Integer.parseInt(dateArray[2]));
		}
		cih.updateItem(toEdit);
		} else {
			System.out.println("---- No results found");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();
	}
	
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to your very own command line calendar!---");
		while (goAgain) {
			System.out.println("* Select an item:");
			System.out.println("* 1 -- Add an event");
			System.out.println("* 2 -- Edit an event");
			System.out.println("* 3 -- Delete an event");
			System.out.println("* 4 -- View the calendar");
			System.out.println("* 5 -- Exit the awesome program");
			System.out.print("* Your selection: ");
			int selection = in.nextInt();
			in.nextLine();
			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				cih.cleanUp();
				System.out.println(" Goodbye! ");
				goAgain = false;
			}
		}
	}
	
	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<CalendarItem> allItems = cih.showAllItems();
		for(CalendarItem singleItem : allItems) {
			System.out.println(singleItem.returnEventDetails());
		}
	}
}