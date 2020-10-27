package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.GasLeak;
import model.disasters.Injury;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class Ambulance extends MedicalUnit {

	public Ambulance(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);

		Citizen target = (Citizen) getTarget();
		if (target.getHp() == 0) {
			jobsDone();
			return;
		} else if (target.getBloodLoss() > 0) {
			target.setBloodLoss(target.getBloodLoss() - getTreatmentAmount());
			if (target.getBloodLoss() == 0)
				target.setState(CitizenState.RESCUED);
		}

		else if (target.getBloodLoss() == 0)

			heal();

	}

	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
		Citizen c1=null;	
		if(r instanceof ResidentialBuilding) {
				ResidentialBuilding b=(ResidentialBuilding) r;
				boolean f=true;
				for(Citizen c: b.getOccupants()) {
					if(c.getBloodLoss()>0) {
						f=false;
						c1=c;
					}
				}
				if(f)
				throw new IncompatibleTargetException(this,r, "Invalid Target plz choose valid one");
			}
			
			if(r instanceof Citizen) {
			Citizen c=(Citizen) r;
			if(c.getHp()<=0||c.getBloodLoss()>=100)
				throw new CannotTreatException(this, r,"Cannot treat the target");
			
			
			if((r.getDisaster() instanceof Injury)) {
				super.respond(r);
			}
			if(c1!=null)
		 if((c1.getDisaster() instanceof Injury)) {
				super.respond(c1);
			}
			if(!super.canTreat(r)||!(r.getDisaster() instanceof Injury)) {
				throw new CannotTreatException(this, r,"Cannot treat the target ");
			}
		}
			
		else {
		
		if (getTarget() != null && ((Citizen) getTarget()).getBloodLoss() > 0
				&& getState() == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);
	}

}
}
