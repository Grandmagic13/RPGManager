package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum Languages {
	ANDER, ANTIVAN, TRADE_TONGUE, DWARVEN_LANGUAGE, ELVEN, QUNLAT, ORLESIAN, RIVAINI, TEVENE, ANCIENT_TEVENE;

	@SuppressWarnings("unused")
	private final String text;

	private Languages() {
		this.text = generateEnumText(this.name());
	}

}
