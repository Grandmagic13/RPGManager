package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Focuses;
import rpg_database.character_sheet.Languages;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.Talents;
import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.common.FieldRulesFactory;
import rpg_database.character_sheet.common.Keys;
import rpg_database.character_sheet.exceptions.FieldRuleException;

public class FieldRulesTests {
	// fields
	private final String TEST_RULE_FILE_PATH = "test/unit_test/character_sheet_unit_tests/resources/test.rule";

	// test setup
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// test methods
	// FieldRules tests
	@Test
	public void expectException_wrongFilePath() {
		expectExceptionWithMessage(FieldRuleException.class, "Error happened while trying to read .rule file!");
		@SuppressWarnings("unused")
		final FieldRules fieldRules = new FieldRules("");
	}

	@Test
	public void testGetRootJSONObject() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		final JSONObject actualRoot = fieldRules.getJSONRoot();
		final String expectedJSONString = "{\"ASSASSIN\":{\"someAttribute\":true,\"someOtherAttribute\":\"Orcs\",\"ATTRIBUTE_REQUIREMENTS\":[{\"FIELD\":\"CUNNING_VALUE\",\"VALUE\":3},{\"FIELD\":\"DEXTERITY_VALUE\","
				+ "\"VALUE\":3}]},\"ANDER_SURVIVOR\":{\"boolDataTrue\":true,\"boolDataFalse\":false,\"stringData\":\"Orcs\",\"intData\":3,\"BASE_CLASSES_ARRAY\":[\"MAGE\",\"WARRIOR\",\"ROGUE\"],\"LANGUAGES_ARRAY\""
				+ ":[\"ANDER\",\"TRADE_TONGUE\"],\"BASE_CLASS\":\"WARRIOR\",\"ARMOR_RATING\":3,\"ARMOR_PENALTY\":5,\"ARMOR_TYPE\":\"HEAVY_LEATHER\"},\"SHADOW\":{\"FOCUSES\":[[\"LEGERDEMAIN\"],[\"STEALTH\"]]},"
				+ "\"ARMOR_TRAINING\":{\"FOCUSES\":[[]]},\"MUSIC\":{\"FOCUSES\":[[\"PERFORMANCE\",\"MUSICAL_LORE\"]]}}";
		final JSONObject expectedRoot = new JSONObject(expectedJSONString);
		assertEquals(expectedRoot.toString(), actualRoot.toString());
	}

	@Test
	public void testGetFieldData() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		final JSONObject actualFieldData = fieldRules.getFieldData(SpecializationClasses.ASSASSIN);
		final String expectedJSONString = "{\"someAttribute\":true,\"someOtherAttribute\":\"Orcs\",\"ATTRIBUTE_REQUIREMENTS\":[{\"FIELD\":\"CUNNING_VALUE\",\"VALUE\":3},{\"FIELD\":\"DEXTERITY_VALUE\",\"VALUE\":3}]}";
		final JSONObject expectedFieldData = new JSONObject(expectedJSONString);
		assertEquals(expectedFieldData.toString(), actualFieldData.toString());
	}

	@Test
	public void testGetBaseClassesArrayByField() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		ArrayList<BaseClasses> expecteds = new ArrayList<>(Arrays.asList(BaseClasses.MAGE, BaseClasses.WARRIOR, BaseClasses.ROGUE));
		ArrayList<BaseClasses> actuals = fieldRules.getEnumsForField(Background.ANDER_SURVIVOR, BaseClasses.class, Keys.BASE_CLASSES_ARRAY);
		assertEquals(expecteds, actuals);
	}

	@Test
	public void testGetLanguagesArrayByField() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		ArrayList<Languages> expecteds = new ArrayList<>(Arrays.asList(Languages.ANDER, Languages.TRADE_TONGUE));
		ArrayList<Languages> actuals = fieldRules.getEnumsForField(Background.ANDER_SURVIVOR, Languages.class, Keys.LANGUAGES_ARRAY);
		assertEquals(expecteds, actuals);
	}

	@Test
	public void testGetBaseClassByField() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		BaseClasses expected = BaseClasses.WARRIOR;
		BaseClasses actual = fieldRules.getEnumForField(Background.ANDER_SURVIVOR, BaseClasses.class, Keys.BASE_CLASS);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetArmorsByField() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		Armors expected = Armors.HEAVY_LEATHER;
		Armors actual = fieldRules.getEnumForField(Background.ANDER_SURVIVOR, Armors.class, Keys.ARMOR_TYPE);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAttributeRequirementsByField() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		HashMap<Fields, Integer> expecteds = new HashMap<>();
		expecteds.put(Fields.CUNNING_VALUE, 3);
		expecteds.put(Fields.DEXTERITY_VALUE, 3);
		HashMap<Fields, Integer> actuals = fieldRules.getAttributeRequirements(SpecializationClasses.ASSASSIN);
		assertEquals(expecteds, actuals);
	}

	@Test
	public void testGetIntegerByField_ArmorRating() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		Integer expected = 3;
		Integer actual = fieldRules.getIntegerForField(Background.ANDER_SURVIVOR, Keys.ARMOR_RATING);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetIntegerByField_ArmorPenalty() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		Integer expected = 5;
		Integer actual = fieldRules.getIntegerForField(Background.ANDER_SURVIVOR, Keys.ARMOR_PENALTY);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAndOrRelatedEnumsByField_EmptyList() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		HashSet<HashSet<Focuses>> expecteds = new HashSet<>();
		HashSet<HashSet<Focuses>> actuals = fieldRules.getAndOrRelatedEnumsForField(Talents.ARMOR_TRAINING, Focuses.class, Keys.FOCUSES);
		assertEquals(expecteds, actuals);
	}

	@Test
	public void testGetAndOrRelatedEnumsByField_FocusesORRelation() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		HashSet<HashSet<Focuses>> expecteds = new HashSet<>();
		HashSet<Focuses> focusesSet = new HashSet<>();
		focusesSet.add(Focuses.PERFORMANCE);
		focusesSet.add(Focuses.MUSICAL_LORE);
		expecteds.add(focusesSet);
		HashSet<HashSet<Focuses>> actuals = fieldRules.getAndOrRelatedEnumsForField(Talents.MUSIC, Focuses.class, Keys.FOCUSES);
		assertEquals(expecteds, actuals);
	}

	@Test
	public void testGetAndOrRelatedEnumsByField_FocusesANDRelation() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		HashSet<HashSet<Focuses>> expecteds = new HashSet<>();
		HashSet<Focuses> focusesSet1 = new HashSet<>();
		HashSet<Focuses> focusesSet2 = new HashSet<>();
		focusesSet1.add(Focuses.LEGERDEMAIN);
		focusesSet2.add(Focuses.STEALTH);
		expecteds.add(focusesSet1);
		expecteds.add(focusesSet2);
		HashSet<HashSet<Focuses>> actuals = fieldRules.getAndOrRelatedEnumsForField(SpecializationClasses.SHADOW, Focuses.class, Keys.FOCUSES);
		assertEquals(expecteds, actuals);
	}

	// FieldRulesFactory tests
	@Test
	public void testGetStaticFieldRulesObject() {
		FieldRules fieldRules1 = FieldRulesFactory.getFieldRules(FieldRulesFactory.ARMORS);
		FieldRules fieldRules2 = FieldRulesFactory.getFieldRules(FieldRulesFactory.ARMORS);
		assertTrue(fieldRules1 == fieldRules2);
	}

	// private methods

	private void expectExceptionWithMessage(final Class<? extends Exception> exceptionClass, final String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
