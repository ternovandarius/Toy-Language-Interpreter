package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.Values.Value;

public interface Exp {
	Value eval(MyIDictionary<String, Value> tbl) throws MyException;
	public String toString();
}
