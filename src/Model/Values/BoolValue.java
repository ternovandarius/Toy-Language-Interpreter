package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{
	boolean val;
	
	public BoolValue(boolean val) {
		this.val=val;
	}
	
	public boolean getValue() {
		return this.val;
	}
	
	public String toString() {
		return Boolean.toString(val);
	}
	
	public Type getType() {
		return new BoolType();
	}
}
