package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Talent;
import rpg_database.character_sheet.TalentLevels;

public class TalentTests {

	// test setup
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// test methods
	@Test
	public void testDefaultTalentLevel() {
		Talent talent = new Talent();
		assertEquals(TalentLevels.NOVICE, talent.getTalentLevel());
	}

	@Test
	public void testMasterTalentLevel() {
		Talent talent = new Talent(TalentLevels.MASTER);
		assertEquals(TalentLevels.MASTER, talent.getTalentLevel());
	}

	// private methods

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
