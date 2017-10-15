package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetter;

public class CharacterAttribute implements MultipleFieldsGetterSetter<CharacterAttribute, Object> {

	private int value;
	private boolean isMajor;
	private static final int DEFENSE_BASE_VALUE = 10;
	private final CharacterSheet characterSheet;

	private final static ArrayList<Fields> attributesList = new ArrayList<>(Arrays.asList(Fields.COMMUNICATION_VALUE, Fields.CONSTITUTION_VALUE,
			Fields.CUNNING_VALUE, Fields.DEXTERITY_VALUE, Fields.MAGIC_VALUE, Fields.PERCEPTION_VALUE, Fields.STRENGTH_VALUE,
			Fields.WILLPOWER_VALUE));

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
			if (field.getAllowedClass() == Integer.class && attributesList.contains(field)) {
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
			if (attributesList.contains(field)) {
				return value;
			} else if (field.equals(Fields.DEFENSE)) {
				return DEFENSE_BASE_VALUE + value < 0 ? 0 : DEFENSE_BASE_VALUE + value;
			} else if (field.equals(Fields.SPEED)) {
				int speedCalculation = getBackgroundBaseSpeed() + getArmorPenaltyValue() + value;
				return speedCalculation < 0 ? 0 : speedCalculation;
			} else
				throw new InvalidParameterException(generateExceptionMessage(field));
		} else if (field.getAllowedClass() == Boolean.class)
			return isMajor;
		else
			throw new InvalidParameterException(generateExceptionMessage(field));
	}

	private String generateExceptionMessage(Fields field) {
		return field.getAllowedClass() == Integer.class ? field.equals(Fields.DEFENSE) || field.equals(Fields.SPEED) ? String.format(
				"You can not set %s value manually!", field.name().toLowerCase())
				: String.format("The %s is not a valid member of CharacterAttribute.class!", field.name())
				: String.format("Unknown allowed field class: '%s'", field.getAllowedClass().toString());
	}

	private int getArmorPenaltyValue() {
		return characterSheet.<Armors>getData(Fields.ARMOR_TYPE).getArmorPenalty();
	}

	private int getBackgroundBaseSpeed() {
		return characterSheet.<Background>getData(Fields.BACKGROUND).getBaseSpeed();
	}
}
