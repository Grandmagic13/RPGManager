package rpg_database.character_sheet;

public interface CustomSetter<DATA extends CustomSetter<DATA>> {
	public Class<DATA> getImplementingClass();

	public void setSelfInSheet(CharacterSheet characterSheet);
}
