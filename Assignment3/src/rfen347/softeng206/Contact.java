package rfen347.softeng206; 

import java.io.Serializable;

import android.graphics.Bitmap;
 
//a contact class that represents the object of the contact that the user wants to add
public class Contact implements Serializable {
	//Serializable allows contact to be passed from different activities using intent.
	private static final long serialVersionUID = 1L;
	private byte[] picture = new byte[]{};
	//the fields inside the contact
	private int _id;
	private String _firstName;
	private String _lastName;
	private String _mobile;
	private String _home;
	private String _work;
	private String _email;
	private String _address;
	private String _dob;
	
	
	public Contact(){
        
    }
	
	//Contact without picture (includes id)
	Contact(int id, String fn, String ln, String mb, String hm, String wk, String em, String adrs, String dob){
		this._id = id;
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
		this._home = hm;
		this._work = wk;
		this._email = em;
		this._address = adrs;
		this._dob = dob;
	}
	//Contact with picture + id
	Contact(int id, String fn, String ln, String mb, String hm, String wk, String em, String adrs, String dob, byte[] b){
		this._id = id;
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
		this._home = hm;
		this._work = wk;
		this._email = em;
		this._address = adrs;
		this._dob = dob;
		this.picture = b;
		
	}
	//Contact with picture
	Contact(String fn, String ln, String mb, String hm, String wk, String em, String adrs, String dob, byte[] b){
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
		this._home = hm;
		this._work = wk;
		this._email = em;
		this._address = adrs;
		this._dob = dob;
		this.picture = b;
		
	}
	
	
	//contact without picture
	Contact(String fn, String ln, String mb, String hm, String wk, String em, String adrs, String dob){
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
		this._home = hm;
		this._work = wk;
		this._email = em;
		this._address = adrs;
		this._dob = dob;
	}
	
		
	//The following methods are getters and setters used for get and set the fields of contact
	public void set_id(int _id) {
		this._id = _id;
	}

	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}

	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}

	public void set_mobile(String _mobile) {
		this._mobile = _mobile;
	}

	public void set_home(String _home) {
		this._home = _home;
	}

	public void set_work(String _work) {
		this._work = _work;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

	public void set_dob(String _dob) {
		this._dob = _dob;
	}
	public byte[] getPicture() {
		return picture;
	}

	public void set_picture(byte[] picture) {
		this.picture = picture;
	}
	
	
	
	public int get_id() {
		return _id;
	}

	public String getFirstName() {
		return this._firstName;
	}

	public String getLastName() {
		return this._lastName;
	}

	public String getMobile() {
		return this._mobile;

	}

	public String getHome() {
		return this._home;
	}

	public String getWork() {
		return this._work;
	}

	public String getEmail() {
		return this._email;
	}

	public String getAddress() {
		return this._address;
	}

	public String getDob() {
		return this._dob;
	}
	
}