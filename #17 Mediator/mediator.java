import java.util.Date;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.*;

class CheckInOut {
   public static void tagOn(Cat c, JTextArea t){
	   c.checkedIn = true;
      t.append("\n"+new Date().toString() + " [" + c.getName() + "] : " + "has been checked in.");
   }
   
   public static void tagOff(Cat c, JTextArea t){
	   c.checkedIn = false;
      t.append("\n"+new Date().toString() + " [" + c.getName() + "] : " + "has been checked out.");
   }
}

class Cat {
   private String name;
   public boolean checkedIn;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Cat(String name){
      this.name  = name;
	  checkedIn = false;
   }
   
   public String toString(){
	   return name;
   }

   public void checkIn(JTextArea t){
      CheckInOut.tagOn(this, t);
   }
   
   public void checkOut(JTextArea t){
	  CheckInOut.tagOff(this, t);
   }
}

class MediatorPatternProgram extends JFrame {
	
	private JPanel panel;
	private static JTextArea textArea;
	private JScrollPane scrollBar;
	private JLabel intro;
	private JComboBox catBox;
	private JButton inButton;
	private JButton outButton;
	
   public static void main(String[] args) {

      new MediatorPatternProgram();
   }
   
   public MediatorPatternProgram(){
	   
	   //Make cats
	   Cat snuggles = new Cat("Snuggles");
	   Cat smokey = new Cat("Smokey");
	  
	  //Set up window
	   this.setSize(390,300);
		this.setTitle("Cattery World");
		//Get things to help me build the GUI well
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		//Find centre of screen
		int xPosition = (dim.width/2)-(this.getWidth()/2);
		int yPosition = (dim.height/2)-(this.getHeight()/2);
		//Make sure it spawns in the centre of the screen
		this.setLocation(xPosition, yPosition);
		this.setResizable(false);
		
		panel = new JPanel();
		intro = new JLabel("Welcome to Cattery World! Select a cat and check in to get started!");
		panel.add(intro);
		
		catBox = new JComboBox();
		catBox.addItem(snuggles);
		catBox.addItem(smokey);
		inButton = new JButton("Check in!");
		inButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                Cat c = (Cat)catBox.getSelectedItem();
				if (c.checkedIn == false)
					c.checkIn(textArea);
				else
					textArea.append("\n"+c.getName()+" is already checked in!");
            }
        });
		outButton = new JButton("Check out!");
		outButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                Cat c = (Cat)catBox.getSelectedItem();
				if (c.checkedIn == true)
					c.checkOut(textArea);
				else
					textArea.append("\n"+c.getName()+" is already checked out!");
            }
        });
		panel.add(catBox);
		panel.add(inButton);
		panel.add(outButton);
		
		//Build battle display
		textArea = new JTextArea(12, 30);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollBar);
		
		this.add(panel);
		
		//Make sure frame closes when I click x button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
   }
}