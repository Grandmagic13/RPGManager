package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
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

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Race;
import unit_test.character_sheet_unit_tests.common.DataKeys;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.DEFAULT_RACE_DATA;

@RunWith(Parameterized.class)
public class GetDefaultRaceForEachBackgroundUnitTests {

	@Parameters(name = "Background : ''{0}'', Expected Race :''{1}'' ")
	public static Collection<Object[]> data() throws JSONException, FileNotFoundException, IOException {
		return getTestData(DEFAULT_RACE_DATA, DataKeys.BACKGROUND, DataKeys.RACE);
	}

	@Parameter(0)
	public Background background;

	@Parameter(1)
	public Race expectedRace;

	@Test
	public void testGetDefaultBackgroundForLanguages() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, getFirstValidBaseClassByBackground());
		testCharacterSheet.setData(Fields.BACKGROUND, background);
		Race actualRace = testCharacterSheet.getData(Fields.RACE);
		assertEquals(expectedRace, actualRace);
	}

	private BaseClasses getFirstValidBaseClassByBackground() {
		return background.getAllowedBaseClasses().iterator().next();
	}

}
