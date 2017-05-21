package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static rpg_database.character_sheet.Talents.*;

import org.junit.Test;

import rpg_database.character_sheet.Talents;

public class TalentsTests {

	@Test
	public void testTalentsContent() {
		Talents[] expectedTalents = { ALCHEMY, ANIMAL_TRAINING, ARMOR_TRAINING, ARCHERY_STYLE, BLACKSMITHING, CAROUSING, CHIRURGY, COMMAND, CONTACTS,
				CREATION_MAGIC, DUAL_WEAPON_STYLE, ENTROPY_MAGIC, HORSEMANSHIP, INTRIGUE, LEATHERWORKING, LINGUISTICS, LORE, MUSIC, POISON_MAKING,
				MOUNTED_COMBAT_STYLE, OBSERVATION, ORATORY, POLE_WEAPON_STYLE, PRIMAL_MAGIC, QUICK_REFLEXES, RUNECRAFTING, SCOUTING,
				SINGLE_WEAPON_STYLE, SPELL_EXPERTISE, SPIRIT_MAGIC, THIEVERY, THROWN_WEAPON_STYLE, TRAP_MAKING, TWO_HANDER_STYLE, UNARMED_STYLE,
				WEAPON_AND_SHIELD_STYLE, WOODWORKING };
		assertArrayEquals(expectedTalents, Talents.values());
	}

	@Test
	public void testTalentsTextGeneration() {
		assertEquals("Weapon And Shield Style", WEAPON_AND_SHIELD_STYLE.toString());
	}

}
