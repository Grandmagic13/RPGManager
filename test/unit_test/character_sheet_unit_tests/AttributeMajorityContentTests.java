package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.ATTRIBUTE_MAJORITY_DATA;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getTestDataHierarchicalToFirstKey;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class AttributeMajorityContentTests {

	@Parameters(name = "Class: ''{0}'', Attribute majority field: ''{1}'' Expected Majority:''{2}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestDataHierarchicalToFirstKey(ATTRIBUTE_MAJORITY_DATA, DataKeys.BASE_CLASS, DataKeys.FIELD, DataKeys.EXPECTED_MAJORITY);
	}

	@Parameter(0)
	public BaseClasses baseClass;

	@Parameter(1)
	public Fields majority;

	@Parameter(2)
	public boolean expectedMajority;

	@Test
	public void testAttributeMajority() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, baseClass);
		boolean actual = testCharacterSheet.getData(majority);
		assertEquals(expectedMajority, actual);
	}

}