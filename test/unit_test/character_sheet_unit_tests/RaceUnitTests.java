package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Race;
import rpg_database.character_sheet.exceptions.CharacterSheetException;

public class RaceUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_TestBackgroundHasMoreRaceOptionChoiceInvalidRace() {
		expectExceptionWithMessage(CharacterSheetException.class, "Seheron Convert does not offer Qunari race for choicing!");
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		characterSheet.setData(Fields.BACKGROUND, Background.SEHERON_CONVERT);
		characterSheet.setData(Fields.RACE, Race.QUNARI);
	}

	// Functional tests

	@Test
	public void testGetRaceName() {
		assertEquals("Dwarf", Race.DWARF.toString());
	}

	@Test
	public void testBaseSpeedHuman() {
		assertEquals(10, Race.HUMAN.getBaseSpeed());
	}

	@Test
	public void testBaseSpeedElf() {
		assertEquals(12, Race.ELF.getBaseSpeed());
	}

	@Test
	public void testBaseSpeedDwarf() {
		assertEquals(8, Race.DWARF.getBaseSpeed());
	}

	@Test
	public void testBaseSpeedQunari() {
		assertEquals(10, Race.QUNARI.getBaseSpeed());
	}

	// TODO Add test to check the multiple select arrays

	@Test
	public void testBackgroundHasMoreRaceOptionChoiceRace() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		characterSheet.setData(Fields.BACKGROUND, Background.SEHERON_CONVERT);
		characterSheet.setData(Fields.RACE, Race.HUMAN);
		Race actualRace = characterSheet.getData(Fields.RACE);
		assertEquals(Race.HUMAN, actualRace);
	}

	// private methods

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
