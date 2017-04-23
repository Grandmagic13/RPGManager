package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.SPECIALIZATION_ATTRIBUTE_FILTER_ATTRIBUTE_VALUES_CONTENT_DATA;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getAttributesRequirementsData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;

@RunWith(Parameterized.class)
public class SpecializationAttributeFilter_AttributeValuesContentTests {
	// Description:
	//
	// This class tests all the attribute requirement content for each
	// specialization

	@Parameters(name = "{index}: ''{0}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getAttributesRequirementsData(SPECIALIZATION_ATTRIBUTE_FILTER_ATTRIBUTE_VALUES_CONTENT_DATA);
	}

	@Parameter(0)
	public SpecializationClasses specializationClass;

	@Parameter(1)
	public HashMap<Fields, Integer> attributeValues;

	@Test
	public void testGetSpecializationClassRequiredAttributes() {
		HashMap<Fields, Integer> expectedRequiredAttributeValues = attributeValues;
		assertEquals(expectedRequiredAttributeValues, specializationClass.getRequiredAttributeValues());
	}
}