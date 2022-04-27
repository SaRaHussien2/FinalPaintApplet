/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Applet.java to edit this template
 */

package finalpaintapplet;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

public class MyPaint extends Applet {
	int i = 0;
	Shape currentShape;
	int oldx=0;
	int oldy=0;
	Color myColor= new Color(0,0,0);
	boolean isFill=false;
	boolean isEraser=false;
	boolean isLine=true;
	boolean isRect=false;
	boolean isOval=false;
	boolean isFreeHand=false;
	public Vector<Shape> shapes = new Vector<Shape>();
	public Vector<Shape> undoShapes = new Vector<Shape>();
	Image offScreenImage;
	Image img;
	Graphics offScreenGraphics;
	File myFile;
	//////////////////////////////////////////////////////////////////////////////
	abstract public class Shape
	{
		private int x1;
		private int y1;
		Color color;
		public Shape(int xx, int yy, Color c)
		{
			x1=xx;
			y1=yy;
			color=c;
		}
		public int getX1()
		{
			return x1;
		}
		public int getY1()
		{
			return y1;
		}
		public void setX1(int x)
		{
			x1=x;
		}
		public void setY1(int y)
		{
			y1=y;
		}
		public Color getColor()
		{
			return color;
		}
		abstract public int getX2();
		abstract public int getY2();
		abstract public void setX2(int c);
		abstract public void setY2(int c);
		abstract public void setWidth(int c);
		abstract public void setHieght(int c);
		abstract public int getWidth();
		abstract public int getHeight();
		abstract public boolean isFiilled();
		
	}
	//////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////
	public class MyLine extends Shape
	{
		public int x2;
		public int y2;
		public MyLine(int xx1,int yy1, int xx2, int yy2, Color c)
		{
			super(xx1, yy1, c);
			x2=xx2;
			y2=yy2;
		}
		public int getX2() {
			return x2;
		}
		public int getY2() {
			return y2;
		}
		public void setX2(int xx2) {
			x2=xx2;
		}
		public void setY2(int yy2) {
			y2=yy2;
		}
		@Override
		public void setWidth(int c) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void setHieght(int c) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public int getWidth() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int getHeight() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public boolean isFiilled() {
			// TODO Auto-generated method stub
			return false;
		}
	}
	//////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////
	public class MyRect extends Shape
	{
		public int width;
		public int height;
		public boolean isFilled;
		public MyRect(int xx, int yy, int wid, int heig, Color c, boolean isF)
		{
			super(xx, yy, c);
			width=wid;
			height=heig;
			isFilled=isF;
		}
		public int getX2() {
			return 0;
		}
		public int getY2() {
			return 0;
		}
		@Override
		public void setX2(int c) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void setY2(int c) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void setWidth(int c) {
			width=c;
			
		}
		@Override
		public void setHieght(int c) {
			height=c;
			
		}
		@Override
		public int getWidth() {
			return width;
		}
		@Override
		public int getHeight() {
			return height;
		}
		@Override
		public boolean isFiilled() {
			return isFilled;
		}

	}
	//////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////
	public class MyOval extends Shape
	{
		public int width;
		public int height;
		public boolean isFilled;
		public MyOval(int xx, int yy, int wid, int heig, Color c, boolean isF)
		{
			super(xx, yy, c);
			isFilled=isF;
		}
		public int getX2() {
			return 0;
		}
		public int getY2() {
			return 0;
		}
		@Override
		public void setX2(int c) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void setY2(int c) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void setWidth(int c) {
			width=c;
			
		}
		@Override
		public void setHieght(int c) {
			height=c;
			
		}
		@Override
		public int getWidth() {
			return width;
		}
		@Override
		public int getHeight() {
			return height;
		}
		@Override
		public boolean isFiilled() {
			return isFilled;
		}

	}
	
	//////////////////////////////////////////////////////////////////////////////

	class LineClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			isLine=true;
			isRect=false;
			isOval=false;
			isFreeHand=false;
			isEraser=true;
		}
	}
	////////////////////////////////////////////////
	
	class RectClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			isLine=false;
			isRect=true;
			isOval=false;
			isFreeHand=false;
			isEraser=true;
		}
	}
	///////////////////////////////////////////////
	class OvalClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			isLine=false;
			isRect=false;
			isOval=true;
			isFreeHand=false;
			isEraser=true;
		}
	}
	
