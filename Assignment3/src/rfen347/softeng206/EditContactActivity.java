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

public class EditContactActivity extends Activity {
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
		contact = (Contact) intent.getSerializableExtra("contact2");
		
		editfn.setText(contact.getFirstName(), TextView.BufferType.EDITABLE);
		editln.setText(contact.getLastName(),TextView.BufferType.EDITABLE);
		editmp.setText(contact.getMobile(),TextView.BufferType.EDITABLE);
		edithn.setText(contact.getHome(),TextView.BufferType.EDITABLE);
		editwn.setText(contact.getWork(),TextView.BufferType.EDITABLE);
		editem.setText(contact.getEmail(),TextView.BufferType.EDITABLE);
		editad.setText(contact.getAddress(),TextView.BufferType.EDITABLE);
		editdob.setText(contact.getDob(),TextView.BufferType.EDITABLE);
		
		
		
	
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_contact, menu);
		return true;
	}

}
