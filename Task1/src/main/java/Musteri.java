
public class Musteri {

	private int musteriId;
	private String ad;
	private String sehir;
	// private int sehir;
	// private double age;
	
	public Musteri(int musteriId, String ad, String sehir) {
		this.musteriId = musteriId;
		this.ad = ad;
		this.sehir = sehir;
	}
	
	public int getMusteriID() {
		return musteriId;
	}
	
	public void setMusteriID(int musteriId) {
		this.musteriId = musteriId;
	}
	
	public String getMusteriName() {
		return ad;
	}
	
	public void setMusteriName(String ad) {
		this.ad = ad;
	}
	
	public String getMusteriSehir() {
		return sehir;
	}
	
	public void setMusteriSehir(String sehir) {
		this.sehir = sehir;
	}
	
    @Override
    public String toString() {
    	return musteriId + "\t" + ad + "\t" + sehir;
    }
    
}
