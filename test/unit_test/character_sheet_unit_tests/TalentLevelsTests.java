package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import rpg_database.character_sheet.TalentLevels;

public class TalentLevelsTests {

	// test methods
	@Test
	public void testTalentLevelsContents() {
		TalentLevels[] allTalentLevelsValues = { TalentLevels.NOVICE, TalentLevels.JOURNEYMAN, TalentLevels.MASTER };
		assertArrayEquals("TalentLevels enum contents are flawed", allTalentLevelsValues, TalentLevels.values());
	}

	@Test
	public void testTalentLevelsTextConversion() {
		assertEquals("Journeyman", TalentLevels.JOURNEYMAN.toString());
	}

	@Test
	public void testNoviceValue() {
		assertEquals(1, TalentLevels.NOVICE.getValue());
	}

	@Test
	public void testJourneymanValue() {
		assertEquals(2, TalentLevels.JOURNEYMAN.getValue());
	}

	@Test
	public void testMasterValue() {
		assertEquals(3, TalentLevels.MASTER.getValue());
	}

}
