package bean;

import java.time.LocalDate;

public class IeriOggi {
	
	private LocalDate ieri;
	private int ieri_t;
	private LocalDate oggi;
	private int oggi_t;
	
	
	public IeriOggi(LocalDate ieri, int ieri_t, LocalDate oggi, int oggi_t) {
		super();
		this.ieri = ieri;
		this.ieri_t = ieri_t;
		this.oggi = oggi;
		this.oggi_t = oggi_t;
	}
	public LocalDate getIeri() {
		return ieri;
	}
	public void setIeri(LocalDate ieri) {
		this.ieri = ieri;
	}
	public int getIeri_t() {
		return ieri_t;
	}
	public void setIeri_t(int ieri_t) {
		this.ieri_t = ieri_t;
	}
	public LocalDate getOggi() {
		return oggi;
	}
	public void setOggi(LocalDate oggi) {
		this.oggi = oggi;
	}
	public int getOggi_t() {
		return oggi_t;
	}
	public void setOggi_t(int oggi_t) {
		this.oggi_t = oggi_t;
	}

	public String toString(){
		String r ;
		r = ieri+" "+ieri_t+" "+oggi+" "+oggi_t+" \n";
		return r;
	}
	
}
