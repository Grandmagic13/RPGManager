package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum Gender {
	MALE, FEMALE;

	private final String text;

	private Gender() {
		this.text = generateEnumText(this.name());
	}

	@Override
	public String toString() {
		return text;
	}
}