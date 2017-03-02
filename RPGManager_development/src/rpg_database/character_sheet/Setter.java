package rpg_database.character_sheet;

public interface Setter<DATA extends Setter<DATA>> {
	public Class<DATA> getImplementingClass();

	public void setSelfInSheet(CharacterSheet characterSheet);
}
