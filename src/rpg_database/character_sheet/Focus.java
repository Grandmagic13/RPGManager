package rpg_database.character_sheet;

public class Focus {

	private boolean isFocusImproved;
	private int focusImprovementValue;
	private Focuses focus;

	public Focus(Focuses focus) {
		this(focus, false);
	}

	public Focus(Focuses focus, boolean isImproved) {
		if (isImproved) {
			focusImprovementValue = 3;
			isFocusImproved = true;
		} else {
			focusImprovementValue = 2;
			isFocusImproved = false;
		}
		this.focus = focus;
	}

	public boolean isFocusImproved() {
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
		if (isImproved) {
			focusImprovementValue = 3;
			isFocusImproved = true;
		}
	}
}
