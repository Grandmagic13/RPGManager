package unit_test.character_sheet_unit_tests.resources;

import java.util.HashMap;

import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.SpecializationClasses;

public class SpecializationCompatibilityData {
	// Specializations by base classes
	final static public SpecializationClasses[] mageSpecializations = { SpecializationClasses.ARCANE_WARRIOR, SpecializationClasses.BLOOD_MAGE,
			SpecializationClasses.FORCE_MAGE, SpecializationClasses.KEEPER, SpecializationClasses.NECROMANCER, SpecializationClasses.SAAREBAS,
			SpecializationClasses.SHAPESHIFTER, SpecializationClasses.SPIRIT_HEALER };
	final static public SpecializationClasses[] rogueSpecializations = { SpecializationClasses.ASSASSIN, SpecializationClasses.BARD,
			SpecializationClasses.DUELIST, SpecializationClasses.LEGIONNAIRE_SCOUT, SpecializationClasses.MARKSMAN, SpecializationClasses.RANGER,
			SpecializationClasses.SHADOW, SpecializationClasses.TEMPEST };
	final static public SpecializationClasses[] warriorSpecializations = { SpecializationClasses.BERSERKER, SpecializationClasses.CHAMPION,
			SpecializationClasses.CHEVALIER, SpecializationClasses.GUARDIAN, SpecializationClasses.LEGIONNAIRE_WARRIOR,
			SpecializationClasses.LYRIUM_WARRIOR, SpecializationClasses.REAVER, SpecializationClasses.SPIRIT_WARRIOR, SpecializationClasses.TEMPLAR };

	// required attribute values by specializations
	final static private HashMap<Fields, Integer> WILLPOWER_3 = attributeRequirements(3, Fields.WILLPOWER_VALUE);
	final static private HashMap<Fields, Integer> DEXTERITY_4 = attributeRequirements(4, Fields.DEXTERITY_VALUE);
	final static private HashMap<Fields, Integer> DEXTERITY_MAGIC_3 = attributeRequirements(3, Fields.DEXTERITY_VALUE, Fields.MAGIC_VALUE);
	final static private HashMap<Fields, Integer> CONSTITUTION_MAGIC_3 = attributeRequirements(3, Fields.CONSTITUTION_VALUE, Fields.MAGIC_VALUE);
	final static private HashMap<Fields, Integer> CUNNING_MAGIC_3 = attributeRequirements(3, Fields.CUNNING_VALUE, Fields.MAGIC_VALUE);
	final static private HashMap<Fields, Integer> COMMUNICATION_MAGIC_3 = attributeRequirements(3, Fields.COMMUNICATION_VALUE, Fields.MAGIC_VALUE);
	final static private HashMap<Fields, Integer> MAGIC_WILLPOWER_3 = attributeRequirements(3, Fields.MAGIC_VALUE, Fields.WILLPOWER_VALUE);
	final static private HashMap<Fields, Integer> STRENGTH_MAGIC_3 = attributeRequirements(3, Fields.STRENGTH_VALUE, Fields.MAGIC_VALUE);
	final static private HashMap<Fields, Integer> CUNNING_DEXTERITY_3 = attributeRequirements(3, Fields.CUNNING_VALUE, Fields.DEXTERITY_VALUE);
	final static private HashMap<Fields, Integer> COMMUNICATION_DEXTERITY_3 = attributeRequirements(3, Fields.COMMUNICATION_VALUE,
			Fields.DEXTERITY_VALUE);
	final static private HashMap<Fields, Integer> DEXTERITY_PERCEPTION_3 = attributeRequirements(3, Fields.DEXTERITY_VALUE, Fields.PERCEPTION_VALUE);
	final static private HashMap<Fields, Integer> CONSTITUTION_WILLPOWER_3 = attributeRequirements(3, Fields.CONSTITUTION_VALUE,
			Fields.WILLPOWER_VALUE);
	final static private HashMap<Fields, Integer> COMMUNICATION_PERCEPTION_3 = attributeRequirements(3, Fields.COMMUNICATION_VALUE,
			Fields.PERCEPTION_VALUE);
	final static private HashMap<Fields, Integer> STRENGTH_WILLPOWER_3 = attributeRequirements(3, Fields.STRENGTH_VALUE, Fields.WILLPOWER_VALUE);
	final static private HashMap<Fields, Integer> COMMUNICATION_STRENGTH_3 = attributeRequirements(3, Fields.COMMUNICATION_VALUE,
			Fields.STRENGTH_VALUE);
	final static private HashMap<Fields, Integer> DEXTERITY_STRENGTH_3 = attributeRequirements(3, Fields.DEXTERITY_VALUE, Fields.STRENGTH_VALUE);
	final static private HashMap<Fields, Integer> CONSTITUTION_DEXTERITY_3 = attributeRequirements(3, Fields.CONSTITUTION_VALUE,
			Fields.DEXTERITY_VALUE);
	final static private HashMap<Fields, Integer> CONSTITUTION_STRENGTH_3 = attributeRequirements(3, Fields.CONSTITUTION_VALUE,
			Fields.STRENGTH_VALUE);

