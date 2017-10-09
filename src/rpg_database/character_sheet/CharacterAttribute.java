package rpg_database.character_sheet;

import java.security.InvalidParameterException;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetter;

public class CharacterAttribute implements MultipleFieldsGetterSetter<CharacterAttribute, Object> {

	private int value;
	private boolean isMajor;
	private static final int DEFENSE_BASE_VALUE = 10;
	private final CharacterSheet characterSheet;
	public final static Fields[] ATTRIBUTES = { Fields.COMMUNICATION, Fields.CONSTITUTION, Fields.CUNNING, Fields.DEXTERITY, Fields.MAGIC,
			Fields.PERCEPTION, Fields.STRENGTH, Fields.WILLPOWER };

	protected CharacterAttribute(int value, boolean isMajor, CharacterSheet characterSheet) {
		this.value = value;
		this.isMajor = isMajor;
		this.characterSheet = characterSheet;
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
		try {
			if (field.getAllowedClass() == Integer.class) {
				this.value = (int) value;
			} else {
				throw field.getAllowedClass() == Boolean.class ? new InvalidCharacterClassException(
						"To change majority please modify the base class!") : new InvalidParameterException(generateExceptionMessage(field));
			}
		} catch (ClassCastException exception) {
			throw new ClassCastException(exception.getMessage());
		}

	}

	@Override
	public Object getStoredValueByField(Fields field) {
		if (field.getAllowedClass() == Integer.class) {
			if (field.equals(Fields.DEFENSE)) {
				return DEFENSE_BASE_VALUE + value < 0 ? 0 : DEFENSE_BASE_VALUE + value;
			} else if (field.equals(Fields.SPEED)) {
				int speedCalculation = getBackgroundBaseSpeed() + getArmorPenaltyValue() + value;
				return speedCalculation < 0 ? 0 : speedCalculation;
			}
			return value;
		} else if (field.getAllowedClass() == Boolean.class)
			return isMajor;
		else
			throw new InvalidParameterException(generateExceptionMessage(field));
	}

	private String generateExceptionMessage(Fields field) {
		return String.format("Unknown allowed field class: '%s'", field.getAllowedClass().toString());
	}

	private int getArmorPenaltyValue() {
		return characterSheet.<Armors>getData(Fields.ARMOR_TYPE).getArmorPenalty();
	}

	private int getBackgroundBaseSpeed() {
		return characterSheet.<Background>getData(Fields.BACKGROUND).getBaseSpeed();
	}
}
