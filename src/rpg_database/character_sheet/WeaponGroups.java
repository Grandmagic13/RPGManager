package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum WeaponGroups {
	AXES, BLUDGEONS, BOWS, BRAWLING, DUELING, HEAVY_BLADES, LANCES, LIGHT_BLADES, POLEARMS, SPEARS, STAVES;

	private final String text;

	private WeaponGroups() {
		this.text = generateEnumText(this.name());
	}

	@Override
	public String toString() {
		return text;
	}
}
