package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Race;

public class RaceUnitTests {

	@Test
	public void testGetRaceName() {
		assertEquals("Dwarf", Race.DWARF.toString());
	}

	@Test
	public void testSetBackgroundGetRace() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		Race expectedRace = Race.ELF;
		characterSheet.setData(Fields.BACKGROUND, Background.CITY_ELF);
		Race actualRace = characterSheet.getData(Fields.RACE);
		assertEquals(expectedRace, actualRace);
	}
}
