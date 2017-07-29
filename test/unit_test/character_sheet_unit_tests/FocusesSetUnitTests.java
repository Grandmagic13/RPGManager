package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Focuses;
import rpg_database.character_sheet.Focus;
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
		FocusesSet expectedFocuses = new FocusesSet(new Focus(Focuses.ETIQUETTE));
		characterSheet.setData(Fields.FOCUSES, expectedFocuses);
		FocusesSet actualFocus = characterSheet.getData(Fields.FOCUSES);
		assertEquals(expectedFocuses, actualFocus);
	}

	@Test
	public void testSetFocusWithFocusesEnum() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet expectedFocuses = new FocusesSet(Focuses.ETIQUETTE);
		characterSheet.setData(Fields.FOCUSES, expectedFocuses);
		FocusesSet actualFocus = characterSheet.getData(Fields.FOCUSES);
		assertEquals(expectedFocuses, actualFocus);
	}

	@Test
	public void testGetSingleTimeSetIsFocusImproved() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING);
		assertEquals(false, tempFocuses.getFocus(Focuses.ANIMAL_HANDLING).isFocusImproved());
	}

	@Test
	public void testGetMultipleTimesSetIsFocusImproved() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING, Focuses.ANIMAL_HANDLING, Focuses.BARGAINING, Focuses.COURAGE);
		assertEquals(true, tempFocuses.getFocus(Focuses.ANIMAL_HANDLING).isFocusImproved());
	}

	@Test
	public void testAddFocusMultipleTimesAfterCreatedFocusesObject() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING, Focuses.BARGAINING, Focuses.COURAGE);
		tempFocuses.add(Focuses.ANIMAL_HANDLING);
		assertEquals(true, tempFocuses.getFocus(Focuses.ANIMAL_HANDLING).isFocusImproved());
	}

	@Test
	public void testGetFocusValue() {
		FocusesSet tempFocuses = new FocusesSet(new Focus(Focuses.ANIMAL_HANDLING, true), new Focus(Focuses.BARGAINING), new Focus(Focuses.COURAGE));
		assertEquals(3, tempFocuses.getFocus(Focuses.ANIMAL_HANDLING).getFocuseValue());
	}

	@Test
	public void testImproveFocus() {
		FocusesSet tempFocuses = new FocusesSet(new Focus(Focuses.ANIMAL_HANDLING), new Focus(Focuses.BARGAINING), new Focus(Focuses.COURAGE));
		tempFocuses.ImproveFocus(Focuses.ANIMAL_HANDLING);
		assertEquals(3, tempFocuses.getFocus(Focuses.ANIMAL_HANDLING).getFocuseValue());
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}