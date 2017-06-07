package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static rpg_database.character_sheet.Focuses.JUMPING;

import org.junit.Test;

import rpg_database.character_sheet.Focuses;
import rpg_database.character_sheet.FocusesLogic;

public class FocusesLogicUnitTests {

	@Test
	public void testGetFocus() {
		FocusesLogic expected = new FocusesLogic(Focuses.COURAGE);
		assertEquals(Focuses.COURAGE, expected.getFocus());
	}

	@Test
	public void testGetFocusValue() {
		FocusesLogic expected = new FocusesLogic(Focuses.JUMPING);
		assertEquals(2, expected.getFocusValue());
	}

	@Test
	public void testSetFocusImprovement() {
		FocusesLogic expected = new FocusesLogic(Focuses.JUMPING);
		expected.setFocusImprovement();
		assertEquals(3, expected.getFocusValue());
	}
}
