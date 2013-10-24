package rfen347.softeng206;

import java.util.ArrayList;
import java.util.List;

import rfen347.softeng206.MainActivity.ListItemClickedListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//DeleteContactActivity allows user to delete contacts from list
public class DeleteContactActivity extends Activity {
	//fields
	public DatabaseHandler db = new DatabaseHandler(this);
	private Button deleteContact;
	private Button cancel;
	private ListView listView;
	private CheckBox checkbox;
	private Contact selectedContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_contact);
		//Reference layout xml to contact
		deleteContact= (Button)findViewById(R.id.delete2_contact_button);
		cancel = (Button)findViewById(R.id.cancel_contact_button);
		listView = (ListView)findViewById(R.id.delete_listview);
	
		//sets up the ListView
		setupListView();
		
		//cancel sends user back to the MainActivity
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DeleteContactActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		})
		;
		//deleteContact deletes ALL CONTACTS from the database and updates the list
		//contains a dialogue box asking whether user would like to delete
		deleteContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DeleteContactActivity.this);
				
				dialogBuilder.setTitle("Are you sure you wish to remove contact(s)?");
				dialogBuilder.setMessage("This cannot be undone!");
				dialogBuilder.setPositiveButton("No", null);
				dialogBuilder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
					//deletes when clicked "OK"
					@Override
					public void onClick(DialogInterface arg0, int arg1){ 
						List<Contact> cl = db.getAllContacts();
						//iterate through all the contacts and delete from database
						for(Contact contact: db.getAllContacts()){
							db.deleteContact(contact);
						}
						
						String displayString = "Contacts deleted";
						Toast.makeText(deleteContact.getContext(), displayString, Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
						intent.setClass(DeleteContactActivity.this, MainActivity.class);
						startActivity(intent);
					}
				});
				dialogBuilder.setCancelable(true);
				
				dialogBuilder.create().show();
				
			}
			
		}
		);

	}

	ContactList c = new ContactList();
	private void setupListView(){
		//sets up the listView using adapter
		ListAdapter listAdapter = new CustomListAdapter(DeleteContactActivity.this, db.getAllContacts());
		
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new ListItemClickedListener());
	}
	
	class ListItemClickedListener implements AdapterView.OnItemClickListener{

		//Everytime an item is clicked, the selectedContact gets deleted
		@Override
		public void onItemClick(AdapterView<?> parentView, View clickedView, int clickedViewPosition, long id) {
			
			Contact selectedContact = db.getAllContacts().get(clickedViewPosition);
			db.deleteContact(selectedContact);
		
			setupListView();
		}
		
	}
	
	

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
			View listItemView = inflater.inflate(R.layout.custom_list_item_delete_layout, null);

			//Similar to the creation of listviews and buttons, we reference the TextView to its ID found in custom_list_item_layout.xml.
			//Sets the style
			TextView fn = (TextView)listItemView.findViewById(R.id.list_item_text_FN2);
			TextView ln = (TextView)listItemView.findViewById(R.id.list_item_text_LN2);
			TextView dob = (TextView)listItemView.findViewById(R.id.list_item_text_mobile2);
			
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
