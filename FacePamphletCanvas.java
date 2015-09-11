/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		GLabel label = new GLabel(msg,(APPLICATION_WIDTH/2),APPLICATION_HEIGHT);
		label.setFont(MESSAGE_FONT);
		add(label);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		GLabel name = new GLabel(profile.getName(),LEFT_MARGIN,TOP_MARGIN);
		GLabel status = new GLabel(profile.getName()+" "+profile.getStatus(),LEFT_MARGIN,TOP_MARGIN+IMAGE_HEIGHT+20);
		GImage image = profile.getImage();
		GLabel friends =  new GLabel("Friends :",APPLICATION_WIDTH/2,TOP_MARGIN+20);
		Iterator<String> it = profile.getFriends();
		double j=0;
		while(it.hasNext())
		{
			GLabel friends_list=new GLabel(it.next(),APPLICATION_WIDTH/2,TOP_MARGIN+20+friends.getHeight()+j);
			add(friends_list);
			j=j+friends_list.getHeight();
		}
		
		if(image==null)
		{
			GRect rect = new GRect(LEFT_MARGIN,TOP_MARGIN+5,IMAGE_WIDTH,IMAGE_HEIGHT);
			GLabel img = new GLabel("No Image",LEFT_MARGIN+(IMAGE_WIDTH/2),TOP_MARGIN+5+(IMAGE_HEIGHT/2));
			add(rect);
			add(img);
		}
		else
		{
			image.setBounds(LEFT_MARGIN,TOP_MARGIN+5,IMAGE_WIDTH,IMAGE_HEIGHT);
			add(image);
		}
		status.setFont(PROFILE_STATUS_FONT);
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name);
		if(profile.getStatus()==null)
			status.setLabel("No status");
		add(status);
		add(friends);
	}

	
}
