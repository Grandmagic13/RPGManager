package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import rpg_database.character_sheet.Focuses;
import rpg_database.character_sheet.Focus;

public class FocusUnitTests {

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
	public void testFocusEquals_NeitherEquals() {
		Focus focus1 = new Focus(Focuses.ACROBATICS);
		Focus focus2 = new Focus(Focuses.ANIMAL_HANDLING, true);
		Assert.assertFalse(focus1.equals(focus2));
	}

	@Test
	public void testFocusEquals_BothEquals() {
		Focus focus1 = new Focus(Focuses.ACROBATICS, true);
		Focus focus2 = new Focus(Focuses.ACROBATICS, true);
		assertEquals(focus1, focus2);
	}

	@Test
	public void testFocusEquals_NameEqualsValueNotEquals() {
		Focus focus1 = new Focus(Focuses.ACROBATICS, true);
		Focus focus2 = new Focus(Focuses.ACROBATICS);
		Assert.assertFalse(focus1.equals(focus2));
	}

	@Test
	public void testFocusEquals_NameNotEqualsValueEquals() {
		Focus focus1 = new Focus(Focuses.ACROBATICS, true);
		Focus focus2 = new Focus(Focuses.BOWS, true);
		Assert.assertFalse(focus1.equals(focus2));
	}
}
