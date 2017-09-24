package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rpg_database.character_sheet.interfaces.CustomSetter;

public class LanguagesSet implements Set<Languages>, CustomSetter<LanguagesSet> {

	private final HashSet<Languages> languagesSet;

	public LanguagesSet(Languages... languages) {
		languagesSet = new HashSet<Languages>();
		addAll(Arrays.asList(languages));
	}

	public LanguagesSet(LanguagesSet languagesSet) {
		this.languagesSet = new HashSet<Languages>();
		addAll(languagesSet);
	}

	@Override
	public Class<LanguagesSet> getImplementingClass() {
		return LanguagesSet.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		characterSheet.characterData.put(Fields.LANGUAGES, new LanguagesSet(this));
	}

	@Override
	public boolean add(Languages languages) {
		return languagesSet.add(languages);
	}

	@Override
	public boolean addAll(Collection<? extends Languages> languagesCollection) {
		return languagesSet.addAll(languagesCollection);
	}

	@Override
	public void clear() {
		languagesSet.clear();
	}

	@Override
	public boolean contains(Object o) {
		return languagesSet.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return languagesSet.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return languagesSet.isEmpty();
	}

	@Override
	public Iterator<Languages> iterator() {
		return languagesSet.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return languagesSet.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return languagesSet.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return languagesSet.retainAll(c);
	}

	@Override
	public int size() {
		return languagesSet.size();
	}

	@Override
	public Object[] toArray() {
		return languagesSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return languagesSet.toArray(a);
	}

	@Override
	public boolean equals(Object obj) {
		return languagesSet.equals(obj);
	}

	@Override
	public String toString() {
		ArrayList<String> languagesNames = new ArrayList<>();
		for (Languages language : languagesSet) {
			languagesNames.add(language.toString());
		}
		return String.join(", ", languagesNames);
	}

}
