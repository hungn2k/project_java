package cardNangCao;
import java.util.Calendar;

import Card.The;

public class TNC extends The {
	public int PDT() {
		return (int)(SoDu*0.01+20000);
	}
	public TNC(String Name,int ID,Calendar calendar, int sodu) {
		time.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
		this.name=Name;
		this.ID=ID;
		this.SoDu=sodu;
	}
	public TNC() {
		time.set(0,0,0);
	}
}
