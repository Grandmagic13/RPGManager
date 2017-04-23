package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.VALID_BACKGROUND_DATA;
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

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class ValidBackgroundUnitTests {

	// Description:
	//
	// This class tests setting up various valid backgrounds to their base
	// classes (mage, rogue, warrior)
	//
	// Every valid class - background pairing is tested

	@Parameters(name = "{index}: Class: ''{0}'' Background: ''{1}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestDataHierarchicalToFirstKey(VALID_BACKGROUND_DATA, DataKeys.BASE_CLASS, DataKeys.BACKGROUND);
	}

	@Parameter(0)
	public BaseClasses baseClass;

	@Parameter(1)
	public Background background;

	@Test
	public void testSetBackground() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, baseClass);
		testCharacterSheet.setData(Fields.BACKGROUND, background);
		Background background = testCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(this.background, background);
	}
}
