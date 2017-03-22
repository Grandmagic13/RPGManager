package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static rpg_database.character_sheet.Fields.ATTRIBUTE_COMMUNICATION_MAJORITY;
import static rpg_database.character_sheet.Fields.ATTRIBUTE_CONSTITUTION_MAJORITY;
import static rpg_database.character_sheet.Fields.ATTRIBUTE_CUNNING_MAJORITY;
import static rpg_database.character_sheet.Fields.ATTRIBUTE_DEXTERITY_MAJORITY;
import static rpg_database.character_sheet.Fields.ATTRIBUTE_MAGIC_MAJORITY;
import static rpg_database.character_sheet.Fields.ATTRIBUTE_PERCEPTION_MAJORITY;
import static rpg_database.character_sheet.Fields.ATTRIBUTE_STRENGTH_MAJORITY;
import static rpg_database.character_sheet.Fields.ATTRIBUTE_WILLPOWER_MAJORITY;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.createCharacterSheetWithCustomClasses;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;

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
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, SpecializationClasses.ARCANE_WARRIOR);
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
	}

	@Test
	public void expectException_SetCharacterBaseClassFromWarriorToMage_Berserker() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Mage is not a base class of Berserker");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.WARRIOR, SpecializationClasses.BERSERKER);
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
	}

	@Test
	public void testSetCharacterBaseClassModifiesMajorityMage() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		ArrayList<Boolean> expectedMajorities = new ArrayList<Boolean>(Arrays.asList(false, false, true, false, true, false, false, true));
		ArrayList<Boolean> actualMajorities = getActualMajorities(characterSheet);
		assertEquals(expectedMajorities, actualMajorities);
	}

	@Test
	public void testSetCharacterBaseClassModifiesMajorityWarrior() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		ArrayList<Boolean> expectedMajorities = new ArrayList<Boolean>(Arrays.asList(false, true, false, true, false, false, true, false));
		ArrayList<Boolean> actualMajorities = getActualMajorities(characterSheet);
		assertEquals(expectedMajorities, actualMajorities);
	}

	@Test
	public void testSetCharacterBaseClassModifiesMajorityRogue() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.ROGUE);
		ArrayList<Boolean> expectedMajorities = new ArrayList<Boolean>(Arrays.asList(true, false, false, true, false, true, false, false));
		ArrayList<Boolean> actualMajorities = getActualMajorities(characterSheet);
		assertEquals(expectedMajorities, actualMajorities);
	}

	// private methods

	private ArrayList<Boolean> getActualMajorities(CharacterSheet characterSheet) {
		ArrayList<Fields> attributeMajorities = new ArrayList<>(Arrays.asList(ATTRIBUTE_COMMUNICATION_MAJORITY, ATTRIBUTE_CONSTITUTION_MAJORITY,
				ATTRIBUTE_CUNNING_MAJORITY, ATTRIBUTE_DEXTERITY_MAJORITY, ATTRIBUTE_MAGIC_MAJORITY, ATTRIBUTE_PERCEPTION_MAJORITY,
				ATTRIBUTE_STRENGTH_MAJORITY, ATTRIBUTE_WILLPOWER_MAJORITY));
		ArrayList<Boolean> actualMajorities = new ArrayList<>();
		for (Fields attributeMajority : attributeMajorities) {
			actualMajorities.add(getMajority(characterSheet, attributeMajority));
		}
		return actualMajorities;
	}

	private Boolean getMajority(CharacterSheet characterSheet, Fields field) {
		return characterSheet.<Boolean>getData(field);
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
