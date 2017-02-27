package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

@RunWith(Parameterized.class)
public class CharacterSheetBackgroundUnitTests {

	@Parameters(name = "{index}: ''{0}''")
	public static Background[] data() {
		return new Background[] { Background.ANDER_SURVIVOR, Background.APOSTATE, Background.ANTIVAN_WAYFARER,
				Background.AVVAR, Background.CHASIND_WILDER, Background.CIRCLE_MAGE, Background.CITY_ELF,
				Background.DALISH_ELF, Background.DWARF_DUSTER, Background.ESCAPED_ELVEN_SLAVE,
				Background.FERELDAN_CRAFTSMEN, Background.FERELDAN_FREEMAN, Background.FERELDAN_NOBLE,
				Background.FREE_MARCHER, Background.HIGH_BORN_DWARF, Background.LOW_BORN_DWARF,
				Background.NEVARRAN_ADVENTURER, Background.ORLESIAN_COMMONER, Background.ORLESIAN_EXILE,
				Background.ORLESIAN_NOBLE, Background.ORLESIAN_STUDENT, Background.QUNARI_BERESAAD,
				Background.RIVAINI_MERCHANT, Background.SEHERON_CONVERT, Background.SURFACE_DWARF,
				Background.TAL_VASHOTH, Background.TEVINTER_ALTUS, Background.TEVINTER_LAETAN,
				Background.TEVINTER_SOPORATI, Background.WAKING_SEA_RAIDER };
	}

	@Parameter()
	public Background background;

	@Test
	public void testSetBackground() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BACKGROUND, background);
		Background background = testCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(background, background);
	}
}