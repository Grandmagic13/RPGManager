package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Gender;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;

public class CharacterSheetUnitTests {
	// fields
	CharacterSheet testCharacterSheet;

	// test set-up
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void clearCharacterSheet() {
		testCharacterSheet = new CharacterSheet("TestCharacterSheet");
	}

	// test methods
	// exception tests
	@Test
	public void expectException_SetCharacterEntryNameMalformedInputSpecialCharacters() {
		expectExceptionWithMessage(IllegalArgumentException.class, "Only alphabet characters and '_' are allowed!");
		new CharacterSheet("T%bor_k!ri*lpja}");
	}

	@Test
	public void expectException_SetCharacterEntryNameMalformedInputSpace() {
		expectExceptionWithMessage(IllegalArgumentException.class, "Only alphabet characters and '_' are allowed!");
		new CharacterSheet("Tibor Karilapja");
	}

	@Test
	public void expectException_SetCharacterNameMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 26.12;
		testCharacterSheet.setData(Fields.NAME, malformedInput);
	}

	@Test
	public void expectException_SetCharacterAgeMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.AGE, malformedInput);
	}

	@Test
	public void expectException_SetCharacterGenderMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.Double value is not an instance of class rpg_database.character_sheet.Gender");
		final double malformedInput = 26.12;
		testCharacterSheet.setData(Fields.GENDER, malformedInput);
	}

	@Test
	public void expectException_SetCharacterXPMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.XP, malformedInput);
	}

	@Test
	public void expectException_SetCharacterSpeedMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.SPEED, malformedInput);
	}

	@Test
	public void expectException_SetCharacterLevelMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.LEVEL, malformedInput);
	}

	@Test
	public void expectException_SetCharacterDefenseMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.DEFENSE, malformedInput);
	}

	@Test
	public void expectException_SetCharacterArmorMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.ARMOR_RATING, malformedInput);
	}

	@Test
	public void expectException_SetCharacterHealthMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.HEALTH_POINTS, malformedInput);
	}

	@Test
	public void expectException_SetCharacterManaMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.MANA_POINTS, malformedInput);
	}

	@Test
	public void expectException_SetCharacterAppearanceMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.APPEARANCE, malformedInput);
	}

	@Test
	public void expectException_SetCharacterDistinguishingFeaturesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.DISTINGUISHING_FEATURES, malformedInput);
	}

	@Test
	public void expectException_SetCharacterOftenUsedEquipmentMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.OFTEN_USED_EQUIPMENT, malformedInput);
	}

	@Test
	public void expectException_SetCharacterGoalsAndTiesMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.GOALS_AND_TIES, malformedInput);
	}

	@Test
	public void expectException_SetCharacterEquipmentMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.Double value is not an instance of class java.lang.String");
		final double malformedInput = 21.10;
		testCharacterSheet.setData(Fields.EQUIPMENT, malformedInput);
	}

	@Test
	public void expectException_SetCharacterAttributeValueMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Integer");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.ATTRIBUTE_STRENGTH_VALUE, malformedInput);
	}

	@Test
	public void expectException_SetCharacterAttributeMajorityMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class, "class java.lang.String value is not an instance of class java.lang.Boolean");
		final String malformedInput = "MALFORMED INPUT";
		testCharacterSheet.setData(Fields.ATTRIBUTE_STRENGTH_MAJORITY, malformedInput);
	}

	@Test
	public void expectException_SetCharacterAttributeMajority() {
		expectExceptionWithMessage(InvalidCharacterClassException.class, "To change majority please modify the base class!");
		testCharacterSheet.setData(Fields.ATTRIBUTE_CUNNING_MAJORITY, true);
	}

	// functional unit tests
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
	public void testSetCharacterNameTibor() {
		final String expectedCharacterName = "Tibor";

		testCharacterSheet.setData(Fields.NAME, expectedCharacterName);
		String characterName = (String) testCharacterSheet.getData(Fields.NAME);
		assertEquals(expectedCharacterName, characterName);
	}

	@Test
	public void testSetCharacterAge21() {
		final int expectedAge = 21;

		testCharacterSheet.setData(Fields.AGE, expectedAge);
		int age = testCharacterSheet.getData(Fields.AGE);
		assertEquals(expectedAge, age);
	}

	@Test
	public void testSetCharacterGenderFemale() {
		testCharacterSheet.setData(Fields.GENDER, Gender.FEMALE);
		Gender gender = testCharacterSheet.getData(Fields.GENDER);
		assertEquals(Gender.FEMALE, gender);
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
	public void testSetCharacterSpeed12() {
		testCharacterSheet.setData(Fields.SPEED, 12);
		int speed = testCharacterSheet.getData(Fields.SPEED);
		assertEquals(12, speed);
	}

	@Test
	public void testSetCharacterLevel10() {
		testCharacterSheet.setData(Fields.LEVEL, 10);
		int level = testCharacterSheet.getData(Fields.LEVEL);
		assertEquals(10, level);
	}

	@Test
	public void testSetCharacterDefense15() {
		testCharacterSheet.setData(Fields.DEFENSE, 15);
		int defense = testCharacterSheet.getData(Fields.DEFENSE);
		assertEquals(15, defense);
	}

	@Test
	public void testSetCharacterArmor6() {
		testCharacterSheet.setData(Fields.ARMOR_RATING, 6);
		int armor = testCharacterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(6, armor);
	}

	@Test
	public void testSetCharacterHealth80() {
		testCharacterSheet.setData(Fields.HEALTH_POINTS, 80);
		int health = testCharacterSheet.getData(Fields.HEALTH_POINTS);
		assertEquals(80, health);
	}

	@Test
	public void testSetCharacterMana50() {
		testCharacterSheet.setData(Fields.MANA_POINTS, 50);
		int mana = testCharacterSheet.getData(Fields.MANA_POINTS);
		assertEquals(50, mana);
	}

	@Test
	public void testSetCharacterAppearanceHandsome() {
		testCharacterSheet.setData(Fields.APPEARANCE, "Handsome");
		String appearance = testCharacterSheet.getData(Fields.APPEARANCE);
		assertEquals("Handsome", appearance);
	}

	@Test
	public void testSetCharacterDistinguishingFeaturesScarOnLeftCheek() {
		testCharacterSheet.setData(Fields.DISTINGUISHING_FEATURES, "ScarOnLeftCheek");
		String distinguishingFeatures = testCharacterSheet.getData(Fields.DISTINGUISHING_FEATURES);
		assertEquals("ScarOnLeftCheek", distinguishingFeatures);
	}

	@Test
	public void testSetCharacterOftenUsedEquipmentPipe() {
		testCharacterSheet.setData(Fields.OFTEN_USED_EQUIPMENT, "Pipe");
		String oftenUsedEquipment = testCharacterSheet.getData(Fields.OFTEN_USED_EQUIPMENT);
		assertEquals("Pipe", oftenUsedEquipment);
	}

	@Test
	public void testSetCharacterGoalsAndTiesHasAVengeance() {
		testCharacterSheet.setData(Fields.GOALS_AND_TIES, "HasAVengeance");
		String goalsAndTies = testCharacterSheet.getData(Fields.GOALS_AND_TIES);
		assertEquals("HasAVengeance", goalsAndTies);
	}

	@Test
	public void testSetCharacterEquipmentHasABackpack() {
		testCharacterSheet.setData(Fields.EQUIPMENT, "HasABackpack");
		String equipment = testCharacterSheet.getData(Fields.EQUIPMENT);
		assertEquals("HasABackpack", equipment);
	}

	// private methods
	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}
}
