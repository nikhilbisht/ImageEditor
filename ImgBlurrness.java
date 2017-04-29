 public class ImgBlurrness extends JFrame implements ChangeListener{

		 JSlider slider;//slider for adjusting blurrness
		
		
		ImgBlurrness(){
			//for window closing event
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					dispose();
				}
			});
			
			Container cont=getContentPane();
			slider=new JSlider(-4,4,0);//creating a slider
			slider.setEnabled(false);//making default value of slider to false
			slider.addChangeListener(this);//event handling for slider
			cont.add(slider, BorderLayout.CENTER);//adding slider to frame making layout as borderlayout
			slider.setEnabled(true);
			setTitle("Image Blurrness");//set title of the slider
			setPreferredSize(new Dimension(300,100));//setting the size of the frame
			setVisible(true);//making visibility as true
			
			
			enabledSlider(false);
					}
         
          
		public void enabledSlider(boolean enabled){
			slider.setEnabled(enabled);
		}
		
		public void stateChanged(ChangeEvent e) {//when user moves slider
			setValue(slider.getValue());//get the value of slider when it is moving
		    setActionSlided(true);   //calling function setActionSlided by passing parameter boolean value true;
		    processImage();//calling function processImage for making image blur
		    repaint();
		    enableSaving(true);
			
		}	
     public void setActionSlided(boolean value){//setting the value of setActionSlided to true
        ActionSlided=true;
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
 public static void main(String[] args){
 ImgBlurrness ib=new ImgBlurrness();
 }
    }
	 
 
