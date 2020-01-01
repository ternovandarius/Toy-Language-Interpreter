package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value{
	String str;
	
	@Override
	public boolean equals(Object another) {
		if (another instanceof StringValue)
		{
			if(str.equals(((StringValue) another).getVal()))
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}
	
	public StringValue(String strr) {
		str=strr;
	}
	
	public String getVal() {
		return str;
	}
	
	@Override
	public String toString() {
		return str;
	}
	
	@Override
	public Type getType() {
		return new StringType();
	}
}
