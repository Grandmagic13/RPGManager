package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Languages;
import static org.junit.Assert.assertNotEquals;

public class LanguagesUnitTests {

		@Test
		public void testSetLanguagesAntivanWayfarer(){
			CharacterSheet characterSheet = new CharacterSheet("characterSheet");
			HashSet<Languages> expectedLanguage = new HashSet<Languages>();
			expectedLanguage.add(Languages.ANTIVAN);
			expectedLanguage.add(Languages.TRADE_TONGUE);
			characterSheet.setData(Fields.LANGUAGES, expectedLanguage);
			HashSet<Languages> actualLanguage = characterSheet.getData(Fields.LANGUAGES);
			assertEquals(expectedLanguage, actualLanguage);
		}
		
		@Test
		public void testSetLanguagesAntivanWayfarerExpectedFail(){
			CharacterSheet characterSheet = new CharacterSheet("characterSheet");
			characterSheet.setData(Fields.BACKGROUND, Background.CITY_ELF);
			HashSet<Languages> expectedLanguage = new HashSet<Languages>();
			expectedLanguage.add(Languages.TRADE_TONGUE);
			expectedLanguage.add(Languages.ANCIENT_TEVENE);
			HashSet<Languages> actualLanguage = characterSheet.getData(Fields.LANGUAGES);
			assertNotEquals(expectedLanguage, actualLanguage);
		}

		
}
