package rfen347.softeng206;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SortContactActivity extends Activity {
	public DatabaseHandler db = new DatabaseHandler(this);
	private Button fnsort;
	private Button lnsort;
	private Button numsort;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sort_contact);
		
		fnsort= (Button)findViewById(R.id.button_1);
		lnsort= (Button)findViewById(R.id.button_2);
		numsort= (Button)findViewById(R.id.button_3);
		
		fnsort.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SortContactActivity.this, MainActivity.class);
				startActivity(intent);
				List<Contact> contacts = db.getAllContacts();
				Collections.sort(contacts, new Comparator<Contact>(){

					@Override
					public int compare(Contact c1, Contact c2) {
						// TODO Auto-generated method stub
						return c1.getFirstName().toLowerCase().compareTo(c2.getFirstName().toLowerCase());
					}
					
				});
				
				
			}
		})
		;
		
		lnsort.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SortContactActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		})
		;
		
		numsort.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SortContactActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		})
		;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sort_contact, menu);
		return true;
	}

}
