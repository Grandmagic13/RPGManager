package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.createCharacterSheetWithCustomClasses;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;

public class SpecializationClassUnitTests {

	// test methods
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetSpecializationClassesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.SpecializationClasses");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, malformedInput);
	}

	@Test
	public void testSetCharacterSpecClassChampion() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		SpecializationClassesSet championSingleton = new SpecializationClassesSet(SpecializationClasses.CHAMPION);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, championSingleton);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(championSingleton, actualClasses);
	}

	@Test
	public void testSetCharacterSpecClassBerserker() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		SpecializationClassesSet berserkerSingleton = new SpecializationClassesSet(SpecializationClasses.BERSERKER);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, berserkerSingleton);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(berserkerSingleton, actualClasses);
	}

	@Test
	public void expectException_SetCharacterSpecializationClassFromArcaneWarriorToAssassin_Mage() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Mage is not a base class of Assassin");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, new SpecializationClassesSet(
				SpecializationClasses.ARCANE_WARRIOR));
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, new SpecializationClassesSet(SpecializationClasses.ASSASSIN));
	}

	@Test
	public void expectException_SetCharacterSpecializationClassFromBerserkerToKeeper_Warrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Warrior is not a base class of Keeper");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.WARRIOR, new SpecializationClassesSet(
				SpecializationClasses.BERSERKER));
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, new SpecializationClassesSet(SpecializationClasses.KEEPER));
	}

	// private methods

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
