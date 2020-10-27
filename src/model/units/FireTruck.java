package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;

public class FireTruck extends FireUnit {

	public FireTruck(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);

		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0) {
			jobsDone();
			return;
		} else if (target.getFireDamage() > 0)

			target.setFireDamage(target.getFireDamage() - 10);

		if (target.getFireDamage() == 0)

			jobsDone();

	}
	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
		if(r instanceof Citizen) {
			throw new IncompatibleTargetException(this,r, "Invalid Target plz choose valid one");
		}
		
		else {
			super.respond(r);
		}
		ResidentialBuilding b=(ResidentialBuilding) r;
		if(b.getFireDamage()>=100) {
			throw new CannotTreatException(this, r,"Cannot treat the target");		
		}
		if((r.getDisaster() instanceof Fire)) {
			super.respond(r);
				
	}
		
	else {
		if(!(r.getDisaster() instanceof Fire))
		throw new CannotTreatException(this, r,"Cannot treat the target");
		
	}
	}
}
