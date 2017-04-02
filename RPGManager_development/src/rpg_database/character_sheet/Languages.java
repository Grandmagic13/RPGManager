package rpg_database.character_sheet;

public enum Languages {
	ANDER("Ander"), ANTIVAN("Antivan"), TRADE_TONGUE("Trade tongue"),
	DWARVEN_LANGUAGE("Dwarven language"), ELVEN("Elven"), 
	QUNLAT("Qunlat"), ORLESIAN("Orlesian"), RIVAINI("Rivaini"), 
	TEVENE("Tevene"), ANCIENT_TEVENE("Ancient Tevene");
	
	public final static LanguagesSet TRADE = new LanguagesSet(Languages.TRADE_TONGUE);
	public final static LanguagesSet ANDER_TRADE = new LanguagesSet(Languages.ANDER,Languages.TRADE_TONGUE);
	public final static LanguagesSet ANTIVAN_TRADE = new LanguagesSet(Languages.ANTIVAN,Languages.TRADE_TONGUE);
	public final static LanguagesSet DWARVEN_TRADE = new LanguagesSet(Languages.DWARVEN_LANGUAGE,Languages.TRADE_TONGUE);
	public final static LanguagesSet ELVEN_TRADE = new LanguagesSet(Languages.ELVEN,Languages.TRADE_TONGUE);
	public final static LanguagesSet QUNLAT_TRADE = new LanguagesSet(Languages.QUNLAT,Languages.TRADE_TONGUE);
	public final static LanguagesSet ORLESIAN_TRADE = new LanguagesSet(Languages.ORLESIAN,Languages.TRADE_TONGUE);
	public final static LanguagesSet RIVAINI_TRADE = new LanguagesSet(Languages.RIVAINI,Languages.TRADE_TONGUE);
	public final static LanguagesSet TEVENE_TRADE = new LanguagesSet(Languages.TEVENE,Languages.TRADE_TONGUE);
	public final static LanguagesSet ANCIENT_TEVENE_TRADE = new LanguagesSet(Languages.ANCIENT_TEVENE,Languages.TRADE_TONGUE);
	public final static LanguagesSet QUNLAT_TEVENE_TRADE = new LanguagesSet(Languages.TEVENE,Languages.QUNLAT,Languages.TRADE_TONGUE);

	private final String text;
	
	private Languages(final String text){
		this.text = text;
	}

	//TODO WAKING_SEA_RAIDER' default languages depends on the land where he is from, can be Antivan, Rivaine or Orlesian
}
