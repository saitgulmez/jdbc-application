
public class Sepet {
	
	private int sepetId;
	private int musteriId;
	private String tarih;
	public Sepet(int musteriId, int sepetId, String tarih) {
		this.musteriId = musteriId;
		this.sepetId = sepetId;
		this.tarih = tarih;
	}
	
	public int getSepetID() {
		return sepetId;
	}
	
	public void setSepetID(int sepetId) {
		this.sepetId = sepetId;
	}

	public int getMusteriID() {
		return musteriId;
	}
	
	public void setMusteriID(int musteriId) {
		this.musteriId = musteriId;
	}
	
	
	public String getSepetTarih() {
		return tarih;
	}
	
	public void setSepetTarih(String tarih) {
		this.tarih = tarih;
	}

	@Override
	public String toString() {
		return musteriId + "\t" + sepetId + "\t" + tarih;
	}
}
