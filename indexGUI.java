import java.awt.*;
import java.awt.event.*;
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
class eventhai extends Frame implements ActionListener{
    TextField nametf,foodIdtf,foodnametf,pricetf,TotalPricetf,quantitytf;
    Button AddBtn,Exitbtn,TotalPricebtn;
    Label nameText,foodId,foodname,priceText,TotalPriceText,quantityText;
    List list1,list2;
    int TotalPricehua=0;

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
        quantitytf=new TextField(10);

        AddBtn =new Button("add to list");
        AddBtn.addActionListener(this);

        TotalPriceText=new Label("     TotalPrice                    ");
        TotalPricetf=new TextField(10);

        TotalPricebtn= new Button("submit");
        TotalPricebtn.addActionListener(this);
        Exitbtn.addActionListener(this);
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
        add(Exitbtn);
    }


    // actions 


    public void actionPerformed(ActionEvent ae)  {
        

        if(ae.getSource()==AddBtn){
            int fid=Integer.parseInt(foodIdtf.getText());
            String fname=foodnametf.getText();
            int fprice=Integer.parseInt(pricetf.getText());
            int fquantity=Integer.parseInt(quantitytf.getText());
            String theflist=String.valueOf(fid)+" : "+fname;
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
            TotalPricetf.setText(String.valueOf(TotalPricehua));

        }

        if(ae.getSource()==Exitbtn){
            System.exit(0);
        }
        
    }


}