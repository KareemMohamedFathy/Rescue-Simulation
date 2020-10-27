package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.infrastructure.ResidentialBuilding;


public class Fire extends Disaster  {

	public Fire(int startCycle, ResidentialBuilding target){
		super(startCycle, target);
		
	}
	
	public void strike () throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException{
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		if(target.getFoundationDamage()>0||  target.getDisaster() instanceof Collapse||target.getStructuralIntegrity()==0) {
			throw new  BuildingAlreadyCollapsedException(this.getTarget().getDisaster(), "Building already Collapsed can't strike disaster");
		}
		else {
		target.setFireDamage(target.getFireDamage()+10);
		
		super.strike();
		}

	}

	public void cycleStep() {
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		target.setFireDamage(target.getFireDamage()+10);
		
	}

}
