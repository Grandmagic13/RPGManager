package unit_test.character_sheet_unit_tests;

import static unit_test.character_sheet_unit_tests.common.CommonMethods.SPECIALIZATION_ATTRIBUTE_FILTER_INVALID_BACKGROUND_DATA;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getTestDataHierarchicalToParentKeys;

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
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;
import rpg_database.character_sheet.exceptions.InvalidBackgroundException;
import unit_test.character_sheet_unit_tests.common.CommonMethods;
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class SpecializationBackgroundFilter_InvalidBackgroundsTests {

	// Description:
	//
	// This class tests setting up various invalid specializations to their
	// respective backgrounds
	//
	// Every invalid background - specialization pairing is tested

	@Parameters(name = "{index}: Class: ''{0}'' Specialization: ''{1}'' Background: ''{2}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestDataHierarchicalToParentKeys(SPECIALIZATION_ATTRIBUTE_FILTER_INVALID_BACKGROUND_DATA, 2, DataKeys.BASE_CLASS,
				DataKeys.SPECIALIZATION_CLASS, DataKeys.BACKGROUND);
	}

	@Parameter(0)
	public BaseClasses baseClass;

	@Parameter(1)
	public SpecializationClasses specializationClass;

	@Parameter(2)
	public Background background;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSetInvalidSpecializationClassForBackground() {
		SpecializationClassesSet specializationClassSingleton = new SpecializationClassesSet(specializationClass);
		thrown.expect(InvalidBackgroundException.class);
		thrown.expectMessage(String.format("Can not take %s specialization with the %s background!", specializationClassSingleton.toString(),
				background.toString()));
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.LEVEL, CommonMethods.LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION);
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.BACKGROUND, background);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClassSingleton);
	}
}