package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum TalentLevels {
	NOVICE(1), JOURNEYMAN(2), MASTER(3);

	private final String text;
	private final int value;

	private TalentLevels(int value) {
		this.text = generateEnumText(this.name());
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return text;
	}
}
