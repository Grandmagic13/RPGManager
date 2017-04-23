package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.*;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION;
import static unit_test.character_sheet_unit_tests.common.CommonMethods.createCharacterSheetWithCustomClassesAndLevelAllAttributes5;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashSet;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.exceptions.InvalidSpecializationRequirementException;
import unit_test.character_sheet_unit_tests.common.CommonMethods;

public class SpecializationClassUnitTests {

	// fields
	private final SpecializationClassesSet FORCE_MAGE = new SpecializationClassesSet(SpecializationClasses.FORCE_MAGE);
	private final SpecializationClassesSet SHADOW = new SpecializationClassesSet(SpecializationClasses.SHADOW);
	private final SpecializationClassesSet SHAPESHIFTER = new SpecializationClassesSet(SpecializationClasses.SHAPESHIFTER);
	private final SpecializationClassesSet FORCE_MAGE_ARCANE_WARRIOR = new SpecializationClassesSet(SpecializationClasses.FORCE_MAGE,
			SpecializationClasses.ARCANE_WARRIOR);
	private final SpecializationClassesSet CHAMPION_BERSERKER = new SpecializationClassesSet(SpecializationClasses.CHAMPION,
			SpecializationClasses.BERSERKER);
	private final SpecializationClassesSet FORCE_MAGE_ARCANE_WARRIOR_BLOOD_MAGE = new SpecializationClassesSet(SpecializationClasses.FORCE_MAGE,
			SpecializationClasses.ARCANE_WARRIOR, SpecializationClasses.BLOOD_MAGE);
	private final SpecializationClassesSet CHAMPION_CHEVALIER_BERSERKER = new SpecializationClassesSet(SpecializationClasses.CHAMPION,
			SpecializationClasses.BERSERKER, SpecializationClasses.CHEVALIER);

	private final Background[] ELF_MAGE_BACKGROUNDS = { Background.DALISH_ELF, Background.ELF_APOSTATE, Background.ELF_CIRCLE_MAGE,
			Background.ELF_CIRCLE_MAGE, Background.ESCAPED_ELVEN_SLAVE };

	private final String MESSAGE_NUMBER_OF_SPECIALIZATIONS_OUT_OF_BOUNDS = "Character can't take more specializations than 3!";
	private final String MESSAGE_CAN_NOT_TAKE_1_SPECIALIZATION = "Character can't take 1 specialization(s) until level 6!";
	private final String MESSAGE_CAN_NOT_TAKE_2_SPECIALIZATIONS = "Character can't take 2 specialization(s) until level 14!";
	private final String MESSAGE_CAN_NOT_TAKE_3_SPECIALIZATIONS = "Character can't take 3 specialization(s) until level 22!";
	private final String MESSAGE_INSUFFICIENT_STATS_SHAPESHIFTER = "Insufficient stats for specialization(s): Shapeshifter!";
	private final String MESSAGE_INSUFFICIENT_STATS_FORCE_MAGE = "Insufficient stats for specialization(s): Force Mage!";
	private final String MESSAGE_INSUFFICIENT_STATS_SHADOW = "Insufficient stats for specialization(s): Shadow!";

