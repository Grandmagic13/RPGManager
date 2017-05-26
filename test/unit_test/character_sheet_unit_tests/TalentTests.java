package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Talent;
import rpg_database.character_sheet.TalentLevels;
import rpg_database.character_sheet.Talents;

public class TalentTests {

	// test setup
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// test methods
	@Test
	public void testGetDefaultTalentLevel() {
		Talent talent = new Talent(Talents.ALCHEMY);
		assertEquals(TalentLevels.NOVICE, talent.getTalentLevel());
	}

	@Test
	public void testGetMasterTalentLevel() {
		Talent talent = new Talent(Talents.ALCHEMY, TalentLevels.MASTER);
		assertEquals(TalentLevels.MASTER, talent.getTalentLevel());
	}

	@Test
	public void testSetMasterTalentLevel() {
		Talent talent = new Talent(Talents.ALCHEMY, TalentLevels.MASTER);
		talent.setTalentLevel(TalentLevels.JOURNEYMAN);
		assertEquals(TalentLevels.JOURNEYMAN, talent.getTalentLevel());
	}

	@Test
	public void testGetTalentName() {
		Talent talent = new Talent(Talents.ALCHEMY, TalentLevels.MASTER);
		assertEquals(Talents.ALCHEMY, talent.getTalentName());
	}

	@Test
	public void testGetAllowedBaseClassesAlchemy() {
		Talent talent = new Talent(Talents.ALCHEMY);
		HashSet<BaseClasses> expectedAllowedMageRogueWarrior = new HashSet<>(Arrays.asList(BaseClasses.MAGE, BaseClasses.ROGUE, BaseClasses.WARRIOR));
		assertEquals(expectedAllowedMageRogueWarrior, talent.getAllowedBaseClasses());
	}

	@Test
	public void testGetAllowedBaseClassesPoleWeaponStyle() {
		Talent talent = new Talent(Talents.POLE_WEAPON_STYLE);
		HashSet<BaseClasses> expectedAllowedBaseClassWarrior = new HashSet<>(Arrays.asList(BaseClasses.WARRIOR));
		assertEquals(expectedAllowedBaseClassWarrior, talent.getAllowedBaseClasses());
	}

	@Test
	public void testGetRequiredAttributeValuesAlchemy() {
		Talent talent = new Talent(Talents.ALCHEMY);
		HashMap<Fields, Integer> expectedAttributeRequirements = new HashMap<>();
		expectedAttributeRequirements.put(Fields.CUNNING_VALUE, 3);
		assertEquals(expectedAttributeRequirements, talent.getRequiredAttributeValues());
	}

	// @Test
	// public void testGetRequiredAttributeValuesDualWeaponStyle() {
	// Talent talent = new Talent(Talents.DUAL_WEAPON_STYLE);
	// HashMap<Fields, Integer> expectedAttributeRequirements = new HashMap<>();
	// expectedAttributeRequirements.put(Fields.DEXTERITY_VALUE, 2);
	// assertEquals(expectedAttributeRequirements,
	// talent.getRequiredAttributeValues());
	// }
	//
	// @Test
	// public void
	// testGetRequiredAttributeValuesPoleWeaponStyle_expectEmptyList() {
	// Talent talent = new Talent(Talents.POLE_WEAPON_STYLE);
	// HashMap<Fields, Integer> expectedAttributeRequirements = new HashMap<>();
	// assertEquals(expectedAttributeRequirements,
	// talent.getRequiredAttributeValues());
	// }

	// @Test
	// public void testGetRequiredTalentsAlchemy() {
	// Talent talent = new Talent(Talents.ALCHEMY);
	// ArrayList<BaseClasses> expectedAllowedMageRogueWarrior = new
	// ArrayList<>(Arrays.asList(BaseClasses.MAGE, BaseClasses.ROGUE,
	// BaseClasses.WARRIOR));
	// assertEquals(expectedAllowedMageRogueWarrior,
	// talent.getAllowedBaseClasses());
	// }

	// requirements for talents & focuses //andOr & weapon group =>
	// or relation

	// & Background later for specializationClasses

	// refactor specializationClasses!

	// descriptions in frontend!

	// private methods

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
