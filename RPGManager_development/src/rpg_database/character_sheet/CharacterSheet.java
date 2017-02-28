package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

import rpg_database.character_sheet.character_class.BaseClasses;
import rpg_database.character_sheet.character_class.CharacterClass;
import rpg_database.character_sheet.character_class.InvalidCharacterClassException;
import rpg_database.character_sheet.character_class.SpecializationClasses;

public class CharacterSheet {

	private final HashMap<Class, Object> defaultData = initializeDefaultData();

	private HashMap<Class, Object> initializeDefaultData() {
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

	// TODO if DataType extends some interface, switch case can be implemented
	// there?
	public <DataType> void setData(Fields field, DataType value) {
		if (value.getClass() == field.getAllowedClass()) {
			switch (field) {
			case BASECLASS:
				setBaseClass((BaseClasses) value);
				break;
			case SPECIALIZATIONCLASS:
				setSpecializationClass((SpecializationClasses) value);
				break;
			case BACKGROUND:
				setBackground((Background) value);
				break;
			default:
				this.characterData.put(field, value);
				break;
			}
		} else {
			throw new InvalidParameterException(String.format("%s value is not an instance of %s", value.getClass().toString(), field
					.getAllowedClass().toString()));
		}
	}

	private void setBaseClass(BaseClasses baseClass) {
		// TODO refactor getter to get baseclass!
		CharacterClass characterClass = getData(Fields.CHARACTERCLASS);
		characterClass.setBaseClass(baseClass);
	}

	private void setSpecializationClass(SpecializationClasses specializationClass) {
		CharacterClass characterClass = getData(Fields.CHARACTERCLASS);
		characterClass.setSpecializationClass(specializationClass);
	}

	private void setBackground(Background background) {
		if (!isCharacterBaseClassAllowed(background.getAllowedBaseClasses())) {
			// TODO refactor getter to get baseclass!
			CharacterClass characterClass = getData(Fields.CHARACTERCLASS);
			throw new InvalidCharacterClassException(String.format("%s is not a %s background!", background.toString(), characterClass.getBaseClass()
					.toString()));
		}
		this.characterData.put(Fields.BACKGROUND, background);
	}

	private boolean isCharacterBaseClassAllowed(ArrayList<BaseClasses> baseClass) {
		CharacterClass characterClass = getData(Fields.CHARACTERCLASS);
		return baseClass.contains(characterClass.getBaseClass());
	}
}
