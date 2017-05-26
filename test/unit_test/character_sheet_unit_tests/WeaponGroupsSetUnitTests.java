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
import rpg_database.character_sheet.WeaponGroups;
import rpg_database.character_sheet.WeaponGroupsSet;

public class WeaponGroupsSetUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetWeaponGroupsMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.WeaponGroups");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.WEAPON_GROUPS, malformedInput);
	}

	@Test
	public void expectException_SetWeaponGroupsInvalidParameter() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class rpg_database.character_sheet.Background value is not an instance of class rpg_database.character_sheet.WeaponGroups");
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.WEAPON_GROUPS, Background.CITY_ELF);
	}

	@Test
	public void testResetWeaponGroupsOnBaseClassChange() {
		WeaponGroupsSet expectedWeaponGroups = new WeaponGroupsSet(WeaponGroups.BRAWLING);
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		WeaponGroupsSet actualWeaponGroups = characterSheet.getData(Fields.WEAPON_GROUPS);
		assertEquals(expectedWeaponGroups, actualWeaponGroups);
	}

	@Test
	public void testResetWeaponGroupsAfterSetting() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		WeaponGroupsSet expectedWeaponGroups = new WeaponGroupsSet(WeaponGroups.BRAWLING);
		WeaponGroupsSet temporaryWeaponGroups = new WeaponGroupsSet(WeaponGroups.HEAVY_BLADES, WeaponGroups.BLUDGEONS, WeaponGroups.AXES);
		characterSheet.setData(Fields.WEAPON_GROUPS, temporaryWeaponGroups);
		characterSheet.setData(Fields.BASECLASS, BaseClasses.ROGUE);
		WeaponGroupsSet actualWeaponGroups = characterSheet.getData(Fields.WEAPON_GROUPS);
		assertEquals(expectedWeaponGroups, actualWeaponGroups);
	}

	@Test
	public void testSetWeaponGroupsAfterResetting() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		WeaponGroupsSet expectedWeaponGroups = new WeaponGroupsSet(WeaponGroups.BRAWLING, WeaponGroups.BOWS, WeaponGroups.LIGHT_BLADES);
		characterSheet.setData(Fields.BASECLASS, BaseClasses.ROGUE);
		characterSheet.setData(Fields.WEAPON_GROUPS, expectedWeaponGroups);
		WeaponGroupsSet actualWeaponGroups = characterSheet.getData(Fields.WEAPON_GROUPS);
		assertEquals(expectedWeaponGroups, actualWeaponGroups);
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
