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
				
				
				Contact contactToAdd = new Contact(fn, ln, mp, hn, wn, em, ad, dob, db.getByteArray(bitmap));
				//This is for the picture
				//If the picture does not exist, add the picture
			/*	if (contactToAdd.getPicture() == null) {
					System.out.println("###bad field, tryig again");
					b = picture.getDrawingCache(true);
					contactToAdd.setPicture(b);
				} else {
					System.out.println("###good field");
				}*/
				//add the contact into database
				db.addContact(contactToAdd);
				
				
				//Display a message, and switch back into main activity
				Intent intent = new Intent();
				intent.setClass(AddContactActivity.this, MainActivity.class);
				startActivity(intent);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            
            BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 10;
            bitmap = BitmapFactory.decodeFile(picturePath, options);
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
