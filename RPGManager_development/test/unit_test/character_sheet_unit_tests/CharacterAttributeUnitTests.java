package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

public class CharacterAttributeUnitTests {

	@Test
	public void testSetCharacterAttribute_Value1() {
		int expectedValue = 1;
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ATTRIBUTE_MAGIC_VALUE, expectedValue);
		int actualValue = characterSheet.getData(Fields.ATTRIBUTE_MAGIC_VALUE);
		assertEquals(expectedValue, actualValue);
	}

}
