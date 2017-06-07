package rpg_database.character_sheet;

public class FocusesLogic {

	private int focusImprovement;
	private Focuses focus;

	public FocusesLogic(Focuses focus) {
		this.focus = focus;
		this.focusImprovement = 0;
	}

	public FocusesLogic(FocusesLogic focusLogic) {
		this.focus = focusLogic.focus;
		this.focusImprovement = focusLogic.focusImprovement;
	}

	public int getFocusValue() {
		return focus.getFocusValue() + focusImprovement;
	}

	public Focuses getFocus() {
		return focus;
	}

	public void setFocusImprovement() {
		focusImprovement = 1;
	}
}
