package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Kata5P1 {
 
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        String path = "C:/Users/RafaelCS/Desktop/KATA5.db";
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:" + path);	
         
        Statement st = con.createStatement();
         
        String query = "SELECT * FROM People";
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }
         
        //Modificamos el query
        query = "CREATE TABLE IF NOT EXISTS MAIL ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'mail' TEXT NOT NULL)";
        st.execute(query);
         
        //Modificamos de nuevo el query
        String emails = "C:/Users/RafaelCS/Downloads/emails.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(emails)));
         
        String new_query;
        while ((emails = reader.readLine()) != null) {
            new_query = "INSERT INTO MAIL (mail) VALUES ('" + emails + "')";
            st.executeUpdate(new_query);
        }
         
        rs.close();
        st.close();
        con.close();
    }    
}
