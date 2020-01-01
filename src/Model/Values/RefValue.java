package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value{

	int address;
	Type locationType;
	
	public RefValue(int addr, Type locType)
	{
		address=addr;
		locationType=locType;
	}
	
	@Override
	public boolean equals(Object another) {
		if (another instanceof RefValue)
		{
			if(address==((RefValue)another).getAddr())
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}
	
	public int getAddr()
	{
		return address;
	}
	
	public void setAddr(int addr)
	{
		address=addr;
	}
	
	@Override
	public String toString()
	{
		return "Ref("+locationType.toString()+")";
	}
	
	public Type getInnerType()
	{
		return locationType;
	}
	
	@Override
	public Type getType()
	{
		return new RefType(locationType);
	}
}
