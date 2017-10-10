package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum Race {

	HUMAN, ELF, DWARF, QUNARI;

	private final String text;

	private Race() {
		this.text = generateEnumText(this.name());
	}

	@Override
	public String toString() {
		return text;
	}
}
