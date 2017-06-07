package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rpg_database.character_sheet.interfaces.CustomSetter;

public class FocusesSet implements Set<FocusesLogic>, CustomSetter<FocusesSet> {

	private final HashSet<FocusesLogic> focusesSet;

	public FocusesSet(FocusesLogic... focuses) {
		focusesSet = new HashSet<FocusesLogic>();
		ArrayList<FocusesLogic> focusList = findMultipleTimesAddedFocusesInTheParameterList(focuses);
		focusesSet.addAll(focusList);
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
	public Iterator<FocusesLogic> iterator() {
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
	public boolean add(FocusesLogic e) {
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
	public boolean addAll(Collection<? extends FocusesLogic> focusesCollection) {
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
		for (FocusesLogic focuse : focusesSet) {
			focusesNames.add(focuse.toString());
		}
		return String.join(", ", focusesNames);
	}

	private ArrayList<FocusesLogic> findMultipleTimesAddedFocusesInTheParameterList(FocusesLogic[] focuses) {
		FocusesLogic[] temp = focuses;
		ArrayList<FocusesLogic> result = new ArrayList<FocusesLogic>();
		for (int i = 0; i < focuses.length; i++) {
			for (int j = i + 1; j < focuses.length; j++) {
				if (temp[j] != null && focuses[i] != null)
					if (focuses[i].getFocus().equals(temp[j].getFocus())) {
						focuses[i].setFocusImprovement();
						focuses[j] = null;
						temp[j] = null;
					}
			}
			if (focuses[i] != null) {
				result.add(focuses[i]);
			}
		}
		return result;
	}

	public FocusesLogic getRightFocusFromSet(Focuses focus) {
		for (FocusesLogic containedFocus : this) {
			containedFocus.getFocus().equals(focus);
			return containedFocus;
		}
		return null;
	}
}
