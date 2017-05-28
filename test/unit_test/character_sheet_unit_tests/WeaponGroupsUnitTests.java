package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static rpg_database.character_sheet.WeaponGroups.*;

import org.junit.Test;

import rpg_database.character_sheet.WeaponGroups;

public class WeaponGroupsUnitTests {

	// test methods
	@Test
	public void testWeaponGroupsContents() {
		WeaponGroups[] expectedContents = { AXES, BLUDGEONS, BOWS, BRAWLING, DUELING, HEAVY_BLADES, LANCES, LIGHT_BLADES, POLEARMS, SPEARS, STAVES };
		assertArrayEquals(expectedContents, WeaponGroups.values());
	}

	@Test
	public void testWeaponGroupsTextOverride() {
		assertEquals("Heavy Blades", HEAVY_BLADES.toString());
	}
}
