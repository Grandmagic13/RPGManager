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
import rpg_database.character_sheet.Gender;
import rpg_database.character_sheet.InvalidCharacterClassException;
import rpg_database.character_sheet.Money;
import rpg_database.character_sheet.SpecializationClasses;

public class CharacterSheetUnitTests {
	// fields

	final CharacterSheet defaultCharacterSheet = new CharacterSheet("DefultCharacterSheet");
	CharacterSheet testCharacterSheet = new CharacterSheet("TestCharacterSheet");

	// test methods
	@Rule
	public ExpectedException thrown = ExpectedException.none();

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

	@Test
	public void expectException_SetCharacterNameMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
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

	@Test
	public void expectException_SetCharacterAgeMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
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

	@Test
	public void expectException_SetCharacterGenderMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.Double value is not an instance of class rpg_database.character_sheet.Gender");
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

	@Test
	public void expectException_SetCharacterXPMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
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

	@Test
	public void expectException_SetCharacterSpeedMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
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

	@Test
	public void expectException_SetCharacterBaseClassFromMageToWarrior_ArcaneWarrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Warrior is not a base class of Arcane Warrior");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, SpecializationClasses.ARCANE_WARRIOR);
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
	}

	@Test
	public void expectException_SetCharacterBaseClassFromWarriorToMage_Berserker() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Mage is not a base class of Berserker");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.WARRIOR, SpecializationClasses.BERSERKER);
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
	}

	@Test
	public void expectException_SetCharacterSpecializationClassFromArcaneWarriorToAssassin_Mage() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Mage is not a base class of Assassin");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, SpecializationClasses.ARCANE_WARRIOR);
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, SpecializationClasses.ASSASSIN);
	}

	@Test
	public void expectException_SetCharacterSpecializationClassFromBerserkerToKeeper_Warrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Warrior is not a base class of Keeper");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.WARRIOR, SpecializationClasses.BERSERKER);
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, SpecializationClasses.KEEPER);
	}

	@Test
	public void expectException_SetCharacterBackgroundMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Background");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.BACKGROUND, malformedInput);
	}

	@Test
	public void expectException_SetInvalidBackgroundApostateForClassWarrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Apostate is not a Warrior background!");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.WARRIOR, SpecializationClasses.BERSERKER);
		characterSheet.setData(Fields.BACKGROUND, Background.APOSTATE);
	}

	@Test
	public void expectException_SetInvalidBackgroundFereldanFreemanForClassMage() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Fereldan Freeman is not a Mage background!");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClasses(BaseClasses.MAGE, SpecializationClasses.KEEPER);
		characterSheet.setData(Fields.BACKGROUND, Background.FERELDAN_FREEMAN);
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

	@Test
	public void expectException_SetCharacterMoneyMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
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
	
	@Test
	public void testGetDefaultCharacterMoneyCoins() {
		int money = defaultCharacterSheet.getData(Fields.MONEY);
		Money coins = new Money(money);
		assertEquals("You have 0 gold, 0 silver and 0 copper coins", coins.toWriteConsole());
	}

	@Test
	public void testSetCharacterMoneyCoins124576() {
		testCharacterSheet.setData(Fields.MONEY, 124576);
		int money = testCharacterSheet.getData(Fields.MONEY);
		Money coins = new Money(money);
		assertEquals("You have 12 gold, 45 silver and 76 copper coins", coins.toWriteConsole());
	}
	
	@Test
	public void expectException_SetCharacterLevelMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.LEVEL, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterLevel() {
		int level = defaultCharacterSheet.getData(Fields.LEVEL);
		assertEquals(0, level);
	}

	@Test
	public void testSetCharacterLevel10() {
		testCharacterSheet.setData(Fields.LEVEL, 10);
		int level = testCharacterSheet.getData(Fields.LEVEL);
		assertEquals(10, level);
	}
	
	@Test
	public void expectException_SetCharacterDefenseMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.DEFENSE, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterDefense() {
		int defense = defaultCharacterSheet.getData(Fields.DEFENSE);
		assertEquals(0, defense);
	}

	@Test
	public void testSetCharacterDefense15() {
		testCharacterSheet.setData(Fields.DEFENSE, 15);
		int defense = testCharacterSheet.getData(Fields.DEFENSE);
		assertEquals(15, defense);
	}
	
	@Test
	public void expectException_SetCharacterArmorMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.ARMOR_RATING, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterArmor() {
		int armor = defaultCharacterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(0, armor);
	}

	@Test
	public void testSetCharacterArmor6() {
		testCharacterSheet.setData(Fields.ARMOR_RATING, 6);
		int armor = testCharacterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(6, armor);
	}
	
	@Test
	public void expectException_SetCharacterHealthMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.HEALTH_POINTS, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterHealth() {
		int health = defaultCharacterSheet.getData(Fields.HEALTH_POINTS);
		assertEquals(0, health);
	}

	@Test
	public void testSetCharacterHealth80() {
		testCharacterSheet.setData(Fields.HEALTH_POINTS, 80);
		int health = testCharacterSheet.getData(Fields.HEALTH_POINTS);
		assertEquals(80, health);
	}
	
	@Test
	public void expectException_SetCharacterManaMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.MANA_POINTS, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterMana() {
		int mana = defaultCharacterSheet.getData(Fields.MANA_POINTS);
		assertEquals(0, mana);
	}

	@Test
	public void testSetCharacterMana50() {
		testCharacterSheet.setData(Fields.MANA_POINTS, 50);
		int mana = testCharacterSheet.getData(Fields.MANA_POINTS);
		assertEquals(50, mana);
	}
	
	@Test
	public void expectException_SetCharacterAppearanceMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.APPEARANCE, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterAppearance() {
		String appearance = defaultCharacterSheet.getData(Fields.APPEARANCE);
		assertEquals("", appearance);
	}

	@Test
	public void testSetCharacterAppearanceHandsome() {
		testCharacterSheet.setData(Fields.APPEARANCE, "Handsome");
		String appearance = testCharacterSheet.getData(Fields.APPEARANCE);
		assertEquals("Handsome", appearance);
	}
	
	@Test
	public void expectException_SetCharacterDistinguishingFeaturesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.DISTINGUISHING_FEATURES, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterDistinguishingFeatures() {
		String distinguishingFeatures = defaultCharacterSheet.getData(Fields.DISTINGUISHING_FEATURES);
		assertEquals("", distinguishingFeatures);
	}

	@Test
	public void testSetCharacterDistinguishingFeaturesScarOnLeftCheek() {
		testCharacterSheet.setData(Fields.DISTINGUISHING_FEATURES, "ScarOnLeftCheek");
		String distinguishingFeatures = testCharacterSheet.getData(Fields.DISTINGUISHING_FEATURES);
		assertEquals("ScarOnLeftCheek", distinguishingFeatures);
	}
	
	@Test
	public void expectException_SetCharacterOftenUsedEquipmentMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.OFTEN_USED_EQUIPMENT, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterOftenUsedEquipment() {
		String oftenUsedEquipment = defaultCharacterSheet.getData(Fields.OFTEN_USED_EQUIPMENT);
		assertEquals("", oftenUsedEquipment);
	}

	@Test
	public void testSetCharacterOftenUsedEquipmentPipe() {
		testCharacterSheet.setData(Fields.OFTEN_USED_EQUIPMENT, "Pipe");
		String oftenUsedEquipment = testCharacterSheet.getData(Fields.OFTEN_USED_EQUIPMENT);
		assertEquals("Pipe", oftenUsedEquipment);
	}
	
	@Test
	public void expectException_SetCharacterGoalsAndTiesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.GOALS_AND_TIES, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterGoalsAndTies() {
		String goalsAndTies = defaultCharacterSheet.getData(Fields.GOALS_AND_TIES);
		assertEquals("", goalsAndTies);
	}

	@Test
	public void testSetCharacterGoalsAndTiesHasAVengeance() {
		testCharacterSheet.setData(Fields.GOALS_AND_TIES, "HasAVengeance");
		String goalsAndTies = testCharacterSheet.getData(Fields.GOALS_AND_TIES);
		assertEquals("HasAVengeance", goalsAndTies);
	}
	
	@Test
	public void expectException_SetCharacterEquipmentMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.EQUIPMENT, malformedInput);
	}

	@Test
	public void testGetDefaultCharacterEquipment() {
		String equipment = defaultCharacterSheet.getData(Fields.EQUIPMENT);
		assertEquals("", equipment);
	}

	@Test
	public void testSetCharacterEquipmentHasABackpack() {
		testCharacterSheet.setData(Fields.EQUIPMENT, "HasABackpack");
		String equipment = testCharacterSheet.getData(Fields.EQUIPMENT);
		assertEquals("HasABackpack", equipment);
	}

	// private methods

	private CharacterSheet createCharacterSheetWithCustomClasses(BaseClasses baseClass, SpecializationClasses specializationClass) {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASS, specializationClass);
		return characterSheet;
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
