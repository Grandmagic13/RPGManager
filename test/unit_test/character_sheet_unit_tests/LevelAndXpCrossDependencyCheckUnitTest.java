package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.exceptions.CrossDependencyException;

public class LevelAndXpCrossDependencyCheckUnitTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectedException_TestSetLevelWithNotEnoughAmpuntOfXp() {
		expectExceptionWithMessage(CrossDependencyException.class, "You don't have enough xp for this level!");
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.XP, 4500);
		characterSheet.setData(Fields.LEVEL, 15);
	}

	@Test
	public void expectedException_TestSetLesserXpThanTheActualLevel() {
		expectExceptionWithMessage(CrossDependencyException.class, "Your level is higher than the given xp!");
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.LEVEL, 15);
		characterSheet.setData(Fields.XP, 4500);
	}

	@Test
	public void expectedException_TestSetLevelWithOverLevelGap() {
		expectExceptionWithMessage(CrossDependencyException.class, "The level gap is: 22");
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.LEVEL, 23);
	}

	@Test
	public void expectedException_TestSetXpWithOverXpGap() {
		expectExceptionWithMessage(CrossDependencyException.class, "The xp gap is: 70000");
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.XP, 71000);
	}

	@Test
	public void expectedException_TestSetXpBackTo16000From44000() {
		expectExceptionWithMessage(CrossDependencyException.class, "Your level is higher than the given xp!");
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.XP, 44000);
		characterSheet.setData(Fields.XP, 16000);
	}

	// Functional tests

	@Test
	public void testSetXpObservingCorrectLevel() {
		int expectedLevel = 9;
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.XP, 20000);
		int actualLevel = characterSheet.getData(Fields.LEVEL);
		assertEquals(expectedLevel, actualLevel);
	}

	@Test
	public void testSetLevelObservingCorrectXp() {
		int expectedXp = 44000;
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.LEVEL, 16);
		int actualXp = characterSheet.getData(Fields.XP);
		assertEquals(expectedXp, actualXp);
	}

	@Test
	public void testSetLevelBackTo8From16() {
		int expectedXp = 16000;
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.LEVEL, 16);
		characterSheet.setData(Fields.LEVEL, 8);
		int actualXp = characterSheet.getData(Fields.XP);
		assertEquals(expectedXp, actualXp);
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
