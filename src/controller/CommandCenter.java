package controller;
import sun.audio.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.print.attribute.standard.MediaSize.Engineering;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CannotTreatException;
import exceptions.CitizenAlreadyDeadException;
import exceptions.IncompatibleTargetException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.MedicalUnit;
import model.units.Unit;
import model.units.UnitState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulator;
import view.Design;

public class CommandCenter implements SOSListener ,ActionListener{
	JButton[][] grid3;
	JButton[][] grid4 ;
	
	private Simulator engine;
	private  ArrayList<ResidentialBuilding> visibleBuildings;
	private  ArrayList<Citizen> visibleCitizens;

	@SuppressWarnings("unused")
	private ArrayList<Unit> emergencyUnits;
	JButton grid1[][];
	JButton grid2[][];
	HashSet<String> set=new HashSet<>();
    private Design d;
	public CommandCenter() throws Exception  {
		engine = new Simulator(this);
		
		d=new Design();
		d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		emergencyUnits = engine.getEmergencyUnits();
		 grid1=d.getGrid();
		for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
    	grid1[x][y].addActionListener(this);
            }
	}
		grid2=d.getUnitgrid();
		grid3=d.getUnitgrid1();
		grid4=d.getUnitgrid2();
		for(int x=0; x<3; x++){
			
            for(int y=0; y<2; y++){
    	grid2[x][y].addActionListener(this);
    	grid3[x][y].addActionListener(this);
    	grid4[x][y].addActionListener(this);
            }
	}
		d.getNextCycle().addActionListener(this);
		GuiCalculations();
	}
	@Override
	public void receiveSOSCall(Rescuable r) {
		
		if (r instanceof ResidentialBuilding) {
			
			if (!visibleBuildings.contains(r)) {
				visibleBuildings.add((ResidentialBuilding) r);
			}
		} else {
			visibleCitizens.add((Citizen) r );
			
		}

	}
	public void citizeninbuilding() {
	}
	public void GuiCalculations() {
		int n=engine.calculateCasualties();
		int x=engine.getCurrentCycle();
	    String s="Number of casaulties: "+n+"\n";
		 
		 s=s+("Current Cycle: "+x+"\n");
		d.addtext(s);		
	}
	public void GuiShow() {
		for(ResidentialBuilding b:visibleBuildings) {
			int x=b.getLocation().getX();
			int y=b.getLocation().getY();
			d.addComponentsB(x, y);
		}
     for(Citizen c:visibleCitizens) {
    		int x=c.getLocation().getX();
			int y=c.getLocation().getY();
			d.addComponentsC(x, y);
		 
     }
	}
	public void actionPerformedOnNextCycle(ActionEvent e) throws BuildingAlreadyCollapsedException, CannotTreatException, CitizenAlreadyDeadException {
		
		JButton btn = (JButton) e.getSource();
		// get its index within the ArrayList of buttons
		if(btn.equals(d.getNextCycle())) { 
				
				engine.nextCycle();
				ExecutedDisasters();
				activeDis();
				
				GuiCalculations();
				checkdead();
				how();
				
				viewDisaster();
				handletwo();
				}
		
	}
	public void handletwo() {
		ImageIcon ii=null;
        int scale=0;
        int width=0;
     int newWidth=0;
		for(Unit u :engine.getEmergencyUnits() ) {
			if(u.getState()==UnitState.TREATING) {
				int x=u.getLocation().getX();
				int y=u.getLocation().getY();
				if(u instanceof Ambulance) {
			    	  
			    	ii = new ImageIcon("images//images3.PNG");
			    	 scale = 5; // 2 times smaller
			    	  width = ii.getIconWidth();
			    	newWidth = width / scale;
			    	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

			    }
			    if(u instanceof DiseaseControlUnit) {
			    	  
			    	ii = new ImageIcon("images//images4.PNG");
			   	 scale = 5; // 2 times smaller
			   	 width = ii.getIconWidth();
			   	 newWidth = width / scale;
			   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

			 	 
			    }
			    if(u instanceof FireTruck) {
			    	  
			    	ii = new ImageIcon("images//images5.PNG");
			   	 scale = 5; // 2 times smaller
			   	 width = ii.getIconWidth();
			   	 newWidth = width / scale;
			   	
			   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

			    }
			    if(u instanceof Evacuator) {
			    	  
			   	ii = new ImageIcon("images//images6.PNG");
			  	 scale = 5; // 2 times smaller
			  	 width = ii.getIconWidth();
			  	 newWidth = width / scale;
			  	 
			  	 if(u.getLocation().getX()!=0 &&u.getLocation().getY()!=0)
			  		d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

			    }
			    if(u instanceof GasControlUnit) {
			    	  
			    	ii = new ImageIcon("images//images4.PNG");
				  	 
			    	scale = 3; // 2 times smaller
			  	 width = ii.getIconWidth();
			  	 newWidth = width / scale;
			    	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));

			    }

			}
		}
	}
	public void checkdead() {
		for(ResidentialBuilding b:visibleBuildings) {
			for(Citizen c:b.getOccupants()) {
	        	if(c.getState()==CitizenState.DECEASED&&!set1.contains(c)) {
	            	String ds=c.getName()+" "+c.getLocation()+" in buidling "+c.getState()+" ";
	            	d.addtext3(ds+"\n");
	            	set1.add(c);
	            }    
	        }	
		}
	}
	private HashSet<Citizen> set1=new HashSet<>();
	public String actionPerformedBuilding(ActionEvent e,int x1 ,int y1) {
		String m="";
		for(ResidentialBuilding b:visibleBuildings) {
		if(b.getLocation().getX()==x1&&b.getLocation().getY()==y1) {
			
			for(Unit u:engine.getEmergencyUnits() ) {
				
				m=m+"Unit id: "+u.getUnitID()+"\n";
	        	m=m+"Steps per cycle :"+u.getStepsPerCycle()+"\n";
	        	m=m+"Location :"+u.getLocation()+"\n";
	        	m=m+"state"+u.getState()+"\n";
	        	if(u.getTarget() instanceof Citizen) {
	        		m=m+"Target: "+"Citizen"+"\n";
	        	}
	        	else {
	        		m=m+"Target: "+"Building"+"\n";        	
	        	}
	        
	    		if(x1==u.getLocation().getX()&&y1==u.getLocation().getY()&& u instanceof  Ambulance) {
	    			m=m+"Ambulance" +"\n";
	    			break;
	    			  }
	    		else if(x1==u.getLocation().getX()&&y1==u.getLocation().getY()&& u instanceof  GasControlUnit) {
	    			m=m+"GasControlUnit" +"\n";
	    			break;
	    			  }
	    		
	    	else if(x1==u.getLocation().getX()&&y1==u.getLocation().getY()&& u instanceof DiseaseControlUnit) {		
	    		m=m+"DiseaseControlUnit" +"\n";	
	    		break;
	    		  }
	    	else if(x1==u.getLocation().getX()&&y1==u.getLocation().getY()&& u instanceof FireTruck) {
	    		m=m+"FireTruck" +"\n";
	    		break;
	    		   }
	    	else if(x1==u.getLocation().getX()&&y1==u.getLocation().getY() && u instanceof Evacuator) {
	    		m=m+"Evacuator" +"\n";
	    		Evacuator v=(Evacuator) u;
	    		m=m+"Evacuator capacity"+v.getPassengers().size()+"\n";
	    		for(Citizen c:((Evacuator) u).getPassengers()) { 			
	    			m=m+"Name: "+c.getName()+"\n";
	            	m=m+"Age: "+c.getAge()+"\n";
	            	m=m+"NationalID: "+c.getNationalID()+"\n";
	            	m=m+"hp: "+c.getHp()+"\n";
	            	m=m+"BloodLoss: "+c.getBloodLoss()+"\n";
	            	m=m+"Toxicity: "+c.getToxicity()+"\n";
	            	m=m+"state: "+c.getState()+"\n";
	            	if(c.getDisaster()!=null) {
	                	m=m+"Disaster: "+c.getDisaster()+"\n";           
	    		
	    		
	    		    	}
	        	}
	    		break;
	    	}
			}
			f=false;
		 m="Building Information"+"\n";
		m=m+"-----------------------"+"\n";
	     m="Location: \n"+x1+" "+y1+"\n";
		m=m+"Structural Integrity: "+b.getStructuralIntegrity()+"\n";
        m=m+"Fire Damage: "+b.getFireDamage()+"\n";
        m=m+"Gas Level: "+b.getGasLevel()+"\n";
        m=m+"Foundation Damage: "+b.getFoundationDamage()+"\n";
        m=m+"N of occupants"+b.getOccupants().size()+"\n";
        //
        System.out.println("m" +m);
		
        for(Citizen c:b.getOccupants()) {
        	m=m+"Name: "+c.getName()+"\n";
        	m=m+"Age: "+c.getAge()+"\n";
        	m=m+"NationalID: "+c.getNationalID()+"\n";
        	m=m+"hp: "+c.getHp()+"\n";
        	m=m+"BloodLoss: "+c.getBloodLoss()+"\n";
        	m=m+"Toxicity: "+c.getToxicity()+"\n";
        	m=m+"state: "+c.getState()+"\n";  
        }
        if(b.getDisaster()!=null) {
        	
        	m=m+"Disaster: "+b.getDisaster()+"\n";
        }
        
    	if(u12!=null) {
    		try {
    			for( Citizen c :b.getOccupants()) {
    				if(c.getBloodLoss()>0&&c.getState()!=CitizenState.DECEASED&& u12 instanceof Ambulance) {
    					u12.respond(c);
    					d.handleRespond(u12);
                       u12=null;
                       return m;
                       
    				}
    				if(c.getToxicity()>0&&c.getState()!=CitizenState.DECEASED&& u12 instanceof GasControlUnit) {
    					u12.respond(c);
    					d.handleRespond(u12);
                       u12=null;
                       return m;
    				}
    			}
    				
    			
    			if(u12.getState()==UnitState.IDLE) {  				
				
    				u12.respond(b);

d.handleRespond(u12);
if(u12 instanceof GasControlUnit) {
	d.getUnitgrid()[2][0].setIcon(null);
	}
if(u12 instanceof Evacuator) {
	d.getUnitgrid()[1][1].setIcon(null);
}
if(u12 instanceof FireTruck) {
	d.getUnitgrid()[1][0].setIcon(null);
}
    			}

	} catch (CannotTreatException e1) {
		d.getExcept().setText("Cannot Treat the target");
				
	} catch (IncompatibleTargetException e1) {
				d.getExcept().setText("Incompatible Target plz assign correct target type");
			}
    		u12=null;
    	}
    
		
			
		
		}
		if(b.getFireDamage()==0&&b.getGasLevel()==0&&b.getStructuralIntegrity()!=0&&!(b.getDisaster() instanceof Collapse)) {
			boolean for1=true;
			
			for(Citizen c: b.getOccupants()) { 
			if(c.getBloodLoss()>0 ||c.getToxicity()>0) {
				for1=false;
				break;
				
			}
			
	  		
	  		}
	  		if(for1)
	  		d.addComponentsB(b.getLocation().getX(), b.getLocation().getY());	             
			
	  	}
		}
		return m;
		
	}
	static boolean f1=true;
	static boolean f=true;
	static int num=0;
    Unit u12=null;
    public void how() {
    	for(Unit u:engine.getEmergencyUnits()) {
    		if(u.getState()==UnitState.TREATING) {
    			d.handletreat(u);
    		}
    	}
    }
    public boolean actiononrespond(ActionEvent e) {
    	JButton btn = (JButton) e.getSource();
    int x1=-1;
    int y1=-1;
    	for(int y=0; y<2; y++){
            for(int x=0; x<3; x++){
     if(grid3[x][y].equals(btn)) {
    	 y1=x;
    	 x1=y;
    	 break;
     }
            }
        
            
    }
    	
    	if(x1!=-1&&y1!=-1)
    	for(Unit u:engine.getEmergencyUnits()) {
    		String s="";
        	s=s+"Unit id: "+u.getUnitID()+"\n";
        	s=s+"Steps per cycle :"+u.getStepsPerCycle()+"\n";
        	s=s+"Location :"+u.getLocation()+"\n";
        	s=s+"state"+u.getState()+"\n";
        	if(u.getTarget() instanceof Citizen) {
        		s=s+"Target: "+"Citizen"+"\n";
        	}
        	else {
        		s=s+"Target: "+"Building"+"\n";        	
        	}
        
    		if(x1==0&&y1==0&& u instanceof  Ambulance) {
    			s=s+"Ambulance"+"\n";
    			d.BuildingInfo(s);
    			return false;		
    			   }
    		
    		
    	else if(x1==1 &&y1==0 && u instanceof DiseaseControlUnit) {		
    		s=s+"DiseaseControlUnit"+"\n";
    		d.BuildingInfo(s);
    			
    		return false;		
    		   }
    	else if(x1==0 &&y1==1 && u instanceof FireTruck) {
    		s=s+"FireTruck"+"\n";
    		d.BuildingInfo(s);
    		
    		return false;		
    		   }
    	else if(x1==1&&y1==1 && u instanceof Evacuator) {
    		s=s+"Evacuator"+"\n";
        	
    		Evacuator v=(Evacuator) u;
    		s=s+"Evacuator capacity"+v.getPassengers().size()+"\n";
    		d.BuildingInfo(s);
    		for(Citizen c:((Evacuator) u).getPassengers()) { 			
    			s=s+"Name: "+c.getName()+"\n";
            	s=s+"Age: "+c.getAge()+"\n";
            	s=s+"NationalID: "+c.getNationalID()+"\n";
            	s=s+"hp: "+c.getHp()+"\n";
            	s=s+"BloodLoss: "+c.getBloodLoss()+"\n";
            	s=s+"Toxicity: "+c.getToxicity()+"\n";
            	s=s+"state: "+c.getState()+"\n";
            	if(c.getDisaster()!=null) {
                	s=s+"Disaster: "+c.getDisaster()+"\n";           
    		
    		
    		    	}
        	}
    		return false;		
    	}
    	else if(x1==0 &&y1==2&& u instanceof GasControlUnit) {
    		s=s+"GasControlUnit"+"\n";
        	
    		d.BuildingInfo(s);
	return false;		
    	}
    		System.out.println(x1+" "+y1);
    	}
    	return true;
    }
    public boolean actionontreat(ActionEvent e) {
    	JButton btn = (JButton) e.getSource();
    int x1=-1;
    int y1=-1;
    	for(int y=0; y<2; y++){
            for(int x=0; x<3; x++){
     if(grid4[x][y].equals(btn)) {
    	 y1=x;
    	 x1=y;
    	 break;
     }
            }
        
            
    }
    	if(x1!=-1&&y1!=-1)
    	for(Unit u:engine.getEmergencyUnits()) {
    		String s="";
        	s=s+"Unit id: "+u.getUnitID()+"\n";
        	s=s+"Steps per cycle :"+u.getStepsPerCycle()+"\n";
        	s=s+"Location :"+u.getLocation()+"\n";
        	s=s+"state"+u.getState()+"\n";
        	if(u.getTarget() instanceof Citizen) {
        		s=s+"Target: "+"Citizen"+"\n";
        	}
        	else {
        		s=s+"Target: "+"Building"+"\n";        	
        	}
        
    		if(x1==0&&y1==0&& u instanceof  Ambulance) {
    			s=s+"Ambulance "+"\n";
    			d.BuildingInfo(s);
    			return false;		
    			   }
    		
    	else if(x1==1 &&y1==0 && u instanceof DiseaseControlUnit) {	
    		s=s+"DiseaseControlUnit"+"\n";
    		d.BuildingInfo(s);
    			
    		return false;		
    		   }
    	else if(x1==0 &&y1==1 && u instanceof FireTruck) {
    		s=s+"FireTruck"+"\n";
    		d.BuildingInfo(s);
    		
    		return false;		
    		   }
    	else if(x1==1&&y1==1 && u instanceof Evacuator) {
    		s=s+"Evacuator"+"\n";
    		Evacuator v=(Evacuator) u;
    		s=s+"Evacuator capacity"+v.getPassengers().size()+"\n";
    		for(Citizen c:((Evacuator) u).getPassengers()) { 			
    			s=s+"Name: "+c.getName()+"\n";
            	s=s+"Age: "+c.getAge()+"\n";
            	s=s+"NationalID: "+c.getNationalID()+"\n";
            	s=s+"hp: "+c.getHp()+"\n";
            	s=s+"BloodLoss: "+c.getBloodLoss()+"\n";
            	s=s+"Toxicity: "+c.getToxicity()+"\n";
            	s=s+"state: "+c.getState()+"\n";
            	if(c.getDisaster()!=null) {
                	s=s+"Disaster: "+c.getDisaster()+"\n";           
    		
    		
    		    	}
        	}
    		d.BuildingInfo(s);
    		
    		return false;		
    	}
    	else if(x1==0 &&y1==2&& u instanceof GasControlUnit) {
    		s=s+"GasControlUnit"+"\n";
    		d.BuildingInfo(s);
	return false;		
    	
			
    		   	}
    
    	}
    	return true;
    }
	
	public void actionPerformed(ActionEvent e)  {
		if(engine.checkGameOver()&&num==0) {
			int x=engine.calculateCasualties();
			d.displayend(x);
			num++;
			}
		else if(engine.checkGameOver()) {
			return;
		}
		// get the JButton that was clicked
		JButton btn = (JButton) e.getSource();
		
		try {
			actionPerformedOnNextCycle(e);
		} catch (BuildingAlreadyCollapsedException e2) {
			// TODO Auto-generated catch block
d.getExcept().setText("The building has been collapsed");
} catch (CannotTreatException e2) {
		
} catch (CitizenAlreadyDeadException e2) {
	d.getExcept().setText("The Citizen is already dead");
}
	if(!actiononrespond(e)){
		return;
	}
	if(!actionontreat(e)) {
		return;
	}
		f1=true;
		f=true;
		grid1=d.getGrid();
		int x1=-5;
		int y1=-5;
		for(int y=0; y<10; y++){
            for(int x=0; x<10; x++){
     if(grid1[x][y].equals(btn)) {
    	 x1=x;
    	 y1=y;
    	 break;
     }
            }
            
    }
		String s="";
					
	
				
		if(x1==0 &&y1==0){
			for(Citizen c:visibleCitizens) {
				if(c.getLocation().getX()==0&&c.getLocation().getY()==0) {
					s=s+"Location: \n"+x1+" "+y1+"\n";
    					
    			s=s+"Name: "+c.getName()+"\n";
            	s=s+"Age: "+c.getAge()+"\n";
            	s=s+"NationalID: "+c.getNationalID()+"\n";
            	s=s+"hp: "+c.getHp()+"\n";
            	s=s+"BloodLoss: "+c.getBloodLoss()+"\n";
            	s=s+"Toxicity: "+c.getToxicity()+"\n";
            	s=s+"state: "+c.getState()+"\n";
            	if(c.getDisaster()!=null) {
            		s=s+"Disaster: "+c.getDisaster()+"\n";
                }
            	System.out.println(s);
				
            	if(u12!=null)
            	if(u12.getState()==UnitState.IDLE) {
					if(u12 instanceof Ambulance && c.getBloodLoss()>0) {
					try {
						u12.respond(c);
						d.handleRespond(u12);
						u12=null;
		        	} catch (CannotTreatException | IncompatibleTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
        			else if(u12 instanceof DiseaseControlUnit &&c.getToxicity()>0) {
        				try {
							u12.respond(c);
							d.handleRespond(u12);
							u12=null;
	            		} catch (CannotTreatException | IncompatibleTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    					
        			}
					
				}
			}
			}
			for(Unit u :engine.getEmergencyUnits()) {
			if(u.getLocation().getX()==0 &&u.getLocation().getY()==0) {
				if(u instanceof Ambulance ) {
				s=s+"ID: "+u.getUnitID()+"\n";
				s=s+"Ambulance"+"\n";			
				
				s=s+"Location :"+u.getLocation()+"\n";
				s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
				
				if(u.getTarget() instanceof ResidentialBuilding) {
					s=s+" TARGET Building "+"\n";	
				}
				else {
					s=s+"Target Citizen "+"\n";		
				}
				s=s+"State :"+u.getState()+"\n";
						
				
			}
			
			else if(u instanceof FireTruck ) {
			s=s+"ID: "+u.getUnitID()+"\n";
			s=s+"FireTruck"+"\n";		
			
			s=s+"Location :"+u.getLocation()+"\n";
			s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
			
			if(u.getTarget() instanceof ResidentialBuilding) {
				s=s+" TARGET Building "+"\n";	
			}
			else {
				s=s+"Target Citizen "+"\n";		
			}
			s=s+"State :"+u.getState()+"\n";
					
			
		}
	
			else	if(u instanceof GasControlUnit ) {
			s=s+"ID: "+u.getUnitID()+"\n";
			s=s+"GasControlUnit"+"\n";		
			
			s=s+"Location :"+u.getLocation()+"\n";
			s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
			
			if(u.getTarget() instanceof ResidentialBuilding) {
				s=s+" TARGET Building "+"\n";	
			}
			else {
				s=s+"Target Citizen "+"\n";		
			}
			s=s+"State :"+u.getState()+"\n";
					
			
		}
			else	if( u instanceof DiseaseControlUnit ) {
			s=s+"ID: "+u.getUnitID()+"\n";
			s=s+"DiseaseControlUnit"+"\n";
			
			s=s+"Location :"+u.getLocation()+"\n";
			s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
			
			if(u.getTarget() instanceof ResidentialBuilding) {
				s=s+" TARGET Building "+"\n";	
			}
			else {
				s=s+"Target Citizen "+"\n";		
			}
			s=s+"State :"+u.getState()+"\n";
					
			
		}
		
		
			else	if(u instanceof Evacuator ) {
			
				s=s+"ID: "+u.getUnitID()+"\n";
			s=s+"Evacuator"+"\n";
				s=s+"Location :"+u.getLocation()+"\n";
			s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
			
			if(u.getTarget() instanceof ResidentialBuilding) {
				s=s+" TARGET Building "+"\n";	
			}
			else {
				s=s+"Target Citizen "+"\n";		
			}
			s=s+"State :"+u.getState()+"\n";
					
			
			int x3=((Evacuator) u).getPassengers().size();
		s=s+"Number of pass:"+x3+"\n";
		for(Citizen c:((Evacuator) u).getPassengers()) { 			
			s=s+"Name: "+c.getName()+"\n";
        	s=s+"Age: "+c.getAge()+"\n";
        	s=s+"NationalID: "+c.getNationalID()+"\n";
        	s=s+"hp: "+c.getHp()+"\n";
        	s=s+"BloodLoss: "+c.getBloodLoss()+"\n";
        	s=s+"Toxicity: "+c.getToxicity()+"\n";
        	s=s+"state: "+c.getState()+"\n";
        	if(c.getDisaster()!=null) {
               
         		s=s+"Disaster: "+c.getDisaster()+"\n";
            
		}
		
		}
			}
			}
		} 				
					f=false;
		}		 
	if(!f) {
		d.BuildingInfo(s);
	}
	
	String m="";
	if(f) {
	m=actionPerformedBuilding(e, x1, y1);

	d.BuildingInfo(m);
	}
	
	if(f) {
		s="";
	 label:for(Citizen c:visibleCitizens) {
    	 if(c.getState()==CitizenState.RESCUED) {
    		 for(ResidentialBuilding b: visibleBuildings) {
   		  if(b.getLocation().getX()==c.getLocation().getX()&&c.getLocation().getY()==c.getLocation().getY()) {
   			 d.addComponentsB(b.getLocation().getX(), b.getLocation().getY());
   			  break label;
   		  }
    			 d.addComponentsC(c.getLocation().getX(), c.getLocation().getY());	             
    		 }
   	}
    	 if(c.getState()==CitizenState.DECEASED){
    		String l="Citizen "+c.getName()+" "+c.getState()+" "+c.getLocation()+"\n";
    		if(!set.contains(l)) 
    		d.addtext3(l); 
    		set.add(l);
    	 }
    	 
    		if(c.getLocation().getX()==x1&&c.getLocation().getY()==y1) {
    			for(Unit u:engine.getEmergencyUnits() ) {
    				s=s+"Unit id: "+u.getUnitID()+"\n";
    	        	s=s+"Steps per cycle :"+u.getStepsPerCycle()+"\n";
    	        	s=s+"Location :"+u.getLocation()+"\n";
    	        	s=s+"state"+u.getState()+"\n";
    	        	if(u.getTarget() instanceof Citizen) {
    	        		s=s+"Target: "+"Citizen"+"\n";
    	        	}
    	        	else {
    	        		s=s+"Target: "+"Building"+"\n";        	
    	        	}
    	        
    	    		if(x1==u.getLocation().getX()&&y1==u.getLocation().getY()&& u instanceof  Ambulance) {
    	    			s=s+"Ambulance" +"\n";
    	    			break;
    	    			  }
    	    		else if(x1==u.getLocation().getX()&&y1==u.getLocation().getY()&& u instanceof  GasControlUnit) {
    	    			s=s+"GasControlUnit" +"\n";
    	    			break;
    	    			  }
    	    		
    	    	else if(x1==u.getLocation().getX()&&y1==u.getLocation().getY()&& u instanceof DiseaseControlUnit) {		
    	    		s=s+"DiseaseControlUnit" +"\n";	
    	    		break;
    	    		  }
    	    	else if(x1==u.getLocation().getX()&&y1==u.getLocation().getY()&& u instanceof FireTruck) {
    	    		s=s+"FireTruck" +"\n";
    	    		break;
    	    		   }
    	    	else if(x1==u.getLocation().getX()&&y1==u.getLocation().getY() && u instanceof Evacuator) {
    	    		s=s+"Evacuator" +"\n";
    	    		Evacuator v=(Evacuator) u;
    	    		s=s+"Evacuator capacity"+v.getPassengers().size()+"\n";
    	    		for(Citizen c1:((Evacuator) u).getPassengers()) { 			
    	    			s=s+"Name: "+c1.getName()+"\n";
    	            	s=s+"Age: "+c1.getAge()+"\n";
    	            	s=s+"NationalID: "+c1.getNationalID()+"\n";
    	            	s=s+"hp: "+c1.getHp()+"\n";
    	            	s=s+"BloodLoss: "+c1.getBloodLoss()+"\n";
    	            	s=s+"Toxicity: "+c1.getToxicity()+"\n";
    	            	s=s+"state: "+c1.getState()+"\n";
    	            	if(c1.getDisaster()!=null) {
    	                	s=s+"Disaster: "+c1.getDisaster()+"\n";           
    	    		
    	    		
    	    		    	}
    	        	}
    	    		break;
    	    	}

    			s="Location: \n"+x1+" "+y1+"\n";
    			f=false;
    					
    			s=s+"Name: "+c.getName()+"\n";
            	s=s+"Age: "+c.getAge()+"\n";
            	s=s+"NationalID: "+c.getNationalID()+"\n";
            	s=s+"hp: "+c.getHp()+"\n";
            	s=s+"BloodLoss: "+c.getBloodLoss()+"\n";
            	s=s+"Toxicity: "+c.getToxicity()+"\n";
            	s=s+"state: "+c.getState()+"\n";
            	if(c.getDisaster()!=null) {
            		s=s+"Disaster: "+c.getDisaster()+"\n";
                }
            	
            	if(u12!=null) {
            		try {
            			if(u12.getState()==UnitState.IDLE) {
						u12.respond(c);
						d.handleRespond(u12);
						if(u12 instanceof Ambulance) {
							d.getUnitgrid()[0][0].setIcon(null);
						}
						if(u12 instanceof DiseaseControlUnit) {
						d.getUnitgrid()[0][1].setIcon(null);
						}
            			
            			}}
					 catch (CannotTreatException e1) {
						d.getExcept().setText("can't treat the current target");
} catch (IncompatibleTargetException e1) {
	d.getExcept().setText("plz choose valid target");
}
            	}
            			
            		
            	u12=null; 
    			} 
    		
     }
     if(m.length()!=0&&s.length()!=0)
     d.BuildingInfo(m+s);
     else if(m.length()!=0) {
    	 d.BuildingInfo(m);
     }
     else {
    	 d.BuildingInfo(s);
         	 
     }
	}
	if(f) {
	    
	s="";
    grid2=d.getUnitgrid();
    btn=(JButton) e.getSource();
    for(Unit u: engine.getEmergencyUnits()) {
    if(u instanceof Ambulance &&u.getState()==UnitState.IDLE) {
      	ImageIcon ii = new ImageIcon("images//images3.PNG");
    	int scale = 4; // 2 times smaller
    	 int width = ii.getIconWidth();
    	 int newWidth = width / scale;
   d.getUnitgrid()[0][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
    	            java.awt.Image.SCALE_SMOOTH)));
   d.getUnitgrid1()[0][0].setIcon(null);
   d.getUnitgrid2()[0][0].setIcon(null);
    	
    }
if(u instanceof FireTruck &&u.getState()==UnitState.IDLE) {
  	ImageIcon ii = new ImageIcon("images//images5.PNG");
	int scale = 4; // 2 times smaller
	 int width = ii.getIconWidth();
	 int newWidth = width / scale;
d.getUnitgrid()[1][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
	            java.awt.Image.SCALE_SMOOTH)));
d.getUnitgrid1()[1][0].setIcon(null);
d.getUnitgrid2()[1][0].setIcon(null);

	
    }if(u instanceof DiseaseControlUnit &&u.getState()==UnitState.IDLE) {
      	ImageIcon ii = new ImageIcon("images//images4.PNG");
    	int scale = 4; // 2 times smaller
    	 int width = ii.getIconWidth();
    	 int newWidth = width / scale;
   d.getUnitgrid()[0][1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
    	            java.awt.Image.SCALE_SMOOTH)));
   d.getUnitgrid1()[0][1].setIcon(null);
   d.getUnitgrid2()[0][1].setIcon(null);
   
    }if(u instanceof Evacuator &&u.getState()==UnitState.IDLE) {
    	ImageIcon ii = new ImageIcon("images//images6.PNG");
    	int scale = 4; // 2 times smaller
    	 int width = ii.getIconWidth();
    	 int newWidth = width / scale;
   d.getUnitgrid()[1][1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
    	            java.awt.Image.SCALE_SMOOTH)));
   d.getUnitgrid1()[1][1].setIcon(null);
   d.getUnitgrid2()[1][1].setIcon(null);
    }if(u instanceof GasControlUnit &&u.getState()==UnitState.IDLE) {
    	ImageIcon ii = new ImageIcon("images//images7.PNG");
    	int scale = 2; // 2 times smaller
    	 int width = ii.getIconWidth();
    	 int newWidth = width / scale;
   d.getUnitgrid()[2][0].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
    	            java.awt.Image.SCALE_SMOOTH)));
   d.getUnitgrid1()[2][0].setIcon(null);
   d.getUnitgrid2()[2][0].setIcon(null);
    }
    
    }	
    for(Unit u: engine.getEmergencyUnits()) {
			
			if(u instanceof Ambulance && e.getActionCommand().equals("Amb")&&u.getState()==UnitState.IDLE) {
				u12=u;
				s=s+"ID: "+u.getUnitID()+"\n";
				s=s+"Ambulance"+"\n";			
				
				s=s+"Location :"+u.getLocation()+"\n";
				s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
				
				if(u.getTarget() instanceof ResidentialBuilding &&u.getState()==UnitState.IDLE) {
					s=s+" TARGET Building "+"\n";	
				}
				else {
					s=s+"Target Citizen "+"\n";		
				}
				s=s+"State :"+u.getState()+"\n";
						
				
			}
			
			else if(u instanceof FireTruck &&e.getActionCommand().equals("Fir")&&u.getState()==UnitState.IDLE) {
				u12=u;
				
				s=s+"ID: "+u.getUnitID()+"\n";
			s=s+"FireTruck"+"\n";		
			
			s=s+"Location :"+u.getLocation()+"\n";
			s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
			
			if(u.getTarget() instanceof ResidentialBuilding) {
				s=s+" TARGET Building "+"\n";	
			}
			else {
				s=s+"Target Citizen "+"\n";		
			}
			s=s+"State :"+u.getState()+"\n";
					
			
		}
	
			else	if(u instanceof GasControlUnit &&e.getActionCommand().equals("Gas")&&u.getState()==UnitState.IDLE) {
			s=s+"ID: "+u.getUnitID()+"\n";
			s=s+"GasControlUnit"+"\n";		
			u12=u;
			
			s=s+"Location :"+u.getLocation()+"\n";
			s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
			
			if(u.getTarget() instanceof ResidentialBuilding) {
				s=s+" TARGET Building "+"\n";	
			}
			else {
				s=s+"Target Citizen "+"\n";		
			
			}
			s=s+"State :"+u.getState()+"\n";
			u12=u;
					
			
		}
			else	if( u instanceof DiseaseControlUnit &&e.getActionCommand().equals("Disease")&&u.getState()==UnitState.IDLE) {
			s=s+"ID: "+u.getUnitID()+"\n";
			s=s+"DiseaseControlUnit"+"\n";
			u12=u;
			
			s=s+"Location :"+u.getLocation()+"\n";
			s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
			
			if(u.getTarget() instanceof ResidentialBuilding) {
				s=s+" TARGET Building "+"\n";	
			}
			else {
				s=s+"Target Citizen "+"\n";		
			}
			s=s+"State :"+u.getState()+"\n";
					
			
		}
		
		
			else	if(u instanceof Evacuator &&e.getActionCommand().equals("Evac")&&u.getState()==UnitState.IDLE) {
				u12=u;
				
				s=s+"ID: "+u.getUnitID()+"\n";
			s=s+"Evacuator"+"\n";
				s=s+"Location :"+u.getLocation()+"\n";
			s=s+"StepsPerCycle :"+u.getStepsPerCycle()+"\n";
			
			if(u.getTarget() instanceof ResidentialBuilding) {
				s=s+" TARGET Building "+"\n";	
			}
			else {
				s=s+"Target Citizen "+"\n";		
			}
			s=s+"State :"+u.getState()+"\n";
					
			
			int x3=((Evacuator) u).getPassengers().size();
		s=s+"Number of pass:"+x3+"\n";
		for(Citizen c:((Evacuator) u).getPassengers()) { 			
			s=s+"Name: "+c.getName()+"\n";
        	s=s+"Age: "+c.getAge()+"\n";
        	s=s+"NationalID: "+c.getNationalID()+"\n";
        	s=s+"hp: "+c.getHp()+"\n";
        	s=s+"BloodLoss: "+c.getBloodLoss()+"\n";
        	s=s+"Toxicity: "+c.getToxicity()+"\n";
        	s=s+"state: "+c.getState()+"\n";
        	if(c.getDisaster()!=null) {
            	s=s+"Disaster: "+c.getDisaster()+"\n";           
		}
		
		}
		
		}

    }
	 }
     d.BuildingInfo(s);
    }
            }
	static HashSet<String> s=new HashSet<>();
	

	public void ExecutedDisasters() {
		ArrayList<Disaster> d2=engine.getExecutedDisasters();
	String s="";
		for(Disaster d1:d2) {
			if(d1.getStartCycle()==engine.getCurrentCycle()) {
			if(d1 instanceof Fire) {
				s=s+"Fire"+" "+"Target Residental Building "+d1.getTarget().getLocation()+"\n";
			}
			else if(d1 instanceof GasLeak) {
				s=s+"GasLeak"+" "+"Target Residental Building "+d1.getTarget().getLocation()+"\n";
			}
			else if(d1 instanceof Collapse) {
				s=s+"Collapse"+" "+"Target Residental Building "+d1.getTarget().getLocation()+"\n";
			}
			else if(d1 instanceof Infection) {
				Citizen c=(Citizen) d1.getTarget();
				s=s+"Infection"+" "+"Target Citizen "+d1.getTarget().getLocation()+" "+c.getName()+"\n";	
			}
			else if(d1 instanceof Injury) {
				Citizen c=(Citizen) d1.getTarget();
				s=s+"Injury"+" "+"Target Citizen "+d1.getTarget().getLocation()+" "+c.getName()+"\n";	
				}
			
			
		}
		}
		d.addtext3(s);
		
	}
			
		
		
	
	public  void viewDisaster() {
		for(Disaster dis: engine.getExecutedDisasters()) {
				int x=dis.getTarget().getLocation().getX();
				int y=dis.getTarget().getLocation().getY();
				
				if(dis instanceof Fire  ) {
					ResidentialBuilding b=(ResidentialBuilding) dis.getTarget();
					
					if(b.getFireDamage()>0) {
					ImageIcon ii = new ImageIcon("images//images10.PNG");
				   	int scale = 2; // 2 times smaller
				   	int width = ii.getIconWidth();
				   	int newWidth = width / scale;
				   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
				   	            java.awt.Image.SCALE_SMOOTH)));
					}
				}
				else if(dis instanceof Collapse) {
					ResidentialBuilding b=(ResidentialBuilding) dis.getTarget();
					if(b.getFoundationDamage()>0) {
					
					ImageIcon ii = new ImageIcon("images//images11.JPG");
				   	int scale = 1; // 2 times smaller
				   	int width = ii.getIconWidth();
				   	int newWidth = width / scale;
				   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
				   	            java.awt.Image.SCALE_SMOOTH)));
					}
				}
				else if(dis instanceof GasLeak) {
					ResidentialBuilding b=(ResidentialBuilding) dis.getTarget();
					if(b.getGasLevel()>0) {
					
					ImageIcon ii = new ImageIcon("images//images12.PNG");
				   	int scale = 3; // 2 times smaller
				   	int width = ii.getIconWidth();
				   	int newWidth = width / scale;
				   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
				   	            java.awt.Image.SCALE_SMOOTH)));
					}
					
				}
				else if(dis instanceof Infection) {
					Citizen c=(Citizen) dis.getTarget();
					if(c.getToxicity()>0) {
					ImageIcon ii = new ImageIcon("images//images13.PNG");
				   	int scale = 4; // 2 times smaller
				   	int width = ii.getIconWidth();
				   	int newWidth = width / scale;
				   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
				   	            java.awt.Image.SCALE_SMOOTH)));
					}
					if(c.getState()==CitizenState.DECEASED) {
						ImageIcon ii = new ImageIcon("images//deceased.PNG");
					   	int scale = 4; // 2 times smaller
					   	int width = ii.getIconWidth();
					   	int newWidth = width / scale;
					   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
					   	            java.awt.Image.SCALE_SMOOTH)));
						
					}
				}
				else if( dis instanceof Injury) {
					Citizen c=(Citizen) dis.getTarget();
					if(c.getBloodLoss()>0) {
					
					ImageIcon ii = new ImageIcon("images//images14.PNG");
				   	int scale = 3; // 2 times smaller
				   	int width = ii.getIconWidth();
				   	int newWidth = width / scale;
				   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
				   	            java.awt.Image.SCALE_SMOOTH)));
					
					}
					if(c.getState()==CitizenState.DECEASED) {
						ImageIcon ii = new ImageIcon("images//deceased.PNG");
					   	int scale = 4; // 2 times smaller
					   	int width = ii.getIconWidth();
					   	int newWidth = width / scale;
					   	d.getGrid()[x][y].setIcon(new ImageIcon(ii.getImage().getScaledInstance(newWidth, -1,
					   	            java.awt.Image.SCALE_SMOOTH)));
						
					}
				}
			}
		}

	
	public void activeDis() {
		String lo="Active disasters"+"\n";
		for(Disaster d:engine.getExecutedDisasters()) {
			if(d.isActive()) {
				if(d instanceof Fire) {
					ResidentialBuilding b=(ResidentialBuilding) d.getTarget();
					lo+="Fire disaster "+"at Building"+b.getLocation()+"\n";
				}
				if(d instanceof GasLeak) {
					ResidentialBuilding b=(ResidentialBuilding) d.getTarget();
					lo+="GAS leak disaster "+"at Building"+b.getLocation()+"\n";
				}if(d instanceof Collapse) {
					ResidentialBuilding b=(ResidentialBuilding) d.getTarget();
					lo+="Collapse disaster "+"at Building"+b.getLocation()+"\n";
				}
				if(d instanceof Injury) {
					Citizen c=(Citizen) d.getTarget();
					lo+="Injury disaster"+"Citizen"+" "+c.getName()+" "+c.getLocation() +"\n";
				}
				if(d instanceof Infection) {
					Citizen c=(Citizen) d.getTarget();
					lo+="Infection disaster"+"Citizen"+" "+c.getName()+" "+c.getLocation()+"\n";
				}
				
			}
		}
		d.getActive().setText(lo);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		CommandCenter c=new CommandCenter();
		
		
		
		
	
	

}
	

}
