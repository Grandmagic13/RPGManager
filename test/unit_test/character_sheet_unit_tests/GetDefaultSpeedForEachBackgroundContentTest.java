package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getTestData;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.DEFAULT_SPEED_DATA;

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
import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import unit_test.character_sheet_unit_tests.common.DataKeys;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.getFirstValidBaseClassByBackground;;

@RunWith(Parameterized.class)
public class GetDefaultSpeedForEachBackgroundContentTest {

	@Parameters(name = "Background : ''{0}'', Expected Speed :''{1}'' ")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestData(DEFAULT_SPEED_DATA, DataKeys.BACKGROUND, DataKeys.SPEED);
	}

	@Parameter(0)
	public Background background;

	@Parameter(1)
	public int expectedSpeed;

	@Test
	public void testGetDefaultBackgroundForSpeed() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, getFirstValidBaseClassByBackground(background));
		testCharacterSheet.setData(Fields.BACKGROUND, background);
		testCharacterSheet.setData(Fields.ARMOR_TYPE, Armors.LIGHT_LEATHER);
		int actualSpeed = testCharacterSheet.getData(Fields.SPEED);
		assertEquals(expectedSpeed, actualSpeed);
	}
}