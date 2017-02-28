package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Test;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Gender;
import rpg_database.character_sheet.character_class.BaseClasses;
import rpg_database.character_sheet.character_class.CharacterClass;
import rpg_database.character_sheet.character_class.InvalidCharacterClassException;
import rpg_database.character_sheet.character_class.SpecializationClasses;

public class CharacterSheetUnitTests {

	final CharacterSheet defaultCharacterSheet = new CharacterSheet("DefultCharacterSheet");
	CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");

	@Test
	public void testGetCharacterSheetEntryName_Tibor() {
		final String expectedCharacterName = "Tibor_karilapja";

		CharacterSheet characterSheet = new CharacterSheet(expectedCharacterName);
		String entryName = characterSheet.getEntryName();
		assertEquals(expectedCharacterName, entryName);
	}

	@Test
	public void testGetCharacterSheetEntryName_Mate() {
		final String expectedCharacterName = "Máté_karilapja";

		CharacterSheet characterSheet = new CharacterSheet(expectedCharacterName);
		String entryName = characterSheet.getEntryName();
		assertEquals(expectedCharacterName, entryName);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterNameMalformedInput() {
		final double malformedInput = 26.12;
		testCharacterSheet.setData(Fields.NAME, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterName() {
		String characterName = (String) defaultCharacterSheet.getData(Fields.NAME);
		assertEquals("", characterName);
	}

	@Test
	public void testSetCharacterNameTibor() {
		final String expectedCharacterName = "Tibor";

		testCharacterSheet.setData(Fields.NAME, expectedCharacterName);
		String characterName = (String) testCharacterSheet.getData(Fields.NAME);
		assertEquals(expectedCharacterName, characterName);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterAgeMalformedInput() {
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.AGE, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterAge() {
		int age = defaultCharacterSheet.getData(Fields.AGE);
		assertEquals(0, age);
	}

	@Test
	public void testSetCharacterAge21() {
		final int expectedAge = 21;

		testCharacterSheet.setData(Fields.AGE, expectedAge);
		int age = testCharacterSheet.getData(Fields.AGE);
		assertEquals(expectedAge, age);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterGenderMalformedInput() {
		final double malformedInput = 26.12;
		testCharacterSheet.setData(Fields.GENDER, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterGender() {
		Gender gender = defaultCharacterSheet.getData(Fields.GENDER);
		assertEquals(Gender.MALE, gender);
	}

	@Test
	public void testSetCharacterGenderFemale() {
		testCharacterSheet.setData(Fields.GENDER, Gender.FEMALE);
		Gender gender = testCharacterSheet.getData(Fields.GENDER);
		assertEquals(Gender.FEMALE, gender);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterXPMalformedInput() {
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.XP, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterExperience() {
		int xp = defaultCharacterSheet.getData(Fields.XP);
		assertEquals(0, xp);
	}

	@Test
	public void testSetCharacterExperienceTo1000() {
		testCharacterSheet.setData(Fields.XP, 1000);
		int xp = testCharacterSheet.getData(Fields.XP);
		assertEquals(1000, xp);
	}

	@Test
	public void testSetCharacterExperienceTo2500() {
		testCharacterSheet.setData(Fields.XP, 2500);
		int xp = testCharacterSheet.getData(Fields.XP);
		assertEquals(2500, xp);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterSpeedMalformedInput() {
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.SPEED, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterSpeed() {
		int speed = defaultCharacterSheet.getData(Fields.SPEED);
		assertEquals(0, speed);
	}

	@Test
	public void testSetCharacterSpeed12() {
		testCharacterSheet.setData(Fields.SPEED, 12);
		int speed = testCharacterSheet.getData(Fields.SPEED);
		assertEquals(12, speed);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterClassMalformedInput() {
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.CHARACTERCLASS, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterBaseClass() {
		CharacterClass characterClass = defaultCharacterSheet.getData(Fields.CHARACTERCLASS);
		assertEquals(BaseClasses.WARRIOR, characterClass.getBaseClass());
	}

	@Test
	public void testGetDefaultCharacterSpecializationClass() {
		CharacterClass characterClass = defaultCharacterSheet.getData(Fields.CHARACTERCLASS);
		assertEquals(SpecializationClasses.NOT_APPLICABLE, characterClass.getSpecializationClass());
	}

	@Test
	public void testSetCharacterClass_Rogue_Assassin() {
		testCharacterSheet.setData(Fields.CHARACTERCLASS, new CharacterClass(BaseClasses.ROGUE,
				SpecializationClasses.ASSASSIN));
		CharacterClass characterClass = testCharacterSheet.getData(Fields.CHARACTERCLASS);
		assertEquals(new CharacterClass(BaseClasses.ROGUE, SpecializationClasses.ASSASSIN), characterClass);
	}

	@Test
	public void testSetCharacterBaseClassMage() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		CharacterClass testSheetClass = (characterSheet.getData(Fields.CHARACTERCLASS));
		testSheetClass.setBaseClass(BaseClasses.MAGE);
		CharacterClass actualSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		BaseClasses actualClass = actualSheetClass.getBaseClass();
		assertEquals(BaseClasses.MAGE, actualClass);
	}

	@Test
	public void testSetCharacterBaseClassRogue() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		CharacterClass testSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		testSheetClass.setBaseClass(BaseClasses.ROGUE);
		CharacterClass actualSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		BaseClasses actualClass = actualSheetClass.getBaseClass();
		assertEquals(BaseClasses.ROGUE, actualClass);
	}

	@Test
	public void testSetCharacterSpecClassChampion() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		CharacterClass testSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		testSheetClass.setSpecializationClass(SpecializationClasses.CHAMPION);
		CharacterClass actualSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		SpecializationClasses actualClass = actualSheetClass.getSpecializationClass();
		assertEquals(SpecializationClasses.CHAMPION, actualClass);
	}

	@Test
	public void testSetCharacterSpecClassBerserker() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		CharacterClass testSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		testSheetClass.setSpecializationClass(SpecializationClasses.BERSERKER);
		CharacterClass actualSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		SpecializationClasses actualClass = actualSheetClass.getSpecializationClass();
		assertEquals(SpecializationClasses.BERSERKER, actualClass);
	}

	// TODO exception handling when setting spec class for wrong base class and
	// vice versa

	@Test(expected = InvalidCharacterClassException.class)
	public void testCreateMalformedCharacterClass_MageAssassin() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.CHARACTERCLASS, new CharacterClass(BaseClasses.MAGE,
				SpecializationClasses.ASSASSIN));
	}

	@Test(expected = InvalidCharacterClassException.class)
	public void testCreateMalformedCharacterClass_RogueChampion() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.CHARACTERCLASS, new CharacterClass(BaseClasses.ROGUE,
				SpecializationClasses.CHAMPION));
	}

	@Test(expected = InvalidCharacterClassException.class)
	public void testSetCharacterBaseClassFromMageToWarrior_ArcaneWarrior() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.CHARACTERCLASS, new CharacterClass(BaseClasses.MAGE,
				SpecializationClasses.ARCANE_WARRIOR));
		CharacterClass testSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		testSheetClass.setBaseClass(BaseClasses.WARRIOR);
	}

	@Test(expected = InvalidCharacterClassException.class)
	public void testSetCharacterSpecializationClassFromArcaneWarriorToAssassin_Mage() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.CHARACTERCLASS, new CharacterClass(BaseClasses.MAGE,
				SpecializationClasses.ARCANE_WARRIOR));
		CharacterClass testSheetClass = characterSheet.getData(Fields.CHARACTERCLASS);
		testSheetClass.setSpecializationClass(SpecializationClasses.ASSASSIN);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterBackgroundMalformedInput() {
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.BACKGROUND, malformedInput);
	}

	@Test
	public void testGetDefaultBackground() {
		Background background = defaultCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(Background.ANDER_SURVIVOR, background);
	}

	@Test
	public void testSetBackgroundApostate() {
		testCharacterSheet.setData(Fields.BACKGROUND, Background.APOSTATE);
		Background background = testCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(Background.APOSTATE, background);
	}
	// TODO test that background knows which class it allows

}
