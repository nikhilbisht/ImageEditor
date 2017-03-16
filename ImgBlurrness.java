//The blurring is achieved by replacement of each pixel with the average value from the pixel and its neighbours.
//ImgBlurrness class allows the user to blur an image
// User can adjust blurness using slider

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
			slider.setEnabled(false);
			slider.addChangeListener(this);//event handling for slider
			cont.add(slider, BorderLayout.CENTER);
			slider.setEnabled(true);
			setTitle("Image Blurrness");
			setPreferredSize(new Dimension(300,100));
			setVisible(true);
			pack();
			
			enabledSlider(false);
					}
		public void enabledSlider(boolean enabled){
			slider.setEnabled(enabled);
		}
		
		public void stateChanged(ChangeEvent e) {
			ima.setValue(slider.getValue());
		    ima.setActionSlided(true);   
		    ima.processImage();//calling function processImage
		    ima.repaint();
		    enableSaving(true);
			
		}		
 public BufferedImage processImage(){
	 float[] blurMatrix=new float[16];// creating an array blurMatrix of type float
	 for (int i = 0; i < 16; i++)
			{blurMatrix[i] = 1.0f/16.0f;//setting up an array of values for the kernel
			}
	 Kernel kernel=new Kernel(4, 4, blurMatrix);//creating kernel object
	    BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null );/*constructing a ConvolveOp object from the kernel and use it for filtering*/
		bufimg = new BufferedImage(BufferedImg.getWidth(),BufferedImg.getHeight(),BufferedImage.TYPE_INT_RGB);
		return op.filter(BufferedImg, bufimg);//calling filter method to transform an image into another
 }

public static void main(String args[]){
	       ImgBlurrness ib=new ImgBlurrness();
	   
	 }
		 
	 }
