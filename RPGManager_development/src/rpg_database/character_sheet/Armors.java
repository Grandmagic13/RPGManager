package rpg_database.character_sheet;

import rpg_database.character_sheet.interfaces.CustomSetter;

public enum Armors implements CustomSetter<Armors> {
	// TODO Implementing mechanism for Lining with Concealed Pockets
	// TODO Later, we should revise the set method, because it'll depends on,
	// which talent and/or specialization will the user choose.

	LIGHT_LEATHER(3, 0, 15), HEAVY_LEATHER(4, -1, 30), LIGHT_MAIL(5, -2, 50), HEAVY_MAIL(7, -3, 75), LIGHT_PLATE(8, -4, 100),
	HEAVY_PLATE(10, -5, 150), LIGHT_LEATHER_DUSTER(3, 0, 15), TAILORED_LEATHER_DUSTER(4, 0, 45), NONE(0, 0, 0);

	private final int armorRating;
	private final int armorPenalty;
	private final int cost;

	Armors(int armorRating, int armorPenalty, int cost) {
		this.armorRating = armorRating;
		this.armorPenalty = armorPenalty;
		this.cost = cost;
	}

	@Override
	public Class<Armors> getImplementingClass() {
		return Armors.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		characterSheet.characterData.put(Fields.ARMOR_TYPE, this);
	}

	public int getCost() {
		return this.cost;
	}

	public int getArmorPenalty() {
		return this.armorPenalty;
	}

	public int getArmorRating() {
		return this.armorRating;
	}

}
