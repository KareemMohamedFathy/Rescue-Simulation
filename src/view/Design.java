package view;
import sun.audio.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
public class Design extends JFrame implements ActionListener
{
private Start st;
    public JButton[][] getGrid() {
		return grid;
	}
    
	public void displayUnit() {
	   Unitgrid1=new JButton[3][2]; 

Unitgrid1[0][0]=new JButton();
	    Unitgrid1[0][1]=new JButton();
	    Unitgrid1[1][0]=new JButton();
	    Unitgrid1[1][1]=new JButton();
	    Unitgrid1[2][0]=new JButton();
	    Unitgrid1[2][1]=new JButton();
	    Unitgrid1[0][0].setBackground(Color.BLACK);
	Unitgrid1[0][1].setBackground(Color.BLACK);
	Unitgrid1[1][0].setBackground(Color.BLACK);
	Unitgrid1[1][1].setBackground(Color.BLACK);
	Unitgrid1[2][0].setBackground(Color.BLACK);
	Unitgrid1[0][0].setBorder(null);	
	Unitgrid1[0][1].setBorder(null);	
	Unitgrid1[1][0].setBorder(null);	
	Unitgrid1[1][1].setBorder(null);	
	
	}
	public void displayUnit2() {
		   Unitgrid2=new JButton[3][2]; 
		   Unitgrid2[0][0]=new JButton();
		   	    Unitgrid2[0][1]=new JButton();
		   	    Unitgrid2[1][0]=new JButton();
		   	    Unitgrid2[1][1]=new JButton();
		   	    Unitgrid2[2][0]=new JButton();
		   	    Unitgrid2[2][1]=new JButton();   
		   	 Unitgrid2[0][0].setBackground(Color.BLACK);
		 	Unitgrid2[0][1].setBackground(Color.BLACK);
		 	Unitgrid2[1][0].setBackground(Color.BLACK);
		 	Unitgrid2[1][1].setBackground(Color.BLACK);
		 	Unitgrid2[2][0].setBackground(Color.BLACK);
		 	Unitgrid2[0][0].setBorder(null);	
			Unitgrid2[0][1].setBorder(null);	
			Unitgrid2[1][0].setBorder(null);	
			Unitgrid2[1][1].setBorder(null);	
				 	
	}

	
    private JButton[][] grid;
    private JButton[][] Unitgrid;
    private JButton[][] Unitgrid1;
    private JButton[][] Unitgrid2;
	
    private JTextArea a;
	private JPanel RescuePanel=new JPanel();
	private JPanel GeneralPanel=new JPanel();
	private JPanel UnitPanel=new JPanel();
	private JPanel Layout1=new JPanel();

	public void addtext(String s) {
	
	a.setText(s);
	
}
public void addtext3(String s) {
	
	a3.append(s);;
	
}
    public Design() 

    {
    		
    super();
    setVisible(false);
setTitle("Rescue Simulation");    
st=new Start();
st.getB().addActionListener(this);
st.getC().addActionListener(this);
setExtendedState(JFrame.MAXIMIZED_BOTH);
st.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
a=new JTextArea();
    RescuePanel.setLayout(new GridLayout(10, 10,4,4));
    RescuePanel.setPreferredSize(new Dimension(700, 700));
    RescuePanel.setName("Rescue Simulation");
  RescuePanel.setBackground(new Color(0,0,0));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
   GridBagConstraints c = new GridBagConstraints();
   grid=new JButton[10][10]; //allocate the size of grid
   
   for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
                    grid[x][y]=new JButton(""); //creates new button     
                    grid[x][y].setPreferredSize(new Dimension(40, 40));
                    RescuePanel.add(grid[x][y]); //adds button to grid
                    grid[x][y].setLayout(new OverlayLayout(grid[x][y]));
                    grid[x][y].setBackground(Color.WHITE);     
          
            
            }
    }
   
	ImageIcon ii = new ImageIcon("images//images8.JPG");
   	int scale = 2; // 2 times smaller
   	int width = ii.getIconWidth();
   	int newWidth = width / scale;
   	grid[0][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
   	            java.awt.Image.SCALE_SMOOTH)));
    c.fill=GridBagConstraints.HORIZONTAL;
    UnitPanel.setLayout(new GridBagLayout());
    GridBagConstraints c1= new GridBagConstraints();
UnitPanel.setPreferredSize(new Dimension(300, 300));
    Unitgrid=new JButton[3][2];


