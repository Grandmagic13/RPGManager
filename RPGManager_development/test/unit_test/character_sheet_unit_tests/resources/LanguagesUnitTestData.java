package unit_test.character_sheet_unit_tests.resources;

import java.util.HashMap;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.Languages;
import rpg_database.character_sheet.LanguagesSet;

public class LanguagesUnitTestData {

	final static public HashMap<Background, LanguagesSet> initializeRequiredLanguages() {
		HashMap<Background, LanguagesSet> requiredLanguages = new HashMap<>();
		requiredLanguages.put(Background.ANDER_SURVIVOR, new LanguagesSet(Languages.ANDER, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.ANTIVAN_WAYFARER, new LanguagesSet(Languages.ANTIVAN, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.AVVAR, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.CHASIND_WILDER, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.CITY_ELF, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.DALISH_ELF, new LanguagesSet(Languages.ELVEN, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.DWARF_DUSTER, new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.ELF_APOSTATE, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.ELF_CIRCLE_MAGE, new LanguagesSet(Languages.ANCIENT_TEVENE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.ESCAPED_ELVEN_SLAVE, new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.FERELDAN_CRAFTSMEN, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.FERELDAN_FREEMAN, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.FERELDAN_NOBLE, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.FREE_MARCHER, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.HIGH_BORN_DWARF, new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.HUMAN_APOSTATE, new LanguagesSet(Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.HUMAN_CIRCLE_MAGE, new LanguagesSet(Languages.ANCIENT_TEVENE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.LOW_BORN_DWARF, new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.NEVARRAN_ADVENTURER, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.ORLESIAN_COMMONER, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.ORLESIAN_EXILE, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.ORLESIAN_NOBLE, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.ORLESIAN_STUDENT, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.QUNARI_BERESAAD, new LanguagesSet(Languages.QUNLAT, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.RIVAINI_MERCHANT, new LanguagesSet(Languages.RIVAINI, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.SEHERON_CONVERT, new LanguagesSet(Languages.TRADE_TONGUE, Languages.TEVENE, Languages.QUNLAT));
		requiredLanguages.put(Background.SURFACE_DWARF, new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.TAL_VASHOTH, new LanguagesSet(Languages.QUNLAT, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.TEVINTER_ALTUS, new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.TEVINTER_LAETAN, new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.TEVINTER_SOPORATI, new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE));
		requiredLanguages.put(Background.WAKING_SEA_RAIDER, new LanguagesSet(Languages.TRADE_TONGUE));
		return requiredLanguages;
	}
}
