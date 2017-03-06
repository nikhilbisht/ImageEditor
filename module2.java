public class SaveUpdatedImage{

//Save the image file
 public void saveToFile(String filename){
  String ftype=filename.substring(filename.lastIndexOf('.')+1);
  try{
   //if user have applied the compression then save the compressed image
   //we separate the compression action from other actions on the image
   if(actionCompressed)
    makeCompression(new File(filename));
  
   else if(actionSlided || actionResized || actionTransparent || actionRotated || drawn) //when user applied various editing function then save the update image
    ImageIO.write(bimg,ftype,new File(filename));
     }catch(IOException e){System.out.println("Error in saving the file");//if there is error in saving the image
}
  }

  //set a value to e variable 
  //this method is invoked when the user makes change to the  image slider
  public void setValue(float value){ 
   e=value;
  }
  
  //Set a boolean value the actionSlided variable 
  public void setActionSlided(boolean value ){ 
   actionSlided=value;
  }
  //Set a boolean value the actionResized variable   
  public void setActionResized(boolean value ){ 
   actionResized=value;
  } 
  //Set a boolean value the actionCompressed variable   
  public void setActionCompressed(boolean value ){ 
   actionCompressed=value;
  }
  //Set a boolean value the actionDraw variable   
  public void setActionDraw(boolean value ){ 
   actionDraw=value;
   
  }

//handling events of sub-menu items on the main program interface
 public void actionPerformed(ActionEvent e){

  JMenuItem source = (JMenuItem)(e.getSource());
  if(source.getText().compareTo("Open...")==0)//when user opens an image
    {
    setImage();
    ia.repaint();
    validate();
      
     }
  else if(source.getText().compareTo("Save as...")==0)//when user clicks saveas button
    {
    showSaveFileDialog(); 
      
     }
  else if(source.getText().compareTo("Save")==0)//when user clicks save button
    {
     
    ia.saveToFile(filename);  
     }
  else if(source.getText().compareTo("Add text on image")==0)//when user selects add text button
    {
    new TextAdd(); 
    }

  else if(source.getText().compareTo("Image brightness")==0)//when user clicks image brightness button to change the brightness of the original image
    {
     
    ImageBrightness ib=new ImageBrightness(); 
    if(ImgArea.imageLoaded)
     ib.enableSlider(true); 
     }
  else if(source.getText().compareTo("Image compression")==0)//when user clicks compression button to change the compression of image
    {
    if(ImgArea.imageLoaded){
     ia.setActionCompressed(true);
     enableSaving(true);
     } 
     }
  
  else if(source.getText().compareTo("Image resize")==0)//when user wants to change the dimension of image
    {
     
    ImageResize ir=new ImageResize();
    if(ImgArea.imageLoaded)
     ir.enableComponents(true);  
     }
  else if(source.getText().compareTo("Image rotation")==0)// when user wants to rotate the image
    {
     
    if(ImgArea.imageLoaded){
     ia.rotateImage();
     enableSaving(true);
     } 
    }
  
  else if(source.getText().compareTo("Image transparency")==0){
   if(ImgArea.c==null){
    JOptionPane dialog=new JOptionPane();
    dialog.showMessageDialog(this,"Click the background area of the image first","Error",JOptionPane.ERROR_MESSAGE);
   }
   else if(ImgArea.imageLoaded){
    ia.makeTransparency(ImgArea.c);
    enableSaving(true);
    }
  } 
  
  else if(source.getText().compareTo("Cancel editing")==0) {
    ia.setImgFileName(filename);
    ia.reset();
    }
  
  else if(source.getText().compareTo("Exit")==0) 
    System.exit(0);
     
    
  } 
      
      public void static main(String[] args)
      {SaveUpdatedImage save=new SaveUpdatedImage();
      }
      
      }
