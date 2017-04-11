package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.exceptions.FieldRuleException;

public class FieldRulesTests {
	// fields
	private final String TEST_RULE_FILE_PATH = "test/unit_test/character_sheet_unit_tests/resources/test.rule";

	// test setup
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// test methods
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
		final String expectedJSONString = "{\"ASSASSIN\":{\"someAttribute\": true,\"someOtherAttribute\":\"Orcs\"},\"SOME_OTHER_FIELD\":{\"otherAttribute\":false,\"yetAnotherAttribute\":12}}";
		final JSONObject expectedRoot = new JSONObject(expectedJSONString);
		assertEquals(expectedRoot.toString(), actualRoot.toString());
	}

	@Test
	public void testGetFieldData() {
		final FieldRules fieldRules = new FieldRules(TEST_RULE_FILE_PATH);
		final JSONObject actualFieldData = fieldRules.getFieldData(SpecializationClasses.ASSASSIN);
		final String expectedJSONString = "{\"someAttribute\": true,\"someOtherAttribute\":\"Orcs\"}";
		final JSONObject expectedFieldData = new JSONObject(expectedJSONString);
		assertEquals(expectedFieldData.toString(), actualFieldData.toString());
	}

	// private methods

	private void expectExceptionWithMessage(final Class<? extends Exception> exceptionClass, final String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

}
