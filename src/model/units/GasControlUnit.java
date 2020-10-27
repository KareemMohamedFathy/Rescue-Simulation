package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Collapse;
import model.disasters.GasLeak;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;

public class GasControlUnit extends FireUnit {

	public GasControlUnit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}
	

	public void treat() {
		getTarget().getDisaster().setActive(false);

		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0) {
			jobsDone();
			return;
		} else if (target.getGasLevel() > 0) 
			target.setGasLevel(target.getGasLevel() - 10);

		if (target.getGasLevel() == 0)
			jobsDone();

	}
public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
	if(r instanceof Citizen) {
		throw new IncompatibleTargetException(this,r, "Invalid Target plz choose valid one");
	}
ResidentialBuilding b=(ResidentialBuilding) r;
	
	if(b.getGasLevel()>=100)
		throw new CannotTreatException(this, r,"Cannot treat the target");
	
	if((r.getDisaster() instanceof GasLeak)) {
			super.respond(r);
			
	}
		
	else {
		if(!(r.getDisaster() instanceof GasLeak))
		throw new CannotTreatException(this, r,"Cannot treat the target");
		
	}
	
	
}
}