	// environment
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// test methods:
	// exception tests
	@Test
	public void expectException_SetSpecializationClassesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.SpecializationClasses");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, malformedInput);
	}

	@Test
	public void expectException_SetCharacterSpecializationClassFromArcaneWarriorToAssassin_Mage() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Mage is not a base class of Assassin");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.MAGE, new SpecializationClassesSet(
				SpecializationClasses.ARCANE_WARRIOR), LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, new SpecializationClassesSet(SpecializationClasses.ASSASSIN));
	}

	@Test
	public void expectException_SetCharacterSpecializationClassFromBerserkerToKeeper_Warrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Warrior is not a base class of Force Mage");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.WARRIOR, new SpecializationClassesSet(
				SpecializationClasses.BERSERKER), LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE);
	}

	@Test
	public void expectException_SetAllFalseCharacterSpecializationClassKeeperAndSpiritHealer_Warrior() {
		String regexPattern = "\\QWarrior is not a base class of Force Mage\\E|\\QWarrior is not a base class of Arcane Warrior\\E";
		thrown.expect(InvalidCharacterClassException.class);
		thrown.expectMessage(matchesRegex(regexPattern));
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.WARRIOR,
				new SpecializationClassesSet(), LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE_ARCANE_WARRIOR);
	}

	@Test
	public void expectException_SetOnlyOneFalseCharacterSpecializationClassBerserkerAndSpiritHealer_Warrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Warrior is not a base class of Spirit Healer");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.WARRIOR,
				new SpecializationClassesSet(), LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, new SpecializationClassesSet(SpecializationClasses.BERSERKER,
				SpecializationClasses.SPIRIT_HEALER));
	}

	@Test
	public void expectException_AddOnlyOneFalseCharacterSpecializationClassBerserkerAndSpiritHealer_Warrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Warrior is not a base class of Spirit Healer");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.WARRIOR,
				new SpecializationClassesSet(), LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, new SpecializationClassesSet(SpecializationClasses.BERSERKER));
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.SPIRIT_HEALER);
	}

	@Test
	public void expectException_AddAllOnlyOneFalseCharacterSpecializationClassBerserkerAndSpiritHealer_Warrior() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "Warrior is not a base class of Spirit Healer");
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.WARRIOR,
				new SpecializationClassesSet(), LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).addAll(Arrays.asList(SpecializationClasses.BERSERKER,
				SpecializationClasses.SPIRIT_HEALER));
	}

	@Test
	public void expectException_AddAllMoreSpecializationsThanPermitted() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_CAN_NOT_TAKE_3_SPECIALIZATIONS);
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.WARRIOR, new SpecializationClassesSet(
				SpecializationClasses.BERSERKER), LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).addAll(Arrays.asList(SpecializationClasses.CHEVALIER,
				SpecializationClasses.CHAMPION));
	}

	@Test
	public void expectException_SetCharacterSpecializationClassForLevel1Character() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_CAN_NOT_TAKE_1_SPECIALIZATION);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(1, BaseClasses.MAGE);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE);
	}

	@Test
	public void expectException_AddCharacterSpecializationClassForLevel1Character() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_CAN_NOT_TAKE_1_SPECIALIZATION);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(1, BaseClasses.MAGE);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.BLOOD_MAGE);
	}

	@Test
	public void expectException_SetCharacterSpecializationClassForLevel6Character() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_CAN_NOT_TAKE_2_SPECIALIZATIONS);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION,
				BaseClasses.MAGE);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE_ARCANE_WARRIOR);
	}

	@Test
	public void expectException_AddCharacterSpecializationClassForLevel6Character() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_CAN_NOT_TAKE_2_SPECIALIZATIONS);
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.MAGE, new SpecializationClassesSet(
				SpecializationClasses.FORCE_MAGE), LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.BLOOD_MAGE);
	}

	@Test
	public void expectException_SetCharacterSpecializationClassForLevel14Character() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_CAN_NOT_TAKE_3_SPECIALIZATIONS);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION,
				BaseClasses.MAGE);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE_ARCANE_WARRIOR_BLOOD_MAGE);
	}

	@Test
	public void expectException_AddCharacterSpecializationClassForLevel14Character() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_CAN_NOT_TAKE_3_SPECIALIZATIONS);
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.MAGE, FORCE_MAGE_ARCANE_WARRIOR,
				LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.BLOOD_MAGE);
	}

	@Test
	public void expectException_SetCharacterSpecializationClassesOutOfBounds() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_NUMBER_OF_SPECIALIZATIONS_OUT_OF_BOUNDS);
		final SpecializationClassesSet FORCE_MAGE_ARCANE_WARRIOR_BLOOD_MAGE_SHAPESHIFTER = new SpecializationClassesSet(
				SpecializationClasses.FORCE_MAGE, SpecializationClasses.ARCANE_WARRIOR, SpecializationClasses.BLOOD_MAGE,
				SpecializationClasses.SHAPESHIFTER);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION,
				BaseClasses.MAGE);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE_ARCANE_WARRIOR_BLOOD_MAGE_SHAPESHIFTER);
	}

	@Test
	public void expectException_AddTooManyCharacterSpecializationClasses() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_NUMBER_OF_SPECIALIZATIONS_OUT_OF_BOUNDS);
		CharacterSheet characterSheet = createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses.MAGE,
				FORCE_MAGE_ARCANE_WARRIOR_BLOOD_MAGE, LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.SHAPESHIFTER);
	}

	@Test
	public void expectException_SetSpecializationClassWithInsufficientStat_Shapeshifter() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_INSUFFICIENT_STATS_SHAPESHIFTER);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.MAGE);
		setConstitutionTo3(characterSheet);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, SHAPESHIFTER);
	}

	@Test
	public void expectException_AddSpecializationClassWithInsufficientStat_Shapeshifter() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_INSUFFICIENT_STATS_SHAPESHIFTER);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.MAGE);
		setConstitutionTo3(characterSheet);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.SHAPESHIFTER);
	}

	@Test
	public void expectException_SetSpecializationClassWithInsufficientStat_ForceMage() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_INSUFFICIENT_STATS_FORCE_MAGE);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.MAGE);
		setMagicTo3(characterSheet);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE);
	}

	@Test
	public void expectException_AddSpecializationClassWithInsufficientStat_ForceMage() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_INSUFFICIENT_STATS_FORCE_MAGE);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.MAGE);
		setMagicTo3(characterSheet);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.FORCE_MAGE);
	}

	@Test
	public void expectException_SetSpecializationClassWithBothInsufficientStats_ForceMage() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_INSUFFICIENT_STATS_FORCE_MAGE);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.MAGE);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE);
	}

	@Test
	public void expectException_AddSpecializationClassWithBothInsufficientStats_ForceMage() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_INSUFFICIENT_STATS_FORCE_MAGE);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.MAGE);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.FORCE_MAGE);
	}

	@Test
	public void expectException_SetSpecializationClassWithBothInsufficientStats_Shadow() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_INSUFFICIENT_STATS_SHADOW);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.ROGUE);
		setDexterityTo(3, characterSheet);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, SHADOW);
	}

	@Test
	public void expectException_AddSpecializationClassWithBothInsufficientStats_Shadow() {
		expectExceptionWithMessage(InvalidSpecializationRequirementException.class, MESSAGE_INSUFFICIENT_STATS_SHADOW);
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.ROGUE);
		setDexterityTo(3, characterSheet);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.SHADOW);
	}

	@Test
	public void expectException_AddAllSpecializationClassWithBothInsufficientStats_ForceMage_Arcane_Warrior() {
		String regexPattern = "\\QInsufficient stats for specialization(s): Force Mage, Arcane Warrior!\\E|\\QInsufficient stats for specialization(s): Arcane Warrior, Force Mage!\\E";
		thrown.expect(InvalidSpecializationRequirementException.class);
		thrown.expectMessage(matchesRegex(regexPattern));
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION, BaseClasses.MAGE);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).addAll(FORCE_MAGE_ARCANE_WARRIOR);
	}

	// functional tests
	@Test
	public void testSetCharacterSpecClassChampion() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION,
				BaseClasses.WARRIOR);
		SpecializationClassesSet championSingleton = new SpecializationClassesSet(SpecializationClasses.CHAMPION);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, championSingleton);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(championSingleton, actualClasses);
	}

	@Test
	public void testAddCharacterSpecClassBerserker() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION,
				BaseClasses.WARRIOR);
		SpecializationClassesSet berserkerSingleton = new SpecializationClassesSet(SpecializationClasses.BERSERKER);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.BERSERKER);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(berserkerSingleton, actualClasses);
	}

	@Test
	public void testSetMultipleCharacterSpecClass_BerserkerChampion() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION,
				BaseClasses.WARRIOR);
		SpecializationClassesSet berserkerAndChampion = new SpecializationClassesSet(SpecializationClasses.BERSERKER, SpecializationClasses.CHAMPION);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, berserkerAndChampion);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(berserkerAndChampion, actualClasses);
	}

	@Test
	public void testSet2CharacterSpecClassesChampionBerserker() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION,
				BaseClasses.WARRIOR);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, CHAMPION_BERSERKER);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(CHAMPION_BERSERKER, actualClasses);
	}

	@Test
	public void testAdd2CharacterSpecClassesChampionBerserker() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION,
				BaseClasses.WARRIOR);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.CHAMPION);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.BERSERKER);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(CHAMPION_BERSERKER, actualClasses);
	}

	@Test
	public void testSet3CharacterSpecClassesChampionBerserker() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION,
				BaseClasses.WARRIOR);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, CHAMPION_CHEVALIER_BERSERKER);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(CHAMPION_CHEVALIER_BERSERKER, actualClasses);
	}

	@Test
	public void testAdd3CharacterSpecClassesChampionBerserker() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClassAllAttributes5(LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION,
				BaseClasses.WARRIOR);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.CHAMPION);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.BERSERKER);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.CHEVALIER);
		SpecializationClassesSet actualClasses = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(CHAMPION_CHEVALIER_BERSERKER, actualClasses);
	}

	@Test
	public void testIsSpecializationClassBackgroundRestricted_False() {
		assertFalse(SpecializationClasses.BERSERKER.isBackgroundRestricted());
	}

	@Test
	public void testGetSpecializationClassRestrictedBackgrounds_empty() {
		assertEquals(new HashSet<Background>(), SpecializationClasses.BERSERKER.getRestrictedBackgrounds());
	}

	@Test
	public void testIsSpecializationClassBackgroundRestricted_True() {
		assertTrue(SpecializationClasses.KEEPER.isBackgroundRestricted());
	}

	@Test
	public void testGetSpecializationClassRestrictedBackgroundsForKeeper() {
		HashSet<Background> expectedBackgrounds = new HashSet<>(Arrays.asList(ELF_MAGE_BACKGROUNDS));
		assertEquals(expectedBackgrounds, SpecializationClasses.KEEPER.getRestrictedBackgrounds());
	}

	@Test
	public void test_SetSpecializationClassWithSufficientStats_ForceMage() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.MAGE);
		setMagicTo3(characterSheet);
		setWillpower3(characterSheet);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE);
		SpecializationClassesSet actualSpecializations = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(FORCE_MAGE, actualSpecializations);
	}

	@Test
	public void test_AddSpecializationClassWithSufficientStats_ForceMage() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.MAGE);
		setMagicTo3(characterSheet);
		setWillpower3(characterSheet);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.FORCE_MAGE);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE);
		SpecializationClassesSet actualSpecializations = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(FORCE_MAGE, actualSpecializations);
	}

	@Test
	public void test_SetSpecializationClassWithSufficientStats_Shadow() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.ROGUE);
		setDexterityTo(4, characterSheet);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, SHADOW);
		SpecializationClassesSet actualSpecializations = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(SHADOW, actualSpecializations);
	}

	@Test
	public void test_AddSpecializationClassWithSufficientStats_Shadow() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION, BaseClasses.ROGUE);
		setDexterityTo(4, characterSheet);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).add(SpecializationClasses.SHADOW);
		SpecializationClassesSet actualSpecializations = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(SHADOW, actualSpecializations);
	}

	@Test
	public void test_SetSpecializationClassWithSufficientStats_ForceMage_Arcane_Warrior() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION, BaseClasses.MAGE);
		setMagicTo3(characterSheet);
		setWillpower3(characterSheet);
		setDexterityTo(3, characterSheet);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, FORCE_MAGE_ARCANE_WARRIOR);
		SpecializationClassesSet actualSpecializations = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(FORCE_MAGE_ARCANE_WARRIOR, actualSpecializations);
	}

	@Test
	public void test_AddAllSpecializationClassWithSufficientStats_ForceMage_Arcane_Warrior() {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION, BaseClasses.MAGE);
		setMagicTo3(characterSheet);
		setWillpower3(characterSheet);
		setDexterityTo(3, characterSheet);
		characterSheet.<SpecializationClassesSet>getData(Fields.SPECIALIZATIONCLASSES).addAll(FORCE_MAGE_ARCANE_WARRIOR);
		SpecializationClassesSet actualSpecializations = characterSheet.getData(Fields.SPECIALIZATIONCLASSES);
		assertEquals(FORCE_MAGE_ARCANE_WARRIOR, actualSpecializations);
	}

	// private methods
	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

	private static CharacterSheet createCharacterSheetWithCustomLevelBaseClassAllAttributes5(int level, BaseClasses baseClass) {
		CharacterSheet characterSheet = createCharacterSheetWithCustomLevelBaseClass(level, baseClass);
		return CommonMethods.setAllAttributesTo5(characterSheet);
	}

	private static CharacterSheet createCharacterSheetWithCustomLevelBaseClass(int level, BaseClasses baseClass) {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.LEVEL, level);
		return characterSheet;
	}

	private void setConstitutionTo3(CharacterSheet characterSheet) {
		characterSheet.setData(Fields.CONSTITUTION_VALUE, 3);
	}

	private void setMagicTo3(CharacterSheet characterSheet) {
		characterSheet.setData(Fields.MAGIC_VALUE, 3);
	}

	private void setDexterityTo(int value, CharacterSheet characterSheet) {
		characterSheet.setData(Fields.DEXTERITY_VALUE, value);
	}

	private void setWillpower3(CharacterSheet characterSheet) {
		characterSheet.setData(Fields.WILLPOWER_VALUE, 3);
	}

	private Matcher<String> matchesRegex(final String regex) {
		return new TypeSafeMatcher<String>() {
			@Override
			protected boolean matchesSafely(final String item) {
				return item.matches(regex);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("matches pattern ").appendValue(regex);
			}

			@Override
			protected void describeMismatchSafely(String item, Description mismatchDescription) {
				mismatchDescription.appendText("does not match");
			}
		};
	}

}
