package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import rpg_database.character_sheet.Fields;

public class FieldsUnitTests {

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

}
