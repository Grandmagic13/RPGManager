package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import rpg_database.character_sheet.common.FieldRules;
import rpg_database.character_sheet.common.FieldRulesFactory;
import rpg_database.character_sheet.common.Keys;
import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Armors implements CustomSetter<Armors> {
	// TODO Implementing mechanism for Lining with Concealed Pockets
	// TODO Later, we should revise the set method, because it'll depends on,
	// which talent and/or specialization will the user choose.

	LIGHT_LEATHER, HEAVY_LEATHER, LIGHT_MAIL, HEAVY_MAIL, LIGHT_PLATE, HEAVY_PLATE, LIGHT_LEATHER_DUSTER, TAILORED_LEATHER_DUSTER, ROBE;

	private final String text;
	private final int armorRating;
	private final int armorPenalty;
	private final int strain;

	private Armors() {
		this.text = generateEnumText(this.name());

		FieldRules armorsRules = FieldRulesFactory.getFieldRules(FieldRulesFactory.ARMORS);
		armorRating = armorsRules.getIntegerForField(this, Keys.ARMOR_RATING);
		armorPenalty = armorsRules.getIntegerForField(this, Keys.ARMOR_PENALTY);
		strain = armorsRules.getIntegerForField(this, Keys.STRAIN);
	}

	@Override
	public Class<Armors> getImplementingClass() {
		return Armors.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		characterSheet.characterData.put(Fields.ARMOR_TYPE, this);
		characterSheet.characterData.put(Fields.ARMOR_RATING, this.getArmorRating());
	}

	@Override
	public String toString() {
		return text;
	}

	public int getArmorPenalty() {
		return this.armorPenalty;
	}

	public int getArmorRating() {
		return this.armorRating;
	}

	public int getStrain() {
		return strain;
	}

}
