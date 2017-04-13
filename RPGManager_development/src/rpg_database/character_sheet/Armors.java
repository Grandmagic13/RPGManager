package rpg_database.character_sheet;

import static rpg_database.character_sheet.common.CharacterSheetCommon.generateEnumText;

import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Armors implements CustomSetter<Armors> {
	// TODO Implementing mechanism for Lining with Concealed Pockets
	// TODO Later, we should revise the set method, because it'll depends on,
	// which talent and/or specialization will the user choose.

	LIGHT_LEATHER(3, 0, 1), HEAVY_LEATHER(4, -1, 2), LIGHT_MAIL(5, -2, 3), HEAVY_MAIL(7, -3, 4), LIGHT_PLATE(8, -4, 5), HEAVY_PLATE(10, -5, 6),
	LIGHT_LEATHER_DUSTER(3, 0, 1), TAILORED_LEATHER_DUSTER(4, 0, 2), ROBE(0, 0, 0);

	private final String text;
	private final int armorRating;
	private final int armorPenalty;
	private final int strain;

	private Armors(int armorRating, int armorPenalty, int strain) {
		this.text = generateEnumText(this.name());
		this.armorRating = armorRating;
		this.armorPenalty = armorPenalty;
		this.strain = strain;
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
