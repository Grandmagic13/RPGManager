package rpg_database.character_sheet;

import java.util.HashSet;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetterLanguages;

public class LanguagesSetter implements MultipleFieldsGetterSetterLanguages<LanguagesSetter,Languages>{
	private HashSet<Languages> defaultLanguages;
	public LanguagesSetter(){
		defaultLanguages = new HashSet<Languages>();
		defaultLanguages.add(Languages.ANDER);
		defaultLanguages.add(Languages.TRADE_TONGUE);
	}

	@Override
	public HashSet<Languages> getStoredValueByField(Fields field) {
		return defaultLanguages;
	}
	
	@Override
	public void setSelfValueByField(Fields field, Object value) {
		defaultLanguages = (HashSet<Languages>) value;
	}

	@Override
	public Class<LanguagesSetter> getImplementingClass() {
		return LanguagesSetter.class;
	}

	@Override
	public Class<Languages> getDataTypeClass() {
		return Languages.class;
	}

	@Override
	public void setSelfValueByField(Languages language, Languages value) {
		// TODO Auto-generated method stub

	}


}
