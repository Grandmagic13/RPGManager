package rpg_database.character_sheet;

public class Focus {

	private boolean isFocusImproved;
	private int focusImprovementValue;
	private Focuses focus;

	public Focus(Focuses focus) {
		this(focus, false, false);
	}

	public Focus(Focuses focus, boolean isImproved) {
		this(focus, isImproved, false);
	}

	public Focus(Focuses focus, boolean isImproved, boolean isImprovedSecondTime) {
		this.focus = focus;
		if (isImprovedSecondTime && isImproved) {
			focusImprovementValue = 3;
			this.isFocusImproved = true;
		} else if (isImproved) {
			focusImprovementValue = 2;
			this.isFocusImproved = true;
		} else {
			focusImprovementValue = 0;
			this.isFocusImproved = false;
		}
	}

	public boolean getIsFocusImproved() {
		return isFocusImproved;
	}

	public int getFocuseImprovementValue() {
		return focusImprovementValue;
	}

	public Focuses getFocus() {
		return focus;
	}

	public void MakeFocusImproved() {
		setIsFocusImproved(true);
	}

	@Override
	public boolean equals(Object obj) {
		Focuses inputFocus = (Focuses) obj;
		return focus.name().equals(inputFocus.name());
	}

	private void setIsFocusImproved(boolean isImproved) {
		if (this.isFocusImproved) {
			focusImprovementValue = 3;
		} else {
			focusImprovementValue = 2;
			isFocusImproved = true;
		}
	}
}
