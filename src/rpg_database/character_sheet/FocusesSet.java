package rpg_database.character_sheet;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rpg_database.character_sheet.exceptions.InvalidFocusesSetException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public class FocusesSet implements Set<Focus>, CustomSetter<FocusesSet> {

	private HashSet<Focus> focusesSet;

	public FocusesSet() {
		focusesSet = new HashSet<Focus>();
	}

	public FocusesSet(Focuses... focuses) {
		focusesSet = new HashSet<Focus>();
		focusesSet = findDuplicates(focuses);

	}

	@Override
	public Class<FocusesSet> getImplementingClass() {
		return FocusesSet.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		characterSheet.characterData.put(Fields.FOCUSES, this);
	}

	@Override
	public int size() {
		return focusesSet.size();
	}

	@Override
	public boolean isEmpty() {
		return focusesSet.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return focusesSet.contains(o);
	}

	@Override
	public Iterator<Focus> iterator() {
		return focusesSet.iterator();
	}

	@Override
	public Object[] toArray() {
		return focusesSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return focusesSet.toArray(a);
	}

	/**
	 * Does some thing in old style.
	 *
	 * @deprecated use {@link #new()} instead.
	 */
	@Deprecated
	@Override
	public boolean add(Focus focus) {
		return focusesSet.add(focus);
	}

	public boolean add(Focuses focus) {
		Focus focusInDb = getFocus(focus, focusesSet);
		if (focusInDb == null) {
			Focus tempFocus = new Focus(focus);
			focusesSet.add(tempFocus);
			return tempFocus.isFocusImproved();
		} else {
			if (focusInDb.isFocusImproved()) {
				throw new InvalidFocusesSetException(focus.name() + " is already improved!");
			}
			focusInDb.makeFocusImproved();
			return focusInDb.isFocusImproved();
		}
	}

	// TODO comment dekorator needed
	public boolean addAllFocuses(Collection<? extends Focuses> focusesCollection) {
		return true;
	}

	@Override
	public boolean remove(Object o) {
		return focusesSet.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> focusesCollection) {
		return focusesSet.containsAll(focusesCollection);
	}

	/**
	 * Does some thing in old style.
	 *
	 * @deprecated use {@link #new()} instead.
	 */
	@Deprecated
	@Override
	public boolean addAll(Collection<? extends Focus> focusesCollection) {
		return focusesSet.addAll(focusesCollection);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return focusesSet.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return focusesSet.removeAll(c);
	}

	@Override
	public void clear() {
		focusesSet.clear();
	}

	@Override
	public boolean equals(Object obj) {
		return focusesSet.equals(obj);
	}

	@Override
	public String toString() {
		ArrayList<String> focusesNames = new ArrayList<>();
		for (Focus focuse : focusesSet) {
			focusesNames.add(focuse.toString());
		}
		return String.join(", ", focusesNames);
	}

	private HashSet<Focus> findDuplicates(Focuses... focuses) {
		final HashSet<Focus> setToReturn = new HashSet<Focus>();
		final HashSet<Focus> setTemp = new HashSet<Focus>();

		for (Focuses focus : focuses) {
			Focus tempFocus = new Focus(focus);
			if (!setTemp.add(tempFocus)) {
				Focus focusEnum = getFocus(focus, setToReturn);
				focusEnum.makeFocusImproved();
			} else {
				setToReturn.add(tempFocus);
			}
		}
		return setToReturn;
	}

	private Focus getFocus(Focuses focus, HashSet<Focus> focusesList) {
		for (Focus containedFocus : focusesList) {
			if (containedFocus.getFocus().equals(focus))
				return containedFocus;
		}
		return null;
	}
}
