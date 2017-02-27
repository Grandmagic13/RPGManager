package rpg_database.character_sheet.character_class;

public enum SpecializationClasses {
	// TODO specializationClasses!
	NOT_APPLICABLE("N/A"), ARCANE_WARRIOR("Arcane Warrior", BaseClasses.MAGE), ASSASSIN("Assassin", BaseClasses.ROGUE),
	CHAMPION("Champion", BaseClasses.WARRIOR);

	private final String text;
	private final BaseClasses baseClass;

	private SpecializationClasses(final String text, BaseClasses baseClass) {
		this.text = text;
		this.baseClass = baseClass;
	}

	// TODO does this work or cause errors?
	private SpecializationClasses(final String text) {
		this.text = text;
		this.baseClass = null;
	}

	public boolean hasBase() {
		return baseClass != null;
	}

	public BaseClasses getBaseClass() {
		return baseClass;
	}

	@Override
	public String toString() {
		return text;
	}
}