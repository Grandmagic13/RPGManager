package rpg_database.character_sheet.interfaces;

import rpg_database.character_sheet.CharacterSheet;

public interface CustomSetter<DATA extends CustomSetter<DATA>> {
	public Class<DATA> getImplementingClass();

	public void setSelfInSheet(CharacterSheet characterSheet);
}
