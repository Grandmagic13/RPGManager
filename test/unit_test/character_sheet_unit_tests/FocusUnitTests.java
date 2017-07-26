package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static rpg_database.character_sheet.Focuses.JUMPING;

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
		assertEquals(false, expected.getIsFocusImproved());
	}

	@Test
	public void testSetFocusImprovement() {
		Focus expected = new Focus(Focuses.JUMPING);
		expected.MakeFocusImproved();
		assertEquals(true, expected.getIsFocusImproved());
	}
}
