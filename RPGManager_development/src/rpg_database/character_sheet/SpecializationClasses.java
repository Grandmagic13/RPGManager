package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.common.FieldRulesFactory;
import rpg_database.character_sheet.common.Keys;

public enum SpecializationClasses {

	ARCANE_WARRIOR, ASSASSIN, BARD, BERSERKER, BLOOD_MAGE, CHAMPION, CHEVALIER, DUELIST, FORCE_MAGE, GUARDIAN, KEEPER, LEGIONNAIRE_SCOUT,
	LEGIONNAIRE_WARRIOR, LYRIUM_WARRIOR, NECROMANCER, MARKSMAN, RANGER, REAVER, SAAREBAS, SHADOW, SHAPESHIFTER, SPIRIT_HEALER, SPIRIT_WARRIOR,
	TEMPEST, TEMPLAR;

	private final String text;
	private final BaseClasses baseClass;
	private final HashSet<Background> restrictedBackgrounds;
	private final HashMap<Fields, Integer> requiredAttributeValues;

	private SpecializationClasses() {
		this.text = generateEnumText(this.name());

		FieldRules specializationClassesRules = FieldRulesFactory.getFieldRules(FieldRulesFactory.SPECIALIZATION_CLASSES);
		baseClass = specializationClassesRules.getEnumForField(this, BaseClasses.class, Keys.BASE_CLASS);
		ArrayList<Background> restrictedBackgroundList = specializationClassesRules.getEnumsForField(this, Background.class,
				Keys.RESTRICTED_BACKGROUNDS);
		restrictedBackgrounds = new HashSet<Background>();
		restrictedBackgrounds.addAll(restrictedBackgroundList);
		requiredAttributeValues = specializationClassesRules.getAttributeRequirements(this);
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