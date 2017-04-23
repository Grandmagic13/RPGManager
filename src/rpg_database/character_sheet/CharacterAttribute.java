package rpg_database.character_sheet;

import java.security.InvalidParameterException;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetter;

public class CharacterAttribute implements MultipleFieldsGetterSetter<CharacterAttribute, Object> {

	private int value;
	private boolean isMajor;
	public final static Fields[] ATTRIBUTES = { Fields.COMMUNICATION, Fields.CONSTITUTION, Fields.CUNNING, Fields.DEXTERITY, Fields.MAGIC,
			Fields.PERCEPTION, Fields.STRENGTH, Fields.WILLPOWER };

	protected CharacterAttribute(int value, boolean isMajor) {
		this.value = value;
		this.isMajor = isMajor;
	}

	protected void setMajority(boolean majority) {
		this.isMajor = majority;
	}

	@Override
	public Class<CharacterAttribute> getImplementingClass() {
		return CharacterAttribute.class;
	}

	@Override
	public Class<Object> getDataTypeClass() {
		return Object.class;
	}

	@Override
	public void setSelfValueByField(Fields field, Object value) {
		if (field.getAllowedClass() == Integer.class) {
			this.value = (int) value;
		} else {
			throw field.getAllowedClass() == Boolean.class ? new InvalidCharacterClassException("To change majority please modify the base class!")
					: new InvalidParameterException(generateExceptionMessage(field));
		}

	}

	@Override
	public Object getStoredValueByField(Fields field) {
		if (field.getAllowedClass() == Integer.class)
			return value;
		else if (field.getAllowedClass() == Boolean.class)
			return isMajor;
		else
			throw new InvalidParameterException(generateExceptionMessage(field));
	}

	private String generateExceptionMessage(Fields field) {
		return String.format("Unknown allowed field class: '%s'", field.getAllowedClass().toString());
	}

}
