package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.infrastructure.ResidentialBuilding;


public class GasLeak extends Disaster {

	public GasLeak(int startCycle, ResidentialBuilding target) {
		super(startCycle, target);
	}
	
	@Override
	public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException 
	{
		
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		if(target.getFoundationDamage()>0||  target.getDisaster() instanceof Collapse||target.getStructuralIntegrity()==0) {
			throw new  BuildingAlreadyCollapsedException(this.getTarget().getDisaster(), "Building already Collapsed can't strike disaster");
		}		target.setGasLevel(target.getGasLevel()+10);
		super.strike();
	}
	@Override
	public void cycleStep() {
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		target.setGasLevel(target.getGasLevel()+15);
		
	}

}