	final static public HashMap<SpecializationClasses, HashMap<Fields, Integer>> requiredAttributeValues = initializeRequiredAttributeValues();

	// private functions
	private static HashMap<SpecializationClasses, HashMap<Fields, Integer>> initializeRequiredAttributeValues() {
		HashMap<SpecializationClasses, HashMap<Fields, Integer>> requiredAttributeValues = new HashMap<>();
		requiredAttributeValues.put(SpecializationClasses.ARCANE_WARRIOR, DEXTERITY_MAGIC_3);
		requiredAttributeValues.put(SpecializationClasses.BLOOD_MAGE, CONSTITUTION_MAGIC_3);
		requiredAttributeValues.put(SpecializationClasses.FORCE_MAGE, MAGIC_WILLPOWER_3);
		requiredAttributeValues.put(SpecializationClasses.KEEPER, CUNNING_MAGIC_3);
		requiredAttributeValues.put(SpecializationClasses.NECROMANCER, WILLPOWER_3);
		requiredAttributeValues.put(SpecializationClasses.SHAPESHIFTER, CONSTITUTION_MAGIC_3);
		requiredAttributeValues.put(SpecializationClasses.SPIRIT_HEALER, COMMUNICATION_MAGIC_3);
		requiredAttributeValues.put(SpecializationClasses.SAAREBAS, STRENGTH_MAGIC_3);
		requiredAttributeValues.put(SpecializationClasses.ASSASSIN, CUNNING_DEXTERITY_3);
		requiredAttributeValues.put(SpecializationClasses.BARD, COMMUNICATION_DEXTERITY_3);
		requiredAttributeValues.put(SpecializationClasses.DUELIST, DEXTERITY_PERCEPTION_3);
		requiredAttributeValues.put(SpecializationClasses.LEGIONNAIRE_SCOUT, CONSTITUTION_WILLPOWER_3);
		requiredAttributeValues.put(SpecializationClasses.MARKSMAN, DEXTERITY_PERCEPTION_3);
		requiredAttributeValues.put(SpecializationClasses.RANGER, COMMUNICATION_PERCEPTION_3);
		requiredAttributeValues.put(SpecializationClasses.SHADOW, DEXTERITY_4);
		requiredAttributeValues.put(SpecializationClasses.TEMPEST, CUNNING_DEXTERITY_3);
		requiredAttributeValues.put(SpecializationClasses.BERSERKER, STRENGTH_WILLPOWER_3);
		requiredAttributeValues.put(SpecializationClasses.CHAMPION, COMMUNICATION_STRENGTH_3);
		requiredAttributeValues.put(SpecializationClasses.CHEVALIER, DEXTERITY_STRENGTH_3);
		requiredAttributeValues.put(SpecializationClasses.GUARDIAN, CONSTITUTION_DEXTERITY_3);
		requiredAttributeValues.put(SpecializationClasses.REAVER, CONSTITUTION_STRENGTH_3);
		requiredAttributeValues.put(SpecializationClasses.SPIRIT_WARRIOR, MAGIC_WILLPOWER_3);
		requiredAttributeValues.put(SpecializationClasses.TEMPLAR, STRENGTH_MAGIC_3);
		requiredAttributeValues.put(SpecializationClasses.LEGIONNAIRE_WARRIOR, CONSTITUTION_WILLPOWER_3);
		requiredAttributeValues.put(SpecializationClasses.LYRIUM_WARRIOR, CONSTITUTION_MAGIC_3);
		return requiredAttributeValues;
	}

	private static HashMap<Fields, Integer> attributeRequirements(int requiredValue, Fields... attributes) {
		HashMap<Fields, Integer> requiredAttributeValues = new HashMap<>();
		for (Fields attribute : attributes) {
			requiredAttributeValues.put(attribute, requiredValue);
		}
		return requiredAttributeValues;
	}
}
