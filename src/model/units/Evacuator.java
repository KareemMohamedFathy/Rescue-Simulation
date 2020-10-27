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

public class Evacuator extends PoliceUnit {

	public Evacuator(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener, int maxCapacity) {
		super(unitID, location, stepsPerCycle, worldListener, maxCapacity);

	}

	@Override
	public void treat() {
		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0|| target.getOccupants().size() == 0) {
			jobsDone();
			return;
		}

		for (int i = 0; getPassengers().size() != getMaxCapacity()
				&& i < target.getOccupants().size(); i++) {
			getPassengers().add(target.getOccupants().remove(i));
			i--;
		}

		setDistanceToBase(target.getLocation().getX()
				+ target.getLocation().getY());

	}
	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
		if(r instanceof Citizen) {
			
			
			throw new IncompatibleTargetException(this,r, "Invalid Target plz choose valid one");
		}
		
		ResidentialBuilding b=(ResidentialBuilding) r;
		if(b.getFoundationDamage()>=100) {
			throw new CannotTreatException(this, r,"Cannot treat the target");
					
		}
		if((r.getDisaster() instanceof Collapse)) {
			super.respond(r);
			
	}
		
	else {
		if(!(r.getDisaster() instanceof Collapse)) {			
		throw new CannotTreatException(this, r,"Cannot treat the target");
		
	}
	}
	
	}
}
