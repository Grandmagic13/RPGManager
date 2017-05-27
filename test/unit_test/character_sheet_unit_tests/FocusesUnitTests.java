package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Focuses;
import rpg_database.character_sheet.FocusesSet;

public class FocusesUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetFocusesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Focuses");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.FOCUS, malformedInput);
	}

	// functional tests

	@Test
	public void testSetFocusWithHashSet() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		HashSet<Focuses> expectedFocuses = new HashSet<Focuses>();
		expectedFocuses.add(Focuses.INTIMIDATION);
		characterSheet.setData(Fields.FOCUS, new FocusesSet(Focuses.INTIMIDATION));
		FocusesSet actualFocus = characterSheet.getData(Fields.FOCUS);
		assertEquals(expectedFocuses, actualFocus);
	}

	@Test
	public void testSetFocusWithFocusesSet() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet expectedFocuses = new FocusesSet(Focuses.ETIQUETTE);
		characterSheet.setData(Fields.FOCUS, expectedFocuses);
		FocusesSet actualFocus = characterSheet.getData(Fields.FOCUS);
		assertEquals(expectedFocuses, actualFocus);
	}

	@Test
	public void testSetMoreFocus() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet expectedFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING, Focuses.ARCANE_LANCE, Focuses.BRAWLING);
		characterSheet.setData(Fields.FOCUS, expectedFocuses);
		FocusesSet actualFocuses = characterSheet.getData(Fields.FOCUS);
		assertEquals(expectedFocuses, actualFocuses);
	}

	@Test
	public void testSetSingleTimesFocus() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING);
		characterSheet.setData(Fields.FOCUS, tempFocuses);
		assertEquals(2, Focuses.ANIMAL_HANDLING.getFocusValue());
	}

	@Test
	public void testSetMultipleTimesFocus() {
		CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
		FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING, Focuses.ANIMAL_HANDLING);
		characterSheet.setData(Fields.FOCUS, tempFocuses);
		assertEquals(3, Focuses.ANIMAL_HANDLING.getFocusValue());
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

	// Majd a front-end get requestét egy ilyen logika kellene, hogy kisolgálja,
	// persze még az algoritmuson csiszolni kell mert ez ilyen formában lassú
	// lesz a
	// két ciklus miatt.
	//
	// @Test
	// public void testSetFocusesAfterGetSpecificType() {
	// CharacterSheet characterSheet = new CharacterSheet("TestCharacterSheet");
	// FocusesSet tempFocuses = new FocusesSet(Focuses.ANIMAL_HANDLING,
	// Focuses.ARCANE_LANCE, Focuses.BRAWLING, Focuses.ACROBATICS,
	// Focuses.BLOOD,
	// Focuses.CREATION, Focuses.MORALE, Focuses.SPEARS, Focuses.SWIMMING);
	// characterSheet.setData(Fields.FOCUS, tempFocuses);
	// FocusesSet expectedFocuses = new FocusesSet(Focuses.ARCANE_LANCE,
	// Focuses.BLOOD, Focuses.CREATION);
	// FocusesSet actualFocuses =
	// findRightFocuses(characterSheet.getData(Fields.FOCUS));
	// assertEquals(expectedFocuses, actualFocuses);
	// }
	//
	// private FocusesSet findRightFocuses(FocusesSet mixedFocuses) {
	// FocusesSet rightFocuses = new FocusesSet();
	// for (Focuses focus : mixedFocuses) {
	// if (helpFindRightFocuses(focus)) {
	// rightFocuses.add(focus);
	// }
	// }
	// return rightFocuses;
	// }
	//
	// private boolean helpFindRightFocuses(Focuses focus) {
	// for (int i = 0; i < STRINGFOCUSES.length; i++) {
	// for (int j = 0; j < STRINGFOCUSES[4].length; j++) {
	// if (STRINGFOCUSES[4][j].equals(focus.getText())) {
	// return true;
	// }
	// }
	// }
	// return false;
	// }
	//
	//
	// private final static String[][] STRINGFOCUSES = { { "Animal Handling",
	// "Bargaining", "Deception", "Disguise", "Etiquette", "Gambling",
	// "Investigation", "Leadership", "Performance", "Persuasion", "Seduction"
	// }, { "Drinking", "Rowing", "Running", "Stamina", "Swimming" }, {
	// "Arcane Lore", "Brewing", "Cartography", "Cryptography", "Cultural Lore",
	// "Enchantment", "Engineering", "Evaluation", "Healing",
	// "Heraldry", "Historical Lore", "Military Lore", "Musical Lore", "Natural
	// Lore", "Navigation", "Poison Lore", "Qun", "Research",
	// "Religious Lore", "Writing" }, { "Acrobatics", "Bows", "Brawling",
	// "Calligraphy", "Crafting", "Dueling", "Grenades", "Initiative",
	// "Legerdemain", "Light Blades", "Lock Picking", "Riding", "Staves",
	// "Stealth", "Traps" }, { "Arcane Lance", "Blood",
	// "Creation", "Entropy", "Primal", "Spirit" }, { "Empathy",
	// "DetectDarkspawn", "Hearing", "Searching", "Seeing",
	// "Smelling", "Tracking" }, { "Axes", "Bludgeons", "Climbing", "Driving",
	// "Heavy Blades", "Intimidation",
	// "Jumping", "Lances", "Might", "Polearms", "Smithing", "Spears" }, {
	// "Courage", "Faith", "Morale",
	// "Self_Discipline" } };
}