package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.ARMOR_RATING_DEFAULT_DATA;
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

import rpg_database.character_sheet.Armors;
import unit_test.character_sheet_unit_tests.common.DataKeys;

@RunWith(Parameterized.class)
public class ArmorRatingDefaultContentTests {

	@Parameters(name = "Amror : ''{0}'' DefaultArmorRating :''{1}'' DefaultArmorPenalty :''{2}''")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestData(ARMOR_RATING_DEFAULT_DATA, DataKeys.ARMOR_TYPE, DataKeys.ARMOR_RATING, DataKeys.ARMOR_PENALTY, DataKeys.STRAIN);
	}

	@Parameter(0)
	public Armors armor;

	@Parameter(1)
	public int defaultArmorRating;

	@Parameter(2)
	public int defaultArmorPenalty;

	@Parameter(3)
	public int defaultStrain;

	@Test
	public void testGetDefaultArmorRating() {
		int actualValue = armor.getArmorRating();
		assertEquals(defaultArmorRating, actualValue);
	}

	@Test
	public void testGetDefaultAmorPenalty() {
		int actualValue = armor.getArmorPenalty();
		assertEquals(defaultArmorPenalty, actualValue);
	}

	@Test
	public void testGetDefaultStrain() {
		int actualValue = armor.getStrain();
		assertEquals(defaultStrain, actualValue);
	}
}