////////////////////////////////////////////////////
	class fillClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(isFill==false)
				isFill=true;
			else
				isFill=false;
		}
	}
	
////////////////////////////////////////////////////
	class BlackClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			myColor = Color.BLACK;
		}
	}
///////////////////////////////////////////////////
	
	class RedClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			myColor = Color.RED;
		}
	}
///////////////////////////////////////////////////
	class GreenClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			myColor = Color.GREEN;
		}
	}
///////////////////////////////////////////////////
	class BlueClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			myColor = Color.BLUE;
		}
	}
///////////////////////////////////////////////////
	class EraseClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			isEraser=true;
			isRect=false;
			isOval=false;
			isLine=false;
			isFreeHand=false;
		}
	}
/////////////////////////////////////////////////////
	class EraseAllClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(shapes.get(shapes.size()-1).getWidth() != 2000 )
			{
				currentShape = new MyRect(0, 0,2000, 2000, Color.WHITE, true);
				shapes.add(currentShape);
				repaint();
			}	
		}
	}
/////////////////////////////////////////////////////
	
	class UndoClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(shapes.size() >= 1 && shapes.size() != 0)
			{
				undoShapes.add(shapes.get((shapes.size()-1)));
				shapes.remove(shapes.size()-1);
				repaint();
			}
		}
	}
/////////////////////////////////////////////////////
	class RedoClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(undoShapes.size() >= 1)
			{
				shapes.add(undoShapes.get((undoShapes.size()-1)));
				undoShapes.remove(undoShapes.size()-1);
				repaint();
			}
		}
	}
/////////////////////////////////////////////////////
	class FreeHandClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			isLine=false;
			isRect=false;
			isOval=false;
			isFreeHand=true;
			isEraser=false;
		}
	}
