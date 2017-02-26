package unit_test;

import junit.framework.TestCase;
import rpg_database.CharacterSheet;
import rpg_database.CharacterSheet.Background;
import rpg_database.CharacterSheet.CharacterClass;
import rpg_database.CharacterSheet.Fields;
import rpg_database.CharacterSheet.Gender;

public class CharacterSheetUnitTests extends TestCase {

	final CharacterSheet defaultCharacterSheet = new CharacterSheet("DefultCharacterSheet");
	CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");

	public void testGetCharacterSheetEntryName_Tibor() {
		final String expectedCharacterName = "Tibor_karilapja";

		CharacterSheet characterSheet = new CharacterSheet(expectedCharacterName);
		String entryName = characterSheet.getEntryName();
		assertEquals(expectedCharacterName, entryName);
	}

	public void testGetCharacterSheetEntryName_Mate() {
		final String expectedCharacterName = "Máté_karilapja";

		CharacterSheet characterSheet = new CharacterSheet(expectedCharacterName);
		String entryName = characterSheet.getEntryName();
		assertEquals(expectedCharacterName, entryName);
	}

	public void testGetDefaultCharacterName() {
		String characterName = (String) defaultCharacterSheet.getData(Fields.NAME);
		assertEquals("", characterName);
	}

	public void testSetCharacterNameTibor() {
		final String expectedCharacterName = "Tibor";

		testCharacterSheet.setData(Fields.NAME, expectedCharacterName);
		String characterName = (String) testCharacterSheet.getData(Fields.NAME);
		assertEquals(expectedCharacterName, characterName);
	}

	public void testGetDefaultCharacterAge() {
		int age = (int) defaultCharacterSheet.getData(Fields.AGE);
		assertEquals(0, age);
	}

	public void testSetCharacterAge21() {
		final int expectedAge = 21;

		testCharacterSheet.setData(Fields.AGE, expectedAge);
		int age = (int) testCharacterSheet.getData(Fields.AGE);
		assertEquals(expectedAge, age);
	}

	public void testGetDefaultCharacterGender() {
		Gender gender = (Gender) defaultCharacterSheet.getData(Fields.GENDER);
		assertEquals(Gender.MALE, gender);
	}

	public void testSetCharacterGenderFemale() {
		testCharacterSheet.setData(Fields.GENDER, Gender.FEMALE);
		Gender gender = (Gender) testCharacterSheet.getData(Fields.GENDER);
		assertEquals(Gender.FEMALE, gender);
	}

	public void testGetDefaultCharacterExperience() {
		int xp = (int) defaultCharacterSheet.getData(Fields.XP);
		assertEquals(0, xp);
	}

	public void testSetCharacterExperienceTo1000() {
		testCharacterSheet.setData(Fields.XP, 1000);
		int xp = (int) testCharacterSheet.getData(Fields.XP);
		assertEquals(1000, xp);
	}

	public void testSetCharacterExperienceTo2500() {
		testCharacterSheet.setData(Fields.XP, 2500);
		int xp = (int) testCharacterSheet.getData(Fields.XP);
		assertEquals(2500, xp);
	}

	public void testGetDefaultCharacterSpeed() {
		int speed = (int) defaultCharacterSheet.getData(Fields.SPEED);
		assertEquals(0, speed);
	}

	public void testSetCharacterSpeed12() {
		testCharacterSheet.setData(Fields.SPEED, 12);
		int speed = (int) testCharacterSheet.getData(Fields.SPEED);
		assertEquals(12, speed);
	}

	public void testGetDefaultCharacterClass() {
		CharacterClass characterClass = (CharacterClass) defaultCharacterSheet.getData(Fields.CHARACTERCLASS);
		assertEquals(CharacterClass.WARRIOR, characterClass);
	}

	public void testSetCharacterClassRogue() {
		testCharacterSheet.setData(Fields.CHARACTERCLASS, CharacterClass.ROGUE);
		CharacterClass characterClass = (CharacterClass) testCharacterSheet.getData(Fields.CHARACTERCLASS);
		assertEquals(CharacterClass.ROGUE, characterClass);
	}

	public void testSetCharacterClassMage() {
		testCharacterSheet.setData(Fields.CHARACTERCLASS, CharacterClass.MAGE);
		CharacterClass characterClass = (CharacterClass) testCharacterSheet.getData(Fields.CHARACTERCLASS);
		assertEquals(CharacterClass.MAGE, characterClass);
	}

	public void testGetDefaultBackground() {
		Background background = (Background) defaultCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(Background.ANDER_SURVIVOR, background);
	}

	public void testSetBackgroundApostate() {
		testCharacterSheet.setData(Fields.BACKGROUND, Background.APOSTATE);
		Background background = (Background) testCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(Background.APOSTATE, background);
	}
}
