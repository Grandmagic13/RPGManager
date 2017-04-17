package rpg_database.character_sheet.common;

import java.util.HashMap;

public class FieldRulesFactory {

	private FieldRulesFactory() {
	}

	public static final String ARMORS = "armors";
	public static final String BACKGROUND = "background";
	public static final String BASE_CLASSES = "baseclasses";

	private static HashMap<String, FieldRules> fieldRulesMap = new HashMap<>();
	private static final String rulesPath = "src/rpg_database/character_sheet/rules/";
	private static final String ruleExtension = ".rule";

	public static FieldRules getFieldRules(String ruleName) {
		FieldRules fieldRules;
		if (fieldRulesMap.containsKey(ruleName)) {
			fieldRules = fieldRulesMap.get(ruleName);
		} else {
			fieldRules = new FieldRules(createRuleFilePath(ruleName));
			fieldRulesMap.put(ruleName, fieldRules);
		}
		return fieldRules;
	}

	private static String createRuleFilePath(String ruleName) {
		return String.format("%s%s%s", rulesPath, ruleName, ruleExtension);
	}

}
