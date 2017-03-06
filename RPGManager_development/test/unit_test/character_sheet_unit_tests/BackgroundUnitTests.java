package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;

import static unit_test.character_sheet_unit_tests.common.CommonMethods.*;

public class BackgroundUnitTests {

	// test methods
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetCharacterBackgroundMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Background");
		final String malformedInput = "MALFORMED INPUT";
		new CharacterSheet("CharacterSheet").setData(Fields.BACKGROUND, malformedInput);
	}

	@Test
	public void expectException_SetInvalidBackgroundApostateForClassWarrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Apostate is not a Warrior background!");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.WARRIOR, SpecializationClasses.BERSERKER);
		characterSheet.setData(Fields.BACKGROUND, Background.APOSTATE);
	}

	@Test
	public void expectException_SetInvalidBackgroundFereldanFreemanForClassMage() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Fereldan Freeman is not a Mage background!");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, SpecializationClasses.KEEPER);
		characterSheet.setData(Fields.BACKGROUND, Background.FERELDAN_FREEMAN);
	}

	@Test
	public void testSetBackgroundChasindWilder() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BACKGROUND, Background.CHASIND_WILDER);
		Background background = characterSheet.getData(Fields.BACKGROUND);
		assertEquals(Background.CHASIND_WILDER, background);
	}

	// private methods

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