c1.weightx=0;
c1.weighty=0;
Unitgrid[0][0]=new JButton("Amb");
    Unitgrid[0][0].setPreferredSize(new Dimension(50, 50));
   Unitgrid[0][0].setForeground(Color.WHITE);
   Unitgrid[0][1]=new JButton("Disease");
   Unitgrid[0][1].setForeground(Color.WHITE);
   Unitgrid[1][0]=new JButton("Fir");
   Unitgrid[1][0].setForeground(Color.WHITE);
   Unitgrid[1][1]=new JButton("Evac");
   Unitgrid[1][1].setForeground(Color.WHITE);
   Unitgrid[2][0]=new JButton("Gas");
   Unitgrid[2][0].setForeground(Color.WHITE);
   Unitgrid[2][1]=new JButton("");
   Unitgrid[0][0].setBorder(null);	
	Unitgrid[0][1].setBorder(null);	
	Unitgrid[1][0].setBorder(null);	
	Unitgrid[1][1].setBorder(null);	
	Unitgrid[2][0].setBorder(null);	
	
   ii = new ImageIcon("images//images3.PNG");
   	 scale = 4; // 2 times smaller
   	 width = ii.getIconWidth();
   	newWidth = width / scale;
   	Unitgrid[0][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
   	            java.awt.Image.SCALE_SMOOTH)));
   	
   	Unitgrid[0][0].setBackground(new Color(0,0,0));
	 UnitPanel.setBackground(Color.black);
  	 ii = new ImageIcon("images//images4.PNG");
  	 scale = 4; // 2 times smaller
  	 width = ii.getIconWidth();
  	 newWidth = width / scale;
  	Unitgrid[0][1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
  	            java.awt.Image.SCALE_SMOOTH)));
  	 ii = new ImageIcon("images//images5.PNG");
  	 scale = 4; // 2 times smaller
  	 width = ii.getIconWidth();
  	 newWidth = width / scale;
  	Unitgrid[0][1].setBackground(new Color(0,0,0));
	 
  	 Unitgrid[1][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
  	            java.awt.Image.SCALE_SMOOTH)));
  	Unitgrid[1][0].setBackground(new Color(0,0,0));
	 
  	ii = new ImageIcon("images//images6.PNG");
 	 width = ii.getIconWidth();
 	 
 	 newWidth = width / scale;
 	 
 	Unitgrid[1][1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
 	            java.awt.Image.SCALE_SMOOTH)));
	 Unitgrid[1][1].setBackground(new Color(0,0,0));
 	 
 	ii = new ImageIcon("images//images7.PNG");
 	scale = 2; // 2 times smaller
	  
 	width = ii.getIconWidth();
 	 newWidth = width / scale;
 	Unitgrid[2][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
 	            java.awt.Image.SCALE_SMOOTH)));
 	 Unitgrid[2][0].setBackground(new Color(0, 0, 0));
 	 
 	 nextCycle=new JButton();
     nextCycle.setText("Next Cycle");
     nextCycle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
     nextCycle.setBackground(Color.blue);
     nextCycle.setPreferredSize(new  Dimension(100, 60));
     nextCycle.setForeground(Color.WHITE);
 	c1.gridx=0;
 	c1.gridy=0;
 	c1.gridwidth=2;
 	UnitPanel.add(nextCycle,c1);
JTextArea j=new JTextArea(370,200);
j.setPreferredSize(new Dimension(300,200));
j.setText("Avaliable Units");
c1.gridx=0;
c1.gridy=1;
c1.gridwidth=2;

c1.weightx=0;
c1.weighty=0;


j.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
j.setBackground(new Color(240,248,255));
j.setOpaque(true);
j.setEditable(false);
UnitPanel.add(j,c1);
c1.gridwidth=1;
c1.gridy++;
UnitPanel.add(Unitgrid[0][0],c1);
c1.gridx++;
UnitPanel.add(Unitgrid[0][1],c1);
c1.gridy=3;
c1.gridx=0;
UnitPanel.add(Unitgrid[1][0],c1);
c1.gridy=3;
c1.gridx=1;

UnitPanel.add(Unitgrid[1][1],c1);
c1.gridy=4;
c1.gridx=0;
UnitPanel.add(Unitgrid[2][0],c1);
add(UnitPanel, BorderLayout.EAST);
JTextArea j1=new JTextArea(370,200);
j1.setPreferredSize(new Dimension(300,200));
j1.setText("Responding Units");
j1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
j1.setBackground(new Color(240,248,255));
j1.setOpaque(true);
j1.setEditable(false);

