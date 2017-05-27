package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.common.FieldRulesFactory;
import rpg_database.character_sheet.common.Keys;

public enum Focuses {
	ANIMAL_HANDLING, BARGAINING, DECEPTION, DISGUISE, ETIQUETTE, GAMBLING, INVESTIGATION, LEADERSHIP, PERFORMANCE, PERSUASION, SEDUCTION, DRINKING,
	ROWING, RUNNING, STAMINA, SWIMMING, ARCANE_LORE, BREWING, CARTOGRAPHY, CRYPTOGRAPHY, CULTURAL_LORE, ENCHANTMENT, ENGINEERING, EVALUATION, HEALING,
	HERALDRY, HISTORICAL_LORE, MILITARY_LORE, MUSICAL_LORE, NATURAL_LORE, NAVIGATION, POISON_LORE, QUN, RESEARCH, RELIGIOUS_LORE, WRITING, ACROBATICS,
	BOWS, BRAWLING, CALLIGRAPHY, CRAFTING, DUELING, GRENADES, INITIATIVE, LEGERDEMAIN, LIGHT_BLADES, LOCK_PICKING, RIDING, STAVES, STEALTH, TRAPS,
	ARCANE_LANCE, BLOOD, CREATION, ENTROPY, PRIMAL, SPIRIT, EMPATHY, DETECT_DARKSPAWN, HEARING, SEARCHING, SEEING, SMELLING, TRACKING, AXES,
	BLUDGEONS, CLIMBING, DRIVING, HEAVY_BLADES, INTIMIDATION, JUMPING, LANCES, MIGHT, POLEARMS, SMITHING, SPEARS, COURAGE, FAITH, MORALE,
	SELF_DISCIPLINE, NONE;

	private final String text;
	private int focusValue;

	private Focuses() {
		this.text = generateEnumText(this.name());
		focusValue = 2;
	}

	public String getText() {
		return text;
	}

	public void setFocusValue() {
		focusValue = 3;
	}

	public int getFocusValue() {
		return focusValue;
	}
}
