package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.exceptions.InvalidLevelException;
import rpg_database.character_sheet.interfaces.CustomSetter;

public class SpecializationClassesSet implements Set<SpecializationClasses>, CustomSetter<SpecializationClassesSet> {

	final static int[] requiredLevelsForSpecializations = new int[] { 0, 6, 14, 22 };

	private HashSet<SpecializationClasses> specClassesSet;
	private CharacterSheet characterSheet;

	public SpecializationClassesSet(CharacterSheet characterSheet) {
		specClassesSet = new HashSet<>();
		this.characterSheet = characterSheet;
	}

	public SpecializationClassesSet(SpecializationClasses... specializationClasses) {
		specClassesSet = new HashSet<>();
		if (specializationClasses != null) {
			addAll(Arrays.asList(specializationClasses));
		}
	}

	@Override
	public boolean add(SpecializationClasses specializationClass) {
		if (characterSheet == null || isLevelMatchingNumberOfSpecializations(specClassesSet.size() + 1, characterSheet))
			return specClassesSet.add(specializationClass);
		else
			throw new InvalidLevelException(generateInvalidLevelMessage(specClassesSet.size() + 1));
	}

	@Override
	public boolean addAll(Collection<? extends SpecializationClasses> specClassCollection) {
		if (characterSheet == null || isLevelMatchingNumberOfSpecializations(specClassCollection.size(), characterSheet))
			return specClassesSet.addAll(specClassCollection);
		else
			throw new InvalidLevelException(generateInvalidLevelMessage(specClassCollection.size()));
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
		if (!isLevelMatchingNumberOfSpecializations(specClassesSet.size(), characterSheet)) {
			throw new InvalidLevelException(generateInvalidLevelMessage(specClassesSet.size()));
		}
		characterSheet.characterData.put(Fields.SPECIALIZATIONCLASSES, this);
		this.characterSheet = characterSheet;
	}

	private String generateInvalidLevelMessage(int numberOfSpecializations) {
		return String.format("Character can't take %s specialization(s) until level %s!", numberOfSpecializations,
				requiredLevelsForSpecializations[numberOfSpecializations]);
	}

	private boolean isLevelMatchingNumberOfSpecializations(int numberOfSpecializations, CharacterSheet characterSheet) {
		int requiredLevel;
		try {
			requiredLevel = requiredLevelsForSpecializations[numberOfSpecializations];
		} catch (ArrayIndexOutOfBoundsException e) {
			String message = String.format("Character can't take more specializations than %s!", requiredLevelsForSpecializations.length - 1);
			throw new InvalidLevelException(message);
		}
		return getCharacterLevel(characterSheet) >= requiredLevel;
	}

	private Integer getCharacterLevel(CharacterSheet characterSheet) {
		return characterSheet.<Integer>getData(Fields.LEVEL);
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
