package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum BaseClasses implements CustomSetter<BaseClasses> {
	WARRIOR("Warrior"), ROGUE("Rogue"), MAGE("Mage");

	private final String text;

	private final static ArrayList<Fields> warriorMajors = new ArrayList<>(Arrays.asList(new Fields[] { Fields.ATTRIBUTE_STRENGTH,
			Fields.ATTRIBUTE_DEXTERITY, Fields.ATTRIBUTE_CONSTITUTION }));

	private BaseClasses(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		SpecializationClasses specializationClass = characterSheet.getData(Fields.SPECIALIZATIONCLASS);
		if (specializationClass.isBaseClassCompatible(this)) {
			characterSheet.characterData.put(Fields.BASECLASS, this);
		} else {
			throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", this, specializationClass));
		}
	}

	@Override
	public Class<BaseClasses> getImplementingClass() {
		return BaseClasses.class;
	}

	public boolean isAttributeMajor(Fields field) {
		if (field.getAllowedClass() == CharacterAttribute.class) {
			return warriorMajors.contains(field);
		} else {
			throw new InvalidParameterException("Field allowed class is not CharacterAttribute!");
		}
	}
}