c1.gridx=0;
c1.gridy=5;
c1.gridwidth=2;
UnitPanel.add(j1,c1);
c1.gridwidth=1;
displayUnit();
c1.gridwidth=1;
c1.gridy=6;
UnitPanel.add(Unitgrid1[0][0],c1);
c1.gridy=6;
c1.gridx++;
UnitPanel.add(Unitgrid1[0][1],c1);
c1.gridy=7;
c1.gridx=0;
UnitPanel.add(Unitgrid1[1][0],c1);
c1.gridy=7;
c1.gridx=1;

UnitPanel.add(Unitgrid1[1][1],c1);
c1.gridy=8;
c1.gridx=0;
UnitPanel.add(Unitgrid1[2][0],c1);


c1.weightx=0;
c1.weighty=0;

c1.gridx=0;

c1.gridy=9;
c1.gridwidth=2;
JTextArea j2=new JTextArea(370,200);
j2.setPreferredSize(new Dimension(300,200));
j2.setText("Treating Units");
j2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
j2.setBackground(new Color(240,248,255));
j2.setOpaque(true);
j2.setEditable(false);
UnitPanel.add(j2,c1);
c1.gridwidth=1;
displayUnit2();
c1.gridy=10;
UnitPanel.add(Unitgrid2[0][0],c1);
c1.gridy=10;
c1.gridx++;
UnitPanel.add(Unitgrid2[0][1],c1);
c1.gridy=11;
c1.gridx=0;
UnitPanel.add(Unitgrid2[1][0],c1);
c1.gridy=11;
c1.gridx=1;

UnitPanel.add(Unitgrid2[1][1],c1);
c1.gridy=12;
c1.gridx=0;
UnitPanel.add(Unitgrid2[2][0],c1);


UnitPanel.setBackground(Color.black);



GeneralPanel.setLayout(new GridBagLayout());
    c.gridx=0;
    c.gridy=0;
    
    JLabel l=new JLabel();
    l.setText("Rescue Panel");
    l.setHorizontalAlignment(JLabel.CENTER);
    l.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
    l.setForeground(Color.RED);
    l.setPreferredSize(new Dimension(600,30));
    l.setBackground(Color.BLACK);
    l.setOpaque(true);
    JLabel l1=new JLabel();
    l1.setHorizontalAlignment(JLabel.CENTER);
    l1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
    l1.setForeground(Color.RED);
    l1.setPreferredSize(new Dimension(600,30));
    l1.setBackground(Color.BLACK);
    l1.setOpaque(true);
    
    JLabel l2=new JLabel();
    l2.setText("Info Panel");
    l2.setHorizontalAlignment(JLabel.LEFT);
    l2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
    l2.setForeground(Color.RED);
    l2.setPreferredSize(new Dimension(350,30));
    l2.setBackground(Color.BLACK);
    l2.setOpaque(true);
    
    Layout1.setLayout(new GridLayout(1,3,0,0));
    c.gridx=0;
    c.gridy=0;
    RescuePanel.add(l,BorderLayout.WEST);
    
    add(RescuePanel,BorderLayout.CENTER);
    getContentPane().setBackground(Color.BLACK);
    add(Layout1,BorderLayout.NORTH);
    JPanel temp=new JPanel();
   GeneralPanel.setPreferredSize(new Dimension(350,700)); 
    WindowDestroyer myListener = new WindowDestroyer();
    addWindowListener(myListener);
GeneralPanel.setBackground(new Color(0,0,0)
		);
    a2=new JTextArea(25,30);
    scroll = new JScrollPane(a2);
	  scroll.setPreferredSize(new Dimension(250, 110));
    a2.setForeground(Color.WHITE);
	 a2.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
	 
	 
     	 
	 
	 GeneralPanel.add(scroll, c);
		 a2.setEditable(false);
	
	    a.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

	    a.setSize(new Dimension(250, 60));
		c.gridx=0;
		c.gridy=1;
		a.setBackground(Color.black);
a.setForeground(new Color(30,144,255));
	    a.setEditable(false);
	    a.setSize(new Dimension(250, 20));
		
	    GeneralPanel.add(a,c);
			    
    a3=new JTextArea();
    
    a3.setFont(new Font("Serif", Font.BOLD, 20));
    a3.setBackground(Color.BLACK);
    a3.setForeground(new Color(204,0,0));
    
    scroll = new JScrollPane(a3);
	  scroll.setPreferredSize(new Dimension(250, 100));
	    a3.setEditable(false);

    c.gridy++;
    
    GeneralPanel.add(scroll, c);
	c.gridy++;;
	add(GeneralPanel,BorderLayout.WEST);
	a3.setEditable(false);
	a2.setForeground(Color.RED);
	a2.setBackground(new Color(240,248,255));
	Layout1.add(l2);   
    Layout1.add(l);
    Layout1.add(l1);
    c.gridy++;
     active=new JTextArea();
     active.setEditable(false);
    scroll.setPreferredSize(new Dimension(250,150));
    active.setForeground(new Color(35,206,150));
	active.setBackground(Color.BLACK);
	active.setVisible(true);
	scroll = new JScrollPane(active);
	active.setFont(new Font("serif",Font.BOLD,20));
	
	GeneralPanel.add(scroll,c);
 except=new JTextArea();
