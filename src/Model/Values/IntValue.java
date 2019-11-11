package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value{
	int val;
	
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
