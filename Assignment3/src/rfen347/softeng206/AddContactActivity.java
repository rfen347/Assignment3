package rfen347.softeng206;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

//this activity handles adding contacts into database
public class AddContactActivity extends Activity {
	//add fields
	public DatabaseHandler db = new DatabaseHandler(this);
	private Button save;
	private Button cancel;
	private ImageView picture;
	private EditText editfn;
	private EditText editln;
	private EditText editmp;
	private EditText edithn;
	private EditText editwn;
	private EditText editem;
	private EditText editad;
	private EditText editdob;
	
	private Bitmap bitmap;
	
	private static int RESULT_LOAD_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);
		
		//Reference the layout xml to the fields of the widgets.
		picture = (ImageView)findViewById(R.id.add_picture);
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
		
		
		
		//This button saves the contact into database
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Convert what was written in the editText into Strings
				String fn = editfn.getText().toString();
				String ln = editln.getText().toString();
				String mp = editmp.getText().toString();
				String hn = edithn.getText().toString();
				String wn = editwn.getText().toString();
				String em = editem.getText().toString();
				String ad = editad.getText().toString();
				String dob = editdob.getText().toString();
				
				byte[] cbytes = new byte[]{};
				if (bitmap != null) {
					cbytes = db.getByteArray(bitmap);
				}
				Contact contactToAdd = new Contact(fn, ln, mp, hn, wn, em, ad, dob, cbytes);
		
				db.addContact(contactToAdd);
				
				
				//Display a message, and switch back into main activity
				Intent intent = new Intent();
				intent.setClass(AddContactActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				String displayString = "Contact Added";
				Toast.makeText(save.getContext(), displayString, Toast.LENGTH_LONG).show();
			}
		})
		;
		//this button simply cancels the add and goes back to main activity
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AddContactActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		})
		;
		//this button allows you to click a picture and choose a picture you want to add
		//for the contact
		picture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 
                startActivityForResult(i, RESULT_LOAD_IMAGE);
				
			}
		})
		;
		
	}
	
	@Override
    protected void onActivityResult(int requestC, int resultC, Intent data) {
        super.onActivityResult(requestC, resultC, data);
         //if the triggered activity was indeed image gallery
        if (requestC == RESULT_LOAD_IMAGE && resultC == RESULT_OK && null != data) {
            Uri selectedpic = data.getData();
            String[] fpColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedpic,
                    fpColumn, null, null, null);
            cursor.moveToFirst();
            //gets picture from the column index
            int columnIndex = cursor.getColumnIndex(fpColumn[0]);
            String path = cursor.getString(columnIndex);
            cursor.close();
            
            BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 10;
            bitmap = BitmapFactory.decodeFile(path, options);
            picture.setImageBitmap(bitmap);
         
        }
     
     
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
		return true;
	}

}
