 
	 //The Addtext class represents the class that allows you to add your text to the image 
	 //In this class you can add text and add color ,font size to the text
	 
	 public class AddText extends JFrame implements ActionListener {
	  JPanel panel;
	  JTextArea txtText;
	  JComboBox cbFontNames;
	  JComboBox cbFontSizes;
	  JButton btOK;
	  JButton btSetColor;
	  String seFontName;
	  Color colorText;
	  int seFontSize;
	  TextAdd(){
	  colorText=null;
	  setTitle("Add text to the image");
	  //setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setPreferredSize(new Dimension(400,150));
	  
	  btOK=new JButton("OK");
	  btOK.setBackground(Color.BLACK);
	  btOK.setForeground(Color.BLUE);  
	  btOK.addActionListener(this);

	  btSetColor=new JButton("Set text color");
	  btSetColor.setBackground(Color.BLACK);
	  btSetColor.setForeground(Color.WHITE);  
	  btSetColor.addActionListener(this);

	  txtText=new JTextArea(1,30);
	  cbFontNames=new JComboBox();
	  cbFontSizes=new JComboBox();
	  panel=new JPanel();
	  panel.setLayout(new GridLayout(4,1));
	  panel.add(new JLabel("Text:"));
	  panel.add(txtText);
	  panel.add(new JLabel("Font Name:"));  
	  panel.add(cbFontNames);
	  panel.add(new JLabel("Font Size:"));  
	  panel.add(cbFontSizes);
	  panel.add(btSetColor);
	  panel.add(btOK);
	  panel.setBackground(Color.GRAY);
	  add(panel, BorderLayout.CENTER);
	  setVisible(true);
	  pack();
	  listFonts();
	  }

	  
	  public void actionPerformed(ActionEvent e){
	   if(e.getSource()==btOK){ //the button OK is clicked so the text is ready to place on the image
	    ima.setActionDraw(true); 
	    String textDraw=txtText.getText(); //retrieving the text entered by the user
	    String fontName=cbFontNames.getSelectedItem().toString();
	    int fontSize=Integer.parseInt(cbFontSizes.getSelectedItem().toString());
	    ima.setText(textDraw,fontName,fontSize,colorText);//calling the method setText
	    dispose();
	    }
	   else if(e.getSource()==btSetColor){ //show color chooser dialog for color selection
	    JColorChooser jser=new JColorChooser();
	    colorText=jser.showDialog(this,"Color Chooser",Color.BLACK);
	     
	   }
	  }
	  
	  //The listFonts method get all available fonts from the system 
	  public void listFonts(){
	   //get the available font names and add them to the font names combobox
	   GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment(); 
	   String[] fonts=ge.getAvailableFontFamilyNames();
	   for(String f:fonts)
	    cbFontNames.addItem(f);
	   //Initialize font sizes
	   for(int i=8;i<50;i++)
	    cbFontSizes.addItem(i);
	   
	  }
//The addTextToImage method adds the text specified by the user to the image
	  public void addTextToImage(int x,int y, BufferedImage img){
	   
	   BufferedImage bi=(BufferedImage)createImage(img.getWidth(),img.getHeight());//it will create a blank buffered image
	   
	   Graphics2D  g2d=(Graphics2D)bi.createGraphics();//it will creates the Graphics2D object from the buffered image   
	   
	   g2d.setFont(new Font(fontName,Font.BOLD,fontSize));//Set the fontsize of drawing pen
	   
	   g2d.setPaint(colorTextDraw);//Set text coloe
	   
	   g2d.drawImage(img,0,0,null);//it will draw the image on the blank buffered image
	   
	   g2d.drawString(textToDraw,x,y);//it will draw the text on the buffered image
	   
	   bufimg=bi;//updating the image
	   
	   drawn=true;//there is a text drawing on the image
	   
	   g2d.dispose();//it will clean the Graphics2D object    
	   
	   repaint(); //repainting the updated image
	   }

//The setText method will set the text specified by the user onto image
  public void setText(String text,String fName, int fSize, Color color){
		   textToDraw=text;
		   fontName=fName;
		   fontSize=fSize;
		   if(color==null)
		    colorTextDraw=new Color(0,0,0);
		   else
		    colorTextDraw=color;
		  }

//set the selected color to the c variable
  public void setColor(Color color){
   c=color;   
  }

//If user add the text on image, update the value of actionDraw
 public void setActionDraw(boolean value ){ 
	   actionDraw=value;
	   
	  }
           public static void main(String[] args)
            {AddText at=new AddText();
           }
	 } 
