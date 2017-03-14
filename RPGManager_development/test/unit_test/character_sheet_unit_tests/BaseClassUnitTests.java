package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;

import static unit_test.character_sheet_unit_tests.common.CommonMethods.*;

import java.security.InvalidParameterException;

public class BaseClassUnitTests {

	// test methods
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetBaseClassesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.BaseClasses");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, malformedInput);
	}

	@Test
	public void testSetCharacterBaseClassMage() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		BaseClasses actualClass = characterSheet.getData(Fields.BASECLASS);
		assertEquals(BaseClasses.MAGE, actualClass);
	}

	@Test
	public void testSetCharacterBaseClassRogue() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.ROGUE);
		BaseClasses actualClass = characterSheet.getData(Fields.BASECLASS);
		assertEquals(BaseClasses.ROGUE, actualClass);
	}

	@Test
	public void expectException_SetCharacterBaseClassFromMageToWarrior_ArcaneWarrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Warrior is not a base class of Arcane Warrior");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, new SpecializationClassesSet(
				SpecializationClasses.ARCANE_WARRIOR));
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
	}

	@Test
	public void expectException_SetCharacterBaseClassFromWarriorToMage_Berserker() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Mage is not a base class of Berserker");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.WARRIOR, new SpecializationClassesSet(
				SpecializationClasses.BERSERKER));
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
	}

	// private methods

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
