
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FileOperations {

    public static FileWriter createFileWriter( String path) throws IOException {
        File f = new File( path);

        FileWriter fileWriter = null;

        if( f.isDirectory() && !f.exists())
            f.mkdirs();
        else if( !f.isDirectory() && !f.getParentFile().exists())
            f.getParentFile().mkdirs();

        if( !f.isDirectory() && f.exists())
            f.delete();

        fileWriter = new FileWriter( f, false);

        return fileWriter;
    }

    public static Musteri[] readMusteriFile(String pathToFile){
    	
    	FileReader fileReader = null;
    	BufferedReader bufferedReader = null; 
    	
    	String strLine;
    	
    	List<Musteri> musteriList = new ArrayList<>();
    	Musteri[] MusteriArray = null;
    	
    	try {
    		
    		fileReader = new FileReader(pathToFile);
    		bufferedReader = new BufferedReader(fileReader);
    		
    		//example strline
    		//musteriId		ad    	rating  	 age
    		
    		while((strLine = bufferedReader.readLine())!=null) {
    			
    			//parse strLine
    			String[] words = strLine.split("\t");
    			
    			if (words.length < 3) {
    				System.out.println("There is a problem in User File Reading phase");
    			} 
    			else {
    				int musteriId = Integer.parseInt(words[0]);
    				String ad = words[1];
    				String sehir = words[2];
    				Musteri Musteri = new Musteri(musteriId, ad, sehir);
    				musteriList.add(Musteri);
    			}
    			
    		}//End of while
    		
    		//Close bufferedReader
    		bufferedReader.close();
    		
    		MusteriArray = new Musteri[musteriList.size()];
    		musteriList.toArray(MusteriArray);
    		
    	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return MusteriArray;
    }
    
    public static Sepet[] readSepetFile(String pathToFile){
    	
    	FileReader fileReader = null;
    	BufferedReader bufferedReader = null; 
    	
    	String strLine;
    	
    	List<Sepet> SepetList = new ArrayList<>();
    	Sepet[] SepetArray = null;
    	
    	try {
    		
    		fileReader = new FileReader(pathToFile);
    		bufferedReader = new BufferedReader(fileReader);
    		
    		while((strLine = bufferedReader.readLine())!=null) {
    			
    			//parse strLine
    			// sepetId		bname		tarih
    			String[] words = strLine.split("\t");
    			
    			if (words.length < 3) {
    				System.out.println("There is a problem in User File Reading phase");
    			} 
    			else {
    				int sepetId = Integer.parseInt(words[0]);
    				int musteriId = Integer.parseInt(words[1]);
    				String tarih = words[2];
    				
    				Sepet Sepet = new Sepet(sepetId, musteriId, tarih);
    				SepetList.add(Sepet);
    			}
    			
    		}//End of while
    		
    		//Close bufferedReader
    		bufferedReader.close();
    		
    		SepetArray = new Sepet[SepetList.size()];
    		SepetList.toArray(SepetArray);
    		
    	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return SepetArray;
    }
    public static Urun[] readUrunFile(String pathToFile){

        FileReader fileReader = null;
        BufferedReader bufferedReader = null; 

        String strLine;

        List<Urun> UrunList = new ArrayList<>();
        Urun[] UrunArray = null;
        
        try {

            fileReader = new FileReader(pathToFile);
            bufferedReader = new BufferedReader(fileReader);

            while((strLine = bufferedReader.readLine())!=null) {

                //parse strLine
            	// musteriId		sepetId		tarih
                String[] words = strLine.split("\t");
                
                if (words.length < 4) {
                    System.out.println("There is a problem in User File Reading phase");
                } 
                else {
                    int urunId = Integer.parseInt(words[0]);
                    double tutar = Double.parseDouble(words[1]);
                    String aciklama = words[2];
                    int sepetId = Integer.parseInt(words[3]);

                    Urun Urun = new Urun(urunId, tutar, aciklama, sepetId);
                    UrunList.add(Urun);
                }

            }//End of while

            //Close bufferedReader
            bufferedReader.close();

            UrunArray = new Urun[UrunList.size()];
            UrunList.toArray(UrunArray);

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return UrunArray;
    }
    

}
