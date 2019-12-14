import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * main
 */
public class indexGUI {

    public static void main(String[] args) {
        eventhai ob =new eventhai();

    }
}

/**
 * event1
 */
class eventhai extends Frame implements ActionListener,KeyListener{
    TextField nametf,foodIdtf,foodnametf,pricetf,TotalPricetf,quantitytf;
    Button AddBtn,Exitbtn,TotalPricebtn,Printbtn;
    Label nameText,foodId,foodname,priceText,TotalPriceText,quantityText;
    List list1,list2;
    int TotalPricehua=0;
    dbHelpher db=new dbHelpher();

    eventhai(){
        

        nameText=new Label("enter numbers            ");
        nametf=new TextField(10);

        Exitbtn =new Button("exit application");
        
        foodId=new Label("Enter food id            ");
        foodIdtf=new TextField(10);

        foodname=new Label("enter food name            ");
        foodnametf=new TextField(10);

        priceText=new Label("enter price              ");
        pricetf=new TextField(10);

        quantityText=new Label("enter quantity           ");
        quantitytf=new TextField("1",10);

        AddBtn =new Button("add to list");
        AddBtn.addActionListener(this);

        Printbtn=new Button("get print");
        Printbtn.addActionListener(this);

        TotalPriceText=new Label("     TotalPrice                    ");
        TotalPricetf=new TextField(10);

        

        TotalPricebtn= new Button("submit");
        TotalPricebtn.addActionListener(this);
        Exitbtn.addActionListener(this);
        foodIdtf.addKeyListener(this);

        list1=new List(20);
        list2=new List(20);


        // Exitbtn.addActionListener(this);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400,600);

        // add
        add(nameText);
        add(nametf);
        add(foodId);

        add(foodIdtf);
        add(foodname);
        add(foodnametf);
        add(priceText);
        add(pricetf);
        add(quantityText);
        add(quantitytf);
        add(list1);
        add(list2);
        add(AddBtn);
        add(TotalPriceText);
        add(TotalPricetf);
        add(TotalPricebtn);
        add(Printbtn);
        add(Exitbtn);
    }


    // actions 


    public void actionPerformed(ActionEvent ae)  {
        

        if(ae.getSource()==AddBtn){
            // int fid=Integer.parseInt(foodIdtf.getText());
            String fname=foodnametf.getText();
            // String fname=db.getname(fid);
            // System.out.println(fname);

            int fprice=Integer.parseInt(pricetf.getText());
            // int fprice=db.getPrice(fid);
            // System.out.println(fprice);

            
            int fquantity=Integer.parseInt(quantitytf.getText());
            String theflist=fname;
            String theplist=String.valueOf(fprice)+"*"+String.valueOf(fquantity)+"="+String.valueOf(fprice*fquantity);
            TotalPricehua+=fprice*fquantity;
            list1.add(theflist);
            list2.add(theplist);
            foodIdtf.setText("");
            foodnametf.setText("");
            pricetf.setText("");
            quantitytf.setText("");
        }

        if(ae.getSource()==TotalPricebtn){
            
            String namehai=nametf.getText();
            String listOfdishes="";
            TotalPricetf.setText(String.valueOf(TotalPricehua));

            int TotalPrice1=Integer.parseInt(TotalPricetf.getText());
            for (int i = 0; i < list1.getItemCount(); i++) {
                listOfdishes+=list1.getItem(i)+"/";
            }
            
            db.addTodb(namehai, listOfdishes, TotalPrice1);



        }

        if(ae.getSource()==Printbtn){
            
        }

        if(ae.getSource()==Exitbtn){
            System.exit(0);
        }
        
    }

    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getSource() == foodIdtf)
		{
            int fid=Integer.parseInt(foodIdtf.getText());
            System.out.println("Key in Text Field is pressed! And key pressed is: "+ke.getKeyChar());
            String fname=db.getname(fid);
            int fprice=db.getPrice(fid);
            foodnametf.setText(fname);
            pricetf.setText(String.valueOf(fprice));
		}

    }




}




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
        String name=null;

        try {
            String sqlname="SELECT dishName FROM `menu` WHERE id="+id;
            PreparedStatement statement =conn.prepareStatement(sqlname);
            ResultSet rs =statement.executeQuery();
            while(rs.next()){
                name=rs.getString(1);
            }
            System.out.println(name);
            return name;    

        } catch (Exception e) {
            return null;
        }
        

    }
    public int getPrice(int id) {
        int price=0;

        try {
            String sqlprice="SELECT price FROM `menu` WHERE id="+id;
            PreparedStatement statement =conn.prepareStatement(sqlprice);
            ResultSet rs1 =statement.executeQuery();
            while(rs1.next()){
                price=rs1.getInt(1);
            }
            System.out.println(price);
            return price;
        } catch (Exception e) {
            return 0;

        }
           
        
    }

    public void addTodb(String pName,String listOfdishes,int TotalPrice ) {
        System.out.println(pName);
        System.out.println(listOfdishes);
        System.out.println(TotalPrice);
        try{
        String sqltoadd="INSERT INTO `allorder`(`pName`, `listOfdishes`, `TotalPrice`) VALUES ('"+pName+"','"+listOfdishes+"',"+TotalPrice+")";
        PreparedStatement statement =conn.prepareStatement(sqltoadd);
        statement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        
    }    


    
}