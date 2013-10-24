package rfen347.softeng206;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

/*This class is where the contacts manager displays the contacts list
 *It is called as soon as the application is started */
public class MainActivity extends Activity {
	//List of fields within the MainActivity
	public DatabaseHandler db = new DatabaseHandler(this);
	private List<Contact> contacts;
	private Button addContact;
	private Button deleteContact;
	private Button sortFirst;
	private Button sortLast;
	private Button sortNum;
	private Button sortDat;
	private EditText search;
	private ListView listView;
	int textlength = 0;
	
	//john
	private List<Contact> c = null;

	ArrayList<String> text_sort = new ArrayList<String>();
	ArrayList<Integer> image_sort = new ArrayList<Integer>();
	ArrayList<Integer> id_list = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*Reference all the Buttons, EditTexts and ListViews from the layout xml to its corresponding
		  fields
		 */
		search = (EditText)findViewById(R.id.search);
		addContact= (Button)findViewById(R.id.add_contact_button);
		deleteContact= (Button)findViewById(R.id.delete_contact_button);
		sortFirst= (Button)findViewById(R.id.sort_first_button);
		sortLast= (Button)findViewById(R.id.sort_last_button);
		sortNum = (Button)findViewById(R.id.sort_num_button);
		sortDat = (Button)findViewById(R.id.sort_date_button);
		listView = (ListView)findViewById(R.id.main_listview);
		
		//Gets all the contacts stored in the database and places them into 'contacts'
		contacts = db.getAllContacts();
		//use setupListView method to setup the list view
		setupListView(contacts);
		
