package rpg_database.character_sheet.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.exceptions.FieldRuleException;

public class FieldRules {

	private final JSONObject ruleRoot;

	public FieldRules(String ruleFilePath) {
		try {
			String ruleString = parseDependencyJSONFile(ruleFilePath);
			ruleRoot = new JSONObject(ruleString);
		} catch (IOException e) {
			throw new FieldRuleException(String.format("Error happened while trying to read .rule file! See:\n%s", e.getMessage()));
		}
	}

	private String parseDependencyJSONFile(String path) throws IOException {
		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String jsonString = "";
		String jsonStringLine = null;
		while ((jsonStringLine = bufferedReader.readLine()) != null) {
			jsonString += jsonStringLine;
		}
		bufferedReader.close();
		fileReader.close();
		return jsonString;
	}

	public JSONObject getJSONRoot() {
		return ruleRoot;
	}

	public JSONObject getFieldData(Enum<?> enumField) {
		return ruleRoot.getJSONObject(enumField.name());
	}

	public <E extends java.lang.Enum<E>> ArrayList<E> getEnumsForField(Enum<?> field, Class<E> enumClass, Keys key) {
		ArrayList<E> enumsList = new ArrayList<>();
		JSONObject fieldData = getFieldData(field);
		JSONArray jsonArray = fieldData.getJSONArray(key.toString());
		for (int index = 0; index < jsonArray.length(); index++) {
			enumsList.add(jsonArray.getEnum(enumClass, index));
		}
		return enumsList;
	}

	public <E extends java.lang.Enum<E>> E getEnumForField(Enum<?> field, Class<E> enumClass, Keys key) {
		return getFieldData(field).getEnum(enumClass, key.toString());
	}

	public HashMap<Fields, Integer> getAttributeRequirements(SpecializationClasses specializationClass) {
		HashMap<Fields, Integer> attributeRequirements = new HashMap<Fields, Integer>();
		JSONObject fieldData = getFieldData(specializationClass);
		JSONArray attributeRequirementsArray = fieldData.getJSONArray(Keys.ATTRIBUTE_REQUIREMENTS.toString());
		for (int index = 0; index < attributeRequirementsArray.length(); index++) {
			JSONObject requirement = attributeRequirementsArray.getJSONObject(index);
			Fields attribute = requirement.getEnum(Fields.class, Keys.FIELD.toString());
			Integer value = new Integer(requirement.getInt(Keys.VALUE.toString()));
			attributeRequirements.put(attribute, value);
		}
		return attributeRequirements;
	}

	public Integer getIntegerForField(Enum<?> field, Keys key) {
		return getFieldData(field).getInt(key.toString());
	}

}
