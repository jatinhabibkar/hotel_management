import java.sql.*;
/**
 * index
 */
public class index {
    public static void main(String[] args) {
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=null;
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "");
        String sql="SELECT price FROM `menu` WHERE id=1";

        PreparedStatement statement =conn.prepareStatement(sql);
        ResultSet rs =statement.executeQuery();
        while(rs.next()){
            // System.out.print(rs.getInt(1)+",");
            System.out.print(rs.getString(1)+",");
            // System.out.println(rs.getInt(3));

        }
        conn.close();
    }  
    catch(Exception e){
        System.out.println("do not connect to DB -Error:"+e);
    }
    }
    
}