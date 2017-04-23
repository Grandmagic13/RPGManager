package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum Languages {
	ANDER, ANTIVAN, TRADE_TONGUE, DWARVEN_LANGUAGE, ELVEN, QUNLAT, ORLESIAN, RIVAINI, TEVENE, ANCIENT_TEVENE;

	public final static LanguagesSet TRADE = new LanguagesSet(Languages.TRADE_TONGUE);
	public final static LanguagesSet ANDER_TRADE = new LanguagesSet(Languages.ANDER, Languages.TRADE_TONGUE);
	public final static LanguagesSet ANTIVAN_TRADE = new LanguagesSet(Languages.ANTIVAN, Languages.TRADE_TONGUE);
	public final static LanguagesSet DWARVEN_TRADE = new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE);
	public final static LanguagesSet ELVEN_TRADE = new LanguagesSet(Languages.ELVEN, Languages.TRADE_TONGUE);
	public final static LanguagesSet QUNLAT_TRADE = new LanguagesSet(Languages.QUNLAT, Languages.TRADE_TONGUE);
	public final static LanguagesSet ORLESIAN_TRADE = new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE);
	public final static LanguagesSet RIVAINI_TRADE = new LanguagesSet(Languages.RIVAINI, Languages.TRADE_TONGUE);
	public final static LanguagesSet TEVENE_TRADE = new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE);
	public final static LanguagesSet ANCIENT_TEVENE_TRADE = new LanguagesSet(Languages.ANCIENT_TEVENE, Languages.TRADE_TONGUE);
	public final static LanguagesSet QUNLAT_TEVENE_TRADE = new LanguagesSet(Languages.QUNLAT, Languages.TEVENE, Languages.TRADE_TONGUE);

	@SuppressWarnings("unused")
	private final String text;

	private Languages() {
		this.text = generateEnumText(this.name());
	}

}