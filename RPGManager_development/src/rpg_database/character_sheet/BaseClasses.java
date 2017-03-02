package rpg_database.character_sheet;

public enum BaseClasses implements Setter<BaseClasses> {
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
		if (isSpecializationCompatible(specializationClass)) {
			characterSheet.characterData.put(Fields.BASECLASS, this);
		} else {
			throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", this, specializationClass));
		}
	}

	private boolean isSpecializationCompatible(SpecializationClasses specializationClass) {
		return (specializationClass.hasBase() && specializationClass.getBaseClass().equals(this)) || !specializationClass.hasBase();
	}

	@Override
	public Class<BaseClasses> getImplementingClass() {
		return BaseClasses.class;
	}
}