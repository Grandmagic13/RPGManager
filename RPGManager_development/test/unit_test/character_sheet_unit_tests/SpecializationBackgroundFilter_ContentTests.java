package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.SPECIALIZATION_ATTRIBUTE_FILTER_CONTENT_DATA;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getTestData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.SpecializationClasses;
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class SpecializationBackgroundFilter_ContentTests {

	// Description:
	//
	// This class tests background restriction contents

	@Parameters(name = "{index}: Specialization: ''{0}'' Restricted: ''{2}'' Expected backgrounds: ''{1}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestData(SPECIALIZATION_ATTRIBUTE_FILTER_CONTENT_DATA, DataKeys.SPECIALIZATION_CLASS, DataKeys.BACKGROUND_ARRAY,
				DataKeys.EXPECTED_RESTRICTION);
	}

	@Parameter(0)
	public SpecializationClasses specializationClass;

	@Parameter(1)
	public ArrayList<Background> backgrounds;

	@Parameter(2)
	public boolean expectedRestriction;

	@Test
	public void testCheckRestrictedBackgroundsContent() {
		HashSet<Background> expectedBackgrounds = new HashSet<>(backgrounds);
		assertEquals(expectedBackgrounds, specializationClass.getRestrictedBackgrounds());
	}

	@Test
	public void testCheckIfSpecializationClassIsRestricted() {
		assertEquals(expectedRestriction, specializationClass.isBackgroundRestricted());
	}
}