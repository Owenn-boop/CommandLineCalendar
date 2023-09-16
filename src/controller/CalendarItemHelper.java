package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.CalendarItem;

public class CalendarItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Calendar");
	
	public void insertItem(CalendarItem calendarItem) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(calendarItem);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<CalendarItem> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<CalendarItem> allItems = em.createQuery("SELECT i FROM CalendarItem i").getResultList();
		return allItems;
	}
	
	public void deleteItem(CalendarItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CalendarItem> typedQuery = em.createQuery("select ci from CalendarItem ci where ci.month = :selectedMonth and ci.day = :selectedDay and ci.year = :selectedYear and ci.event = :selectedEvent", CalendarItem.class);
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedMonth", toDelete.getMonth());
		typedQuery.setParameter("selectedDay", toDelete.getDay());
		typedQuery.setParameter("selectedYear", toDelete.getYear());
		typedQuery.setParameter("selectedEvent", toDelete.getEvent());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		CalendarItem result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public CalendarItem searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		CalendarItem found = em.find(CalendarItem.class, idToEdit);
		em.close();
		return found;
	}

	public List<CalendarItem> searchForItemByEvent(String eventName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CalendarItem> typedQuery = em.createQuery("select ci from CalendarItem ci where ci.event = :selectedEvent", CalendarItem.class);
		typedQuery.setParameter("selectedEvent", eventName);
		List<CalendarItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<CalendarItem> searchForItemByDate(int day, int month, int year) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CalendarItem> typedQuery = em.createQuery("select ci from CalendarItem ci where ci.month = :selectedMonth and ci.day = :selectedDay and ci.year = :selectedYear", CalendarItem.class);
		typedQuery.setParameter("selectedMonth", month);
		typedQuery.setParameter("selectedDay", day);
		typedQuery.setParameter("selectedYear", year);
		List<CalendarItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public void updateItem(CalendarItem toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
