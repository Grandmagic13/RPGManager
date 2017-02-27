package unit_test.character_sheet_unit_tests;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CharacterSheetClassAndSpecializationTests {

}

// @RunWith(Parameterized.class)
// public class CharacterSheetBackgroundUnitTests {

// TODO test setting with any permutations for CharacterClass
// TODO test setting BaseClass and SpecializationClass for CharacterClass
// instances!

// @Parameters(name = "{index}: ''{0}''")
// public static Background[] data() {
// return new Background[] { Background.ANDER_SURVIVOR, Background.APOSTATE,
// Background.ANTIVAN_WAYFARER,
// Background.AVVAR, Background.CHASIND_WILDER, Background.CIRCLE_MAGE,
// Background.CITY_ELF,
// Background.DALISH_ELF, Background.DWARF_DUSTER,
// Background.ESCAPED_ELVEN_SLAVE,
// Background.FERELDAN_CRAFTSMEN, Background.FERELDAN_FREEMAN,
// Background.FERELDAN_NOBLE,
// Background.FREE_MARCHER, Background.HIGH_BORN_DWARF,
// Background.LOW_BORN_DWARF,
// Background.NEVARRAN_ADVENTURER, Background.ORLESIAN_COMMONER,
// Background.ORLESIAN_EXILE,
// Background.ORLESIAN_NOBLE, Background.ORLESIAN_STUDENT,
// Background.QUNARI_BERESAAD,
// Background.RIVAINI_MERCHANT, Background.SEHERON_CONVERT,
// Background.SURFACE_DWARF,
// Background.TAL_VASHOTH, Background.TEVINTER_ALTUS,
// Background.TEVINTER_LAETAN,
// Background.TEVINTER_SOPORATI, Background.WAKING_SEA_RAIDER };
// }
//
// @Parameter()
// public Background background;
//
// @Test
// public void testSetBackground() {
// CharacterSheet testCharacterSheet = new
// CharacterSheet("TestCharacterSheet");
// testCharacterSheet.setData(Fields.BACKGROUND, background);
// Background background = (Background)
// testCharacterSheet.getData(Fields.BACKGROUND);
// assertEquals(background, background);
// }
// }