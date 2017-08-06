package rpg_database.character_sheet;

public class Focus {

	private boolean isImproved;
	private int value;
	private Focuses focus;

	public Focus(Focuses focus) {
		this(focus, false);
	}

	public Focus(Focuses focus, boolean isFocusImproved) {
		if (isFocusImproved) {
			value = 3;
			isImproved = true;
		} else {
			value = 2;
			isImproved = false;
		}
		this.focus = focus;
	}

	public boolean isFocusImproved() {
		return isImproved;
	}

	public int getValue() {
		return value;
	}

	public Focuses getFocus() {
		return focus;
	}

	public void makeFocusImproved() {
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

	private void setIsFocusImproved(boolean isFocusImproved) {
		if (isFocusImproved) {
			value = 3;
			isImproved = true;
		}
	}
}
