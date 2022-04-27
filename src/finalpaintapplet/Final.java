package finalpaintapplet;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Final extends Applet implements MouseMotionListener,MouseListener,ActionListener,ItemListener{
    int
        sizeheight,
        sizewidth,
        x1=0,   
        y1=0,
        x2=0,
        y2=0,
        x,
        y,
        count,
        width=0,
        height=0,
        eraserWidth=20,
        eraserHeight=20,
        imgFlag=0;
        char shapeType;
    Button 

    //Colors Buttons 
        redButton,
        blueButton,
        greenButton,
        yellowButton,
        blackButton,
    //Shapes Buttons  
        lineButton,
        rectangleButton,
        ovalButton,

    //Buttons functionality
        freeHandButton,
        eraserButton,
        clearButton,
        undoButton,
        saveButton,
        openButton;
    
    //Checkboxes 
    Checkbox dottedBox,filledBox;
    boolean 
        isDotted = false,
        isFilled = false,
        isCleared = false,
        isUndo = false,
        isSaved=false;
    
    Color currentColor=Color.black;
    Image backbuffer;
    Image i;
    Graphics backg;
    BufferedImage bufferedImage = new BufferedImage ( 2000, 2000,BufferedImage.TYPE_INT_BGR);
    Choice c = new Choice();
    ArrayList <MainShape> shapesArray = new ArrayList<>();
    
    @Override
    public void init() { 
        resize(800,2000);
        setLayout(new FlowLayout());
        shapeType='w';
        
        // Start Adding Select List to choose the shape
            c.add("Line");
            c.add("Rectangle");
            c.add("Oval");
            add(c);
        // End Select List of Shapes
        
        // Start Adding Buttons of Colors to choose the Color
        //Start Black Button
        blackButton=new Button("Black");
        blackButton.setBackground(Color.black);
        blackButton.setForeground(Color.white);
        blackButton.addActionListener(this);
        add(blackButton);
        // End Black Button
        
        //Start Red Button
        redButton=new Button("Red");
        redButton.setBackground(Color.red);
        redButton.setForeground(Color.white);
        redButton.addActionListener(this);
        add(redButton);
        // End Red Button

        //Start Blue Button
        blueButton=new Button("Blue");
        blueButton.setBackground(Color.blue);
        blueButton.setForeground(Color.white);
        blueButton.addActionListener(this);
        add(blueButton);
        // End Blue Button

        //Start Green Button
        greenButton=new Button("Green");
        greenButton.setBackground(Color.green);
        greenButton.addActionListener(this);
        add(greenButton);
        // End Green Button

        //Start Yellow Button
        yellowButton=new Button("Yellow");
        yellowButton.setBackground(Color.yellow);
        yellowButton.setForeground(Color.black);
        yellowButton.addActionListener(this);
        add(yellowButton);
        // End Yellow Button

        // Start Checkboxes of Dotted and Filled Options
        //Dotted checkbox
         dottedBox =new Checkbox("Dotted");
         dottedBox.setBackground(Color.lightGray);
         dottedBox.addItemListener(this);    
         add(dottedBox);
        
        //Filled checkbox
         filledBox =new Checkbox("Filled");
         filledBox.setBackground(Color.lightGray);
         filledBox.addItemListener((ItemListener) this);
         add(filledBox);
        // End Checkboxes of Dotted and Filled Options
    
        //Start FreeHand button
        freeHandButton=new Button("Free Hand");
        freeHandButton.addActionListener((ActionEvent e) -> {
            shapeType= 's';          
        });
        add(freeHandButton);
        //End FreeHand button

         
        //Start Eraser Button
        eraserButton=new Button("Eraser");
        eraserButton.addActionListener((ActionEvent e) -> {
            shapeType='e';
        });
        add(eraserButton);
        // End Eraser Button

        
        //Start Clear Button
        clearButton=new Button("Clear");
        clearButton.addActionListener(this);
        add(clearButton);
        // End Clear Button
        
        //Start Undo Button
        undoButton=new Button("Undo");
        undoButton.addActionListener(this);
        add(undoButton);
        // End Undo Button

        
        //Save button
        saveButton=new Button("Save");
        saveButton.addActionListener(this);
        add(saveButton);
        sizewidth = getSize().width;
        sizeheight = getSize().height;
        backbuffer = createImage( sizewidth, sizeheight );
        backg = backbuffer.getGraphics();
        backg.setColor( Color.white );
        backg.fillRect( 0, 0, sizewidth, sizeheight );
        backg.setColor( Color.black );
        //End Save Button

        //Start Open button
        openButton=new Button("Open");
        openButton.addActionListener((ActionEvent e) -> {
            i =getImage(getDocumentBase(), "image.png");
            repaint();          
        });
        add(openButton);
        //End Open button

        addMouseListener(this);
        addMouseMotionListener(this);
                
//class gahz array list howa da el linked list
    }
   @Override
    public void mouseDragged(MouseEvent e) {
        x2=e.getX();
        y2=e.getY(); 
           switch (shapeType) 
        { 
            case 'e':
                shapesArray.add(new Oval(x2, y2, eraserWidth, eraserHeight, shapeType, isDotted, isFilled,Color.white));
                break;
            case 's':
                shapesArray.add(new Line (x1,y1,x2,y2,shapeType,isDotted,currentColor));
                count+=1;
                if(count % 2 ==0){
                        x1=x2;
                        y1=y2;
                }
                paint(backg);
                break;
            default:
                break;       
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        width=0;
        height=0;
        x1=e.getX();
        y1=e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        x2=e.getX();
        y2=e.getY();
        width= Math.abs(x2-x1);
        height = Math.abs(y2-y1);
        switch (shapeType) 
        {
            case 'l':
                shapesArray.add(new Line (x1,y1,x2,y2,shapeType,isDotted,currentColor));
                paint(backg);
                break;
            
            case 'r':
                x=Math.min(x1, x2);
                y=Math.min(y1, y2);
                shapesArray.add(new Rect(x, y, width, height, shapeType, isDotted, isFilled,currentColor));
                paint(backg);
                break;
                
            case 'o':
                x= Math.min(x1, x2);
                y=Math.min(y1, y2);
                shapesArray.add(new Oval(x, y, width, height, shapeType, isDotted, isFilled,currentColor));
                paint(backg);
                break;
                    
            default:
                break;
        }
        repaint();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    @Override
   public void itemStateChanged (ItemEvent e){
       if (e.getSource()==dottedBox) {
            if (isDotted == false){
                isDotted = true;
                dottedBox.setBackground(Color.gray);
            }
            else {
                isDotted = false;
                dottedBox.setBackground(Color.lightGray);
            }
            repaint();        
       }
        if (e.getSource()==filledBox) {
            if(isFilled== false){
                isFilled=true;
                filledBox.setBackground(Color.gray);
            }
            else{
                isFilled=false;	
                filledBox.setBackground(Color.lightGray);
            }       
       }  
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        String select = e.getActionCommand();

        // Check the button selected to apply the action
        switch (select) {
            case "Black":
                currentColor= Color.black;
                break;
            case "Red":
                currentColor= Color.red;
                break;
            case "Blue":
                currentColor= Color.blue;
                break;
            case "Green":
                currentColor= Color.green;
                break;
            case "Yellow":
                currentColor= Color.yellow;
                break;
            case "Clear":
                isCleared = isCleared==false;
                repaint();
                break;

            case "Undo":
                isUndo = true;
                if(!shapesArray.isEmpty()){
                    shapesArray.remove(shapesArray.size()-1);
                    repaint();
                }
                else
                    System.out.println("There is nothing to Undo");
                break;
            case "Save":
                //convert our image to a bufferedimage
                BufferedImage bufferedImg = new BufferedImage ( sizewidth, sizeheight,BufferedImage.TYPE_INT_BGR );
                bufferedImg.createGraphics().drawImage( backbuffer, 0, 0, this);
                try {
                ImageIO.write( bufferedImg, "png", new File ("F:\\ITI\\image.png") );
                } catch (IOException e1) {
                }
                System.out.println("Saved");
                break;
            default:
                break;
        }

        //Start Check Shapes Type selected
        if (null != c.getSelectedItem()) 
        switch (c.getSelectedItem()) {
            case "Line":
                shapeType = 'l';
                break;
            case "Rectangle":
                shapeType = 'r';
                break;
            case "Oval":
                shapeType = 'o';
                break;
            default:
                break;
        } 
    }     
       
    @Override
    public void paint(Graphics g){  
            g.drawImage(i, 50, 50, this);
                float[] dash = {10,10,10};
        Graphics2D g2d = (Graphics2D) g.create();
        if(isCleared){
            shapesArray = new ArrayList<>();
            isCleared = false;
        }
            for (int j=0; j< shapesArray.size(); j++){
                g.setColor(shapesArray.get(j).getColor());
                g2d.setColor(shapesArray.get(j).getColor());
                if(shapesArray.get(j).isDotted())
                    g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,10,dash,10 ));
                else
                  g2d.setStroke(new BasicStroke(0));
        switch (shapesArray.get(j).getShape()) 
        {
           case 'l':
                if(!shapesArray.get(j).isDotted()){
                    g.drawLine(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(), shapesArray.get(j).getX2(), shapesArray.get(j).getY2());
                }
                else{
                    g2d.drawLine(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(),shapesArray.get(j).getX2(), shapesArray.get(j).getY2());
                }      
                break;
                
            case 'r':
                if(!shapesArray.get(j).isDotted()){
                    g.drawRect(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(), shapesArray.get(j).getX2(), shapesArray.get(j).getY2());
                    if(shapesArray.get(j).isFilled){
                        g.fillRect(shapesArray.get(j).getX1(),shapesArray.get(j).getY1(), shapesArray.get(j).getX2(), shapesArray.get(j).getY2());     
                    }
                }
                else{
                    g2d.drawRect(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(),shapesArray.get(j).getX2(), shapesArray.get(j).getY2());
                    if(shapesArray.get(j).isFilled){
                       g.fillRect(shapesArray.get(j).getX1(),shapesArray.get(j).getY1(), shapesArray.get(j).getX2(), shapesArray.get(j).getY2());     
                    }
                }
                break;
            case 'o':
                if(!shapesArray.get(j).isDotted()){
                    g.drawOval(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(), shapesArray.get(j).getX2(), shapesArray.get(j).getY2());
                    if(shapesArray.get(j).isFilled){
                        g.fillOval(shapesArray.get(j).getX1(),shapesArray.get(j).getY1(), shapesArray.get(j).getX2(), shapesArray.get(j).getY2());     
                    }
                }
                else{
                    g2d.drawOval(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(),shapesArray.get(j).getX2(), shapesArray.get(j).getY2());
                    if(shapesArray.get(j).isFilled){
                       g.fillOval(shapesArray.get(j).getX1(),shapesArray.get(j).getY1(), shapesArray.get(j).getX2(), shapesArray.get(j).getY2());     
                    }
                }
                break;
            
            case 'e':
                g.fillOval(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(), eraserWidth, eraserHeight);
                break;
            
            case 's':
                if(!shapesArray.get(j).isDotted()){
                    g.drawLine(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(), shapesArray.get(j).getX2(), shapesArray.get(j).getY2());
                }
                else{
                    g2d.drawLine(shapesArray.get(j).getX1(), shapesArray.get(j).getY1(),shapesArray.get(j).getX2(), shapesArray.get(j).getY2());              
                }
                break;
            default:
                break;
            } 
        }
    }
}