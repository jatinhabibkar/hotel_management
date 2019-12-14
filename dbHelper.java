package packagehelp;
import java.sql.*;

class dbHelpher {
    Connection conn=null;
    dbHelpher(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "");
         
        }  
        catch(Exception e){
            System.out.println("do not connect to DB -Error:"+e);
        }
    }

    public String getname(int id) {
        String sqlname="SELECT dishName FROM `menu` WHERE id="+id;
        PreparedStatement statement =conn.prepareStatement(sqlname);
        ResultSet rs =statement.executeQuery();
        return rs.getString(1);

    }
    public int getPrice(int id) {
        String sqlname="SELECT price FROM `menu` WHERE id="+id;
        PreparedStatement statement =conn.prepareStatement(sqlname);
        ResultSet rs1 =statement.executeQuery();
        return rs1.getInt(1);
    }

    public void addTodb() {
        
    }    
    
}