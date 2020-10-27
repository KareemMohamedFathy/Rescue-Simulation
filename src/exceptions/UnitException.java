package exceptions;

import model.units.Unit;
import simulation.Rescuable;

public abstract class UnitException extends SimulationException{
private Unit unit;
private Rescuable Target;
public UnitException(Unit unit, Rescuable target){
super();
}
public UnitException(Unit unit, Rescuable target,String message) {
	
}
public Unit getUnit() {
	return unit;
}
public Rescuable getTarget() {
	return Target;
}

}