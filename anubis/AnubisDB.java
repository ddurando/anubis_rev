package anubis;

import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AnubisDB {
   private static Connection c = null;

   public static Connection createOrOpenDatabase(String database) {
      String url = "jdbc:sqlite:" + database;
      try {
         Connection conn = DriverManager.getConnection(url);
         return conn;
     } catch (SQLException e) {
         return null;
     }
  }
 
   public static int createDB(){
      Statement stmt = null;

      try {      
         Connection conn = createOrOpenDatabase("db/AnubisCC.db");

         stmt = conn.createStatement();
         String sql = "CREATE TABLE CCDB " +
                        "(URL TEXT NOT NULL, " + 
                        " KEY CHAR(50) NOT NULL, " + 
                        " TWITTER_ACC TEXT NOT NULL, " + 
                        " DATE DATETIME NOT NULL)"; 
         stmt.executeUpdate(sql);
         stmt.close();
         conn.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return 1;
      }
      return 0;
   }

   public static int insertEntry(String url, String key, String twitterAcc) throws Exception{
      Statement stmt = null;
      Date dt = new Date();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
      String strDate = dateFormat.format(dt);

      try {
         Connection conn = createOrOpenDatabase("db/AnubisCC.db");
         conn.setAutoCommit(false);
         stmt = conn.createStatement();

         String sql = String.format("SELECT * FROM CCDB WHERE URL='%s' AND TWITTER_ACC='%s' AND KEY='%s';", url, twitterAcc, key);       
         ResultSet rs = stmt.executeQuery(sql);
         if (!rs.next()){
            stmt = conn.createStatement();
            sql = String.format("INSERT INTO CCDB (URL,KEY,TWITTER_ACC,DATE) " +
                           "VALUES ('%s', '%s', '%s', '%s');", url, key, twitterAcc, strDate); 
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
            conn.close();
         }
      } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return 1;
      }
      return 0;
   }
  
}