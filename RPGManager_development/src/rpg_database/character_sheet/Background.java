package rpg_database.character_sheet;

public enum Background {
	// TODO lot of enum information. Factory class for reading from file?
	ANDER_SURVIVOR("Ander Survivor"), APOSTATE("Apostate"), ANTIVAN_WAYFARER("Antivan Wayfarer"), AVVAR("Avvar"),
	CHASIND_WILDER("Chasind Wilder"), CIRCLE_MAGE("Circle Mage"), CITY_ELF("City Elf"), DALISH_ELF("Dalish Elf"),
	DWARF_DUSTER("Dwarf Duster"), ESCAPED_ELVEN_SLAVE("Escaped Elven Slave"), FERELDAN_CRAFTSMEN("Fereldan Craftsmen"),
	FERELDAN_FREEMAN("Fereldan Freeman"), FERELDAN_NOBLE("Fereldan Noble"), FREE_MARCHER("Free Marcher"),
	HIGH_BORN_DWARF("High Born Dwarf"), LOW_BORN_DWARF("Low Born Dwarf"), NEVARRAN_ADVENTURER("Nevarran Adventurer"),
	ORLESIAN_COMMONER("Orlesian Commoner"), ORLESIAN_EXILE("Orlesian Exile"), ORLESIAN_NOBLE("Orlesian Noble"),
	ORLESIAN_STUDENT("Orlesian Student"), QUNARI_BERESAAD("Qunari Beresaad"), RIVAINI_MERCHANT("Rivaini Merchant"),
	SEHERON_CONVERT("Seheron Convert"), SURFACE_DWARF("Surface Dwarf"), TAL_VASHOTH("Tal-Vashoth"), TEVINTER_ALTUS(
			"Tevinter Altus"), TEVINTER_LAETAN("Tevinter Laetan"), TEVINTER_SOPORATI("Tevinter Soporati"),
	WAKING_SEA_RAIDER("Waking Sea Raider");

	private final String text;

	private Background(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}