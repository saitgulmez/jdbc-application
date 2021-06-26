import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.*;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.javafaker.Faker;

public class Test {

	private static String user = "root";
	private static String password = "ceng1234";
	private static String host = "localhost";
	private static String database = "mydb";
	private static int port = 3306;
	private static Connection connection = null;

	
	public static void connect() {
		String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
		
		try {
			// Load the MySQL JDBC driver
		      Class.forName("com.mysql.cj.jdbc.Driver");
		      connection = DriverManager.getConnection(url, user, password);
		      System.out.println("MySQL JDBC driver loaded ok.");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addInputTitle(String title, BufferedWriter bufferedWriter) throws IOException {
		bufferedWriter.write("*** " + title + " ***" + System.getProperty("line.separator"));
	}
	
	public static void printAllTables(BufferedWriter bufferedWriter) throws IOException {
		String sql1 = "show tables";
		String sql2 = "describe ";
		
		Vector <String> tables = new Vector<>();
		 try
	        {
	            // Execute query
	            Statement st = connection.createStatement();
	            ResultSet rs = st.executeQuery(sql1);

	            // Process the result set
	            while(rs.next()) {
	                tables.add(rs.getString(1));
	            }

	            for(int i=0; i < tables.size(); i++) {
	                rs = st.executeQuery(sql2 + tables.get(i));

	                // Print table name
	                bufferedWriter.write("--- " + tables.get(i) + " ---" + System.getProperty("line.separator"));

	                // Print field names and types
	                while(rs.next()) {
	                        bufferedWriter.write(rs.getString(1) + " " + rs.getString(2) + System.getProperty("line.separator"));
	                }

	                bufferedWriter.write(System.getProperty("line.separator"));
	            }

	        } catch (SQLException e) {
	           e.printStackTrace();
	        }
	}
	
    private static void printException(SQLException ex) {
        System.out.println(ex.getMessage() + "\n");
    }
	
    public static void printLine(String result, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(result + System.getProperty("line.separator"));
    }

    public static void addDivider(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write( System.getProperty("line.separator")+ "--------------------------------------------------------------" + System.getProperty("line.separator"));
    }
    
    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
	public static void main(String[] args) throws IOException { 
	
		int numberofInsertions = 0;
		int numberofTablesCreated = 0;
		int numberofTablesDropped = 0;		
		
		String dataDirectory = "src" + System.getProperty("file.separator");
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		connect();
		
		FirstApp firstApp = null;


        try {
        	firstApp = new FirstApp();
        	firstApp.initialize();

        	fileWriter = FileOperations.createFileWriter(dataDirectory + System.getProperty("file.separator") + "output" + System.getProperty("file.separator") + "Output.txt");
            bufferedWriter =  new BufferedWriter(fileWriter);
            
/************* DROP TABLES STARTS ****************************/
            addDivider(bufferedWriter);
            addInputTitle("Drop tables", bufferedWriter);
            numberofTablesDropped = 0;

            // Drop tables
            try {
                numberofTablesDropped = firstApp.dropTables();
            } catch(Exception e) {
                e.printStackTrace();
            }

            // Check if tables are dropped
            printLine("Dropped " + numberofTablesDropped + " tables.", bufferedWriter);

            addDivider(bufferedWriter);

            
            addInputTitle("Create tables",bufferedWriter);
            numberofTablesCreated = 0;
            
/***************** CREATE  TABLES STARTS ***************************************/
	            try {
	                numberofTablesCreated = firstApp.createTables();
	
	                // Check if tables are created
	                printLine("Created " + numberofTablesCreated + " tables.", bufferedWriter);
	
	            } catch(Exception e) {
	                printLine("Q3.1: Exception occured: \n\n" + e.toString(),bufferedWriter);
	            }

	            addDivider(bufferedWriter);


/***************** INSERTION STARTS **********************************************/

/*******************Insert INTO Musteri starts**********/
	        addInputTitle("Insert into Musteri",bufferedWriter);
	      
	        Random random = new Random();
	        
	        // cities are used for assigning them randomly to the Musteri Table
	        String[] cities = {"Ankara", "Istanbul", "Izmir", "Sivas"};
	        
	        numberofInsertions = 0;
	        Musteri[] musteris = new Musteri[50];
	        Musteri temp1 = new Musteri(1, "", "");
	      
	        // Generate Primary Key for each musteri in Musteri table [1..50]
	        ArrayList<Integer> generateMusteriID = new ArrayList<>();
	        for(int i = 0; i < 50; i++)
	        {
	        	generateMusteriID.add(i+1);
	        }
	        
	        int storeMusteriID[] = convertIntegers(generateMusteriID);
	       
	        // We use faker object to create fake names randomly
	        Faker faker = new Faker();
	        
	        // Insert 50 musteris into Musteri Table
	        for (int i = 0; i < 50; i++) {
	        	String firstName = faker.name().firstName(); 
	        	String city = cities[random.nextInt(cities.length)];
	        	musteris[i] = temp1;
	        	musteris[i].setMusteriID(storeMusteriID[i]);
	        	musteris[i].setMusteriName(firstName);
	        	musteris[i].setMusteriSehir(city);
	        	numberofInsertions = firstApp.insertMusteri(musteris[i]);
	        	numberofInsertions = i+1;
	        }
	        
	        printLine( numberofInsertions + " musteris are inserted.",bufferedWriter);
	        addDivider(bufferedWriter);	
	        

	       
/*******************Insert INTO sepets starts**********/
	        addInputTitle("INSERT INTO sepets", bufferedWriter);
	        
	        numberofInsertions = 0;
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
	        String date = sdf.format(new Date()); 

	        Sepet[] sepets = new Sepet[50];
	        Sepet temp2 = new Sepet(0, 0, date);

	        // Generate Primary Key for each sepet within the Sepet Table [100..149]
	        ArrayList<Integer> generateSepetID = new ArrayList<>();
	        for(int i = 0; i < 50; i++)
	        {
	        	generateSepetID.add(i + 100);
	        }
	       
	        int storeSepetID[] = convertIntegers(generateSepetID);
	        int randomMusteriID;
	        
	     // Insert 50 sepets into Sepets Table
	        for (int i = 0; i < 50; i++) {
	        	sepets[i] = temp2;
	        	sepets[i].setSepetID(storeSepetID[i]);
	        	randomMusteriID = random.nextInt(50);
		        sepets[i].setMusteriID(randomMusteriID);
		        sepets[i].setSepetTarih(date);
		        numberofInsertions = firstApp.insertSepet(sepets[i]);
		        numberofInsertions = i+1;
	        }
	        printLine(numberofInsertions + " sepets are inserted.", bufferedWriter);
	        addDivider(bufferedWriter);
	        
	        
/*******************Insert INTO uruns starts**********/
	        addInputTitle("INSERT INTO uruns", bufferedWriter);
	        
	        numberofInsertions = 0;

	        Urun[] uruns = new Urun[50];
	        Urun temp3 = new Urun(0, 0.0, "", 0);
	        Random rd = new Random();
	        
	        ArrayList<Integer> generateUrunID = new ArrayList<>();
	        
	        // Generate Primary Key for each urun within the Urun Table [10000..10049]
	        for(int i = 0; i < 50; i++)
	        {
	        	generateUrunID.add(i + 10000);
	        }
	        int storeUrunID[] = convertIntegers(generateUrunID);
	        int randomTempSepetID;
	       
	        // Insert 50 uruns into Urun Table
	        for (int i = 0; i < 50; i++) {
	        	uruns[i] = temp3;
	        	uruns[i].setUrunID(storeUrunID[i]);
	        	uruns[i].setUrunTutar(rd.nextDouble()*100);
	        	uruns[i].setUrunAcıklama("Cheap");
	        	randomTempSepetID = random.nextInt(50) + 100;
	        	uruns[i].setUrunSepetID(randomTempSepetID);
	        	numberofInsertions = firstApp.insertUrun(uruns[i]);
	        	numberofInsertions = i+1;
	        }
	        printLine(numberofInsertions + " uruns are inserted.", bufferedWriter);
	        addDivider(bufferedWriter);
 

/****************************************************************************************************
*																									*
*																									*
*	        QUERY QUERY QUERY QUERY QUERY QUERY QUERY QUERY QUERY QUERY QUERY QUERY QUERY 			*
*																									*
*																									*
****************************************************************************************************/
	        
	        /************************  getAllMusteri()   **********************************************************/
	        addInputTitle("List All Musteri",bufferedWriter);
	        try {
	        	
	        	QueryResult.MusteriResults[] musteriArray = firstApp.getAllMusteri();
	        	
	        	//Header Line
	        	printLine("musteriId" + "\t" + "ad" + "\t" + "sehir",bufferedWriter);
	        	
	        	if(musteriArray != null) {
	        		for(QueryResult.MusteriResults musteriRow : musteriArray){
	        			printLine(musteriRow.toString(),bufferedWriter);	
	        		}	
	        	}
	        	
	        	
	        } catch(Exception e) {
	        	printLine("Q3.3: Exception occured: \n\n" + e.toString(),bufferedWriter);
	        }
	        addDivider(bufferedWriter);
	        
/************************  getAllSepets()   **********************************************************/
	         addInputTitle("List All Sepets",bufferedWriter);
	         try {
	        	
	         	QueryResult.SepetResults[] sepetArray = firstApp.getAllSepets();
	        	
	         	//Header Line
	         	printLine("musteriId" + "\t" + "ad" + "\t" + "sehir",bufferedWriter);
	        	
	         	if(sepetArray != null) {
	         		for(QueryResult.SepetResults sepetRow : sepetArray){
	         			printLine(sepetRow.toString(),bufferedWriter);	
	         		}	
	         	}
	        	
	        	
	         } catch(Exception e) {
	         	printLine("Q3.3: Exception occured: \n\n" + e.toString(),bufferedWriter);
	         }
	         addDivider(bufferedWriter);
	       

            
/************************  getAlluruns()   **********************************************************/
	         addInputTitle("List All Ürüns",bufferedWriter);
	         try {
	        	
	         	QueryResult.UrunResults[] urunArray = firstApp.getAllUruns();
	        	
	         	//Header Line
	         	printLine("urunId" + "\t" + "tutar" + "\t" + "aciklama" + "\t" + "sepetId", bufferedWriter);
	        	
	         	if(urunArray != null) {
	         		for(QueryResult.UrunResults urunRow : urunArray){
	         			printLine(urunRow.toString(),bufferedWriter);	
	         		}	
	         	}
	        	
	        	
	         } catch(Exception e) {
	         	printLine("Q3.3: Exception occured: \n\n" + e.toString(),bufferedWriter);
	         }
	         addDivider(bufferedWriter);
	         
	         
/************************  getAlluruns()   **********************************************************/
	         addInputTitle("********* Result *******",bufferedWriter);
	         try {
	        	
	         	QueryResult.SehirSepetIdTutarResult[] resultArray = firstApp.getSehirSepetTutar();
	        	
	         	//Header Line
	         	printLine("sehir" + "\t\t\t\t" + "SepetSayisi" + "\t\t\t\t" + "ToplamTutar", bufferedWriter);
	        	
	         	if(resultArray != null) {
	         		for(QueryResult.SehirSepetIdTutarResult row1 : resultArray){
	         			printLine(row1.toString(),bufferedWriter);	
	         		}	
	         	}
	        	
	        	
	         } catch(Exception e) {
	         	printLine("Q3.3: Exception occured: \n\n" + e.toString(),bufferedWriter);
	         }
	         addDivider(bufferedWriter);
            
/*********************************************************************************************************/
     
        
        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                 //Close Writer
                 if (bufferedWriter != null) {
                     bufferedWriter.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             //Close Connection
//             disconnect();
        }
        
	}

}