///////////////////////////////////////////////////////

	class  SaveClickHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			try {
				ImageIO.write((RenderedImage)offScreenImage, "jpg",new File("F:\\ITI\\img.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
/////////////////////////////////////////////////////// 

	class  LoadClickHandler  implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			shapes.clear();
			undoShapes.clear();
			img=getImage(getDocumentBase(),"myImage.jpg");
			repaint();
		}
	}
/////////////////////////////////////////////////////// 
	
	public void init()
	{	
		this.setSize(1960, 900);
		this.setBackground(Color.WHITE);

		offScreenImage = createImage(this.getWidth(), this.getHeight());
		myFile = new File("./myImage.jpg");
		offScreenGraphics=offScreenImage.getGraphics();
		
		//start line button
		Button line = new Button("Line");
		add(line);
		LineClickHandler lineClck = new LineClickHandler();
		line.addActionListener(lineClck);
		//end line button
		
		//start rectangle button
		Button rectButton = new Button("Rectangular");
		add(rectButton);
		RectClickHandler rectClck = new RectClickHandler();
		rectButton.addActionListener(rectClck);
		//end rectangle button
		
		//start Oval button
		Button ovalButton = new Button("Ovaaal");
		add(ovalButton);
		OvalClickHandler ovalClck = new OvalClickHandler();
		ovalButton.addActionListener(ovalClck);
		//end Oval button
		
		//start Fill button
		Button fillButton = new Button("Fill/UnFill");
		add(fillButton);
		fillClickHandler fillClck = new fillClickHandler();
		fillButton.addActionListener(fillClck);
		//end fill button
		
		//start black button
		Button blackButton = new Button("Black");
		blackButton.setBackground(Color.BLACK);
		blackButton.setForeground(Color.WHITE);
		add(blackButton);
		BlackClickHandler blackClck = new BlackClickHandler();
		blackButton.addActionListener(blackClck);
		//end black button
		
		//start red button
		Button redButton = new Button("Red");
		redButton.setBackground(Color.RED);
		redButton.setForeground(Color.WHITE);
		add(redButton);
		RedClickHandler redClck = new RedClickHandler();
		redButton.addActionListener(redClck);
		//end red button
		
		//start green button
		Button greenButton = new Button("Green");
		greenButton.setBackground(Color.GREEN);
		greenButton.setForeground(Color.WHITE);
		add(greenButton);
		GreenClickHandler greenClck = new GreenClickHandler();
		greenButton.addActionListener(greenClck);
		//end green button
		
		//start blue button
		Button blueButton = new Button("Blue");
		blueButton.setBackground(Color.BLUE);
		blueButton.setForeground(Color.WHITE);
		add(blueButton);
		BlueClickHandler blueClck = new BlueClickHandler();
		blueButton.addActionListener(blueClck);
		//end blue button
		
		//start erase button
		Button eraseButton = new Button("Eraser");
		add(eraseButton);
		EraseClickHandler eraseClck = new EraseClickHandler();
		eraseButton.addActionListener(eraseClck);
		//end erase button
		
		//start eraseAll button
		Button eraseAllButton = new Button("EraserAll");
		add(eraseAllButton);
		EraseAllClickHandler eraseAllClck = new EraseAllClickHandler();
		eraseAllButton.addActionListener(eraseAllClck );
		//end eraseAll button
		
		//start unDo button
		Button undoButton = new Button("UNDO");
		add(undoButton);
		UndoClickHandler undoClck = new UndoClickHandler();
		undoButton.addActionListener(undoClck);
		//end unDo button//RedoClickHandler
		
		//start reDo button
		Button redoButton = new Button("REDO");
		add(redoButton);
		RedoClickHandler redoClck = new RedoClickHandler();
		redoButton.addActionListener(redoClck);
		//start reDo button
		
		//start freehand button
		Button freehandButton = new Button("FreeHand");
		add(freehandButton);
		FreeHandClickHandler freeHandClck = new FreeHandClickHandler ();
		freehandButton.addActionListener(freeHandClck);
		//end freehand button
		
		//start of save button
		Button saveButton = new Button("Save");
		add(saveButton);
		SaveClickHandler saveClck = new SaveClickHandler ();
		saveButton.addActionListener(saveClck);
		//end of save button
		
		//start of load button
		Button loadButton = new Button("Load");
		add(loadButton);
		LoadClickHandler loadClck = new LoadClickHandler ();
		loadButton.addActionListener(loadClck);
		//end of load button
/////////////////////////////////////////////////////////////////////////////////////////////start of mouse listener on the applet	
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				undoShapes.clear();
				if(isLine == true)
				{
					currentShape=new MyLine(e.getX(),e.getY(),0,0, myColor);
					shapes.add(currentShape);
				}
				else if(isRect == true)
				{
					currentShape=new MyRect(e.getX(),e.getY(), 0, 0, myColor, isFill);
					oldx=e.getX();
					oldy=e.getY();
					shapes.add(currentShape);
				}
				else if(isOval == true)
				{
					currentShape = new MyOval(e.getX(), e.getY(), 0, 0, myColor, isFill);
					oldx=e.getX();
					oldy=e.getY();
					shapes.add(currentShape);
				}
				else if(isEraser == true)
				{
					currentShape=new MyRect(e.getX(), e.getY(), 10, 10, Color.WHITE, true);
					shapes.add(currentShape);
					repaint();

				}
				else if(isFreeHand == true)
				{
					currentShape=new MyLine(e.getX(),e.getY(),e.getX(),e.getY(), myColor);
					oldx=e.getX();
					oldy=e.getY();
					shapes.add(currentShape);
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {	
				if(isLine == true)
				{
					currentShape.setX2(e.getX());
					currentShape.setY2(e.getY());
					repaint();
				}
				else if(isRect == true)
				{
					adjustRectangle(e);
					repaint();
				}
				
				else if(isOval == true)
				{
					adjustOval(e);
					repaint();
				}
				else if(isEraser == true)
				{
					
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
/////////////////////////////////////////////////////////////////////////////////////////////end of mouse listener on the applet	
		
		
		
/////////////////////////////////////////////////////////////////////////////////////////////start of mouse motion on the applet	
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if(isLine == true)
				{
					currentShape.setX2(e.getX());
					currentShape.setY2(e.getY());
					repaint();
				}
				else if(isRect == true)
				{
					adjustRectangle(e);
					repaint();
				}
				else if(isOval == true)
				{
					/////////////////////////
					adjustOval(e);
					repaint();
				}
				else if(isEraser == true)
				{
					currentShape=new MyRect(e.getX(), e.getY(), 10, 10, Color.WHITE, true);
					shapes.add(currentShape);
					repaint();
				}
				if(isFreeHand == true)
				{
					currentShape=new MyLine(oldx,oldy,e.getX(),e.getY(), myColor);
					oldx=e.getX();
					oldy=e.getY();
					shapes.add(currentShape);
					repaint();
				}
			}
		});
/////////////////////////////////////////////////////////////////////////////////////////////end of mouse motion on the applet	

	}//end of init method
	
	
	public void paint(Graphics g)
	{
		
		offScreenGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		offScreenGraphics.drawImage(img, 0, 0, this);
		
		for(int j=0; j<shapes.size(); j++)
		{
			offScreenGraphics.setColor(shapes.get(j).getColor());
			if(shapes.get(j) instanceof MyLine)
			{
				offScreenGraphics.drawLine(shapes.get(j).getX1(), shapes.get(j).getY1(), shapes.get(j).getX2(), shapes.get(j).getY2());
			}
			
			else if(shapes.get(j) instanceof MyRect)
			{
				if(shapes.get(j).isFiilled()==false)
					offScreenGraphics.drawRect(shapes.get(j).getX1(), shapes.get(j).getY1(), shapes.get(j).getWidth(), shapes.get(j).getHeight());
				else
					offScreenGraphics.fillRect(shapes.get(j).getX1(), shapes.get(j).getY1(), shapes.get(j).getWidth(), shapes.get(j).getHeight());
					
			}
			
			else if(shapes.get(j) instanceof MyOval)
			{
				if(shapes.get(j).isFiilled()==false)
					offScreenGraphics.drawOval(shapes.get(j).getX1(), shapes.get(j).getY1(), shapes.get(j).getWidth(), shapes.get(j).getHeight());
				else
					offScreenGraphics.fillOval(shapes.get(j).getX1(), shapes.get(j).getY1(), shapes.get(j).getWidth(), shapes.get(j).getHeight());
			}
		}	


		
		g.drawImage(offScreenImage, 0, 0, this);
	}//end of paint method
	
	public void update(Graphics g)
	{
		paint(g);
	}
	//used to adjust rectangle width, height , x ,y with each movement in mouse motion , and one time at mouse release
	public void adjustRectangle(MouseEvent e)
	{
		currentShape.setWidth(Math.abs(e.getX()-oldx));
		currentShape.setHieght(Math.abs(e.getY()-oldy));
		if( e.getX() <= oldx && e.getY() >= oldy )
		{
			currentShape.setX1(e.getX());
			currentShape.setY1(oldy);
		}
		else if( e.getX() <= oldx && e.getY() <= oldy )
		{
			currentShape.setX1(e.getX());
			currentShape.setY1(e.getY());
		}
		else if(e.getX() >= oldx && e.getY() <= oldy )
		{
			currentShape.setX1(oldx);
			currentShape.setY1(e.getY());
		}
		else
		{
			currentShape.setX1(oldx);
			currentShape.setY1(oldy);
		}
	}//end of adjustRectangle
	
	
	//used to adjust oval width, height , x ,y with each movement in mouse motion , and one time at mouse release
	public void adjustOval(MouseEvent e)
	{
		currentShape.setWidth(Math.abs(e.getX()-oldx));
		currentShape.setHieght(Math.abs(e.getY()-oldy));
		if( e.getX() <= oldx && e.getY() >= oldy )
		{
			currentShape.setX1(e.getX());
			currentShape.setY1(oldy);
		}
		else if( e.getX() <= oldx && e.getY() <= oldy )
		{
			currentShape.setX1(e.getX());
			currentShape.setY1(e.getY());
		}
		else if(e.getX() >= oldx && e.getY() <= oldy )
		{
			currentShape.setX1(oldx);
			currentShape.setY1(e.getY());
		}
		else
		{
			currentShape.setX1(oldx);
			currentShape.setY1(oldy);
		}
	}//end of adjust oval
}
