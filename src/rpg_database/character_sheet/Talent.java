package rpg_database.character_sheet;

public class Talent {

	private final TalentLevels talentLevel;

	public Talent() {
		this(TalentLevels.NOVICE);
	}

	public Talent(TalentLevels talentLevel) {
		this.talentLevel = talentLevel;
	}

	public TalentLevels getTalentLevel() {
		return talentLevel;
	}
}
