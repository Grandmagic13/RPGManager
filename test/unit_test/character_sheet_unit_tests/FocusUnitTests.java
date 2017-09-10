package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Focuses;
import rpg_database.character_sheet.Focus;

public class FocusUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectedException_TestOverridedHashCode() {
		expectExceptionWithMessage(AssertionError.class, "rpg_database.character_sheet.Focus<rpg_database.character_sheet.Focus@5d20e46>");
		Focus focus1 = new Focus(Focuses.ACROBATICS, true);
		Focus focus2 = new Focus(Focuses.ACROBATICS);
		assertEquals(focus1, focus2);
	}

	@Test
	public void testGetFocus() {
		Focus expected = new Focus(Focuses.COURAGE);
		assertEquals(Focuses.COURAGE, expected.getFocus());
	}

	@Test
	public void testGetFocusValue() {
		Focus expected = new Focus(Focuses.JUMPING);
		assertEquals(2, expected.getValue());
	}

	@Test
	public void testGetImprovedFocusValue() {
		Focus expected = new Focus(Focuses.COURAGE, true);
		assertEquals(3, expected.getValue());
	}

	@Test
	public void testGetDefaultFocusImprovment() {
		Focus expected = new Focus(Focuses.ACROBATICS);
		assertEquals(false, expected.isFocusImproved());
	}

	@Test
	public void testSetFocusImprovement() {
		Focus expected = new Focus(Focuses.JUMPING);
		expected.makeFocusImproved();
		assertEquals(3, expected.getValue());
		assertEquals(true, expected.isFocusImproved());
	}

	@Test
	public void testOverridedHashCode() {
		Focus focus1 = new Focus(Focuses.ACROBATICS, true);
		Focus focus2 = new Focus(Focuses.ACROBATICS, true);
		assertEquals(focus1, focus2);
	}

	private void expectExceptionWithMessage(Class<AssertionError> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
