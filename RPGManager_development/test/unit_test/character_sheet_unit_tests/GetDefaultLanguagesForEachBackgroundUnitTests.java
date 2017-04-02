package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Languages;
import rpg_database.character_sheet.LanguagesSet;

@RunWith(Parameterized.class)
public class GetDefaultLanguagesForEachBackgroundUnitTests {

	@Parameters(name = "Background : ''{0}'', Expected Language :''{1}'' ")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ Background.ANDER_SURVIVOR, new LanguagesSet(Languages.ANDER, Languages.TRADE_TONGUE) },
				{ Background.ANTIVAN_WAYFARER, new LanguagesSet(Languages.ANTIVAN, Languages.TRADE_TONGUE) },
				{ Background.APOSTATE, new LanguagesSet(Languages.TRADE_TONGUE) },
				{ Background.AVVAR, new LanguagesSet(Languages.TRADE_TONGUE) },
				{ Background.CHASIND_WILDER, new LanguagesSet(Languages.TRADE_TONGUE) },
				{ Background.CIRCLE_MAGE, new LanguagesSet(Languages.ANCIENT_TEVENE, Languages.TRADE_TONGUE) },
				{ Background.CITY_ELF, new LanguagesSet(Languages.TRADE_TONGUE) },
				{ Background.DALISH_ELF, new LanguagesSet(Languages.ELVEN, Languages.TRADE_TONGUE) },
				{ Background.DWARF_DUSTER, new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE) },
				{ Background.ESCAPED_ELVEN_SLAVE, new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE) },
				{ Background.FERELDAN_CRAFTSMEN, new LanguagesSet(Languages.TRADE_TONGUE) },
				{ Background.FERELDAN_FREEMAN, new LanguagesSet(Languages.TRADE_TONGUE) },
				{ Background.FERELDAN_NOBLE, new LanguagesSet(Languages.TRADE_TONGUE) },
				{ Background.FREE_MARCHER, new LanguagesSet(Languages.TRADE_TONGUE) },
				{ Background.HIGH_BORN_DWARF, new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE) },
				{ Background.LOW_BORN_DWARF, new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE) },
				{ Background.NEVARRAN_ADVENTURER, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE) },
				{ Background.ORLESIAN_COMMONER, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE) },
				{ Background.ORLESIAN_EXILE, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE) },
				{ Background.ORLESIAN_NOBLE, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE) },
				{ Background.ORLESIAN_STUDENT, new LanguagesSet(Languages.ORLESIAN, Languages.TRADE_TONGUE) },
				{ Background.QUNARI_BERESAAD, new LanguagesSet(Languages.QUNLAT, Languages.TRADE_TONGUE) },
				{ Background.RIVAINI_MERCHANT, new LanguagesSet(Languages.RIVAINI, Languages.TRADE_TONGUE) },
				{ Background.SEHERON_CONVERT,
						new LanguagesSet(Languages.QUNLAT, Languages.TEVENE, Languages.TRADE_TONGUE) },
				{ Background.SURFACE_DWARF, new LanguagesSet(Languages.DWARVEN_LANGUAGE, Languages.TRADE_TONGUE) },
				{ Background.TAL_VASHOTH, new LanguagesSet(Languages.QUNLAT, Languages.TRADE_TONGUE) },
				{ Background.TEVINTER_ALTUS, new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE) },
				{ Background.TEVINTER_LAETAN, new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE) },
				{ Background.TEVINTER_SOPORATI, new LanguagesSet(Languages.TEVENE, Languages.TRADE_TONGUE) },
				{ Background.WAKING_SEA_RAIDER, new LanguagesSet(Languages.ANTIVAN, Languages.TRADE_TONGUE) }, });
	}

	@Parameter(0)
	public Background background;

	@Parameter(1)
	public LanguagesSet expectedLanguage;

	@Test
	public void testGetDefaultBackgroundForLanguages() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");		
		testCharacterSheet.setData(Fields.BASECLASS, background.getAllowedBaseClasses().get(0));
		testCharacterSheet.setData(Fields.BACKGROUND, background);
		LanguagesSet actualLanguage = testCharacterSheet.getData(Fields.LANGUAGES);
		assertEquals(expectedLanguage, actualLanguage);
	}
}