except.setPreferredSize(new Dimension(250,40));
c.gridy++;
except.setFont(new Font("serif",Font.BOLD,15));
except.setForeground(new Color(0,191,255));
except.setBackground(Color.BLACK);

GeneralPanel.add(except,c);
pack();
    }
    public JTextArea getExcept() {
		return except;
	}

	public void setExcept(JTextArea except) {
		this.except = except;
	}


	private JTextArea except;
    public JTextArea getActive() {
		return active;
	}

	public void setActive(JTextArea active) {
		this.active = active;
	}


	private JTextArea active;
    
public JButton getNextCycle() {
		return nextCycle;
	}



private    JButton nextCycle;
private JScrollPane scroll;
   public JButton[][] getUnitgrid() {
		return Unitgrid;
	}


private JTextArea a3;
    
public void addComponentsB(int x,int y) {
	ImageIcon ii = new ImageIcon("images//images1.JPG");
	int scale = 2; // 2 times smaller
	int width = ii.getIconWidth();
	int newWidth = width / scale;
	grid[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	            java.awt.Image.SCALE_SMOOTH)));
	     
 
}
public void addComponentsC(int x,int y) {
	ImageIcon ii = new ImageIcon("images//images2.PNG");
	int scale = 5; // 2 times smaller
	int width = ii.getIconWidth();
	int newWidth = width / scale;
	grid[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	            java.awt.Image.SCALE_SMOOTH)));
	   
}
private JTextArea a2;
public void BuildingInfo(String s) {
a2.setText(s);
}

