package unit_test.character_sheet_unit_tests.resources;

import rpg_database.character_sheet.Background;

final public class BackgroundUnitTestData {
	final static public Background[] mageOnlyBackgrounds = { Background.ELF_APOSTATE, Background.ELF_CIRCLE_MAGE, Background.HUMAN_APOSTATE,
			Background.HUMAN_CIRCLE_MAGE, Background.TEVINTER_ALTUS, Background.TEVINTER_LAETAN };
	final static public Background[] rogueOnlyBackgrounds = { Background.DWARF_DUSTER };
	final static public Background[] rogueAndWarriorBackgrounds = { Background.ANTIVAN_WAYFARER, Background.CITY_ELF, Background.FERELDAN_CRAFTSMEN,
			Background.FERELDAN_FREEMAN, Background.FERELDAN_NOBLE, Background.FREE_MARCHER, Background.HIGH_BORN_DWARF, Background.LOW_BORN_DWARF,
			Background.NEVARRAN_ADVENTURER, Background.ORLESIAN_COMMONER, Background.ORLESIAN_NOBLE, Background.ORLESIAN_STUDENT,
			Background.QUNARI_BERESAAD, Background.RIVAINI_MERCHANT, Background.SEHERON_CONVERT, Background.SURFACE_DWARF,
			Background.TEVINTER_SOPORATI, Background.WAKING_SEA_RAIDER };
	final static public Background[] allClassesBackgrounds = { Background.ANDER_SURVIVOR, Background.AVVAR, Background.CHASIND_WILDER,
			Background.DALISH_ELF, Background.ESCAPED_ELVEN_SLAVE, Background.ORLESIAN_EXILE, Background.TAL_VASHOTH };
	public static final Background[] elfMageBackgrounds = { Background.DALISH_ELF, Background.ELF_APOSTATE, Background.ELF_CIRCLE_MAGE,
			Background.ELF_CIRCLE_MAGE, Background.ESCAPED_ELVEN_SLAVE };
	public static final Background[] dwarfRogueBackgrounds = { Background.DWARF_DUSTER, Background.HIGH_BORN_DWARF, Background.LOW_BORN_DWARF,
			Background.SURFACE_DWARF };
	public static final Background[] dwarfWarriorBackgrounds = { Background.HIGH_BORN_DWARF, Background.LOW_BORN_DWARF, Background.SURFACE_DWARF };
	public static final Background[] humanAndElfWarriorBackgrounds = { Background.ANDER_SURVIVOR, Background.ANTIVAN_WAYFARER, Background.AVVAR,
			Background.CHASIND_WILDER, Background.CITY_ELF, Background.DALISH_ELF, Background.ESCAPED_ELVEN_SLAVE, Background.FERELDAN_CRAFTSMEN,
			Background.FERELDAN_FREEMAN, Background.FERELDAN_NOBLE, Background.FREE_MARCHER, Background.NEVARRAN_ADVENTURER,
			Background.ORLESIAN_COMMONER, Background.ORLESIAN_EXILE, Background.ORLESIAN_NOBLE, Background.ORLESIAN_STUDENT,
			Background.RIVAINI_MERCHANT, Background.SEHERON_CONVERT, Background.TEVINTER_SOPORATI, Background.WAKING_SEA_RAIDER };

	private BackgroundUnitTestData() {

	}

}
