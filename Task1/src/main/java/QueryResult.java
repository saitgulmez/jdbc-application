
public class QueryResult {

/***************** MusteriResults   *******************************************/
	public static class MusteriResults {
		int musteriId;
		String ad;
		String sehir;
		
		public MusteriResults(int musteriId, String ad, String sehir) {
			this.musteriId = musteriId;
			this.ad = ad;
			this.sehir = sehir;
		}
		
//		@Override
		public String toString() {
			return (musteriId + "\t" + ad + "\t" + sehir);
		}
	}
	
	
/***************** SepetResults   **********************************************/
	public static class SepetResults {
		int sepetId;
		int musteriId;
		String tarih;
		
		public SepetResults(int sepetId, int musteriId, String tarih) {
			this.sepetId = sepetId;
			this.musteriId = musteriId;
			this.tarih = tarih;
		}
		
//		@Override
		public String toString() {
			return (sepetId + "\t" + musteriId + "\t" + tarih);
		}
	}
	
/***************** UrunResults   ********************************************/
	public static class UrunResults {
		int urunId;
		double tutar;
		String aciklama;
		int sepetId;
		
		public UrunResults(int urunId, double tutar, String aciklama,  int sepetId) {
			this.urunId = urunId;
			this.tutar = tutar;
			this.aciklama = aciklama;
			this.sepetId = sepetId;
		}
		
//		@Override
		public String toString() {
			return (urunId + "\t" + tutar + "\t" + aciklama + "\t" + sepetId );
		}
	}
	
	
/**************** SehirSepetIdTutarResult **************************************/
	
	public static class SehirSepetIdTutarResult {
		private String sehir; 
		private int sepetId;
		private double tutar;
		
		public SehirSepetIdTutarResult(String sehir, int sepetId, double tutar) {
			this.sehir = sehir;
			this.sepetId = sepetId;
			this.tutar = tutar;
		}
//		@Override
		public String toString() {
			return (sehir + "\t\t\t\t" + sepetId + "\t\t\t\t" + tutar);
		}
	}

}
