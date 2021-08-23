package cardCoBan;

import java.util.Calendar;

import Card.The;
public class TCB extends The {
	public int PDT() {
		return (int)(SoDu*0.005);
	}
	public TCB(String Name,int ID,Calendar calendar, int sodu) {
		time.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
		this.name=Name;
		this.ID=ID;
		this.SoDu=sodu;
	}
	public TCB() {
		time.set(0,0,0);
	}
}
