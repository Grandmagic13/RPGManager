package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.HashMap;

import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.common.FieldRulesFactory;
import rpg_database.character_sheet.common.Keys;

public class Talent {

	private TalentLevels talentLevel;
	private final Talents talentName;
	private final ArrayList<BaseClasses> allowedBaseClasses;
	private final HashMap<Fields, Integer> requiredAttributeValues;

	public Talent(Talents talentName) {
		this(talentName, TalentLevels.NOVICE);
	}

	public Talent(Talents talentName, TalentLevels talentLevel) {
		this.talentLevel = talentLevel;
		this.talentName = talentName;
		FieldRules talentsRule = FieldRulesFactory.getFieldRules(FieldRulesFactory.TALENTS);
		this.allowedBaseClasses = talentsRule.getEnumsForField(talentName, BaseClasses.class, Keys.BASE_CLASSES_ARRAY);
		this.requiredAttributeValues = talentsRule.getAttributeRequirements(talentName);
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

	public ArrayList<BaseClasses> getAllowedBaseClasses() {
		return allowedBaseClasses;
	}

	public HashMap<Fields, Integer> getRequiredAttributeValues() {
		return requiredAttributeValues;
	}
}
