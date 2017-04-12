package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Shields implements CustomSetter<Shields> {
	LIGHT_SHIELD(1, 15), MEDIUM_SHIELD(2, 30), HEAVY_SHIELD(3, 60), NONE(0, 0);

	private final String text;
	private final int shieldBonus;
	private final int cost;

	private Shields(int shieldBonus, int cost) {
		this.text = generateEnumText(this.name());
		this.shieldBonus = shieldBonus;
		this.cost = cost;
	}

	@Override
	public Class<Shields> getImplementingClass() {
		return Shields.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		if (characterSheet.characterData.get(Fields.BASECLASS) == BaseClasses.WARRIOR)
			characterSheet.characterData.put(Fields.SHIELDS, this);
		else
			throw new InvalidCharacterClassException(String.format("%s is not a valid BaseClass for Shields.", characterSheet.characterData.get(
					Fields.BASECLASS).toString()));
	}

	public int getShieldBonus() {
		return shieldBonus;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return text;
	}
}
