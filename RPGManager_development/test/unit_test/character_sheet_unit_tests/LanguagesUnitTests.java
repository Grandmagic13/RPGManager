package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Languages;
import rpg_database.character_sheet.exceptions.CoinOutOfBoundsException;
import static org.junit.Assert.assertNotEquals;

public class LanguagesUnitTests {
	// fields
		//CharacterSheet testCharacterSheet;

		// test setup
		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@Before
		public void clearCharacterSheet() {
			//testCharacterSheet = new CharacterSheet("TestCharacterSheet");
		}
		
		// test methods
		
		
		@Test
		public void testSetLanguagesAntivanWayfarer(){
			CharacterSheet characterSheet = new CharacterSheet("characterSheet");
			HashSet<Languages> expectedLanguage = new HashSet<Languages>();
			expectedLanguage.add(Languages.ANTIVAN);
			expectedLanguage.add(Languages.TRADE_TONGUE);
			characterSheet.setData(Fields.LANGUAGES, expectedLanguage);
			HashSet<Languages> language = characterSheet.getData(Fields.LANGUAGES);
			assertEquals(expectedLanguage, language);
		}
		
		@Test
		public void testSetLanguagesAntivanWayfarerExpectedFail(){
			CharacterSheet characterSheet = new CharacterSheet("characterSheet");
			characterSheet.setData(Fields.BACKGROUND, Background.CITY_ELF);
			HashSet<Languages> expectedLanguage = new HashSet<Languages>();
			expectedLanguage.add(Languages.TRADE_TONGUE);
			expectedLanguage.add(Languages.ANCIENT_TEVENE);
			HashSet<Languages> language = characterSheet.getData(Fields.LANGUAGES);
			assertNotEquals(expectedLanguage, language);
		}

		
}
