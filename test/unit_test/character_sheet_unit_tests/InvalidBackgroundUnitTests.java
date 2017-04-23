package unit_test.character_sheet_unit_tests;

import static unit_test.character_sheet_unit_tests.common.CommonMethods.getTestDataHierarchicalToFirstKey;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.INVALID_BACKGROUND_DATA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class InvalidBackgroundUnitTests {

	// Description:
	//
	// This class tests setting up various invalid backgrounds to their base
	// classes (mage, rogue, warrior)
	//
	// Every invalid class - background pairing is tested

	@Parameters(name = "{index}: Class: ''{0}'' Background: ''{1}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestDataHierarchicalToFirstKey(INVALID_BACKGROUND_DATA, DataKeys.BASE_CLASS, DataKeys.BACKGROUND);
	}

	@Parameter(0)
	public BaseClasses baseClass;

	@Parameter(1)
	public Background background;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSetInvalidBackground() {
		thrown.expect(InvalidCharacterClassException.class);
		thrown.expectMessage(String.format("%s is not a %s background!", background.toString(), baseClass.toString()));
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, baseClass);
		testCharacterSheet.setData(Fields.BACKGROUND, background);
	}
}