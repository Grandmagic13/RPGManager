package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import rpg_database.character_sheet.interfaces.CustomSetter;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetter;

public class CharacterSheet {

	private static final String INVALID_PARAMETER_EXCEPTION_MESSAGE_FORMAT = "%s value is not an instance of %s";
	private final HashMap<Class<?>, Object> defaultData = initializeDefaultData();

	private HashMap<Class<?>, Object> initializeDefaultData() {
		HashMap<Class<?>, Object> defaultData = new HashMap<Class<?>, Object>();

		defaultData.put(String.class, "");
		defaultData.put(Integer.class, 0);
		defaultData.put(Gender.class, Gender.MALE);
		defaultData.put(BaseClasses.class, BaseClasses.WARRIOR);
		defaultData.put(SpecializationClasses.class, SpecializationClasses.NOT_APPLICABLE);
		defaultData.put(Background.class, Background.ANDER_SURVIVOR);
		defaultData.put(Money.class, new Money());
		defaultData.put(LanguagesSetter.class, new LanguagesSetter());
		return defaultData;
	}

	private String entryName;
	protected HashMap<Fields, Object> characterData;

	public CharacterSheet(String entryName) {
		this.entryName = entryName;
		this.characterData = new HashMap<>();

		for (Fields field : Fields.values()) {
			if (!field.isContainted()) {
				putDefaultValueByFieldAllowedType(field);
			}
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

	@SuppressWarnings("unchecked")
	public <DataType extends Object> DataType getData(Fields field) {
		if (field.isContainted()) {
			Object containerObject = this.characterData.get(field.getContainingField());
			return (DataType) MultipleFieldsGetterSetter.class.cast(containerObject).getStoredValueByField(field);
		}else {
			return (DataType) (characterData.get(field));
		}
	}

	@SuppressWarnings("unchecked")
	public <DataType> void setData(Fields field, DataType value) {
		if (value.getClass() == field.getAllowedClass()) {
			if (field.isContainted()) {
				Object containerObject = this.characterData.get(field.getContainingField());
				MultipleFieldsGetterSetter.class.cast(containerObject).setSelfValueByField(field, value);
			} else {
				this.characterData.put(field, value);
			} 
		} else {
			throw new InvalidParameterException(createInvalidParameterExceptionMessage(field, value.getClass()));
		}
	}

	public void setData(Fields field, CustomSetter<?> value) {
		if (value.getImplementingClass() == field.getAllowedClass()) {
			value.setSelfInSheet(this);
		} else {
			throw new InvalidParameterException(createInvalidParameterExceptionMessage(field, value.getClass()));
		}
	}

	private String createInvalidParameterExceptionMessage(Fields field, Class<?> valueClass) {
		return String.format(INVALID_PARAMETER_EXCEPTION_MESSAGE_FORMAT, valueClass.toString(), field.getAllowedClass().toString());
	}
}
