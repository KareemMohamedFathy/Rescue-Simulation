package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class DiseaseControlUnit extends MedicalUnit {

	public DiseaseControlUnit(String unitID, Address location,
			int stepsPerCycle, WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() throws CannotTreatException{
		
		getTarget().getDisaster().setActive(false);
		Citizen target = (Citizen) getTarget();
		

		if (target.getHp() == 0) {
			jobsDone();
			return;
		} else if (target.getToxicity() > 0) {
			target.setToxicity(target.getToxicity() - getTreatmentAmount());
			if (target.getToxicity() == 0)
				target.setState(CitizenState.RESCUED);
		}

		else if (target.getToxicity() == 0)
			heal();
		}
	

	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
		if((r.getDisaster() instanceof Infection && r instanceof Citizen)) {
			super.respond(r);
		}
		if(r instanceof ResidentialBuilding) {
				ResidentialBuilding b=(ResidentialBuilding) r;
				boolean f=true;
				Citizen c1=null;
				for(Citizen c: b.getOccupants()) {
					if(c.getToxicity()>0) {
						f=false;
						c1=c;
					}
				}
				if(f) {
					
					throw new IncompatibleTargetException(this,r, "Invalid Target plz choose valid one");
				}
				if(!f) {
					super.respond(c1);
					
					return;
				}
				}
			if(!(r instanceof ResidentialBuilding)){
			Citizen c=(Citizen) r;
			if(c.getHp()<=0||c.getToxicity()>=100) {
				throw new CannotTreatException(this, r,"Cannot treat the target");
			}
			}
			
					
			
			
				
			if(!(r.getDisaster() instanceof Infection)) {
				
				throw new CannotTreatException(this,r,"invalid target can't treat");	
			}	
			
		if (getTarget() != null && ((Citizen) getTarget()).getToxicity() > 0
				&& getState() == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);
	}
			 


}