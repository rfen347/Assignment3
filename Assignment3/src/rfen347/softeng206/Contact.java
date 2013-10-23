package rfen347.softeng206; 
 
public class Contact {
	
	private int _id;
	private String _firstName;
	private String _lastName;
	private String _mobile;
	private String _home;
	private String _work;
	private String _email;
	private String _address;
	private String _dob;
	private boolean selected = false;
	
	
	public Contact(){
        
    }
	
	//Contact with full details (includes id)
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
	//contact with main features
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
	//Contact - dob
	Contact(String fn, String ln, String mb, String hm, String wk, String em, String adrs){
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
		this._home = hm;
		this._work = wk;
		this._email = em;
		this._address = adrs;
	}
	//Contact - dob - address
	Contact(String fn, String ln, String mb, String hm, String wk, String em){
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
		this._home = hm;
		this._work = wk;
		this._email = em;
	}
	//Contact - dob - address - email
	Contact( String fn, String ln, String mb, String hm, String wk){
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
		this._home = hm;
		this._work = wk;
	}
	//Contact - dob - address - email - work
	Contact(String fn, String ln, String mb, String hm){
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
		this._home = hm;
	}
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

	//Contact - dob - address - email - work - home
	Contact(int id, String fn, String ln, String mb){
		this._id = id;
		this._firstName = fn;
		this._lastName = ln;
		this._mobile = mb;
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
	
	
	
	public boolean isSelected(){
		return selected;
	}
	public void setSelected(boolean selected){
		this.selected = selected;
	}
}