		/*adding a listener such that the button responds 
		 *addContact button switches activity to the AddContactActivity */
		addContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				//switch activity
				intent.setClass(MainActivity.this, AddContactActivity.class);
				startActivity(intent);
				
			}
		})
		;
		/*adding a listener such that the button responds 
		 *deleteContact button switches activity to the DeleteContactActivity */
		deleteContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				//switch activity
				intent.setClass(MainActivity.this, DeleteContactActivity.class);
				startActivity(intent);
				
			}
		})
		;
		//========================================================================
		/*The following 4 buttons:
		 * 						sortFirst
		 * 						sortLast
		 * 						sortNum
		 * 						sortDat
		 	sort the contacts by their First name, Last name, Mobile Phone Number and Date added.
		 	Uses an onClickListener and a Comparator that compares between the first name, last name and mobile phone numbers*/
		sortFirst.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				contacts = db.getAllContacts();
				Collections.sort(contacts, new Comparator<Contact>(){

					@Override
					public int compare(Contact c1, Contact c2) {
						//sort by first name by comparing c1 to c2
						return c1.getFirstName().toLowerCase().compareTo(c2.getFirstName().toLowerCase());
}
				});	
			setupListView(contacts); //setup the ListView once again
			}
		})
		;

		sortLast.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				contacts = db.getAllContacts();
				Collections.sort(contacts, new Comparator<Contact>(){

					@Override
					public int compare(Contact c1, Contact c2) {
						//sort by last name by comparing c1 to c2
						return c1.getLastName().toLowerCase().compareTo(c2.getLastName().toLowerCase());
}
					
				});
				
				
			setupListView(contacts);}
		})
		;
		
		
		sortNum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				contacts = db.getAllContacts();
				Collections.sort(contacts, new Comparator<Contact>(){

					@Override
					public int compare(Contact c1, Contact c2) {
						//sort by mobile by comparing c1 to c2
						return c1.getMobile().toLowerCase().compareTo(c2.getMobile().toLowerCase());
}
					
				});
				
				
			setupListView(contacts);}
		})
		;
		
		sortDat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			contacts = db.getAllContacts();
			setupListView(contacts);}
		})
		;
		
		//===============================================================================================
		/*This feature 'search' allows users to search through the list by their first names*/
		search.addTextChangedListener(new TextWatcher() {
			//onTextChanged gets called whenever the user changes input on the search bar.
	           @Override
	           public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
	        	   textlength = search.getText().length(); //length of the input text
	        	   text_sort.clear();
	        	   id_list.clear();
	        	   image_sort.clear();
	        	   c = new ArrayList<Contact>(); //c is where the list will be stored
	        	   
	        	   for (int i = 0; i < contacts.size(); i++) {
	        		   /*is the length of the input is less than length of the name*/
	        		     if (textlength <=contacts.get(i).getFirstName().length()) {
	        		    	 /*if the search equals the subsequence of the user input*/
	        		    	if (search.getText().toString().equalsIgnoreCase((String) contacts.get(i).getFirstName().subSequence(0, textlength))){
	        		    		//add the contact into c
	        		    		c.add(db.getAllContacts().get(i));
	        		    	}
	        		    	
	        		     }
	        		     
	        	   }
	        	   //setup this new listview by putting in the contact array list c.
	        	   setupListView(c);
	        	   
	        	   
	           }
	           
	           //a method inside the search (not used)
	           @Override
	           public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
	                   int arg3) {
	               // TODO Auto-generated method stub
	                
	           }
	            //a method inside the search (not used)
	           @Override
	           public void afterTextChanged(Editable arg0) {
	               // TODO Auto-generated method stub                          
	           }
	       });
	   }   
		

	//this sets up the List view 
	private void setupListView(List<Contact> contacts){
		ListAdapter listAdapter = new CustomListAdapter(MainActivity.this, contacts);
		listView.setAdapter(listAdapter);
		//setOnItemClickListener allows you to interact with the contacts in the list
		listView.setOnItemClickListener(new ListItemClickedListener());
		
		
		
	}
	
	//ListItemClickedListener lists and handles what happens when item is clicked.
	//When item is clicked, the screen changes to ViewContactActivity of the corresponding contact that you clicked
	class ListItemClickedListener implements AdapterView.OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parentView, View clickedView, int clickedViewPosition, long id) {
			//get the selected contact by getting its view position
			Contact selectedContact = contacts.get(clickedViewPosition);
			
			Intent intent = new Intent();
			//if c (the list that is created when the search function is called) exists
			if (c != null) {
				//pass the clicked contact from 'c' into the intent 'contact'
			intent.putExtra("contact", c.get(clickedViewPosition));
			} else {
				//pass the clicked contact from 'contacts' into the intent 'contact'
				intent.putExtra("contact", contacts.get(clickedViewPosition));
			}
			//switch screen to the View Contact screen
			intent.setClass(MainActivity.this, ViewContactActivity.class);
			startActivity(intent);
			
			}
		
	}
	//this is a CustomListAdapter that handles the layout of the list
	private class CustomListAdapter extends ArrayAdapter<Contact>{
		private Context context;
		private List<Contact> contacts;
		CustomListAdapter(Context context, List<Contact> contact){
			super(context, android.R.layout.simple_list_item_1, contact);
			
			this.context = context;
			this.contacts = contact;
		}
		//this method defines the layout used for each item
		public View getView(int position, View convertView, ViewGroup parent){
			//Converts a layout described in xml into java code
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View listItemView = inflater.inflate(R.layout.custom_list_item_layout, null);
			
			//Similar to the creation of listviews and buttons, we reference the TextView to its ID found in custom_list_item_layout.xml.
			//Sets the style
			TextView fn = (TextView)listItemView.findViewById(R.id.list_item_text_FN);
			TextView ln = (TextView)listItemView.findViewById(R.id.list_item_text_LN);
			TextView dob = (TextView)listItemView.findViewById(R.id.list_item_text_mobile);
			
			//Sets the text for each position
			fn.setText(contacts.get(position).getFirstName());
			ln.setText(contacts.get(position).getLastName());
			dob.setText(contacts.get(position).getMobile());
			
			return listItemView;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
