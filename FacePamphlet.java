/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		label = new JLabel("Name ");
		b1 = new JButton("Add");
		b2 = new JButton("Delete");
		b3 = new JButton("Lookup");
		b4 = new JButton("Change Status");
		b5 = new JButton("Change Picture");
		b6 = new JButton("Add Friend");
		t1 = new JTextField(TEXT_FIELD_SIZE);
		t2 = new JTextField(TEXT_FIELD_SIZE);
		t3 = new JTextField(TEXT_FIELD_SIZE);
		t4 = new JTextField(TEXT_FIELD_SIZE);
		add(label,NORTH);
		add(t1,NORTH);
		add(b1,NORTH);
		add(b2,NORTH);
		add(b3,NORTH);
		add(t2,WEST);
		add(b4,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		add(t3,WEST);
		add(b5,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		add(t4,WEST);
		add(b6,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		fpd = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		add(canvas);
		addActionListeners();
		t2.addActionListener(this);
		t3.addActionListener(this);
		t4.addActionListener(this);
		
    }
	
	
     /*ivars*/
	public JLabel label;
	public JButton b1,b2,b3,b4,b5,b6;
	public JTextField t1,t2,t3,t4;
	public FacePamphletDatabase fpd;
	public FacePamphletProfile cp;
	public FacePamphletProfile fp;
	public FacePamphletCanvas canvas;
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	if(e.getSource()==b1 && !t1.getText().equals(""))
    		{
     
    		cp = new FacePamphletProfile(t1.getText());
    		if(fpd.containsProfile(t1.getText()))
    			println("name already exists");
    		else{
    			canvas.removeAll();
    			canvas.displayProfile(cp);
    			canvas.showMessage("displaying profile of "+t1.getText());
    			fpd.addProfile(cp);
    		//println("new profile added "+t1.getText());
    		    }
    		}
    	if(e.getSource()==b2){
    		if(fpd.containsProfile(t1.getText()))
   		  {
   			fpd.deleteProfile(t1.getText());
   			println("profile deleted");
   		  }
   		else
   			println("profile doesn't exist");
    	  cp=null;
    	}
    	if(e.getSource()==b3)
    		{
    		if(fpd.containsProfile(t1.getText()))
    		   {
    			println(t1.getText());
    			cp=fpd.getProfile(t1.getText());
    			canvas.removeAll();
    			canvas.displayProfile(cp);
    			canvas.showMessage("displaying profile of "+t1.getText());
    		   }
    		else
    			println("profile doesn't exist");
    		}
    	if(e.getSource()==b4 || e.getSource()==t2)
    	{
    		if(cp==null)
    			println("select the profile to change the status");
    		else
    		{
    			cp.setStatus(t2.getText());
    			canvas.removeAll();
    			canvas.displayProfile(cp);
    			canvas.showMessage("displaying profile of "+t1.getText());	
    			println(t1.getText()+" "+cp.getStatus());
    		}
    	}
    	if(e.getSource()==b5 || e.getSource()==t3)
    	{
    		if(cp==null)
    			println("select the profile to change the status");
    		else
    		{
    			GImage image =null;
    			try
    			{
    				image = new GImage(t3.getText());
    				cp.setImage(image);
    				canvas.removeAll();
        			canvas.displayProfile(cp);
        			canvas.showMessage("displaying profile of "+t1.getText());
        			println(cp.getName()+" image added");
    			}
    			catch(ErrorException ex)
    			{
    				println("image doesn't exist");
    			}
    
    		}
    	}
    	if(e.getSource()==b6 || e.getSource()==t4)
    	{
    		if(cp==null)
    			println("select the profile to change the status");
    		else if(!fpd.containsProfile(t4.getText()))
    			println("this profile doesn't exist");
    		else
    		{
    			boolean p=cp.addFriend(t4.getText());
    			canvas.removeAll();
    			canvas.displayProfile(cp);
    			canvas.showMessage("displaying profile of "+t1.getText());
    			fp=fpd.getProfile(t4.getText());
    			fp.addFriend(cp.getName());
    			if(p)
    				println(t4.getText()+" is now friends with "+t1.getText());
    			else
    				println(t1.getText()+" and "+t4.getText()+" are already friends");
    		}
    			
    	}
    	if(cp!=null)
    	println("current profile -> "+cp.getName());
	}
    

}
