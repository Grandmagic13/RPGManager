package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Focuses;
import rpg_database.character_sheet.FocusesLogic;
import rpg_database.character_sheet.FocusesSet;

public class FocusesSetUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetFocusesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Focuses");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.FOCUSES, malformedInput);
	}

	// functional tests
	@Test
	public void testSetFocusWithFocusesSet() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet expectedFocuses = new FocusesSet(new FocusesLogic(Focuses.ETIQUETTE));
		characterSheet.setData(Fields.FOCUSES, expectedFocuses);
		FocusesSet actualFocus = characterSheet.getData(Fields.FOCUSES);
		assertEquals(expectedFocuses, actualFocus);
	}

	@Test
	public void testSetMoreDifferentFocus() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet expectedFocuses = new FocusesSet(new FocusesLogic(Focuses.ANIMAL_HANDLING), new FocusesLogic(Focuses.ARCANE_LANCE),
				new FocusesLogic(Focuses.BRAWLING));
		characterSheet.setData(Fields.FOCUSES, expectedFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUSES);
		assertEquals(expectedFocuses, actualFocuses);
	}

	@Test
	public void testSetFocusMultipleTimes() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet tempFocuses = new FocusesSet(new FocusesLogic(Focuses.ANIMAL_HANDLING), new FocusesLogic(Focuses.ANIMAL_HANDLING));
		characterSheet.setData(Fields.FOCUSES, tempFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUSES);
		assertEquals(3, actualFocuses.getRightFocusFromSet(Focuses.ANIMAL_HANDLING).getFocusValue());
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}