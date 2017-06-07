package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rpg_database.character_sheet.Focuses;

import static rpg_database.character_sheet.Focuses.*;

public class FocusesUnitTests {

	// test methods
	@Test
	public void testFocusesContents() {
		Focuses[] expectedContents = { ANIMAL_HANDLING, BARGAINING, DECEPTION, DISGUISE, ETIQUETTE, GAMBLING, INVESTIGATION, LEADERSHIP, PERFORMANCE,
				PERSUASION, SEDUCTION, DRINKING, ROWING, RUNNING, STAMINA, SWIMMING, ARCANE_LORE, BREWING, CARTOGRAPHY, CRYPTOGRAPHY, CULTURAL_LORE,
				ENCHANTMENT, ENGINEERING, EVALUATION, HEALING, HERALDRY, HISTORICAL_LORE, MILITARY_LORE, MUSICAL_LORE, NATURAL_LORE, NAVIGATION,
				POISON_LORE, QUN, RESEARCH, RELIGIOUS_LORE, WRITING, ACROBATICS, BOWS, BRAWLING, CALLIGRAPHY, CRAFTING, DUELING, GRENADES, INITIATIVE,
				LEGERDEMAIN, LIGHT_BLADES, LOCK_PICKING, RIDING, STAVES, STEALTH, TRAPS, ARCANE_LANCE, BLOOD, CREATION, ENTROPY, PRIMAL, SPIRIT,
				EMPATHY, DETECT_DARKSPAWN, HEARING, SEARCHING, SEEING, SMELLING, TRACKING, AXES, BLUDGEONS, CLIMBING, DRIVING, HEAVY_BLADES,
				INTIMIDATION, JUMPING, LANCES, MIGHT, POLEARMS, SMITHING, SPEARS, COURAGE, FAITH, MORALE, SELF_DISCIPLINE };
		assertArrayEquals(expectedContents, Focuses.values());
	}

	@Test
	public void testGetFocusValue() {
		assertEquals(2, JUMPING.getFocusValue());
	}

	@Test
	public void testGetFocusName() {
		assertEquals("Jumping", JUMPING.toString());
	}
}
