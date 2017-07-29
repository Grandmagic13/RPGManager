package rpg_database.character_sheet;

public class Focus {

	private boolean isFocusImproved;
	private int focusValue;
	private Focuses focus;

	public Focus(Focuses focus) {
		this(focus, false);
	}

	public Focus(Focuses focus, boolean isImproved) {
		if (isImproved) {
			focusValue = 3;
			isFocusImproved = true;
		} else {
			focusValue = 2;
			isFocusImproved = false;
		}
		this.focus = focus;
	}

	public boolean isFocusImproved() {
		return isFocusImproved;
	}

	public int getFocuseValue() {
		return focusValue;
	}

	public Focuses getFocus() {
		return focus;
	}

	public void MakeFocusImproved() {
		setIsFocusImproved(true);
	}

	@Override
	public boolean equals(Object obj) {
		Focuses inputFocus = ((Focus) obj).focus;
		return focus.name().equals(inputFocus.name());
	}

	@Override
	public int hashCode() {
		return this.focus.hashCode();
	}

	private void setIsFocusImproved(boolean isImproved) {
		if (isImproved) {
			focusValue = 3;
			isFocusImproved = true;
		}
	}
}
