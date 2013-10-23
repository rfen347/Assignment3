package rfen347.softeng206;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.QuickContactBadge;
import android.widget.Toast;

public class AddContactActivity extends Activity {
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);
		
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
		
		
		
		
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String fn = editfn.getText().toString();
				String ln = editln.getText().toString();
				String mp = editmp.getText().toString();
				String hn = edithn.getText().toString();
				String wn = editwn.getText().toString();
				String em = editem.getText().toString();
				String ad = editad.getText().toString();
				String dob = editdob.getText().toString();
				
				db.addContact(new Contact(fn, ln, mp, hn, wn, em, ad, dob));
				
				
				
				Intent intent = new Intent();
				intent.setClass(AddContactActivity.this, MainActivity.class);
				startActivity(intent);
				String displayString = "Contact Added";
				Toast.makeText(save.getContext(), displayString, Toast.LENGTH_LONG).show();
			}
		})
		;
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AddContactActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		})
		;
		picture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AddContactActivity.this, GalleryActivity.class);
				startActivity(intent);
				
			}
		})
		;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
		return true;
	}

}
