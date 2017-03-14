package rpg_database.character_sheet;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

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
		SpecializationClassesSet specializationClassesSet = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		for (SpecializationClasses specializationClass : specializationClassesSet) {
			if (!specializationClass.isBaseClassCompatible(this)) {
				throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", this, specializationClass.toString()));
			}
		}
		characterSheet.characterData.put(Fields.BASECLASS, this);
	}

	@Override
	public Class<BaseClasses> getImplementingClass() {
		return BaseClasses.class;
	}
}