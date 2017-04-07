import java.applet.*; 
import java.awt.*; import java.awt.image.*;
 public class Crop extends Applet 
{ Image i, j; 
public void init () 
{ MediaTracker mt = new MediaTracker (this);
 i = getImage(getDocumentBase(), "rosey.jpg"); mt.addImage (i, 0); 
try 
{ mt.waitForAll(); 
int width = i.getWidth(this); 
int height = i. getHeight(this); 
j = createImage (new FilteredImageSource(i.getSource(), new CropImageFilter(width/3, height/3, width/3, height/3))); 
} 
catch (InterruptedException e) { e.printStackTrace(); } 
} 

public void paint (Graphics g) 
{ g.drawImage (i, 10, 10, this); // regular 

if (j != null) { g.drawImage (j, 10, 90, this); // cropped } 
}

 }
