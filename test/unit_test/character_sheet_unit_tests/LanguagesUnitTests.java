package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Languages;
import rpg_database.character_sheet.LanguagesSet;

public class LanguagesUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetLanguagesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Languages");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.LANGUAGES, malformedInput);
	}

	@Test
	public void expectException_SetLanguagesInvalidParameter() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class rpg_database.character_sheet.Background value is not an instance of class rpg_database.character_sheet.Languages");
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.LANGUAGES, Background.CITY_ELF);
	}

	@Test
	public void testSetLanguagesAntivanWayfarer() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		LanguagesSet expectedLanguage = new LanguagesSet(Languages.ANTIVAN, Languages.TRADE_TONGUE);
		characterSheet.setData(Fields.BACKGROUND, Background.ANTIVAN_WAYFARER);
		LanguagesSet actualLanguage = characterSheet.getData(Fields.LANGUAGES);
		assertEquals(expectedLanguage, actualLanguage);
	}

	@Test
	public void testSetLanguagesAntivanWayfarerAfterSetCityElf() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		LanguagesSet expectedLanguages = new LanguagesSet(Languages.TRADE_TONGUE);
		LanguagesSet temporaryLanguages = new LanguagesSet(Languages.TRADE_TONGUE, Languages.ANCIENT_TEVENE);
		characterSheet.setData(Fields.LANGUAGES, temporaryLanguages);
		characterSheet.setData(Fields.BACKGROUND, Background.CITY_ELF);
		LanguagesSet actualLanguage = characterSheet.getData(Fields.LANGUAGES);
		assertEquals(expectedLanguages, actualLanguage);
	}

	@Test
	public void testSetLanguagesAntivanWayfarerAfterSetMoreLanguages() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		LanguagesSet expectedLanguages = new LanguagesSet(Languages.TRADE_TONGUE, Languages.ANTIVAN, Languages.ELVEN, Languages.ANCIENT_TEVENE);
		characterSheet.setData(Fields.BACKGROUND, Background.ANTIVAN_WAYFARER);
		LanguagesSet temporaryLanguages = new LanguagesSet(Languages.TRADE_TONGUE, Languages.ANTIVAN, Languages.ELVEN, Languages.ANCIENT_TEVENE);
		characterSheet.setData(Fields.LANGUAGES, temporaryLanguages);
		LanguagesSet actualLanguage = characterSheet.getData(Fields.LANGUAGES);
		assertEquals(expectedLanguages, actualLanguage);
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
