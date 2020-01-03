package UI;

import javafx.beans.property.SimpleStringProperty;

public class TableEntry {
	private final SimpleStringProperty firstCol = new SimpleStringProperty("");
	private final SimpleStringProperty secondCol = new SimpleStringProperty("");
	
	public TableEntry(String first, String second)
	{
		this.setFirstCol(first);
		this.setSecondCol(second);
	}
	
	public String getFirstCol()
	{
		return firstCol.get();
	}
	
	public void setFirstCol(String newValue)
	{
		firstCol.set(newValue);
	}
	
	public String getSecondCol()
	{
		return secondCol.get();
	}
	
	public void setSecondCol(String newValue)
	{
		secondCol.set(newValue);
	}
}
