package rfen347.softeng206;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class GalleryActivity extends Activity {
	private ImageButton picture;
	private ImageButton picture2;
	private ImageButton picture3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		picture=(ImageButton)findViewById(R.id.add_picture);
		picture2=(ImageButton)findViewById(R.id.add2_picture);
		picture3=(ImageButton)findViewById(R.id.add3_picture);
		picture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(GalleryActivity.this, AddContactActivity.class);
				startActivity(intent);
				
			}
		})
		;
		picture2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(GalleryActivity.this, AddContactActivity.class);
				startActivity(intent);
				
			}
		})
		;
		picture3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(GalleryActivity.this, AddContactActivity.class);
				startActivity(intent);
				
			}
		})
		;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gallery, menu);
		return true;
	}

}
