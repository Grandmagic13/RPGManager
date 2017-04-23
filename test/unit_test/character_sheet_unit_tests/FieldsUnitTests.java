package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Fields;

public class FieldsUnitTests {

	// test set-up
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// test methods
	// exception tests
	@Test
	public void expectException_getContainingFieldNull() {
		expectExceptionWithMessage(NullPointerException.class, "XP is not contained by another field!");
		Fields.XP.getContainingField();
	}

	// functional tests
	@Test
	public void testGetAllowedFieldClass() {
		assertEquals(Integer.class, Fields.GOLD_COIN.getAllowedClass());
	}

	@Test
	public void testGetContainingField() {
		assertEquals(Fields.MONEY, Fields.GOLD_COIN.getContainingField());
	}

	@Test
	public void testIsContainedFalse() {
		assertFalse(Fields.MONEY.isContainted());
	}

	@Test
	public void testIsContainedTrue() {
		assertTrue(Fields.GOLD_COIN.isContainted());
	}

	// private methods
	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
