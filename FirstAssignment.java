/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Your name
 *	@since	Today's date
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	    	
    	//	Line 2
    	GLabel s2 = new GLabel("I spent most of my summer at home but I also spent over a month in San Diego.", 10, 40);
    	s2.setFont(f);
    	add(s2);
    	
    	//	Line 3
    	GLabel s3 = new GLabel("I went to a summer program in UCSD called COSMOS and made a lot of new friends.", 10, 60);
    	s3.setFont(f);
    	add(s3);
    	
    	//	Line 4
    	GLabel s4 = new GLabel("I walked around the UCSD campus and bought things from their bookstore.", 10, 80);
    	s4.setFont(f);
    	add(s4);
    	
    	//	Line 5
    	GLabel s5 = new GLabel("I explored different places in San Diego, such as their beaches and the San Diego Zoo", 10, 100);
    	s5.setFont(f);
    	add(s5);
    	
    	//	Line 6
    	GLabel s6 = new GLabel("I went to one place called Little Italy, and took a lot of photos and ate a lot of yummy food", 10, 120);
    	s6.setFont(f);
    	add(s6);
    	
    	//	Line 7
    	GLabel s7 = new GLabel("At home, I spent a lot of time scrolling through Instagram and texting with my friends.", 10, 140);
    	s7.setFont(f);
    	add(s7);
    	
    	//	Line 8
    	GLabel s8 = new GLabel("I did a lot of studying for Science Olympiad, for events like Materials Science", 10, 160);
    	s8.setFont(f);
    	add(s8);
    	
    	//	Line 9
    	GLabel s9 = new GLabel("I went to Monta Vista for Science Olympiad study sessions and fun potlucks", 10, 180);
    	s9.setFont(f);
    	add(s9);
    	
    	//	Line 10
    	GLabel s10 = new GLabel("I spent one evening in Jollyman park enjoying a picnic with my two friends", 10, 200);
    	s10.setFont(f);
    	add(s10);
    	
    	//	Line 11
    	GLabel s11 = new GLabel("One day I went to Target with my friend and we got matcha from the nearby coffee shop", 10, 220);
    	s11.setFont(f);
    	add(s11);
    	
    	//	Line 12
    	GLabel s12 = new GLabel("I slept usually from 1AM to 10AM but I slept from 12AM to 6AM in the summer program", 10, 240);
    	s12.setFont(f);
    	add(s12);
    	
    	//	Line 13
    	GLabel s13 = new GLabel("I made scrapbook birthday cards for my friends with August and September birthdays.", 10, 260);
    	s13.setFont(f);
    	add(s13);
    	
    	//	Line 14
    	GLabel s14 = new GLabel("I made a lot of Spotify playlists and listened to a lot of new music.", 10, 280);
    	s14.setFont(f);
    	add(s14);
    	
    	//	Line 15
    	GLabel s15 = new GLabel("Overall, I had a really relaxing and nice break from all the schoolwork from Monta Vista", 10, 300);
    	s15.setFont(f);
    	add(s15);
    }
    
}