public void displayend(int x) {
	JDialog d = new JDialog(this, "End Game Now"); 
    d.setSize(300,300); 
	d.setFont(new Font("Serif", Font.BOLD, 30));
    // create a label 
	d.setLocationRelativeTo(null);
    JLabel l = new JLabel("Game is over  "+"\n" +"Your current score is :" +x); 
    l.setSize(new Dimension(500, 500));
    d.add(l); 
   l.setFont(new Font("Serif", Font.BOLD, 15));
    // setsize of dialog 

    // set visibility of dialog 
    d.setVisible(true); 

return;

}
public void handleRespond(Unit u) {
ImageIcon ii=null;
        int scale=0;
        int width=0;
     int newWidth=0;
	    if(u instanceof Ambulance) {
	    	ii = new ImageIcon("images//images3.PNG");
	    	 scale = 5; // 2 times smaller
	    	  width = ii.getIconWidth();
	    	newWidth = width / scale;
	    	Unitgrid1[0][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	    	            java.awt.Image.SCALE_SMOOTH)));
	    	
	    	Unitgrid1[0][0].setBackground(new Color(0,0,0));
	    }
	    if(u instanceof DiseaseControlUnit) {
	    	ii = new ImageIcon("images//images4.PNG");
	   	 scale = 5; // 2 times smaller
	   	 width = ii.getIconWidth();
	   	 newWidth = width / scale;
	   	Unitgrid1[0][1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	   	            java.awt.Image.SCALE_SMOOTH)));
	   	Unitgrid1[0][1].setBackground(new Color(0,0,0));
	 	 
	    }
	    if(u instanceof FireTruck) {
	   	 ii = new ImageIcon("images//images5.PNG");
	   	 scale = 5; // 2 times smaller
	   	 width = ii.getIconWidth();
	   	 newWidth = width / scale;
	   	
	   	 Unitgrid1[1][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	   	            java.awt.Image.SCALE_SMOOTH)));
	   	Unitgrid1[1][0].setBackground(new Color(0,0,0));
	    }
	    if(u instanceof Evacuator) {
	   	ii = new ImageIcon("images//images6.PNG");
	  	 scale = 5; // 2 times smaller
	  	 width = ii.getIconWidth();
	  	 newWidth = width / scale;
	  	 
	  	Unitgrid1[1][1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	  	            java.awt.Image.SCALE_SMOOTH)));
	 	 Unitgrid1[1][1].setBackground(new Color(0,0,0));
	    }
	    if(u instanceof GasControlUnit) {
	  	ii = new ImageIcon("images//images7.PNG");
	  	 scale = 3; // 2 times smaller
	  	 width = ii.getIconWidth();
	  	 newWidth = width / scale;
	  	Unitgrid1[2][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	  	            java.awt.Image.SCALE_SMOOTH)));
	  	
	  	
	  	Unitgrid1[2][0].setBackground(new Color(0, 0, 0));
	    }
	    
}
public JButton[][] getUnitgrid1() {
	return Unitgrid1;
}
public void setUnitgrid1(JButton[][] unitgrid1) {
	Unitgrid1 = unitgrid1;
}
public JButton[][] getUnitgrid2() {
	return Unitgrid2;
}
public void setUnitgrid2(JButton[][] unitgrid2) {
	Unitgrid2 = unitgrid2;
}
public void handletreat(Unit u) {
ImageIcon ii=null;
        int scale=0;
        int width=0;
     int newWidth=0;
     int x=u.getLocation().getX();
     int y=u.getLocation().getY();
     
   if(u instanceof Ambulance) {
	    	  Unitgrid1[0][0].setIcon(null);
	    	  Unitgrid2[0][0].setPreferredSize(new Dimension(40, 40));
	    	  
	    	ii = new ImageIcon("images//images3.PNG");
	    	 scale = 5; // 2 times smaller
	    	  width = ii.getIconWidth();
	    	newWidth = width / scale;
	    	Unitgrid2[0][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	    	            java.awt.Image.SCALE_SMOOTH)));
	    	
	    	Unitgrid2[0][0].setBackground(new Color(0,0,0));
	    	grid[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

	    }
	    if(u instanceof DiseaseControlUnit) {
	    	Unitgrid1[0][1].setIcon(null);
	    	Unitgrid2[0][1].setPreferredSize(new Dimension(40, 40));
	    	  
	    	ii = new ImageIcon("images//images4.PNG");
	   	 scale = 5; // 2 times smaller
	   	 width = ii.getIconWidth();
	   	 newWidth = width / scale;
	   	Unitgrid2[0][1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	   	            java.awt.Image.SCALE_SMOOTH)));
	   	Unitgrid2[0][1].setBackground(new Color(0,0,0));
    	grid[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

	 	 
	    }
	    if(u instanceof FireTruck) {
	    	Unitgrid1[1][0].setIcon(null);
	    	Unitgrid2[1][0].setPreferredSize(new Dimension(40, 40));
	    	  
	    	ii = new ImageIcon("images//images5.PNG");
	   	 scale = 5; // 2 times smaller
	   	 width = ii.getIconWidth();
	   	 newWidth = width / scale;
	   	
	   	 Unitgrid2[1][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	   	            java.awt.Image.SCALE_SMOOTH)));
	   	Unitgrid2[1][0].setBackground(new Color(0,0,0));
    	grid[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

	    }
	    if(u instanceof Evacuator) {
	    	 Unitgrid1[1][1].setIcon(null);
	    	 Unitgrid2[1][1].setPreferredSize(new Dimension(40, 40));
	    	  
	   	ii = new ImageIcon("images//images6.PNG");
	  	 scale = 5; // 2 times smaller
	  	 width = ii.getIconWidth();
	  	 newWidth = width / scale;
	  	 
	  	Unitgrid2[1][1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	  	            java.awt.Image.SCALE_SMOOTH)));
	 	 Unitgrid2[1][1].setBackground(new Color(0,0,0));
	 	 if(u.getLocation().getX()!=0 &&u.getLocation().getY()!=0
	 			 )
	    	grid[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

	    }
	    if(u instanceof GasControlUnit) {
	    	Unitgrid1[2][0].setIcon(null);
	    	Unitgrid2[2][0].setPreferredSize(new Dimension(40, 40));
	    	  
	  	ii = new ImageIcon("images//images7.PNG");
	  	 scale = 3; // 2 times smaller
	  	 width = ii.getIconWidth();
	  	 newWidth = width / scale;
	  	Unitgrid2[2][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	  	            java.awt.Image.SCALE_SMOOTH)));
	  	 Unitgrid2[2][0].setBackground(new Color(0, 0, 0));
	    	grid[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

	    }
}


@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==st.getB()) {
		st.dispose();
		this.setVisible(true);
	}
	if(e.getSource()==st.getC()) {
		
		st.dispose();
	}
}





}
