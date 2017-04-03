package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.Gender;
import rpg_database.character_sheet.SpecializationClasses;

public class CharacterSheetCommonUnitTests {

	// test methods
	@Test
	public void testEnumTextGenerationOneWordEnum_SpecializationClasses() {
		assertEquals("Assassin", SpecializationClasses.ASSASSIN.toString());
	}

	@Test
	public void testEnumTextGenerationOneWordEnum_BaseClasses() {
		assertEquals("Rogue", BaseClasses.ROGUE.toString());
	}

	@Test
	public void testEnumTextGenerationOneWordEnum_Gender() {
		assertEquals("Female", Gender.FEMALE.toString());
	}

	@Test
	public void testEnumTextGenerationTwoWordEnum_SpecializationClasses() {
		assertEquals("Arcane Warrior", SpecializationClasses.ARCANE_WARRIOR.toString());
	}

	@Test
	public void testEnumTextGenerationThreeWordEnum_Background() {
		assertEquals("Escaped Elven Slave", Background.ESCAPED_ELVEN_SLAVE.toString());
	}
}
