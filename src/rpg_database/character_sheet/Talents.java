package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

public enum Talents {
	ALCHEMY, ANIMAL_TRAINING, ARMOR_TRAINING, ARCHERY_STYLE, BLACKSMITHING, CAROUSING, CHIRURGY, COMMAND, CONTACTS, CREATION_MAGIC, DUAL_WEAPON_STYLE,
	ENTROPY_MAGIC, HORSEMANSHIP, INTRIGUE, LEATHERWORKING, LINGUISTICS, LORE, MUSIC, POISON_MAKING, MOUNTED_COMBAT_STYLE, OBSERVATION, ORATORY,
	POLE_WEAPON_STYLE, PRIMAL_MAGIC, QUICK_REFLEXES, RUNECRAFTING, SCOUTING, SINGLE_WEAPON_STYLE, SPELL_EXPERTISE, SPIRIT_MAGIC, THIEVERY,
	THROWN_WEAPON_STYLE, TRAP_MAKING, TWO_HANDER_STYLE, UNARMED_STYLE, WEAPON_AND_SHIELD_STYLE, WOODWORKING;

	private final String text;

	private Talents() {
		this.text = generateEnumText(this.name());
	}

	@Override
	public String toString() {
		return text;
	}
}
