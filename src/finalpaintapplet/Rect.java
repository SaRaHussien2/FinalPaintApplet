/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalpaintapplet;
import java.awt.Color;
/**
 *
 * @author SaRa
 */
public class Rect extends MainShape{
        public Rect(){}
        public Rect(int x1,int y1, int x2,int y2, char shapeType,  boolean isDotted, boolean isFilled,Color c){
			super(x1,y1,x2,y2,shapeType,isDotted,isFilled,c);
		}
}
