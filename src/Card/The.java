package Card;

import java.util.Calendar;
public  class The {
	public String name;
	public int ID;
	public Calendar time= Calendar.getInstance();
	public int SoDu;
	//public abstract int PDT();
	public void setName(String name) {
		this.name=name;
	}
	public void setID(int ID){
		this.ID=ID;
	}
	public void setCalendar(int year,int month,int day) {
		time.set(year, month, day);
	}
	public void setSodu(int Sodu) {
		this.SoDu=Sodu;
	}
	public String getName() {
		return name;
	}
	public int getID() {
		return ID;
	}
	public Calendar getCalendar() {
		return time;
	}
	public int getSodu() {
		return SoDu;
	}

}