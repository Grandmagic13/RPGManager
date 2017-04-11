package rpg_database.character_sheet.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

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

	public JSONObject getJSONRoot() {
		return ruleRoot;
	}

	public JSONObject getFieldData(Enum<?> enumField) {
		return ruleRoot.getJSONObject(enumField.name());
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

	// TODO remove?
	// private BaseClasses getBaseClassDependency(String jsonStr) {
	// BaseClasses baseClass = null;
	// try {
	// JSONObject rootObject = new JSONObject(jsonStr);
	// JSONObject specializationClassData =
	// rootObject.getJSONObject(this.name());
	// String baseClassName = specializationClassData.getString("baseClass");
	// baseClass = BaseClasses.valueOf(baseClassName);
	// } catch (JSONException e) {
	// // JSON Parsing error
	// e.printStackTrace();
	// }
	// return baseClass;
	// }

}
