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
	public void testSetMoreDifferentFocus() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet expectedFocuses = new FocusesSet(new Focus(Focuses.ANIMAL_HANDLING), new Focus(Focuses.ARCANE_LANCE), new Focus(Focuses.BRAWLING));
		characterSheet.setData(Fields.FOCUSES, expectedFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUSES);
		assertEquals(expectedFocuses, actualFocuses);
	}

	@Test
	public void testGetSingleTimeSetIsFocusImproved() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet tempFocuses = new FocusesSet(new Focus(Focuses.ANIMAL_HANDLING));
		characterSheet.setData(Fields.FOCUSES, tempFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUSES);
		assertEquals(false, actualFocuses.getRightFocusFromSet(Focuses.ANIMAL_HANDLING).isFocusImproved());
	}

	@Test
	public void testGetMultipleTimesSetIsFocusImproved() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet tempFocuses = new FocusesSet(new Focus(Focuses.ANIMAL_HANDLING, true), new Focus(Focuses.BARGAINING), new Focus(Focuses.COURAGE));
		characterSheet.setData(Fields.FOCUSES, tempFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUSES);
		assertEquals(true, actualFocuses.getRightFocusFromSet(Focuses.ANIMAL_HANDLING).isFocusImproved());
	}

	@Test
	public void testSetIsFocusImprovedAfterCreatedFocusesObject() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet tempFocuses = new FocusesSet(new Focus(Focuses.ANIMAL_HANDLING), new Focus(Focuses.BARGAINING), new Focus(Focuses.COURAGE));
		characterSheet.setData(Fields.FOCUSES, tempFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUSES);
		actualFocuses.ImproveFocus(Focuses.ANIMAL_HANDLING);
		assertEquals(true, actualFocuses.getRightFocusFromSet(Focuses.ANIMAL_HANDLING).isFocusImproved());
	}

	@Test
	public void testGetFocusImprovementValue() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet tempFocuses = new FocusesSet(new Focus(Focuses.ANIMAL_HANDLING, true), new Focus(Focuses.BARGAINING), new Focus(Focuses.COURAGE));
		characterSheet.setData(Fields.FOCUSES, tempFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUSES);
		assertEquals(3, actualFocuses.getRightFocusFromSet(Focuses.ANIMAL_HANDLING).getFocuseImprovementValue());
	}

	@Test
	public void testGetFocusImprovementValueWithImproveFocus() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet tempFocuses = new FocusesSet(new Focus(Focuses.ANIMAL_HANDLING), new Focus(Focuses.BARGAINING), new Focus(Focuses.COURAGE));
		characterSheet.setData(Fields.FOCUSES, tempFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUSES);
		actualFocuses.ImproveFocus(Focuses.ANIMAL_HANDLING);
		assertEquals(3, actualFocuses.getRightFocusFromSet(Focuses.ANIMAL_HANDLING).getFocuseImprovementValue());
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}