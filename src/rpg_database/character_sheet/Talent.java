package rpg_database.character_sheet;

import java.util.HashMap;
import java.util.HashSet;

import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.common.FieldRulesFactory;
import rpg_database.character_sheet.common.Keys;

public class Talent {

	private TalentLevels talentLevel;
	private final Talents talentName;
	private final HashSet<BaseClasses> allowedBaseClasses;
	private final HashMap<Fields, Integer> requiredAttributeValues;
	private final HashSet<WeaponGroups> requiredWeaponGroups;
	private final HashSet<HashSet<Focuses>> requiredFocuses;

	public Talent(Talents talentName) {
		this(talentName, TalentLevels.NOVICE);
	}

	public Talent(Talents talentName, TalentLevels talentLevel) {
		this.talentLevel = talentLevel;
		this.talentName = talentName;
		FieldRules talentsRule = FieldRulesFactory.getFieldRules(FieldRulesFactory.TALENTS);
		this.allowedBaseClasses = new HashSet<>();
		this.allowedBaseClasses.addAll(talentsRule.getEnumsForField(talentName, BaseClasses.class, Keys.BASE_CLASSES_ARRAY));
		this.requiredAttributeValues = talentsRule.getAttributeRequirements(talentName);
		this.requiredWeaponGroups = new HashSet<>();
		this.requiredWeaponGroups.addAll(talentsRule.getEnumsForField(talentName, WeaponGroups.class, Keys.WEAPON_GROUPS));
		this.requiredFocuses = talentsRule.getAndOrRelatedEnumsForField(talentName, Focuses.class, Keys.FOCUSES);
	}

	public TalentLevels getTalentLevel() {
		return talentLevel;
	}

	public void setTalentLevel(TalentLevels talentLevel) {
		this.talentLevel = talentLevel;
	}

	public Talents getTalentName() {
		return talentName;
	}

	public HashSet<BaseClasses> getAllowedBaseClasses() {
		return allowedBaseClasses;
	}

	public HashMap<Fields, Integer> getRequiredAttributeValues() {
		return requiredAttributeValues;
	}

	public HashSet<WeaponGroups> getWeaponGroupsFulfillingRequirement() {
		return requiredWeaponGroups;
	}

	public HashSet<HashSet<Focuses>> getFocusesFulfillingRequirement() {
		return requiredFocuses;
	}
}
