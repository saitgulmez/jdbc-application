
public class Urun {

	private int urunId;
	private double tutar;
	private String aciklama;
	private int sepetId;
	
	public Urun(int urunId, double tutar, String aciklama, int sepetId) {
		this.urunId = urunId;
		this.tutar = tutar;
		this.aciklama = aciklama;
		this.sepetId = sepetId;
	}
	
	public int getUrunID() {
		return urunId;
	}
	
	public void setUrunID(int urunId) {
		this.urunId = urunId;
	}
	

	public double getUrunTutar() {
		return tutar;
	}
	
	public void setUrunTutar(double tutar) {
		this.tutar = tutar;
	}

	
	public String getUrunAcıklama() {
		return aciklama;
	}
	
	public void setUrunAcıklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
	public int getUrunSepetID() {
		return sepetId;
	}
	
	public void setUrunSepetID(int sepetId) {
		this.sepetId = sepetId;
	}
	
    @Override
    public String toString() {
    	return urunId + "\t"  + tutar + "\t" + aciklama + "\t" +  sepetId;
    }
    

}
