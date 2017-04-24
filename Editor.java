import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.awt.color.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.*;
import javax.imageio.stream.*;

////start the ImgArea class
//The ImgArea class acts as a drawing area of the Image Editor program

class ImgArea extends Canvas{ 

  Image Img;
  BufferedImage BufferedImg;
  BufferedImage bufimg; 
  BufferedImage bufimg1; 
  float e;
  float radian;
  Dimension ds;
  int mX;
  int mY;
  int x;
  int y;
  static boolean imgLoad;
  boolean actionSlided;
  boolean actionResized;
  boolean actionCompressed;
  boolean actionTransparent;
  boolean actionRotated;
  boolean actionDraw;
  boolean actionNegative
  boolean drawn;
  MediaTracker mt;//used to track the image
  static Color c;
  Color colorTextDraw;
  Robot rb;
  boolean dirHor;
  String imgFileName;
  String fontName;
  int fontSize;
  String textToDraw;
  public ImgArea(){

   
  
	  addMouseListener(new mousexy());
	 
	   addKeyListener(new KeyList()); 

	   try{
	    rb=new Robot(); 
	   }catch(AWTException e){}

	   ds=getToolkit().getScreenSize();    
	   mX=(int)ds.getWidth()/2; 
	   mY=(int)ds.getHeight()/2;
	   
	  }
	  
	  public void paint(Graphics g){
	   Graphics2D g2d=(Graphics2D)g;   //it will create graphics 2d object
	   if(imgLoad){

		    
		    if(actionSlided || actionResized || actionTransparent || actionRotated || drawn ){
		     x=mX-bufimg.getWidth()/2;
		     y=mY-bufimg.getHeight()/2;
		     g2d.translate(x,y);  // it will move to coordinate x and y
		     g2d.drawImage(bufimg,0,0,null); // it will draw the image
		     
		     }
	 
		    else{
	     
	   
	     x=mX-BufferedImg.getWidth()/2;
	     y=mY-BufferedImg.getHeight()/2;
	     g2d.translate(x,y); 
	     g2d.drawImage(BufferedImg,0,0,null); 
	     
		    }}
	   g2d.dispose(); //it will clear the graphic 2d object
	   
	  }

	  class mousexy extends MouseAdapter{
	   
	   public void mousePressed(MouseEvent e){
	 
	   }
	   
	   
	  }
	  

	

	//the KeyList class expands KeyAdapter so that it can handle key events 
	  class KeyList extends KeyAdapter{
	   public void keyPressed(KeyEvent e){
	    if(e.getKeyCode()==27){ 
	     actionDraw=false;
	     textToDraw="";
	     fontName="";
	     fontSize=0;
	     }
	    }
	   }

 //it will prepare the image so that it can be edited and display on the screen 
  public void prepareImg(String filename){
   
   try{
  
   mt=new MediaTracker(this);    // it will track the image loading processs
   Img=Toolkit.getDefaultToolkit().getImage(filename); 
   mt.addImage(Img,0);
    mt.waitForID(0); 
    
   int width=Img.getWidth(null);
   int height=Img.getHeight(null);
   //creating buffered image from the image so any change to the image can be made
   BufferedImg=createBufferedImageFromImage(Img,width,height,false);
   
   bufimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
	   //the update image data is stored in the buffered image   
   imgLoad=true;
   }catch(Exception e){System.exit(-1);}
  }

  

 public BufferedImage createBufferedImageFromImage(Image image, int width, int height, boolean tran)
   { BufferedImage dest ;
  if(tran) 
       dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
  else
   dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       Graphics2D g2 = dest.createGraphics();
       g2.drawImage(image, 0, 0, null);
       g2.dispose();
       return dest;
   }
 



 public void setValue(float value){ 
  e=value;
 }

 public void setActionSlided(boolean value ){ 
  actionSlided=value;
 }

	//This method will initialize all the variables
 public void initialize(){
	   imgLoad=false; 
	   actionSlided=false;
	   actionResized=false;
	   actionCompressed=false;
	   actionTransparent=false;
	   actionRotated=false;
	   actionDraw=false;
	   drawn=false;
	   dirHor=false;
	   c=null;
	   radian=0.0f;
	   e=0.0f;
	   }
 //cancelling the editing done so far
 public void reset(){
 if(imgLoad){
 prepareImg(imgFileName);
 repaint();
 }
 
}
 
 //w is width entered by the user, h is the height entered by the user
 public void ImgResize(int w,int h){
   BufferedImage bi=(BufferedImage)createImage(w,h);
   Graphics2D g2d=(Graphics2D)bi.createGraphics();////resize the update image
   

   if(actionSlided || actionTransparent || actionRotated ||drawn)
    g2d.drawImage(bufimg,0,0,w,h,null);
  //resize the original image
   else
    g2d.drawImage(BufferedImg,0,0,w,h,null);
   bufimg=bi;
   g2d.dispose();
  
 }

 public void setActionResized(boolean value ){ 
  actionResized=value;
 } 

	   public BufferedImage processImage(){
	 float[] blurMatrix=new float[16];// creating an array blurMatrix of type float
	 for (int i = 0; i < 16; i++)
			{blurMatrix[i] = 1.0f/16.0f;//setting up an array of values for the kernel
			}
	 Kernel kernel=new Kernel(4, 4, blurMatrix);//creating kernel object
	    BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null );/*constructing a ConvolveOp object from the kernel and use it for filtering*/
		bufimg = new BufferedImage(BufferedImg.getWidth(),BufferedImg.getHeight(),BufferedImage.TYPE_INT_RGB);//it will create a new image
		return op.filter(BufferedImg, bufimg);//calling filter method to transform an image into another
 }
 
  public void convertNegative()
	  {
		  BufferedImage bi;
		    if(actionSlided || actionResized || actionRotated || drawn){//if any of these action selected then update the buffered image
		     bi=bufimg;     
		    }
		    else{
		     bi=BufferedImg;//if user has not selected any of the above actions
		    }
		    
		    ImgNegative(bi));//calling function ImgNegative by passing image as an arguement
	        
		    actionNegative=true; //set the actionNegative to true 
		    repaint();
		  
	  }
	  
public void ImgNegative(BufferedImage image){
		  int width = image.getWidth();//retrieving width from the image
	        int height = image.getHeight();//retrieving height from the image
	        
	        //convert to negative
	        for(int y-pixel = 0; y-pixel< height; y++){//outer loop
	            for(int x-pixel = 0; x-pixel < width; x++){//inner loop
	                int p = image.getRGB(x,y);
	                
	                int a = (p>>24)&0xff;
	                int r = (p>>16)&0xff;
	                int g = (p>>8)&0xff;
	                int b = p&0xff;
	                
	                //subtract RGB from 255
	                r = 255 - r;
	                g = 255 - g;
	                b = 255 - b;
	                
	                //set new RGB value
	                p = (a<<24) | (r<<16) | (g<<8) | b;
	                
	             image.setRGB(x, y, p);
	            
	             
	            }
	        }
	  }
	 
 
}

public class Editor {
	public static void main(String args[]){
	       Main m=new Main();
}
}
