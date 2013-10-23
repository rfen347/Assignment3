package rfen347.softeng206;

import java.util.ArrayList;
import java.util.List;

public class ContactList {

	private List<Contact> displayList = new ArrayList<Contact>();
	public void contactListAdd(Contact contact){
		displayList.add(contact);
		
	}
	private void contactListDelete(Contact contact){
		
	}
	public List<Contact> getDisplayList() {
		return displayList;
	}
}
