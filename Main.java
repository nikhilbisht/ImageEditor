import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener{
	 ImgArea ima;//object of the class ImgArea where image is going to load
	 JFileChooser Choose; 
	 JMenuBar mainmenu;
	 JMenu menu;
	 JMenu editmenu;
	 JMenuItem open;
	 JMenuItem saveas;
	 JMenuItem save;
	 JMenuItem exit; 
	 JMenuItem bright; 
	 JMenuItem negative
	 JMenuItem resize;
	 JMenuItem rotate;
	 
	 JMenuItem cancel;
	 String filename;
	 Main(){
	  ima=new ImgArea();
	  Container cont=getContentPane();
	  cont.add(ima,BorderLayout.CENTER );  
	  mainmenu=new JMenuBar();//specifying the menubar
	  //for file Menu
	  menu=new JMenu("File");
	  menu.setMnemonic(KeyEvent.VK_F);
	  
	  //for option open  in file menu
	  open=new JMenuItem("Open...");
	  open.setMnemonic(KeyEvent.VK_O);
	  open.addActionListener(this);

	  saveas=new JMenuItem("Save as...");
	  saveas.setMnemonic(KeyEvent.VK_S);
	  saveas.addActionListener(this);

	  save=new JMenuItem("Save");
	  save.setMnemonic(KeyEvent.VK_V);
	  save.addActionListener(this);  

	  exit=new JMenuItem("Exit");
	  exit.setMnemonic(KeyEvent.VK_X);
	  exit.addActionListener(this);
	  menu.add(open);
	  menu.add(saveas);
	  menu.add(save);
	  menu.add(exit);  

	  editmenu=new JMenu("Edit");
	  editmenu.setMnemonic(KeyEvent.VK_E);
	  bright=new JMenuItem("Image brightness");
	  bright.setMnemonic(KeyEvent.VK_B);
	  bright.addActionListener(this);

	  addingtext=new JMenuItem("Add text on image");
	  addingtext.setMnemonic(KeyEvent.VK_A);
	  addingtext.addActionListener(this);  

	  resize=new JMenuItem("Image resize");
	  resize.setMnemonic(KeyEvent.VK_R);
	  resize.addActionListener(this);
	 
	  negative=new JMenuItem("Negative");
	  negative.setMnemonic(KeyEvent.VK_P);
	  negative.addActionListener(this);

	 
	 
	  cancel=new JMenuItem("Cancel editing");
	  cancel.setMnemonic(KeyEvent.VK_L);
	  cancel.addActionListener(this);

	  
	  editmenu.add(bright);
	  editmenu.add(negative);
	  editmenu.add(resize);
	  
	  editmenu.add(cancel);

	  mainmenu.add(menu);
	  mainmenu.add(editmenu);
	  setJMenuBar(mainmenu);
	 
	  setTitle("Image Editor");
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
	     setVisible(true); 

	  Choose = new JFileChooser();
	      FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "gif","bmp","png");
	      Choose.setFileFilter(filter);
	      Choose.setMultiSelectionEnabled(false);
	  enableSaving(false);
	  ima.requestFocus();
	  }

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
    }
	 


	//ImageResize class allows to resize the image by resizing width and height of image
	 public class ImgResized extends JFrame implements ActionListener {
	  JPanel panel;
	  JTextField textWidth;//textfield where user going to enter width for image resize
	  JTextField textHeight;//textfield where user going to enter height for image resize
	  JButton Okbt;//ok button for confirmation that user wants to convert into desired height and width of an image
	  ImgResized(){
	  setTitle("Image resize");
	  //setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setPreferredSize(new Dimension(400,100));
	  
	  Okbt=new JButton("OK");
	  Okbt.setBackground(Color.BLACK);
	  Okbt.setForeground(Color.BLUE);  
	  Okbt.addActionListener(this);

	  textWidth=new JTextField(4);
	  textWidth.addKeyListener(new KeyList());
	  textHeight=new JTextField(4);
	  textHeight.addKeyListener(new KeyList());
	  panel=new JPanel();
	  panel.setLayout(new FlowLayout());
	  panel.add(new JLabel("Width:"));
	  panel.add(textWidth);
	  panel.add(new JLabel("Height:"));
	  
	  panel.add(textHeight);
	  panel.add(Okbt);
	  panel.setBackground(Color.GRAY);
	  add(panel, BorderLayout.CENTER);
	  setVisible(true);
	  pack();
	  enableComponents(false);
	  }
	  
	  public void enableComponents(boolean enabled){
	   textWidth.setEnabled(enabled);
	   textHeight.setEnabled(enabled);
	   Okbt.setEnabled(enabled);
	  }
		 //if user select Open button for selecting the image
	 public void actionPerformed(ActionEvent e){

	  JMenuItem source = (JMenuItem)(e.getSource());
	  if(source.getText().compareTo("Open...")==0)
	    {
	    setImage();
	    ima.repaint();
	     validate();
	    }
	 
	  else if(source.getText().compareTo("Image resize")==0)
	    {
	     
	    ImgResized ir=new ImgResized();
	    if(ImgArea.imgLoad)
	     ir.enableComponents(true);  
	     }
	  else if(source.getText().compareTo("Negative")==0)
	    {
	     
	   
	    if(ImgArea.imgLoad){
	     ima.convertNegative();
			  enableSaving(true); } 
	     }
	  
	    
	     
	  } 
	      
	  //event handling for OK button of Image Resize class
	  public void actionPerformed(ActionEvent e){
	   if(e.getSource()==Okbt){//if user clicks the OK button then following functions are called
	    ima.setActionResized(true);     
	    ima.ImgResize(Integer.parseInt(textWidth.getText()),Integer.parseInt(textHeight.getText()));//converting the input of user into integer
	    enableSaving(true);
	    ima.repaint();
	    }
	  }
	  //handling keyboard events
	  public class KeyList extends KeyAdapter{
	     public void keyTyped(KeyEvent ke){
	 
	    char c = ke.getKeyChar(); 
	    int intkey=(int)c;
	    if(!(intkey>=48 && intkey<=57 || intkey==8 || intkey==127))
	     {
	     ke.consume();
	  
	      }  
	     
	   }
	  
	  } 
	 }
	

	 public void setImage(){
	  
	  int retValue = Choose.showOpenDialog(this);
	      if(retValue == JFileChooser.APPROVE_OPTION) {   
	   filename=Choose.getSelectedFile().toString();
	   ima.prepareImg(filename);
	   }
	           
	  }



	 
	 public void enableSaving(boolean f){
	  saveas.setEnabled(f);
	  save.setEnabled(f); 
	  
	  }

	} 

