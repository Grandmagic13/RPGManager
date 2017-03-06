package rpg_database.character_sheet.interfaces;

import rpg_database.character_sheet.Fields;

public interface MultipleFieldsSetter<DATA extends MultipleFieldsSetter<DATA, DATATYPE>, DATATYPE> {
	public Class<DATA> getImplementingClass();

	public Class<DATATYPE> getDataTypeClass();

	public void setSelfValueByField(Fields field, DATATYPE value);
}
