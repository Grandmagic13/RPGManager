package unit_test.character_sheet_unit_tests.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterAttribute;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Languages;
import rpg_database.character_sheet.LanguagesSet;
import rpg_database.character_sheet.Race;
import rpg_database.character_sheet.SpecializationClasses;
import rpg_database.character_sheet.SpecializationClassesSet;

public class CommonMethods {

	private enum DataStructureType {
		ARRAY, HIERARCHICAL, ATTRIBUTE_REQUIREMENT;
	}

	public static int LEVEL_REQUIRED_FOR_FIRST_SPECIALIZATION = 6;
	public static int LEVEL_REQUIRED_FOR_SECOND_SPECIALIZATION = 14;
	public static int LEVEL_REQUIRED_FOR_THIRD_SPECIALIZATION = 22;
	private static final String dataPath = "test/unit_test/character_sheet_unit_tests/resources/";
	private static final String extension = ".json";
	public static final String ARMOR_RATING_DEFAULT_DATA = "armorRatingDefaultData";
	public static final String ATTRIBUTE_MAJORITY_DATA = "attributeMajorityData";
	public static final String DEFAULT_ATTRIBUTE_DATA = "defaultAttributeData";
	public static final String DEFAULT_LANGUAGES_DATA = "defaultLanguagesData";
	public static final String DEFAULT_RACE_DATA = "defaultRaceData";
	public static final String INVALID_BACKGROUND_DATA = "invalidBackgroundData";
	public static final String INVALID_CLASS_AND_SPECIALIZATION_PAIRINGS_DATA = "invalidClassAndSpecializationPairingsData";
	public static final String SPECIALIZATION_ATTRIBUTE_FILTER_ATTRIBUTE_VALUES_CONTENT_DATA = "specializationattributefilter_attributeValuesContentData";
	public static final String SPECIALIZATION_ATTRIBUTE_FILTER_CONTENT_DATA = "specializationattributefilter_ContentData";
	public static final String SPECIALIZATION_ATTRIBUTE_FILTER_INVALID_BACKGROUND_DATA = "specializationattributefilter_invalidBackgroundData";
	public static final String SPECIALIZATION_ATTRIBUTE_FILTER_VALID_BACKGROUND_DATA = "specializationattributefilter_validBackgroundData";
	public static final String VALID_BACKGROUND_DATA = "validBackgroundData";
	public static final String VALID_CLASS_AND_SPECIALIZATION_PAIRINGS_DATA = "validClassAndSpecializationPairingsData";

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

	public static ArrayList<Object[]> getTestData(String testDataName, DataKeys... keys) throws JSONException, FileNotFoundException, IOException {
		return getTestData(DataStructureType.ARRAY, testDataName, 0, keys);
	}

	public static ArrayList<Object[]> getTestDataHierarchicalToParentKeys(String testDataName, int numberOfParentKeys, DataKeys... keys)
			throws JSONException, FileNotFoundException, IOException {
		return getTestData(DataStructureType.HIERARCHICAL, testDataName, numberOfParentKeys, keys);
	}

	public static ArrayList<Object[]> getTestDataHierarchicalToFirstKey(String testDataName, DataKeys... keys) throws JSONException,
			FileNotFoundException, IOException {
		return getTestData(DataStructureType.HIERARCHICAL, testDataName, 1, keys);
	}

	public static ArrayList<Object[]> getAttributesRequirementsData(String testDataName) throws JSONException, FileNotFoundException, IOException {
		return getTestData(DataStructureType.ATTRIBUTE_REQUIREMENT, testDataName, 0);
	}

	private static ArrayList<Object[]> getTestData(DataStructureType dataStructureType, String testDataName, int numberOfParentKeys, DataKeys... keys)
			throws JSONException, FileNotFoundException, IOException {
		JSONArray dataArray = new JSONArray(readDataToJSONString(testDataName));
		ArrayList<Object[]> data = new ArrayList<>();
		for (int index = 0; index < dataArray.length(); index++) {
			JSONObject element = dataArray.getJSONObject(index);
			switch (dataStructureType) {
			case ARRAY:
				addObjectsToDataByKeys(data, element, keys);
				break;
			case HIERARCHICAL:
				addHierarchicalObjectsToDataByKeys(data, element, getParents(keys, numberOfParentKeys), getChildren(keys, numberOfParentKeys));
				break;
			case ATTRIBUTE_REQUIREMENT:
				addAttributeRequirementsToData(data, element);
				break;
			default:
				addObjectsToDataByKeys(data, element, keys);
				break;
			}
		}
		return data;
	}

