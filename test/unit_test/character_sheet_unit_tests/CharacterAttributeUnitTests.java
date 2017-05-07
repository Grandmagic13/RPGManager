package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterAttribute;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

public class CharacterAttributeUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_setSelfValueByFieldWrongObjectType() {
		expectExceptionWithMessage(InvalidParameterException.class, "Unknown allowed field class: 'class java.lang.Integer'");
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.<CharacterAttribute>getData(Fields.STRENGTH).setSelfValueByField(Fields.STRENGTH_VALUE, "MALFORMED INPUT");
	}

	@Test
	public void expectException_getStoredValueByFieldWrongField() {
		expectExceptionWithMessage(InvalidParameterException.class, "Unknown allowed field class: 'class java.lang.String'");
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.<CharacterAttribute>getData(Fields.STRENGTH).getStoredValueByField(Fields.APPEARANCE);
	}

	// Functional

	@Test
	public void testgetImplementingClass() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		assertEquals(rpg_database.character_sheet.CharacterAttribute.class, characterSheet.<CharacterAttribute>getData(Fields.STRENGTH)
				.getImplementingClass());
	}

	@Test
	public void testgetDataTypeClass() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		assertEquals(Object.class, characterSheet.<CharacterAttribute>getData(Fields.STRENGTH).getDataTypeClass());
	}

	@Test
	public void testSetCharacterAttribute_Value1() {
		int expectedValue = 1;
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.MAGIC_VALUE, expectedValue);
		int actualValue = characterSheet.getData(Fields.MAGIC_VALUE);
		assertEquals(expectedValue, actualValue);
	}

	// private methods
	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
