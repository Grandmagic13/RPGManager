package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public class SpecializationClassesSet implements Set<SpecializationClasses>, CustomSetter<SpecializationClassesSet> {

	private HashSet<SpecializationClasses> specClassesSet;

	public SpecializationClassesSet() {
		specClassesSet = new HashSet<>();
	}

	public SpecializationClassesSet(SpecializationClasses specializationClass) {
		specClassesSet = new HashSet<>();
		specClassesSet.add(specializationClass);
	}

	@Override
	public boolean add(SpecializationClasses specializationClass) {
		return specClassesSet.add(specializationClass);
	}

	@Override
	public boolean addAll(Collection<? extends SpecializationClasses> specializationClassCollection) {
		return specClassesSet.addAll(specializationClassCollection);
	}

	@Override
	public void clear() {
		specClassesSet.clear();
	}

	@Override
	public boolean contains(Object o) {
		return specClassesSet.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return specClassesSet.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return specClassesSet.isEmpty();
	}

	@Override
	public Iterator<SpecializationClasses> iterator() {
		return specClassesSet.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return specClassesSet.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return specClassesSet.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return specClassesSet.retainAll(c);
	}

	@Override
	public int size() {
		return specClassesSet.size();
	}

	@Override
	public Object[] toArray() {
		return specClassesSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return specClassesSet.toArray(a);
	}

	@Override
	public boolean equals(Object obj) {
		return specClassesSet.equals(obj);
	}

	@Override
	public Class<SpecializationClassesSet> getImplementingClass() {
		return SpecializationClassesSet.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		BaseClasses baseClass = characterSheet.getData(Fields.BASECLASS);
		for (SpecializationClasses specializationClass : specClassesSet) {
			if (!specializationClass.isBaseClassCompatible(baseClass)) {
				throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", baseClass, specializationClass.toString()));
			}
		}
		characterSheet.characterData.put(Fields.SPECIALIZATIONCLASSES, this);

	}

	@Override
	public String toString() {
		ArrayList<String> specClassNames = new ArrayList<>();
		for (SpecializationClasses specializationClass : specClassesSet) {
			specClassNames.add(specializationClass.toString());
		}
		return String.join(", ", specClassNames);
	}

}
