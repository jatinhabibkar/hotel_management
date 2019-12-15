import java.awt.*;
import java.awt.event.*;
import java.sql.*;




// import javax.swing.JFrame;

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
    // JFrame frame = new JFrame();
    TextField nametf,foodIdtf,foodnametf,pricetf,TotalPricetf,quantitytf;
    Button AddBtn,Exitbtn,TotalPricebtn,Resetbtn,billbtn;
    Label nameText,foodId,foodname,priceText,TotalPriceText,quantityText,line,line1,totalis,totalisval;
    List list1,list2;
    int TotalPricehua=0;

   
    // bill
    TextField billhotel,billname, billTotal, billdish, billprice;


    dbHelpher db=new dbHelpher();

    eventhai(){
        

        nameText=new Label("Enter name               ");
        nametf=new TextField(10);

        Exitbtn =new Button("exit application");
        
        foodId=new Label("Enter food id            ");
        foodIdtf=new TextField(10);

        foodname=new Label("Enter food name          ");
        foodnametf=new TextField(10);

        priceText=new Label("Enter price              ");
        pricetf=new TextField(10);

        quantityText=new Label("Enter quantity           ");
        quantitytf=new TextField("1",10);

        AddBtn =new Button("                 add to list                ");
        AddBtn.addActionListener(this);
        AddBtn.setBackground(Color.decode("#343233"));
        AddBtn.setForeground(Color.decode("#e0c390"));
        

        Resetbtn=new Button("reset");
        Resetbtn.setBackground(Color.decode("#343233"));
        Resetbtn.setForeground(Color.decode("#e0c390"));
        Resetbtn.addActionListener(this);

        billbtn =new Button("get bill");
        TotalPriceText=new Label("TotalPrice");
        TotalPricetf=new TextField(10);

        

        Exitbtn.addActionListener(this);
        Exitbtn.setBackground(Color.decode("#343233"));
        Exitbtn.setForeground(Color.decode("#e0c390"));
        foodIdtf.addKeyListener(this);
        billbtn.setBackground(Color.decode("#343233"));
        billbtn.setForeground(Color.decode("#e0c390"));
        billbtn.addActionListener(this);

        list1=new List(20);
        list2=new List(20);


        // Exitbtn.addActionListener(this);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(330,690);
        // setLocation(null);
        setTitle("JAVA_break;fast");

        setBackground(Color.decode("#e0c390"));
        Font font = new Font ("Courier New",0,15);
        setFont(font);
        setForeground(Color.decode("#343233"));
        
        setResizable(false);
        TotalPricetf.disable();
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
        add(AddBtn);
        add(list1);
        add(list2);
        add(TotalPriceText);
        add(TotalPricetf);
        add(billbtn);
        add(Resetbtn);
        add(Exitbtn);
        

    }


    // actions 


    public void actionPerformed(ActionEvent ae)  {
        

        if(ae.getSource()==AddBtn){
            String fname=foodnametf.getText();
            int fprice=Integer.parseInt(pricetf.getText());
            int fquantity=Integer.parseInt(quantitytf.getText());
            String theflist=fname;
            String theplist=String.valueOf(fprice)+"*"+String.valueOf(fquantity)+"="+String.valueOf(fprice*fquantity);
            TotalPricehua+=fprice*fquantity;
            list1.add(theflist);
            list2.add(theplist);
            foodIdtf.setText("");
            foodnametf.setText("");
            pricetf.setText("");
        }

     

        if(ae.getSource()==Resetbtn){
            list1.clear();
            list2.clear();
            foodIdtf.setText("");
            foodnametf.setText("");
            pricetf.setText("");
            quantitytf.setText("1");
            nametf.setText("");

        }

        if(ae.getSource()==billbtn){
            //enter in database
            String namehai=nametf.getText();
            String listOfdishes="";
            TotalPricetf.setText(String.valueOf(TotalPricehua));

            int TotalPrice1=Integer.parseInt(TotalPricetf.getText());
            for (int i = 0; i < list1.getItemCount(); i++) {
                listOfdishes+=list1.getItem(i)+"/";
            }
            
            db.addTodb(namehai, listOfdishes, TotalPrice1);


            //second frame
            System.out.println(list1);
            System.out.println(list2);
            Frame frame1 =new Frame();
            frame1.setSize(300,400);
            frame1.setTitle("JAVA_break;fast");
    
            frame1.setBackground(Color.decode("#e0c390"));
            Font font = new Font ("ARIAL",0,15);
            
            frame1.setFont(font);
            frame1.setForeground(Color.decode("#343233"));
            Label billhotel=new Label("########JAVA_break;fast########");
            billhotel.setBounds(20,50, 250,40);

            Label billname=new Label("name of the customer  :"+nametf.getText());
            billname.setBounds(30,80, 400,40);

            Label line=new Label("---------------------------BILL-------------------------------");
            line.setBounds(0,130, 1000,20);

            Label totalis =new Label("total is :");
            totalis.setBounds(160,360,70,20);

            Label line1=new Label("----------------------TOTAL_COST-------------------------------");
            line1.setBounds(0,330, 1000,20);

            Label totalisval =new Label(String.valueOf(TotalPricehua));
            totalisval.setBounds(230,345,90,50);



            frame1.add(billhotel);frame1.add(billname);frame1.add(line);frame1.add(totalis);frame1.add(totalisval);frame1.add(line1);



            for (int i = 0; i < list2.getItemCount(); i++) {
                    Label listagain1=new Label(list2.getItem(i));
                    listagain1.setBounds(200,150+(i*30), 300,20);
                    frame1.add(listagain1);
            }

            for (int i = 0; i < list1.getItemCount(); i++) {
                    Label listagain=new Label(list1.getItem(i));
                    listagain.setBounds(20,150+(i*30), 300,20);
                    frame1.add(listagain);
                    
            }
            frame1.setResizable(false);
            frame1.setLayout(null);
            frame1.setVisible(true); 

        
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
        
            try{
            int fid=Integer.parseInt(foodIdtf.getText());
            System.out.println("Key in Text Field is pressed! And key pressed is: "+ke.getKeyChar());
            String fname=db.getname(fid);
            int fprice=db.getPrice(fid);
            foodnametf.setText(fname);
            pricetf.setText(String.valueOf(fprice));
            }catch(Exception e){
                foodnametf.setText("");
                pricetf.setText("");
                System.out.println("yo");
            }
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