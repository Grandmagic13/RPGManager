package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.HashMap;

import rpg_database.character_sheet.character_class.BaseClasses;
import rpg_database.character_sheet.character_class.CharacterClass;
import rpg_database.character_sheet.character_class.SpecializationClasses;

public class CharacterSheet {

	private static final String INVALID_PARAMETER_EXCEPTION_MESSAGE_FORMAT = "%s value is not an instance of %s";
	private final HashMap<Class<?>, Object> defaultData = initializeDefaultData();

	private HashMap<Class<?>, Object> initializeDefaultData() {
		HashMap<Class<?>, Object> defaultData = new HashMap<Class<?>, Object>();
		CharacterClass defaultClass = new CharacterClass(BaseClasses.WARRIOR, SpecializationClasses.NOT_APPLICABLE);

		defaultData.put(String.class, "");
		defaultData.put(Integer.class, 0);
		defaultData.put(Gender.class, Gender.MALE);
		defaultData.put(CharacterClass.class, defaultClass);
		defaultData.put(Background.class, Background.ANDER_SURVIVOR);
		return defaultData;
	}

	private String entryName;
	protected HashMap<Fields, Object> characterData;

	public CharacterSheet(String entryName) {
		this.entryName = entryName;
		this.characterData = new HashMap<>();

		for (Fields field : Fields.values()) {
			putDefaultValueByFieldAllowedType(field);
		}
	}

	private void putDefaultValueByFieldAllowedType(Fields field) {
		Class<?> allowedType = field.getAllowedClass();
		Object defaultValue = defaultData.get(allowedType);
		characterData.put(field, allowedType.cast(defaultValue));
	}

	public String getEntryName() {
		return entryName;
	}

	public <DataType extends Object> DataType getData(Fields field) {
		DataType data;
		switch (field) {
		case BASECLASS:
			data = (DataType) getBaseClass();
			break;
		case SPECIALIZATIONCLASS:
			data = (DataType) getSpecializationClass();
			break;
		default:
			data = (DataType) (characterData.get(field));
			break;
		}
		return data;
	}

	// TODO if DataType implements some interface, switch case can be
	// implemented
	// there?
	public <DataType> void setData(Fields field, DataType value) {
		if (value.getClass() == field.getAllowedClass()) {
			this.characterData.put(field, value);
		} else {
			throw new InvalidParameterException(createInvalidParameterExceptionMessage(field, value.getClass()));
		}
	}

	public void setData(Fields field, Setter value) {
		if (value.getImplementingClass() == field.getAllowedClass()) {
			value.setDataInSheet(this, value);
		} else {
			throw new InvalidParameterException(createInvalidParameterExceptionMessage(field, value.getClass()));
		}
	}

	private String createInvalidParameterExceptionMessage(Fields field, Class<?> valueClass) {
		return String.format(INVALID_PARAMETER_EXCEPTION_MESSAGE_FORMAT, valueClass.toString(), field.getAllowedClass().toString());
	}

	private BaseClasses getBaseClass() {
		CharacterClass characterClass = getData(Fields.CHARACTERCLASS);
		return characterClass.getBaseClass();
	}

	private SpecializationClasses getSpecializationClass() {
		CharacterClass characterClass = getData(Fields.CHARACTERCLASS);
		return characterClass.getSpecializationClass();
	}
}
