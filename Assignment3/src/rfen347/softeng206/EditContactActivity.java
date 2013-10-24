package rfen347.softeng206;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
//EditContactActivity allows you to edit contact
public class EditContactActivity extends Activity {
	//fields 
	public DatabaseHandler db = new DatabaseHandler(this);
	private Button save;
	private Button cancel;
	private ImageButton picture;
	private EditText editfn;
	private EditText editln;
	private EditText editmp;
	private EditText edithn;
	private EditText editwn;
	private EditText editem;
	private EditText editad;
	private EditText editdob;
	private Contact contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		//Reference the layout xml to its corresponding widgets
		picture = (ImageButton)findViewById(R.id.add_picture);
		save = (Button)findViewById(R.id.save_contact_button);
		cancel = (Button)findViewById(R.id.cancel2_contact_button);
		editfn = (EditText)findViewById(R.id.edit_first_name);
		editln = (EditText)findViewById(R.id.edit_last_name);
		editmp = (EditText)findViewById(R.id.edit_mobile_number);
		edithn = (EditText)findViewById(R.id.edit_home_number);
		editwn = (EditText)findViewById(R.id.edit_work_number);
		editem = (EditText)findViewById(R.id.edit_email_address);
		editad = (EditText)findViewById(R.id.edit_home_address);
		editdob = (EditText)findViewById(R.id.edit_date_of_birth);
		
		Intent intent = getIntent();
		//get the contact from ViewContactActivity
		contact = (Contact) intent.getSerializableExtra("contact2");
		//set the text to be editable by user, but also have it contain the names and numbers.
		editfn.setText(contact.getFirstName(), TextView.BufferType.EDITABLE);
		editln.setText(contact.getLastName(),TextView.BufferType.EDITABLE);
		editmp.setText(contact.getMobile(),TextView.BufferType.EDITABLE);
		edithn.setText(contact.getHome(),TextView.BufferType.EDITABLE);
		editwn.setText(contact.getWork(),TextView.BufferType.EDITABLE);
		editem.setText(contact.getEmail(),TextView.BufferType.EDITABLE);
		editad.setText(contact.getAddress(),TextView.BufferType.EDITABLE);
		editdob.setText(contact.getDob(),TextView.BufferType.EDITABLE);
		//sends the user back to the MainActivity
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(EditContactActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		})
		;
		//Saves the content and updates it
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//grab the text in the EditText and convert to string
				String fn = editfn.getText().toString();
				String ln = editln.getText().toString();
				String mp = editmp.getText().toString();
				String hn = edithn.getText().toString();
				String wn = editwn.getText().toString();
				String em = editem.getText().toString();
				String ad = editad.getText().toString();
				String dob = editdob.getText().toString();
				//set the contact's new fields to be the text inside the EditText
				contact.set_firstName(fn);
				contact.set_lastName(ln);
				contact.set_mobile(mp);
				contact.set_home(hn);
				contact.set_work(wn);
				contact.set_email(em);
				contact.set_address(ad);
				contact.set_dob(dob);
				//update the database with this contact
				db.updateContact(contact);
				//display message
				String displayString = "Contact Updated";
				Toast.makeText(save.getContext(), displayString, Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				//go back to MainActivity
				intent.setClass(EditContactActivity.this, MainActivity.class);
				startActivity(intent);
			}
		})
		;
		
	
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_contact, menu);
		return true;
	}

}
