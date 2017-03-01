package rpg_database.character_sheet.character_class;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Setter;

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
	public void setDataInSheet(CharacterSheet characterSheet, BaseClasses baseClass) {
		CharacterClass characterClass = characterSheet.getData(Fields.CHARACTERCLASS);
		characterClass.setBaseClass(baseClass);

	}

	@Override
	public Class<BaseClasses> getImplementingClass() {
		return BaseClasses.class;
	}
}