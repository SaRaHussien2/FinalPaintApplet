/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Applet.java to edit this template
 */
package finalpaintapplet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SaRa
 */
public class Myapplet extends Applet implements ActionListener  {
    Button b1=new Button("Ok");
    Button b2=new Button("Cancle");
    String str=" ";

/*
String name = " ",
gender = " ";
int age;
TextField n = new TextField(10);

CheckboxGroup g = new CheckboxGroup();
Checkbox m = new Checkbox("Male",g,true);
Checkbox f = new Checkbox("Female",g,true);


Choice c = new Choice();

Label l1 = new Label ("Enter Name :");

Label l2 = new Label ("Select Gender :");

Label l3 = new Label ("Age :");

Button b1 = new Button("Submit");
*/
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        
        add(b1);
        add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);


    }
        // TODO start asynchronous download of heavy resources
        
     /*   
add(l1);
add(n);
add(l2);
add(m);
add(f);
add(l3);


c.add("18");
c.add("19");
c.add("20");
c.add("21");
add(c);


add(b1);
b1.addActionListener(this);
}
*/
    
@Override
public void paint (Graphics g){
    
    g.drawString(str, 20, 50);
/*
g.drawString("Name: "+name,20,100);
g.drawString("Gender: "+gender,20,120);
g.drawString("Age: "+age,20,140);
*/

}

    // TODO overwrite start(), stop() and destroy() methods

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String st = e.getActionCommand();
        if(st.equals("OK")) 
        {
            str = "Ok Clicked";
        }
        if(st.equals("Cancle")) 
        {
            str = "Cancled Clicked" ;
        }
        repaint();
        
        /*
        name = n.getText();
        gender = g.getSelectedCheckbox().getLabel();
        age = Integer.parseInt(c.getSelectedItem());
        repaint();
        */

    }
}
