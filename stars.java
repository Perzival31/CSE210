package package1;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JFrame; 
import java.util.*;
import java.util.concurrent.TimeUnit; 


class Variables {
	
	 int x;
	 int y;
	 int speedx;
	 int speedy;
	 int sizex;
	 int sizey;
	 int amount;
	 Color color;
	 
}

public class stars extends JFrame {
 
 static int width = 800;
 static int height = 600;
 static ArrayList<Variables> starsinfo = new ArrayList<Variables>();
 static ArrayList<Variables> ovalsinfo = new ArrayList<Variables>();
 

 public stars() {  // initial setting
     super("Stars");
     setBounds(100, 100, width, height);  // output position on your monitor 
     setResizable(false);
     setVisible(true);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
 } 
    
 public void paint(Graphics g) {   // call methods from here
	 for (int i = 0; i != 1;) {
		drawbackground(g);
		drawovals(g);
		drawstars(g);
		 try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	 }
 }
 
 
 
 public static void main(String[] args) { 
	 getamount();
	 makeshapes(true);
	 makeshapes(false);
	 new stars();
 }
 
 public static void getamount() {
	 Variables star = new Variables();
	 Variables oval = new Variables();
	 Scanner input = new Scanner(System.in); 
	 System.out.println("Please input amount of Stars then amount of Ovals. (Must be below 100)");
	 boolean validinput = false;
	 do {
		 System.out.print("\nStars:");
		 star.amount = input.nextInt();
		 System.out.print("\nOvals: ");
		 oval.amount = input.nextInt();
		 
		 if (star.amount < 0 || star.amount > 100 || oval.amount < 0 || oval.amount > 100) {
			 System.out.println("Please input values between 0 and 100.");
		 }
		 
		 else { validinput = true; }
		 
	 } while (!validinput);
	
	starsinfo.add(star);
	ovalsinfo.add(oval);
 }
 
 public static void drawovals(Graphics g) {
	 
	 for (int i = 2; i < ovalsinfo.size(); i++) {
		 g.setColor(ovalsinfo.get(i).color);
		 ovalsinfo.get(i).x += ovalsinfo.get(i).speedx;
		 ovalsinfo.get(i).y += ovalsinfo.get(i).speedy;
		 if (ovalsinfo.get(i).x > width || ovalsinfo.get(i).x < 0) {ovalsinfo.get(i).speedx = ovalsinfo.get(i).speedx * -1;}
		 if (ovalsinfo.get(i).y > height || ovalsinfo.get(i).y < 50) {ovalsinfo.get(i).speedy = ovalsinfo.get(i).speedy * -1;}
		 g.fillOval(ovalsinfo.get(i).x, ovalsinfo.get(i).y, ovalsinfo.get(i).sizex, ovalsinfo.get(i).sizey);
		 
	 }
 }
 
 public static void drawstars(Graphics g) {
	 for (int i = 2; i < starsinfo.size(); i++) {
		 g.setColor(ovalsinfo.get(i).color);
		 int[] xCoords = new int[10];
		 int[] yCoords = new int[10];
		 int ang = 90-36;
		 double rad;
		 double PI=3.14149;
		 starsinfo.get(i).x += starsinfo.get(i).speedx;
		 starsinfo.get(i).y += starsinfo.get(i).speedy;
		 if (starsinfo.get(i).x > width || starsinfo.get(i).x < 0) {starsinfo.get(i).speedx = starsinfo.get(i).speedx * -1;}
		 if (starsinfo.get(i).y > height || starsinfo.get(i).y < 50) {starsinfo.get(i).speedy = starsinfo.get(i).speedy * -1;}
		 	for (int j = 0; j < 10; j++) {
		 		if (j%2==0) rad=starsinfo.get(i).sizex*0.38;
		 		else rad=starsinfo.get(i).sizex;
		 		xCoords[j]+=(int)(rad*Math.cos(PI*ang/180.0));
		 		yCoords[j]-=(int)(rad*Math.sin(PI*ang/180.0));
		 		ang+=36;
		 	    xCoords[j]+=starsinfo.get(i).x;
		 	    yCoords[j]+=starsinfo.get(i).y;
		 	}
		 	g.fillPolygon(xCoords, yCoords, 10);
	 }
 }
 
 public static void makeshapes(boolean isstar) {
	 Random rand = new Random();
	 int amount = 0;
	 if (isstar) { amount = starsinfo.get(0).amount; }
	 else { amount = ovalsinfo.get(0).amount; }
	 for (int i = 0; i <= amount; i++) {
		 Variables shapeinfo = new Variables();
		 shapeinfo.x = rand.nextInt(600);
		 shapeinfo.y = rand.nextInt(400) + 50;
		 shapeinfo.speedx = rand.nextInt(5)+1;
		 shapeinfo.speedy = rand.nextInt(5)+1;
		 shapeinfo.sizex = rand.nextInt(50);
		 shapeinfo.sizey = rand.nextInt(50);
		 int r = rand.nextInt(255);
		 int g = rand.nextInt(255);
		 int b = rand.nextInt(255);
		 shapeinfo.color = new Color(r, g, b);
		 if (isstar) { starsinfo.add(shapeinfo); }
		 else { ovalsinfo.add(shapeinfo); }
	 }
 }
 
 public static void drawbackground(Graphics g) {
     g.setColor(Color.BLACK);
     g.fillRect(0, 0, width, height);
 }
 

}
