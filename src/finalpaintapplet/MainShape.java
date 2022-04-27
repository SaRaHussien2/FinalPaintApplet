/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalpaintapplet;


//package finalpaintprojectpackage;

import java.awt.Color;
/**
 *
 * @author SaRa
 */
public class MainShape {
	protected int x1; //starting point
	protected int y1;
	protected int x2; 
	protected int y2;
	protected char shapeType;
	protected boolean isDotted;
	protected boolean isFilled;
	protected Color color;
	
        
        
        
        public MainShape(){
        }
        public MainShape(int x1,int y1,int x2,int y2,char shapeType,boolean isDotted,boolean isFilled,Color c){
                        
                        this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
			this.shapeType=shapeType;
			this.isDotted=isDotted;
			this.isFilled=isFilled;
                        this.color=c;
                
        }
        //For Line
         public MainShape(int x1,int y1,int x2,int y2,char shapeType,boolean isDotted,Color c){
                        
                        this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
			this.shapeType=shapeType;
			this.isDotted=isDotted;
			this.color=c;
                
        }
         
        public  boolean isFilled(){
		return isFilled;
	}
	public void setShape(char s){
		shapeType=s;
        }
	public boolean isDotted(){
		return this.isDotted;
	}
	public char getShape(){
		return shapeType;
        }
	public int getX1(){
		return x1;
        }
	public int getY1(){
		return y1;
	}
	public int getX2(){
		return x2;
	}
	public int getY2(){
		return y2;
	}
	public Color getColor(){
		return color;
	}
    
}
