package rpg_database.character_sheet;

import rpg_database.character_sheet.exceptions.InvalidFocusesSetException;

public class Focus {

	private static final int FOCUS_VALUE_DEFAULT = 2;
	private static final int FOCUS_VALUE_IMPROVED = 3;
	private int value;
	private Focuses focus;

	public Focus(Focuses focus) {
		this(focus, false);
	}

	public Focus(Focuses focus, boolean isFocusImproved) {
		value = isFocusImproved ? FOCUS_VALUE_IMPROVED : FOCUS_VALUE_DEFAULT;
		this.focus = focus;
	}

	public boolean isFocusImproved() {
		return value > 2;
	}

	public int getValue() {
		return value;
	}

	public Focuses getFocus() {
		return focus;
	}

	public void makeFocusImproved() {
		if (this.isFocusImproved())
			throw new InvalidFocusesSetException(focus.name() + " is already improved!");
		setIsFocusImproved(true);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Focus inputFocus = ((Focus) obj);
		return focus.equals(inputFocus.focus) && value == inputFocus.value;
	}

	@Override
	public int hashCode() {
		return this.focus.hashCode();
	}

	private void setIsFocusImproved(boolean isFocusImproved) {
		if (isFocusImproved) {
			value = 3;
		}
	}
}
