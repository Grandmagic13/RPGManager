package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.LanguagesSet;
import unit_test.character_sheet_unit_tests.resources.LanguagesUnitTestData;;

@RunWith(Parameterized.class)
public class GetDefaultLanguagesForEachBackgroundUnitTests {

	@Parameters(name = "Background : ''{0}'', Expected Language :''{1}'' ")
	public static Collection<Object[]> data() {
		ArrayList<Object[]> parameters = new ArrayList<>();
		HashMap<Background, LanguagesSet> initializeDefaultLanguagesMap = LanguagesUnitTestData.initializeRequiredLanguages();
		for (Background background : initializeDefaultLanguagesMap.keySet()) {
			parameters.add(new Object[] { background, initializeDefaultLanguagesMap.get(background) });
		}
		return parameters;
	}

	@Parameter(0)
	public Background background;

	@Parameter(1)
	public LanguagesSet expectedLanguage;

	@Test
	public void testGetDefaultBackgroundForLanguages() {
		CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		testCharacterSheet.setData(Fields.BASECLASS, getFirstValidBaseClassByBackground());
		testCharacterSheet.setData(Fields.BACKGROUND, background);
		LanguagesSet actualLanguage = testCharacterSheet.getData(Fields.LANGUAGES);
		assertEquals(expectedLanguage, actualLanguage);
	}

	private BaseClasses getFirstValidBaseClassByBackground() {
		return background.getAllowedBaseClasses().get(0);
	}

}