import java.util.*;
import java.util.Vector;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FirstApp {
	private static String user = "root";
	private static String password = "ceng1234";
	private static String host = "localhost";
	private static String database = "mydb";
	private static int port = 3306;
	
	public void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
         
        String oldContent = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent
             
            String newContent = oldContent.replaceAll(oldString, newString);
             
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
	
	private static Connection connection = null;
	
	public void initialize() {
		String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
		try {
			connection = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int createTables() {
		int numberOfTables = 0;
		
		String queryCreateMusteriTable = "CREATE TABLE IF NOT EXISTS Musteri (" +
				"musteriId    INT NOT NULL ," +
				"ad  VARCHAR(32) ," +
				"sehir VARCHAR(32) ," +
				"PRIMARY KEY (musteriId))";

		String queryCreateSepetTable = "CREATE TABLE IF NOT EXISTS  Sepet (" +
				"sepetId     INT NOT NULL," +
				"musteriId     INT NOT NULL," +
				"tarih   VARCHAR(10) not null ," +
				"PRIMARY KEY (sepetId))";

		String queryCreateUrunTable = "CREATE TABLE IF NOT EXISTS Urun (" +
				"urunId INT NOT NULL ," +
				"tutar DOUBLE NOT NULL ," +
				"aciklama VARCHAR(100) NOT NULL ," +
				"sepetId INT NOT NULL ," +
				"PRIMARY KEY (urunId))";
				/*"FOREIGN KEY (urunId) REFERENCES Musteri(urunId) ON DELETE CASCADE ON UPDATE CASCADE ," +
				"FOREIGN KEY (sepetId) REFERENCES Sepet(sepetId) ON DELETE CASCADE ON UPDATE CASCADE)";*/ 
				
		try {
			Statement statement = this.connection.createStatement();
			
			// Musteri Table
			statement.executeUpdate(queryCreateMusteriTable);
			numberOfTables++;
			// Sepet Table
			statement.executeUpdate(queryCreateSepetTable);
			numberOfTables++;
			// Urun Table
			statement.executeUpdate(queryCreateUrunTable);
			numberOfTables++;
			
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return numberOfTables;
	}
	
	public int dropTables() {
		int numberofTablesDropped = 0;
		
		String queryDropMusteriTable  = "DROP TABLE IF EXISTS Musteri";
		String queryDropSepetTable    = "DROP TABLE IF EXISTS Sepet";
		String queryDropUrunTable = "DROP TABLE IF EXISTS Urun";
		
		try {
			Statement statement = this.connection.createStatement();
			
			statement.executeUpdate(queryDropUrunTable);
			numberofTablesDropped++;
			
			statement.executeUpdate(queryDropMusteriTable);
			numberofTablesDropped++;
			
			statement.executeUpdate(queryDropSepetTable);
			numberofTablesDropped++;
			
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return numberofTablesDropped;
	}
	
	
	public int insertMusteri(Musteri musteri) {
		int numberofRowsInserted = 0;
			try {
				PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO Musteri "
						+ "VALUES(?, ?, ?)");	
				stmt.setInt(1, musteri.getMusteriID());
				stmt.setString(2, musteri.getMusteriName());
				stmt.setString(3, musteri.getMusteriSehir());
				
				stmt.executeUpdate();
				
				stmt.close();
				numberofRowsInserted++;
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		return numberofRowsInserted;
	}
	
	public int insertSepet(Sepet sepet) {
		int numberofRowsInserted = 0;
			try {
				
				PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO Sepet "
						+ "VALUES(?, ?, ?)");	
				stmt.setInt(1, sepet.getSepetID());
				stmt.setInt(2, sepet.getMusteriID());
				stmt.setString(3, sepet.getSepetTarih());
				
				stmt.executeUpdate();
				
				stmt.close();
				numberofRowsInserted++;
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		return numberofRowsInserted;
	}
	
	public int insertUrun(Urun urun) {
		int numberofRowsInserted = 0;
			try {
				
				PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO Urun "
						+ "VALUES(?, ?, ?, ?)");	
				stmt.setInt(1, urun.getUrunID());
				stmt.setDouble(2, urun.getUrunTutar());
				stmt.setString(3, urun.getUrunAcıklama());
				stmt.setInt(4, urun.getUrunSepetID());
				
				stmt.executeUpdate();
				
				stmt.close();
				numberofRowsInserted++;
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		return numberofRowsInserted;
	}
	

	
	
/**************************  QueryResult.MusteriResults  ********************************************************/

	public QueryResult.MusteriResults[] getAllMusteri() {
		ResultSet rs;
		ArrayList<QueryResult.MusteriResults> MusteriList = new ArrayList<>();
		
		String query = "SELECT DISTINCT M.musteriId, M.ad, M.sehir\n" +
				"FROM Musteri M\n";
		
		try {
			Statement st = this.connection.createStatement();
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				int musteriId		= rs.getInt("musteriId");
				String ad	= rs.getString("ad");
				String sehir	= rs.getString("sehir");
				
				QueryResult.MusteriResults obj = new QueryResult.MusteriResults(musteriId, ad, sehir);
				MusteriList.add(obj);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QueryResult.MusteriResults[] resarray = new QueryResult.MusteriResults[MusteriList.size()];
		return MusteriList.toArray(resarray);
	}

/***********************   QueryResult.SepetResults    ***********************************************/

	public QueryResult.SepetResults[] getAllSepets() {
		ResultSet rs;
		ArrayList<QueryResult.SepetResults> SepetList = new ArrayList<>();
		
		String query = "SELECT DISTINCT S.sepetId, S.musteriId, S.tarih\n" +
				"FROM Sepet S\n";
		
		try {
			Statement st = this.connection.createStatement();
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				int sepetId		= rs.getInt("sepetId");
				int musteriId	= rs.getInt("musteriId");
				String tarih	= rs.getString("tarih");
				
				QueryResult.SepetResults obj = new QueryResult.SepetResults(sepetId, musteriId, tarih);
				SepetList.add(obj);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QueryResult.SepetResults[] resarray = new QueryResult.SepetResults[SepetList.size()];
		return SepetList.toArray(resarray);
	}

/**************************  QueryResult.UrunResults  ************************************************/

	public QueryResult.UrunResults[] getAllUruns() {
		ResultSet rs;
		ArrayList<QueryResult.UrunResults> UrunList = new ArrayList<>();
		
		String query = "SELECT DISTINCT U.urunId, U.tutar, U.aciklama, U.sepetId\n" +
				"FROM Urun U\n";
		
		try {
			Statement st = this.connection.createStatement();
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				int urunId		= rs.getInt("urunId");
				Double tutar	= rs.getDouble("tutar");
				String aciklama	= rs.getString("aciklama");
				int sepetId		= rs.getInt("sepetId");
				
				QueryResult.UrunResults obj = new QueryResult.UrunResults(urunId, tutar, aciklama, sepetId);
				UrunList.add(obj);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QueryResult.UrunResults[] resarray = new QueryResult.UrunResults[UrunList.size()];
		return UrunList.toArray(resarray);
	}

/****************************  SehirSepetIdTutarResult  ********************************************************/
	
	public QueryResult.SehirSepetIdTutarResult[] getSehirSepetTutar() {
		ResultSet rs;
		ArrayList<QueryResult.SehirSepetIdTutarResult> resultList = new ArrayList<>();
		
		// Not efficient but need to be implemented another way. 
        String query = "SELECT M.sehir, Count(S.sepetId) AS SepetSayisi, Sum(U.tutar) AS ToplamTutar\n" +
                "FROM Musteri M, Sepet S, Urun U\n" +
                "WHERE M.musteriId = S.musteriId\n" +
                "\t  AND S.sepetId = U.sepetId\n" +
                "GROUP BY M.sehir\n" +
        		"ORDER BY M.sehir;";
   
		// Not working part need to be improved
//        String query = "SELECT M.sehir, Count(S1.sepetId) AS SepetSayisi\n" +
//                "FROM Musteri M, Sepet S1\n" +
//                "WHERE M.musteriId = S1.musteriId\n" +
//                "AND S1.musteriId  IN \n" +
//                " (SELECT S2.musteriId \n" +
//                	"FROM Sepet S2, Urun U1\n" +
//                	"WHERE S2.sepetId = U1.sepetId)\n"+
//               
//                "UNION\n" +
//                "SELECT Sum(U.tutar) AS ToplamTutar, U.urunId\n" +
//                "FROM Urun U, Sepet S1\n" +
//                "WHERE U.sepetId = S1.sepetId\n" +
//                "AND S1.musteriId  IN\n" +
//                " (SELECT S2.musteriId \n" +
//                	"FROM Musteri M, Sepet S2\n" +
//                	"WHERE S2.musteriId = M.musteriId)\n"	+
//                "GROUP BY M.sehir\n" +
//        		"ORDER BY M.sehir;";

		
		try {
			Statement st = this.connection.createStatement();
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				String sehir	= rs.getString("sehir");
				int sepetId		= rs.getInt("SepetSayisi");
				Double tutar	= rs.getDouble("ToplamTutar");
				int i = 0;
//				int sepetId = i++;
				QueryResult.SehirSepetIdTutarResult obj = new QueryResult.SehirSepetIdTutarResult(sehir, sepetId, tutar);
				resultList.add(obj);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		QueryResult.SehirSepetIdTutarResult[] resarray = new QueryResult.SehirSepetIdTutarResult[2];
		return resultList.toArray(resarray);
	}

	
	
}








