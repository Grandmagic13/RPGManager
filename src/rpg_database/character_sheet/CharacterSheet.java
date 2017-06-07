package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.HashMap;

import rpg_database.character_sheet.interfaces.CustomSetter;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetter;

public class CharacterSheet {

	private static final String ENTRY_NAME_PATTERN = "^(?U)[\\p{Alpha}_]+";
	private static final String INVALID_PARAMETER_EXCEPTION_MESSAGE_FORMAT = "%s value is not an instance of %s";
	private final HashMap<Class<?>, Object> defaultData = initializeDefaultData();

	private HashMap<Class<?>, Object> initializeDefaultData() {
		HashMap<Class<?>, Object> defaultData = new HashMap<Class<?>, Object>();

		defaultData.put(String.class, "");
		defaultData.put(Integer.class, 0);
		defaultData.put(Gender.class, Gender.MALE);
		defaultData.put(BaseClasses.class, BaseClasses.WARRIOR);
		defaultData.put(SpecializationClassesSet.class, new SpecializationClassesSet(this));
		defaultData.put(Background.class, Background.ANDER_SURVIVOR);
		defaultData.put(Money.class, new Money());
		defaultData.put(LanguagesSet.class, new LanguagesSet(Languages.TRADE_TONGUE, Languages.ANDER));
		defaultData.put(WeaponGroupsSet.class, new WeaponGroupsSet(WeaponGroups.BRAWLING, WeaponGroups.HEAVY_BLADES, WeaponGroups.BLUDGEONS,
				WeaponGroups.AXES));
		defaultData.put(Armors.class, Armors.HEAVY_LEATHER);
		defaultData.put(FocusesSet.class, new FocusesSet());
		return defaultData;
	}

	private String entryName;
	protected HashMap<Fields, Object> characterData;

	public CharacterSheet(String entryName) {
		if (entryName.matches(ENTRY_NAME_PATTERN)) {
			this.entryName = entryName;
		} else {
			throw new IllegalArgumentException("Only alphabet characters and '_' are allowed!");
		}
		this.characterData = new HashMap<>();

		for (Fields field : Fields.values()) {
			if (!field.isContainted()) {
				putDefaultValueByFieldAllowedType(field);
			}
		}
	}

	private void putDefaultValueByFieldAllowedType(Fields field) {
		Class<?> allowedType = field.getAllowedClass();
		Object defaultValue;
		if (allowedType == CharacterAttribute.class) {
			defaultValue = new CharacterAttribute(0, BaseClasses.WARRIOR.isAttributeMajor(field));
		} else if (field.name().equals("ARMOR_RATING")) {
			defaultValue = 4;
		} else {
			defaultValue = defaultData.get(allowedType);
		}
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
		} else {
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
