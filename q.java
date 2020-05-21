import java.awt.*;
import java.awt.event.*;
class ChoiceAction extends Frame
{
Choice c;
Label l;
    public ChoiceAction()
    {
   
    // Set frame properties
    setTitle("Choice with ItemListener Demo");
    setSize(400,400);
    setLayout(new FlowLayout());
    setLocationRelativeTo(null);
    setVisible(true);
   
    // Create choice
    c=new Choice();
   
    // Create label
    l=new Label();
   
    // Add items
    c.add("Apple");
    c.add("Mango");
    c.add("Guava");
    c.add("Orange");
    c.add("Pineapple");
    c.add("Grapes");
   
    // Add choice
    add(c);
   
    // Add label
    add(l);
   
    // Add item listener
    c.addItemListener(new ItemListener(){
        public void itemStateChanged(ItemEvent ie)
        {
        l.setText("You selected "+c.getSelectedItem());
        }
    });
   
    }
    public static void main(String args[])
    {
    new ChoiceAction();
    }
}