package rpg_database.character_sheet.character_class;

public enum BaseClasses {
	WARRIOR("Warrior"), ROGUE("Rogue"), MAGE("Mage");

	private final String text;

	private BaseClasses(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}