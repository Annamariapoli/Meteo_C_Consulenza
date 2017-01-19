package bean;

import java.time.LocalDate;

public class CoppieDate {
	
	private LocalDate ieri;
	private LocalDate oggi;
	public CoppieDate(LocalDate ieri, LocalDate oggi) {
		super();
		this.ieri = ieri;
		this.oggi = oggi;
	}
	public LocalDate getIeri() {
		return ieri;
	}
	public void setIeri(LocalDate ieri) {
		this.ieri = ieri;
	}
	public LocalDate getOggi() {
		return oggi;
	}
	public void setOggi(LocalDate oggi) {
		this.oggi = oggi;
	}
	
	public String toString(){
		String r ;
		r = ieri+"  "+oggi+" \n";
		return r;
	}

}
