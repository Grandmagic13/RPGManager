package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Focus;
import rpg_database.character_sheet.FocusesSet;
import rpg_database.character_sheet.Gender;
import rpg_database.character_sheet.Languages;
import rpg_database.character_sheet.LanguagesSet;
import rpg_database.character_sheet.SpecializationClassesSet;
import rpg_database.character_sheet.WeaponGroups;
import rpg_database.character_sheet.WeaponGroupsSet;

public class GetDefaultValuesUnitTest {
	// fields
	final private CharacterSheet defaultCharacterSheet = new CharacterSheet("DefultCharacterSheet");

	// test methods
	@Test
	public void testGetDefaultCharacterName() {
		String characterName = (String) defaultCharacterSheet.getData(Fields.NAME);
		assertEquals("", characterName);
	}

	@Test
	public void testGetDefaultCharacterAge() {
		int age = defaultCharacterSheet.getData(Fields.AGE);
		assertEquals(0, age);
	}

	@Test
	public void testGetDefaultCharacterGender() {
		Gender gender = defaultCharacterSheet.getData(Fields.GENDER);
		assertEquals(Gender.MALE, gender);
	}

	@Test
	public void testGetDefaultCharacterExperience() {
		int xp = defaultCharacterSheet.getData(Fields.XP);
		assertEquals(0, xp);
	}

	@Test
	public void testGetDefaultCharacterSpeed() {
		int speed = defaultCharacterSheet.getData(Fields.SPEED);
		assertEquals(0, speed);
	}

	@Test
	public void testGetDefaultCopperCoins() {
		int coppers = defaultCharacterSheet.getData(Fields.COPPER_COIN);
		assertEquals(0, coppers);
	}

	@Test
	public void testGetDefaultSilverCoins() {
		int coppers = defaultCharacterSheet.getData(Fields.SILVER_COIN);
		assertEquals(0, coppers);
	}

	@Test
	public void testGetDefaultGoldCoins() {
		int coppers = defaultCharacterSheet.getData(Fields.GOLD_COIN);
		assertEquals(0, coppers);
	}

	@Test
	public void testGetDefaultCharacterLevel() {
		int level = defaultCharacterSheet.getData(Fields.LEVEL);
		assertEquals(0, level);
	}

	@Test
	public void testGetDefaultCharacterDefense() {
		int defense = defaultCharacterSheet.getData(Fields.DEFENSE);
		assertEquals(0, defense);
	}

	@Test
	public void testGetDefaultCharacterArmor() {
		int armor = defaultCharacterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(4, armor);
	}

	@Test
	public void testGetDefaultCharacterHealth() {
		int health = defaultCharacterSheet.getData(Fields.HEALTH_POINTS);
		assertEquals(0, health);
	}

	@Test
	public void testGetDefaultCharacterMana() {
		int mana = defaultCharacterSheet.getData(Fields.MANA_POINTS);
		assertEquals(0, mana);
	}

	@Test
	public void testGetDefaultCharacterAppearance() {
		String appearance = defaultCharacterSheet.getData(Fields.APPEARANCE);
		assertEquals("", appearance);
	}

	@Test
	public void testGetDefaultCharacterDistinguishingFeatures() {
		String distinguishingFeatures = defaultCharacterSheet.getData(Fields.DISTINGUISHING_FEATURES);
		assertEquals("", distinguishingFeatures);
	}

	@Test
	public void testGetDefaultCharacterOftenUsedEquipment() {
		String oftenUsedEquipment = defaultCharacterSheet.getData(Fields.OFTEN_USED_EQUIPMENT);
		assertEquals("", oftenUsedEquipment);
	}

	@Test
	public void testGetDefaultCharacterGoalsAndTies() {
		String goalsAndTies = defaultCharacterSheet.getData(Fields.GOALS_AND_TIES);
		assertEquals("", goalsAndTies);
	}

	@Test
	public void testGetDefaultCharacterEquipment() {
		String equipment = defaultCharacterSheet.getData(Fields.EQUIPMENT);
		assertEquals("", equipment);
	}

	@Test
	public void testGetDefaultCharacterBaseClass() {
		assertEquals(BaseClasses.WARRIOR, defaultCharacterSheet.getData(Fields.BASECLASS));
	}

	@Test
	public void testGetDefaultCharacterSpecializationClass() {
		assertEquals(new SpecializationClassesSet(), defaultCharacterSheet.getData(Fields.SPECIALIZATIONCLASSES));
	}

	@Test
	public void testGetDefaultBackground() {
		Background background = defaultCharacterSheet.getData(Fields.BACKGROUND);
		assertEquals(Background.ANDER_SURVIVOR, background);
	}

	@Test
	public void testGetDefaultLanguages() {
		HashSet<Languages> expectedLanguages = new HashSet<Languages>();
		expectedLanguages.add(Languages.TRADE_TONGUE);
		expectedLanguages.add(Languages.ANDER);
		LanguagesSet actualLanguage = defaultCharacterSheet.getData(Fields.LANGUAGES);
		assertEquals(expectedLanguages, actualLanguage);
	}

	@Test
	public void testGetDefaultWeaponGroups() {
		HashSet<WeaponGroups> expectedWeaponGroups = new HashSet<WeaponGroups>();
		expectedWeaponGroups.add(WeaponGroups.BRAWLING);
		expectedWeaponGroups.add(WeaponGroups.HEAVY_BLADES);
		expectedWeaponGroups.add(WeaponGroups.BLUDGEONS);
		expectedWeaponGroups.add(WeaponGroups.AXES);
		WeaponGroupsSet actualWeaponGroups = defaultCharacterSheet.getData(Fields.WEAPON_GROUPS);
		assertEquals(expectedWeaponGroups, actualWeaponGroups);
	}

	@Test
	public void testGetDefaultArmorType() {
		assertEquals(Armors.HEAVY_LEATHER, defaultCharacterSheet.getData(Fields.ARMOR_TYPE));
	}

	@Test
	public void testGetDefaultStrain() {
		int strain = defaultCharacterSheet.getData(Fields.STRAIN);
		assertEquals(0, strain);
	}

	@Test
	public void testGetDefaultFocus() {
		HashSet<Focus> expectedFocuses = new HashSet<Focus>();
		FocusesSet actualFocuses = defaultCharacterSheet.getData(Fields.FOCUSES);
		assertEquals(expectedFocuses, actualFocuses);
	}

}