	private static DataKeys[] getChildren(DataKeys[] keys, int numberOfParents) {
		DataKeys[] childKeys = new DataKeys[keys.length - numberOfParents];
		System.arraycopy(keys, numberOfParents, childKeys, 0, keys.length - numberOfParents);
		return childKeys;
	}

	private static DataKeys[] getParents(DataKeys[] keys, int numberOfParents) {
		DataKeys[] parentKeys = new DataKeys[numberOfParents];
		System.arraycopy(keys, 0, parentKeys, 0, numberOfParents);
		return parentKeys;
	}

	private static void addObjectsToDataByKeys(ArrayList<Object[]> data, JSONObject element, DataKeys... keys) {
		ArrayList<Object> objectList = new ArrayList<>();
		for (DataKeys key : keys) {
			objectList.add(getObjectByKey(element, key));
		}
		data.add(objectList.toArray());
	}

	private static void addHierarchicalObjectsToDataByKeys(ArrayList<Object[]> data, JSONObject element, DataKeys parentKeys[],
			DataKeys... childKeys) {
		JSONArray children = element.getJSONArray("CHILDREN");
		for (int childIndex = 0; childIndex < children.length(); childIndex++) {
			ArrayList<Object> objectList = new ArrayList<>();
			for (DataKeys parentKey : parentKeys) {
				objectList.add(getObjectByKey(element, parentKey));
			}
			JSONObject childElement = children.getJSONObject(childIndex);
			for (DataKeys key : childKeys) {
				objectList.add(getObjectByKey(childElement, key));
			}
			data.add(objectList.toArray());
		}
	}

	private static void addAttributeRequirementsToData(ArrayList<Object[]> data, JSONObject element) {
		JSONArray children = element.getJSONArray("CHILDREN");
		ArrayList<Object> objectList = new ArrayList<>();
		objectList.add(getObjectByKey(element, DataKeys.SPECIALIZATION_CLASS));
		HashMap<Fields, Integer> attributeRequirements = new HashMap<>();
		for (int childIndex = 0; childIndex < children.length(); childIndex++) {
			JSONObject childElement = children.getJSONObject(childIndex);
			attributeRequirements.put((Fields) getObjectByKey(childElement, DataKeys.FIELD), (Integer) getObjectByKey(childElement, DataKeys.VALUE));
		}
		objectList.add(attributeRequirements);
		data.add(objectList.toArray());
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

	private static Object getObjectByKey(JSONObject element, DataKeys key) {
		boolean isArray = key.name().contains("ARRAY");
		Class<?> keyClass = key.getKeyClass();
		Object object;
		if (keyClass.isAssignableFrom(Integer.class)) {
			object = element.getInt(key.toString());
		} else if (keyClass.isAssignableFrom(Boolean.class)) {
			object = element.getBoolean(key.toString());
		} else if (keyClass.isAssignableFrom(Armors.class)) {
			object = element.getEnum(Armors.class, key.toString());
		} else if (keyClass.isAssignableFrom(BaseClasses.class)) {
			object = element.getEnum(BaseClasses.class, key.toString());
		} else if (keyClass.isAssignableFrom(SpecializationClasses.class)) {
			object = element.getEnum(SpecializationClasses.class, key.toString());
		} else if (keyClass.isAssignableFrom(Fields.class)) {
			object = element.getEnum(Fields.class, key.toString());
		} else if (keyClass.isAssignableFrom(Race.class)) {
			if (isArray) {
				HashSet<Race> races = new HashSet<>();
				JSONArray jsonArray = element.getJSONArray(key.toString());
				for (int index = 0; index < jsonArray.length(); index++) {
					races.add(jsonArray.getEnum(Race.class, index));
				}
				object = races;
			} else {
				throw new InvalidParameterException("The key must be array!");
			}
		} else if (keyClass.isAssignableFrom(Background.class)) {
			if (isArray) {
				ArrayList<Background> backgrounds = new ArrayList<>();
				JSONArray jsonArray = element.getJSONArray(key.toString());
				for (int index = 0; index < jsonArray.length(); index++) {
					backgrounds.add(jsonArray.getEnum(Background.class, index));
				}
				object = backgrounds;
			} else {
				object = element.getEnum(Background.class, key.toString());
			}
		} else if (keyClass.isAssignableFrom(Languages.class)) {
			if (isArray) {
				LanguagesSet languagesSet = new LanguagesSet();
				JSONArray jsonArray = element.getJSONArray(key.toString());
				for (int index = 0; index < jsonArray.length(); index++) {
					languagesSet.add(jsonArray.getEnum(Languages.class, index));
				}
				object = languagesSet;
			} else {
				object = element.getEnum(Languages.class, key.toString());
			}
		} else {
			throw new IllegalArgumentException("Unknown dataKey class!");
		}
		return object;
	}
}
