package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value{
	int val;
	
	public boolean equals(Object another) {
		if (another instanceof IntValue)
		{
			if(val==((IntValue) another).getVal())
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}
	
	public IntValue(int val) {
		this.val=val;
	}
	
	public int getVal() {
		return this.val;
	}
	
	public String toString() {
		return Integer.toString(val);
	}
	
	public Type getType() {
		return new IntType();
	}
}
