package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Focuses;
import rpg_database.character_sheet.FocusesSet;
import rpg_database.character_sheet.exceptions.InvalidFocusesSetException;

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

	@Test
	public void expectException_SetSameFocusesMoreThanAllowed() {
		expectExceptionWithMessage(InvalidFocusesSetException.class, "ANIMAL_HANDLING is already improved!");
		FocusesSet focusesSet = new FocusesSet(Focuses.ANIMAL_HANDLING, Focuses.ANIMAL_HANDLING);
		focusesSet.add(Focuses.ANIMAL_HANDLING);
	}

	@Test
	public void expectException_GetFocusesWithWrongParameter() {
		expectExceptionWithMessage(InvalidFocusesSetException.class, "BRAWLING is not in the list!");
		FocusesSet focusesSet = new FocusesSet();
		focusesSet.getFocus(Focuses.BRAWLING);
	}

	// functional tests

	@Test
	public void testSetFocusWithEtiquetteEnum() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet expectedFocuses = new FocusesSet(Focuses.ETIQUETTE);
		characterSheet.setData(Fields.FOCUSES, expectedFocuses);
		FocusesSet actualFocus = characterSheet.getData(Fields.FOCUSES);
		assertEquals(expectedFocuses, actualFocus);
	}

	@Test
	public void testGetSingleTimeSetIsFocusImproved() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING);
		assertEquals(2, tempFocuses.getFocus(Focuses.ANIMAL_HANDLING).getValue());
	}

	@Test
	public void testGetMultipleTimesSetIsFocusImproved() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING, Focuses.ANIMAL_HANDLING);
		assertEquals(3, tempFocuses.getFocus(Focuses.ANIMAL_HANDLING).getValue());
	}

	@Test
	public void testAddFocusMultipleTimesAfterCreatedFocusesObject() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING);
		tempFocuses.add(Focuses.ANIMAL_HANDLING);
		assertEquals(3, tempFocuses.getFocus(Focuses.ANIMAL_HANDLING).getValue());
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}