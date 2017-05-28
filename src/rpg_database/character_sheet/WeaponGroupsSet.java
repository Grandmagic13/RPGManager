package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rpg_database.character_sheet.interfaces.CustomSetter;

public class WeaponGroupsSet implements Set<WeaponGroups>, CustomSetter<WeaponGroupsSet> {

	private final HashSet<WeaponGroups> weaponGroupsSet;

	public WeaponGroupsSet(WeaponGroups... weaponGroups) {
		weaponGroupsSet = new HashSet<WeaponGroups>();
		weaponGroupsSet.addAll(Arrays.asList(weaponGroups));
	}

	public WeaponGroupsSet(WeaponGroupsSet weaponGroupsSet) {
		this((WeaponGroups[]) weaponGroupsSet.toArray(new WeaponGroups[weaponGroupsSet.size()]));
	}

	@Override
	public Class<WeaponGroupsSet> getImplementingClass() {
		return WeaponGroupsSet.class;
	}

	@Override
	public void setSelfInSheet(CharacterSheet characterSheet) {
		characterSheet.characterData.put(Fields.WEAPON_GROUPS, new WeaponGroupsSet(this));
	}

	@Override
	public boolean add(WeaponGroups weaponGroups) {
		return weaponGroupsSet.add(weaponGroups);
	}

	@Override
	public boolean addAll(Collection<? extends WeaponGroups> weaponGroupsCollection) {
		return weaponGroupsSet.addAll(weaponGroupsCollection);
	}

	@Override
	public void clear() {
		weaponGroupsSet.clear();
	}

	@Override
	public boolean contains(Object o) {
		return weaponGroupsSet.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return weaponGroupsSet.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return weaponGroupsSet.isEmpty();
	}

	@Override
	public Iterator<WeaponGroups> iterator() {
		return weaponGroupsSet.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return weaponGroupsSet.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return weaponGroupsSet.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return weaponGroupsSet.retainAll(c);
	}

	@Override
	public int size() {
		return weaponGroupsSet.size();
	}

	@Override
	public Object[] toArray() {
		return weaponGroupsSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return weaponGroupsSet.toArray(a);
	}

	@Override
	public boolean equals(Object obj) {
		return weaponGroupsSet.equals(obj);
	}

	@Override
	public String toString() {
		ArrayList<String> weaponGroupsNames = new ArrayList<>();
		for (WeaponGroups weaponGroup : weaponGroupsSet) {
			weaponGroupsNames.add(weaponGroup.toString());
		}
		return String.join(", ", weaponGroupsNames);
	}

}
