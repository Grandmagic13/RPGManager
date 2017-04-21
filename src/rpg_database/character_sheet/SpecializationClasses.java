package rpg_database.character_sheet;

import static rpg_database.character_sheet.Background.dwarfRogues;
import static rpg_database.character_sheet.Background.dwarfWarriors;
import static rpg_database.character_sheet.Background.elfMages;
import static rpg_database.character_sheet.Background.humanAndElfWarriors;
import static rpg_database.character_sheet.Background.qunariMages;
import static rpg_database.character_sheet.Fields.*;

import java.util.HashMap;
import java.util.HashSet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum SpecializationClasses {

	ARCANE_WARRIOR(BaseClasses.MAGE, attributeRequirements(3, DEXTERITY_VALUE, MAGIC_VALUE)), ASSASSIN(BaseClasses.ROGUE, attributeRequirements(3,
			CUNNING_VALUE, DEXTERITY_VALUE)), BARD(BaseClasses.ROGUE, attributeRequirements(3, COMMUNICATION_VALUE, DEXTERITY_VALUE)),
	BERSERKER(BaseClasses.WARRIOR, attributeRequirements(3, STRENGTH_VALUE, WILLPOWER_VALUE)), BLOOD_MAGE(BaseClasses.MAGE, attributeRequirements(3,
			CONSTITUTION_VALUE, MAGIC_VALUE)), CHAMPION(BaseClasses.WARRIOR, attributeRequirements(3, COMMUNICATION_VALUE, STRENGTH_VALUE)),
	CHEVALIER(BaseClasses.WARRIOR, attributeRequirements(3, DEXTERITY_VALUE, STRENGTH_VALUE)), DUELIST(BaseClasses.ROGUE, attributeRequirements(3,
			DEXTERITY_VALUE, PERCEPTION_VALUE)), FORCE_MAGE(BaseClasses.MAGE, attributeRequirements(3, MAGIC_VALUE, WILLPOWER_VALUE)),
	GUARDIAN(BaseClasses.WARRIOR, attributeRequirements(3, CONSTITUTION_VALUE, DEXTERITY_VALUE)),
	KEEPER(BaseClasses.MAGE, elfMages(), attributeRequirements(3, CUNNING_VALUE, MAGIC_VALUE)),
	LEGIONNAIRE_SCOUT(BaseClasses.ROGUE, dwarfRogues(), attributeRequirements(3, CONSTITUTION_VALUE, WILLPOWER_VALUE)),
	LEGIONNAIRE_WARRIOR(BaseClasses.WARRIOR, dwarfWarriors(), attributeRequirements(3, CONSTITUTION_VALUE, WILLPOWER_VALUE)),
	LYRIUM_WARRIOR(BaseClasses.WARRIOR, humanAndElfWarriors(), attributeRequirements(3, CONSTITUTION_VALUE, MAGIC_VALUE)),
	NECROMANCER(BaseClasses.MAGE, attributeRequirements(3, WILLPOWER_VALUE)), MARKSMAN(BaseClasses.ROGUE, attributeRequirements(3, DEXTERITY_VALUE,
			PERCEPTION_VALUE)), RANGER(BaseClasses.ROGUE, attributeRequirements(3, COMMUNICATION_VALUE, PERCEPTION_VALUE)),
	REAVER(BaseClasses.WARRIOR, attributeRequirements(3, CONSTITUTION_VALUE, STRENGTH_VALUE)),
	SAAREBAS(BaseClasses.MAGE, qunariMages(), attributeRequirements(3, MAGIC_VALUE, STRENGTH_VALUE)), SHADOW(BaseClasses.ROGUE, attributeRequirements(
			4, DEXTERITY_VALUE)), SHAPESHIFTER(BaseClasses.MAGE, attributeRequirements(3, CONSTITUTION_VALUE, MAGIC_VALUE)),
	SPIRIT_HEALER(BaseClasses.MAGE, attributeRequirements(3, COMMUNICATION_VALUE, MAGIC_VALUE)),
	SPIRIT_WARRIOR(BaseClasses.WARRIOR, attributeRequirements(3, MAGIC_VALUE, WILLPOWER_VALUE)), TEMPEST(BaseClasses.ROGUE, attributeRequirements(3,
			CUNNING_VALUE, DEXTERITY_VALUE)), TEMPLAR(BaseClasses.WARRIOR, attributeRequirements(3, MAGIC_VALUE, STRENGTH_VALUE));

	private final String text;
	private final BaseClasses baseClass;
	private final HashSet<Background> restrictedBackgrounds;
	private final HashMap<Fields, Integer> requiredAttributeValues;

	private static HashMap<Fields, Integer> attributeRequirements(int requiredValue, Fields... attributes) {
		HashMap<Fields, Integer> requiredAttributeValues = new HashMap<>();
		for (Fields attribute : attributes) {
			requiredAttributeValues.put(attribute, requiredValue);
		}
		return requiredAttributeValues;
	}

	private SpecializationClasses(BaseClasses baseClass, HashMap<Fields, Integer> requiredAttributeValues) {
		this(baseClass, new HashSet<Background>(), requiredAttributeValues);
	}

	private SpecializationClasses(BaseClasses baseClass, HashSet<Background> restrictedBackgrounds,
			HashMap<Fields, Integer> requiredAttributeValues) {
		this.text = generateEnumText(this.name());
		this.baseClass = baseClass;
		this.restrictedBackgrounds = restrictedBackgrounds;
		this.requiredAttributeValues = requiredAttributeValues;
	}

	public BaseClasses getBaseClass() {
		return baseClass;
	}

	public boolean isBackgroundRestricted() {
		return !restrictedBackgrounds.isEmpty();
	}

	public HashSet<Background> getRestrictedBackgrounds() {
		return restrictedBackgrounds;
	}

	public HashMap<Fields, Integer> getRequiredAttributeValues() {
		return requiredAttributeValues;
	}

	@Override
	public String toString() {
		return text;
	}

	public boolean isBaseClassCompatible(BaseClasses baseClass) {
		return getBaseClass().equals(baseClass);
	}
}