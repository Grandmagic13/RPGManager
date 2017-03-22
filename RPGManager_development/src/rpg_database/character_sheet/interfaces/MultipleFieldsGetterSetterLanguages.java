package rpg_database.character_sheet.interfaces;

import java.util.HashSet;

import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Languages;

public interface MultipleFieldsGetterSetterLanguages<DATA, DATATYPE> extends MultipleFieldsGetterSetter{
	public Class<DATA> getImplementingClass();

	public Class<DATATYPE> getDataTypeClass();
	
	public void setSelfValueByField(Languages language, DATATYPE value);

	public HashSet<DATATYPE> getStoredValueByField(Fields field);
}
