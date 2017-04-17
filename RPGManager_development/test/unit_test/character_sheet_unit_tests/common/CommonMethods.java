package unit_test.character_sheet_unit_tests.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterAttribute;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClassesSet;
import rpg_database.character_sheet.common.Keys;

public class CommonMethods {

	public static int LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION = 6;
	public static int LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION = 14;
	public static int LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION = 22;
	private static final String dataPath = "test/unit_test/character_sheet_unit_tests/resources/";
	private static final String extension = ".json";
	public static final String ARMOR_RATING_DEFAULT_DATA = "armorRatingDefaultData";

	public static CharacterSheet createCharacterSheetWithCustomClassesAndLevelAllAttributes5(BaseClasses baseClass,
			SpecializationClassesSet specializationClasses, int level) {
		CharacterSheet characterSheet = setAllAttributesTo5(new CharacterSheet("CharacterSheet"));
		characterSheet.setData(Fields.LEVEL, level);
		characterSheet.setData(Fields.BASECLASS, baseClass);
		characterSheet.setData(Fields.SPECIALIZATIONCLASSES, specializationClasses);
		return characterSheet;
	}

	public static CharacterSheet setAllAttributesTo5(CharacterSheet characterSheet) {
		for (Fields attribute : CharacterAttribute.ATTRIBUTES) {
			characterSheet.setData(Fields.valueOf(attribute.name() + "_VALUE"), 5);
		}
		return characterSheet;
	}

	public static ArrayList<Object[]> getTestData(String testDataName, Keys... keys) throws JSONException, FileNotFoundException, IOException {
		JSONArray dataArray = new JSONArray(readDataToJSONString(testDataName));
		ArrayList<Object[]> data = new ArrayList<>();
		for (int index = 0; index < dataArray.length(); index++) {
			JSONObject element = dataArray.getJSONObject(index);
			ArrayList<Object> objectList = new ArrayList<>();
			for (Keys key : keys) {
				objectList.add(getObjectByKey(element, objectList, key));
			}
			data.add(objectList.toArray());
		}
		return data;
	}

	private static String readDataToJSONString(String testDataName) throws FileNotFoundException, IOException {
		String jsonString = "";
		FileReader fileReader = new FileReader(createDataFilePath(testDataName));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String jsonStringLine = null;
		while ((jsonStringLine = bufferedReader.readLine()) != null) {
			jsonString += jsonStringLine;
		}
		bufferedReader.close();
		fileReader.close();
		return jsonString;
	}

	private static String createDataFilePath(String dataName) {
		return String.format("%s%s%s", dataPath, dataName, extension);
	}

	private static Object getObjectByKey(JSONObject element, ArrayList<Object> objectList, Keys key) {
		Class<?> keyClass = getKeyClass(key);
		Object object;
		if (keyClass.isAssignableFrom(Integer.class)) {
			object = element.getInt(key.toString());
		} else if (keyClass.isAssignableFrom(Armors.class)) {
			object = element.getEnum(Armors.class, key.toString());
		} else {
			throw new IllegalArgumentException("Unknown key class!");
		}
		return object;
	}

	private static Class<?> getKeyClass(Keys key) {
		Class<?> keyClass;
		switch (key) {
		case ARMOR_PENALTY:
		case ARMOR_RATING:
		case STRAIN:
			keyClass = Integer.class;
			break;
		case ARMOR_TYPE:
			keyClass = Armors.class;
			break;
		default:
			throw new IllegalArgumentException("Unknown key!");
		}
		return keyClass;
	}

}
