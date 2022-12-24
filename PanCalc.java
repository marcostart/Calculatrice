package calculatrice;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;


public class PanCalc extends JButton{
	
	private String name;
	
	public PanCalc(String str) {
		super(str);
		this.name = str;
	}
	
	public void paintComponent (Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gc = new GradientPaint(20, 20, Color.yellow, 90, 90, Color.green,true);
//		GradientPaint gp = new GradientPaint(0, 0,
//		Color.red, 0, this.getHeight()/6, Color.magenta, true);
//		GradientPaint gp2 = new GradientPaint(0, this.getHeight()/6,
//		Color.magenta, 0, this.getHeight()/3, Color.blue, true);
//		GradientPaint gp3 = new GradientPaint(0, this.getHeight()/3,
//		Color.blue, 0, this.getHeight()/2, Color.green, true);
//		GradientPaint gp4 = new GradientPaint(0, this.getHeight()/2,
//		Color.green, 0, 2*this.getHeight()/3, Color.yellow, true);
//		GradientPaint gp5 = new GradientPaint(0, 2*this.getHeight()/3,
//		Color.yellow, 0, 5*this.getHeight()/6, Color.orange, true);
//		GradientPaint gp6 = new GradientPaint(0, 5*this.getHeight()/6,
//		Color.orange, 0, this.getHeight(), Color.red, true);
		
		//g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setPaint(gc);
		g2d.fillOval(this.getWidth()/8, this.getHeight()/8, 3*this.getWidth()/4, 3*this.getHeight()/4);
		g2d.setColor(Color.white);
		g2d.drawString(this.name, 3*this.getWidth()/8+5, this.getHeight()/2+5);
	}
}
