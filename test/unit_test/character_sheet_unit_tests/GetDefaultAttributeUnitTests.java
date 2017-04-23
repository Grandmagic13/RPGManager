package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.DEFAULT_ATTRIBUTE_DATA;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getTestData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class GetDefaultAttributeUnitTests {

	@Parameters(name = "Attribute fields: ''{0}'', ''{2}'' Expected Majority:''{1}'' Expected Value: 0")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestData(DEFAULT_ATTRIBUTE_DATA, DataKeys.MAJORITY_FIELD, DataKeys.EXPECTED_MAJORITY, DataKeys.VALUE_FIELD);
	}

	@Parameter(0)
	public Fields majority;

	@Parameter(1)
	public boolean expectedMajority;

	@Parameter(2)
	public Fields value;

	@Test
	public void testGetDefaultAttributeMajority() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		boolean actual = testCharacterSheet.getData(majority);
		assertEquals(expectedMajority, actual);
	}

	@Test
	public void testGetDefaultAttributeValue() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		int actual = testCharacterSheet.getData(value);
		assertEquals(0, actual);
	}
}