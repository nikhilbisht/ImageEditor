public class ImageNegative{
  public void convertNegative()
	  {
		  BufferedImage bi;
		    if(actionSlided || actionResized || actionRotated || drawn){//if any of these action slected
		     bi=bufimg;     //change the buffered image
		    }
		    else{
		     bi=BufferedImg;
		    }
		    
		    ImgNegative(bi,bi.getHeight(),bi.getWidth());//calling function ImgNeative with image,width and height
	        
		    actionNegative=true; //set the actionNegative to true 
		    repaint();
		  
	  }
	  
public void ImgNegative(BufferedImage image,int h,int w){
		  int width = image.getWidth();//retrieving width
	        int height = image.getHeight();//retrieving height
	        
	        //convert to negative
	        for(int y = 0; y < height; y++){
	            for(int x = 0; x < width; x++){
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
public static void main(String[] args){
ImageNegative in=new ImageNegative();//object of ImgNegtive class
}
}