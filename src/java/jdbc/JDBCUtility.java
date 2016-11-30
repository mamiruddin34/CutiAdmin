/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.util.*;
import java.sql.*;
/**
 *
 * @author Amiruddin
 */
public class JDBCUtility {

    Connection conn;
    String driver;
    String url;
    String userName;
    String password;
    
    private PreparedStatement psSelectAllFromDaftarCuti = null;
    private PreparedStatement psInsertIntoDaftarCuti = null;
    private PreparedStatement psSelectAllFromDaftarCutiWhereKampus = null;
    private PreparedStatement psDeleteFromDaftarCutiWhereID = null;
    private PreparedStatement psSelectAllFromMohonCutiUmum = null;
    private PreparedStatement psDeleteFromMohonCutiUmumWhereID = null;
    private PreparedStatement psSelectAllFromMohonCutiUmumWhereID = null;
    
    public JDBCUtility(String driver,
                      String url,
                      String userName,
                      String password)
    {
      this.driver = driver;
      this.url = url;
      this.userName = userName;
      this.password = password;
    }
    
    public  void jdbcConnect()
    {
      try
	   {
         Class.forName (driver);
         conn = DriverManager.getConnection(url, userName, password);
         DatabaseMetaData dma = conn.getMetaData ();
         System.out.println("\nConnected to " + dma.getURL());
         System.out.println("Driver       " + dma.getDriverName());
         System.out.println("Version      " + dma.getDriverVersion());
         System.out.println("");
	   }
	   catch (SQLException ex)
	   {
         while (ex != null)
         {
		      System.out.println ("SQLState: " +
                                 ex.getSQLState ());
            System.out.println ("Message:  " +
                                 ex.getMessage ());
		      System.out.println ("Vendor:   " +
                                 ex.getErrorCode ());
            ex = ex.getNextException ();
		      System.out.println ("");
         }

         System.out.println("Connection to the database error");
	   }
	   catch (java.lang.Exception ex)
	   {
         ex.printStackTrace ();
	   }
    }
    
    public Connection jdbcGetConnection()
    {
   	return conn;
    }

   public void jdbcConClose()
    {
   	try
   	{
         conn.close();
   	}
   	catch (Exception ex)
	   {
	   }
    }
   
   public void prepareSQLStatement() {
       
       try {
            //select all daftarCuti
            String sqlSelectAllFromDaftarCuti = "SELECT * FROM daftarcuti";
            psSelectAllFromDaftarCuti = conn.prepareStatement(sqlSelectAllFromDaftarCuti); 
            
            //insert cuti
            String sqlInsertIntoDaftarCuti = "INSERT INTO daftarcuti(tarikhCuti, kampus, keterangan) VALUES(?, ?, ?)";
            psInsertIntoDaftarCuti = conn.prepareStatement(sqlInsertIntoDaftarCuti);
            
            String sqlSelectAllFromDaftarCutiWhereKampus = "SELECT * FROM daftarcuti WHERE kampus = ? AND tarikhCuti LIKE '%?%' ";
            psSelectAllFromDaftarCutiWhereKampus = conn.prepareStatement(sqlSelectAllFromDaftarCutiWhereKampus);
            
            String sqlDeleteFromDaftarCutiWhereID = "DELETE FROM daftarcuti WHERE cutiUmumID = ?";
            psDeleteFromDaftarCutiWhereID = conn.prepareStatement(sqlDeleteFromDaftarCutiWhereID);
            
            String sqlSelectAllFromMohonCutiUmum = "SELECT * FROM mohoncutiumum";
            psSelectAllFromMohonCutiUmum = conn.prepareStatement(sqlSelectAllFromMohonCutiUmum);
            
            String sqlDeleteFromMohonCutiUmumWhereID = "DELETE FROM mohoncutiumum WHERE id = ?";
            psDeleteFromMohonCutiUmumWhereID = conn.prepareStatement(sqlDeleteFromMohonCutiUmumWhereID);
            
            String sqlSelectAllFromMohonCutiUmumWhereID = "SELECT * FROM mohoncutiumum WHERE YEAR(tarikhMohon) = ? AND id = ?";
            psSelectAllFromDaftarCutiWhereKampus = conn.prepareStatement(sqlSelectAllFromMohonCutiUmumWhereID);
       }
       
       catch(SQLException ex) {
           while (ex != null)
            {
                System.out.println ("SQLState: " +
                                 ex.getSQLState ());
                System.out.println ("Message:  " +
                                 ex.getMessage ());
		System.out.println ("Vendor:   " +
                                 ex.getErrorCode ());
                ex = ex.getNextException ();
		      System.out.println ("");
            }
            
            System.out.println("Connection to the database error");
	}
	catch (java.lang.Exception ex) {
            ex.printStackTrace ();
	}
   }

    /**
     * @return the psSelectAllFromDaftarCuti
     */
    public PreparedStatement getPsSelectAllFromDaftarCuti() {
        return psSelectAllFromDaftarCuti;
    }

    /**
     * @return the psInsertIntoDaftarCuti
     */
    public PreparedStatement getPsInsertIntoDaftarCuti() {
        return psInsertIntoDaftarCuti;
    }

    /**
     * @return the psSelectAllFromDaftarCutiWhereKampus
     */
    public PreparedStatement getPsSelectAllFromDaftarCutiWhereKampus() {
        return psSelectAllFromDaftarCutiWhereKampus;
    }

    /**
     * @return the psDeleteFromDaftarCutiWhereID
     */
    public PreparedStatement getPsDeleteFromDaftarCutiWhereID() {
        return psDeleteFromDaftarCutiWhereID;
    }
    
    /**
     * @return the psSelectAllFromMohonCuti
     */
    public PreparedStatement getPsSelectAllFromMohonCutiUmum() {
        return psSelectAllFromMohonCutiUmum;
    }

    /**
     * @return the psDeleteFromMohonCutiWhereID
     */
    public PreparedStatement getPsDeleteFromMohonCutiUmumWhereID() {
        return psDeleteFromMohonCutiUmumWhereID;
    }

    /**
     * @return the psSelectFromMohonCutiUmumWhereID
     */
    public PreparedStatement getPsSelectAllFromMohonCutiUmumWhereID() {
        return psSelectAllFromMohonCutiUmumWhereID;
    }
}

    
