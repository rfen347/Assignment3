package rfen347.softeng206;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//Activity that allows you to view the contact
public class ViewContactActivity extends Activity {
	//fields
	public DatabaseHandler db = new DatabaseHandler(this);
	private Button edit;
	private Button back;
	private ImageView pic;
	private TextView fn;
	private TextView ln;
	private TextView mp;
	private TextView hn;
	private TextView wn;
	private TextView em;
	private TextView ad;
	private TextView dob;
	private Contact contact;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);
		//Referencing layout xml to its corresponding widgets
		pic = (ImageView)findViewById(R.id.contact_picture);
		edit = (Button)findViewById(R.id.edit_button);
		back = (Button)findViewById(R.id.back_button);
		fn = (TextView)findViewById(R.id.view_first_name);
		ln = (TextView)findViewById(R.id.view_last_name);
		mp = (TextView)findViewById(R.id.view_mobile_number);
		hn = (TextView)findViewById(R.id.view_home_number);
		wn = (TextView)findViewById(R.id.view_work_number);
		em = (TextView)findViewById(R.id.view_email_address);
		ad = (TextView)findViewById(R.id.view_home_address);
		dob = (TextView)findViewById(R.id.view_date_of_birth);
		
		Intent intent = getIntent();
		//get the contact that was clicked previously
		contact = (Contact) intent.getSerializableExtra("contact");
		//set the text to the contact's details
		fn.setText(contact.getFirstName());
		ln.setText(contact.getLastName());
		mp.setText(contact.getMobile());
		hn.setText(contact.getHome());
		wn.setText(contact.getWork());
		em.setText(contact.getEmail());
		ad.setText(contact.getAddress());
		dob.setText(contact.getDob());
		//if a picture exists, set the picture
		if (contact.getPicture() != null){
			if (contact.getPicture().length > 0) {
				pic.setImageBitmap(db.getBitmap(contact.getPicture()));
			}
		}
		//goes back to main activity
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ViewContactActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		//edit sends user to EditContactActivity and passes the information of this contact
		//into the new activity
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("contact2", db.getContact(contact.get_id()));
				intent.setClass(ViewContactActivity.this, EditContactActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contact, menu);
		return true;
	}

}
