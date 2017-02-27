package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.HashMap;

import rpg_database.character_sheet.character_class.BaseClasses;
import rpg_database.character_sheet.character_class.CharacterClass;
import rpg_database.character_sheet.character_class.InvalidCharacterClassException;
import rpg_database.character_sheet.character_class.SpecializationClasses;

public class CharacterSheet {

	private final HashMap<Class, Object> defaultData = initializeDefaultData();

	private static HashMap<Class, Object> initializeDefaultData() {
		HashMap<Class, Object> defaultData = new HashMap<Class, Object>();
		CharacterClass defaultClass = new CharacterClass(BaseClasses.WARRIOR, SpecializationClasses.NOT_APPLICABLE);

		defaultData.put(String.class, "");
		defaultData.put(Integer.class, 0);
		defaultData.put(Gender.class, Gender.MALE);
		defaultData.put(CharacterClass.class, defaultClass);
		defaultData.put(Background.class, Background.ANDER_SURVIVOR);
		return defaultData;
	}

	private String entryName;
	private HashMap<Fields, Object> characterData;

	public CharacterSheet(String entryName) {
		this.entryName = entryName;
		this.characterData = new HashMap<>();

		for (Fields field : Fields.values()) {
			putDefaultValueByFieldAllowedType(field);
		}
	}

	private void putDefaultValueByFieldAllowedType(Fields field) {
		Class allowedType = field.getAllowedClass();
		Object defaultValue = defaultData.get(allowedType);
		characterData.put(field, allowedType.cast(defaultValue));
	}

	public String getEntryName() {
		return entryName;
	}

	public <DataType extends Object> DataType getData(Fields field) {
		return (DataType) (characterData.get(field));
	}

	public <DataType> void setData(Fields field, DataType value) {
		if (value.getClass() == field.getAllowedClass())
			this.characterData.put(field, value);
		else
			throw new InvalidParameterException(String.format("%s value is not an instance of %s", value.getClass()
					.toString(), field.getAllowedClass().toString()));
	}
}
