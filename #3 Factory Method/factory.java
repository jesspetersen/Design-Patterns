import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.*;

interface Militia {
   String attack();
}

class Hoplite implements Militia {

   @Override
   public String attack() {
      return "Hoplite attacks!!";
   }
}

class Toxotes implements Militia {

   @Override
   public String attack() {
      return "Toxotes attacks!!";
   }
}

class Hypaspist implements Militia {

   @Override
   public String attack() {
      return "Hypaspist attacks!!";
   }
}

class MilitiaFactory {
	
   //use getMilitia method to get object of type Militia
   public Militia getMilitia(){
   
   //Use a random number generator to select type of militia created
	int RNG = ThreadLocalRandom.current().nextInt(1, 4);	
      if(RNG == 1){
         return new Hoplite();
         
      } else if(RNG == 2){
         return new Toxotes();
         
      } else if(RNG == 3){
         return new Hypaspist();
      }
      
      return null;
   }
}

class FactoryPatternProgram extends JFrame {
	
	private static JTextArea textArea;
	private JScrollPane scrollBar;
	private JPanel panel;
	private JLabel intro;
	private JButton startBtn;

   public static void main(String[] args) {
	   
	   new FactoryPatternProgram();
	   fightBattle();
   }
   
   public FactoryPatternProgram(){
	   this.setSize(370,300);
		this.setTitle("Militia Simulator");
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
		intro = new JLabel("Welcome to militia simulator! Press the button to fight!");
		startBtn = new JButton("Fight!");
		startBtn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                fightBattle();
            }
        });
		panel.add(intro);
		panel.add(startBtn);
		this.add(panel);
		
		//Build battle display
		textArea = new JTextArea(12, 30);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollBar);
		
		//Make sure frame closes when I click x button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
   }
   
   public static void fightBattle(){
	   MilitiaFactory militiaFactory = new MilitiaFactory();
	   textArea.append("\n----------------------------\nEnemy is approaching - marshall the army!!\n----------------------------");
	   
	    //get a militia object and call its attack() method
      Militia testMilitia = militiaFactory.getMilitia();
      textArea.append("\n"+testMilitia.attack());
	  
	  //Use a random number generator to select amount of militia created
	int RNG = ThreadLocalRandom.current().nextInt(3, 6);
	for (int i = 0; i < RNG; i++){
		testMilitia = militiaFactory.getMilitia();
		textArea.append("\n"+testMilitia.attack());
	}
	int RNGWin = ThreadLocalRandom.current().nextInt(1, 4);
	if (RNGWin >= 2)
		textArea.append("\n----------------------------\nEnemy was defeated!!\n----------------------------");
	else
		textArea.append("\n----------------------------\nYou were defeated!!\n----------------------------");
   }
}