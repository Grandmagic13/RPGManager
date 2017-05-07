package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rpg_database.character_sheet.exceptions.InvalidBackgroundException;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;
import rpg_database.character_sheet.exceptions.InvalidSpecializationRequirementException;
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
		if (characterSheet != null) {
			checkCharacterSheetInconsistencies(characterSheet, specializationClass);
		} else {
			throw new NullPointerException("Can't add null element as specializationClass enum!");
		}
		return specClassesSet.add(specializationClass);
	}

	@Override
	public boolean addAll(Collection<? extends SpecializationClasses> specClassCollection) {
		if (characterSheet != null) {
			checkCharacterSheetInconsistencies(characterSheet, specClassCollection);
		}
		return specClassesSet.addAll(specClassCollection);
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
		checkCharacterSheetInconsistencies(characterSheet);
		characterSheet.characterData.put(Fields.SPECIALIZATIONCLASSES, this);
		this.characterSheet = characterSheet;
	}

	private void checkCharacterSheetInconsistencies(CharacterSheet characterSheet) {
		checkCharacterSheetInconsistencies(specClassesSet.size(), characterSheet, specClassesSet);
	}

	private void checkCharacterSheetInconsistencies(CharacterSheet characterSheet, SpecializationClasses specializationClass) {
		checkCharacterSheetInconsistencies(specClassesSet.size() + 1, characterSheet, Arrays.asList(specializationClass));
	}

	private void checkCharacterSheetInconsistencies(CharacterSheet characterSheet, Collection<? extends SpecializationClasses> specClassCollection) {
		checkCharacterSheetInconsistencies(specClassesSet.size() + specClassCollection.size(), characterSheet, specClassCollection);
	}

	private void checkCharacterSheetInconsistencies(int numberOfSpecializations, CharacterSheet characterSheet,
			Collection<? extends SpecializationClasses> specClassCollection) {
		BaseClasses baseClass = characterSheet.getData(Fields.BASECLASS);
		for (SpecializationClasses specializationClass : specClassCollection) {
			checkBaseClassCompatibility(baseClass, specializationClass);
			checkBackgroundCompatibility(characterSheet, specializationClass);
		}
		checkAttributeCompatibility(characterSheet, specClassCollection);
		checkIfLevelMatchesNumberOfSpecializations(numberOfSpecializations, characterSheet);
	}

	private void checkBaseClassCompatibility(BaseClasses baseClass, SpecializationClasses specializationClass) {
		if (!specializationClass.isBaseClassCompatible(baseClass)) {
			throw new InvalidCharacterClassException(String.format("%s is not a base class of %s", baseClass, specializationClass.toString()));
		}
	}

	private void checkBackgroundCompatibility(CharacterSheet characterSheet, SpecializationClasses specializationClass) {
		if (specializationClass.isBackgroundRestricted()) {
			Background characterBackground = characterSheet.getData(Fields.BACKGROUND);
			if (!specializationClass.getRestrictedBackgrounds().contains(characterBackground)) {
				throw new InvalidBackgroundException(String.format("Can not take %s specialization with the %s background!", specializationClass
						.toString(), characterBackground.toString()));
			}
		}
	}

	private void checkAttributeCompatibility(CharacterSheet characterSheet, Collection<? extends SpecializationClasses> specClassCollection) {
		HashSet<SpecializationClasses> specializationClassesWithUnfullfilledRequirements = new HashSet<>();
		for (SpecializationClasses specializationClass : specClassCollection) {
			HashMap<Fields, Integer> requiredAttributeValues = specializationClass.getRequiredAttributeValues();
			for (Fields attributeValueField : requiredAttributeValues.keySet()) {
				Integer characterSheetValue = characterSheet.getData(attributeValueField);
				Integer requiredValue = requiredAttributeValues.get(attributeValueField);
				if (characterSheetValue < requiredValue) {
					specializationClassesWithUnfullfilledRequirements.add(specializationClass);
				}
			}
		}
		if (!specializationClassesWithUnfullfilledRequirements.isEmpty()) {
			throw new InvalidSpecializationRequirementException(generateInsufficientStatsMessage(specializationClassesWithUnfullfilledRequirements));
		}
	}

	private String generateInsufficientStatsMessage(HashSet<SpecializationClasses> specializationClasses) {
		String specializationClassesString = "";
		for (SpecializationClasses specializationClass : specializationClasses) {
			specializationClassesString = String.join(getDelimiter(specializationClassesString), specializationClassesString, specializationClass
					.toString());
		}
		return String.format("Insufficient stats for specialization(s): %s!", specializationClassesString);
	}

	private CharSequence getDelimiter(String specializationClassesString) {
		return specializationClassesString.isEmpty() ? "" : ", ";
	}

	private String generateInvalidLevelMessage(int numberOfSpecializations) {
		return String.format("Character can't take %s specialization(s) until level %s!", numberOfSpecializations,
				requiredLevelsForSpecializations[numberOfSpecializations]);
	}

	private void checkIfLevelMatchesNumberOfSpecializations(int numberOfSpecializations, CharacterSheet characterSheet) {
		int requiredLevel;
		try {
			requiredLevel = requiredLevelsForSpecializations[numberOfSpecializations];
		} catch (ArrayIndexOutOfBoundsException e) {
			String message = String.format("Character can't take more specializations than %s!", requiredLevelsForSpecializations.length - 1);
			throw new InvalidSpecializationRequirementException(message);
		}
		if (getCharacterLevel(characterSheet) < requiredLevel) {
			throw new InvalidSpecializationRequirementException(generateInvalidLevelMessage(numberOfSpecializations));
		}
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
