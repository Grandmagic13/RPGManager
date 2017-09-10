package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Focus;
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
	public void expectException_SetSameFocusesMoreThanAllowedWithAddALl() {
		expectExceptionWithMessage(InvalidFocusesSetException.class, "ANIMAL_HANDLING is already improved!");
		FocusesSet tempFocuses = new FocusesSet();
		ArrayList<Focuses> focusesList = new ArrayList<Focuses>();
		focusesList.add(Focuses.ANIMAL_HANDLING);
		focusesList.add(Focuses.ANIMAL_HANDLING);
		focusesList.add(Focuses.ANIMAL_HANDLING);
		tempFocuses.addAllFocuses(focusesList);
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
	public void testGetDefaultFocusValueWhenFocusAdded() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING);
		assertEquals(2, getOnlyElementOf(tempFocuses).getValue());
	}

	@Test
	public void testGetMultipleTimesAddedFocusValue() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING, Focuses.ANIMAL_HANDLING);
		assertEquals(3, getOnlyElementOf(tempFocuses).getValue());
	}

	@Test
	public void testAddFocusMultipleTimesAfterCreatedFocusesObject() {
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING);
		tempFocuses.add(Focuses.ANIMAL_HANDLING);
		assertEquals(3, getOnlyElementOf(tempFocuses).getValue());
	}

	@Test
	public void testAddAllFocuses() {
		FocusesSet expectedFocuses = new FocusesSet(Focuses.ACROBATICS, Focuses.ANIMAL_HANDLING, Focuses.ARCANE_LANCE);
		ArrayList<Focuses> focusesList = new ArrayList<Focuses>();
		focusesList.add(Focuses.ACROBATICS);
		focusesList.add(Focuses.ANIMAL_HANDLING);
		focusesList.add(Focuses.ARCANE_LANCE);
		FocusesSet actualFocuses = new FocusesSet();
		actualFocuses.addAllFocuses(focusesList);
		assertEquals(expectedFocuses, actualFocuses);
	}

	@Test
	public void testAddAllMultipleTimesFocuses() {
		FocusesSet tempFocuses = new FocusesSet();
		ArrayList<Focuses> focusesList = new ArrayList<Focuses>();
		focusesList.add(Focuses.ACROBATICS);
		focusesList.add(Focuses.ACROBATICS);
		tempFocuses.addAllFocuses(focusesList);
		assertEquals(3, getOnlyElementOf(tempFocuses).getValue());
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

	private Focus getOnlyElementOf(FocusesSet tempFocuses) {
		return tempFocuses.iterator().next();
	}
}