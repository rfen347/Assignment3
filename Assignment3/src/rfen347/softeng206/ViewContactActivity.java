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

public class ViewContactActivity extends Activity {
	public DatabaseHandler db = new DatabaseHandler(this);
	private Button edit;
	private Button back;
	private ImageButton pic;
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
		
	/*	pic = (ImageButton)findViewById(R.id.contact_picture);
		edit = (Button)findViewById(R.id.edit_button);
		back = (Button)findViewById(R.id.back_button);
		fn = (EditText)findViewById(R.id.view_first_name);
		ln = (EditText)findViewById(R.id.view_last_name);
		mp = (EditText)findViewById(R.id.view_mobile_number);
		hn = (EditText)findViewById(R.id.view_home_number);
		wn = (EditText)findViewById(R.id.view_work_number);
		em = (EditText)findViewById(R.id.view_email_address);
		ad = (EditText)findViewById(R.id.view_home_address);
		dob = (EditText)findViewById(R.id.view_date_of_birth);*/
		
		Intent intent = getIntent();
		contact = (Contact) intent.getSerializableExtra("contact");
		
	/*	fn.setText(contact.getFirstName());
		ln.setText(contact.getLastName());
		mp.setText(contact.getMobile());
		hn.setText(contact.getHome());
		wn.setText(contact.getWork());
		em.setText(contact.getEmail());
		ad.setText(contact.getAddress());
		dob.setText(contact.getDob());*/
		
		
		
	/*	back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(ViewContactActivity.this, MainActivity.class);
				startActivity(intent);
			}
		})
		;*/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contact, menu);
		return true;
	}

}
