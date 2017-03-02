package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Test;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Gender;
import rpg_database.character_sheet.InvalidCharacterClassException;
import rpg_database.character_sheet.SpecializationClasses;

public class CharacterSheetUnitTests {
	// fields

	final CharacterSheet defaultCharacterSheet = new CharacterSheet("DefultCharacterSheet");
	CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");

	// test methods

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

	@Test
	public void testGetDefaultCharacterBaseClass() {
		assertEquals(BaseClasses.WARRIOR, defaultCharacterSheet.getData(Fields.BASECLASS));
	}

	@Test
	public void testGetDefaultCharacterSpecializationClass() {
		assertEquals(SpecializationClasses.NOT_APPLICABLE, defaultCharacterSheet.getData(Fields.SPECIALIZATIONCLASS));
	}

	@Test
	public void testSetCharacterBaseClassMage() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		BaseClasses actualClass = characterSheet.getData(Fields.BASECLASS);
		assertEquals(BaseClasses.MAGE, actualClass);
	}

	@Test
	public void testSetCharacterBaseClassRogue() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.ROGUE);
		BaseClasses actualClass = characterSheet.getData(Fields.BASECLASS);
		assertEquals(BaseClasses.ROGUE, actualClass);
	}

	@Test
	public void testSetCharacterSpecClassChampion() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, SpecializationClasses.CHAMPION);
		SpecializationClasses actualClass = characterSheet.getData(Fields.SPECIALIZATIONCLASS);
		assertEquals(SpecializationClasses.CHAMPION, actualClass);
	}

	@Test
	public void testSetCharacterSpecClassBerserker() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, SpecializationClasses.BERSERKER);
		SpecializationClasses actualClass = characterSheet.getData(Fields.SPECIALIZATIONCLASS);
		assertEquals(SpecializationClasses.BERSERKER, actualClass);
	}

	@Test(expected = InvalidCharacterClassException.class)
	public void testSetCharacterBaseClassFromMageToWarrior_ArcaneWarrior() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, SpecializationClasses.ARCANE_WARRIOR);
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
	}

	@Test(expected = InvalidCharacterClassException.class)
	public void testSetCharacterSpecializationClassFromArcaneWarriorToAssassin_Mage() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, SpecializationClasses.ARCANE_WARRIOR);
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, SpecializationClasses.ASSASSIN);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterBackgroundMalformedInput() {
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.BACKGROUND, malformedInput);
	}

	@Test(expected = InvalidCharacterClassException.class)
	public void testSetInvalidBackgroundApostateForClassWarrior() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.WARRIOR, SpecializationClasses.BERSERKER);
		characterSheet.setData(Fields.BACKGROUND, Background.APOSTATE);
	}

	@Test
	public void testGetDefaultBackground() {
		Background background = defaultCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(Background.ANDER_SURVIVOR, background);
	}

	@Test
	public void testSetBackgroundApostate() {
		testCharacterSheet.setData(Fields.BACKGROUND, Background.CHASIND_WILDER);
		Background background = testCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(Background.CHASIND_WILDER, background);
	}

	@Test(expected = InvalidParameterException.class)
	public void testSetCharacterMoneyMalformedInput() {
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.MONEY, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterMoney() {
		int money = defaultCharacterSheet.getData(Fields.MONEY);
		assertEquals(0, money);
	}

	@Test
	public void testSetCharacterMoney1200() {
		testCharacterSheet.setData(Fields.MONEY, 1200);
		int money = testCharacterSheet.getData(Fields.MONEY);
		assertEquals(1200, money);
	}

	// private methods

	private CharacterSheet createCharacterSheetWithCustomClasses(BaseClasses baseClass, SpecializationClasses specializationClass) {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, specializationClass);
		return characterSheet;
	}
}
