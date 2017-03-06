package rpg_database.character_sheet;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;

public enum BaseClasses implements CustomSetter<BaseClasses> {
	WARRIOR("Warrior"), ROGUE("Rogue"), MAGE("Mage");

	private final String text;

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
}