package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rpg_database.character_sheet.interfaces.CustomSetter;

public class FocusesSet implements Set<Focuses>, CustomSetter<FocusesSet> {

	private final HashSet<Focuses> focusesSet;

	public FocusesSet(Focuses... focuses) {
		isMultipleTimesAddedFocusInTheParameterList(focuses);
		focusesSet = new HashSet<Focuses>();
		focusesSet.addAll(Arrays.asList(focuses));
	}

	public FocusesSet(FocusesSet focusesSet) {
		this((Focuses[]) focusesSet.toArray(new Focuses[focusesSet.size()]));
	}

	@Override
	public Class<FocusesSet> getImplementingClass() {
		return FocusesSet.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		characterSheet.characterData.put(Fields.FOCUS, new FocusesSet(this));
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
	public Iterator<Focuses> iterator() {
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

	@Override
	public boolean add(Focuses e) {
		return focusesSet.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return focusesSet.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> focusesCollection) {
		return focusesSet.containsAll(focusesCollection);
	}

	@Override
	public boolean addAll(Collection<? extends Focuses> focusesCollection) {
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
		for (Focuses focuse : focusesSet) {
			focusesNames.add(focuse.toString());
		}
		return String.join(", ", focusesNames);
	}

	private void isMultipleTimesAddedFocusInTheParameterList(Focuses[] focuses) {
		Focuses[] temp = focuses;
		for (int i = 0; i < focuses.length; i++) {
			for (int j = i + 1; j < focuses.length; j++) {
				if (focuses[i] == temp[j]) {
					focuses[i].setFocusValue();
				}
			}
		}
	}
}
