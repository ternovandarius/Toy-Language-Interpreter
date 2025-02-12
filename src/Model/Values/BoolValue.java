package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{
	boolean val;
	
	@Override
	public boolean equals(Object another) {
		if (another instanceof BoolValue)
		{
			if(val==((BoolValue) another).getValue())
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}
	
	public BoolValue(boolean val) {
		this.val=val;
	}
	
	public boolean getValue() {
		return this.val;
	}
	
	@Override
	public String toString() {
		return Boolean.toString(val);
	}
	
	@Override
	public Type getType() {
		return new BoolType();
	}
}
