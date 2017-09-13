package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.HashMap;

import rpg_database.character_sheet.exceptions.CrossDependencyException;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetter;

public class LevelAndXPCrossDependencyCheck implements MultipleFieldsGetterSetter<LevelAndXPCrossDependencyCheck, Integer> {

	private int xp;
	private int level;
	private final int maxLevel = 22;
	private final int maxXp = 70000;

	private final HashMap<Integer, Integer> xpNeedingForLevelMap = fillUpWithLevelXpPairs();

	public LevelAndXPCrossDependencyCheck() {
		xp = 0;
		level = 1;
	}

	public int GetXP() {
		return xp;
	}

	@Override
	public Class<LevelAndXPCrossDependencyCheck> getImplementingClass() {
		return LevelAndXPCrossDependencyCheck.class;
	}

	@Override
	public Class<Integer> getDataTypeClass() {
		return Integer.class;
	}

	@Override
	public void setSelfValueByField(Fields field, Integer value) {
		if (field == Fields.XP) {
			if (value > maxXp)
				throw new CrossDependencyException("The xp gap is: " + maxXp + "!");
			if (level == 1 || xpNeedingForLevelMap.get(level) <= value) {
				xp = value;
				while (xpNeedingForLevelMap.get(level) < xp && xpNeedingForLevelMap.get(level + 1) <= xp) {
					level++;
				}
			} else {
				throw new CrossDependencyException("Your level is higher than the given xp!");
			}
		} else if (field == Fields.LEVEL) {
			if (value > maxLevel)
				throw new CrossDependencyException("The level gap is: " + maxLevel + "!");
			if (xp == 0 || xpNeedingForLevelMap.get(value) <= xp) {
				level = value;
				xp = xpNeedingForLevelMap.get(value);
			} else
				throw new CrossDependencyException("You don't have enough xp for this level!");
		} else {
			throw new InvalidParameterException("It's not a valid Field type!");
		}
	}

	@Override
	public Integer getStoredValueByField(Fields field) {
		if (field == Fields.XP)
			return xp;
		else if (field == Fields.LEVEL)
			return level;
		throw new InvalidParameterException("It's not a valid Field type!");
	}

	private HashMap<Integer, Integer> fillUpWithLevelXpPairs() {
		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
		temp.put(1, 0);
		temp.put(2, 2000);
		temp.put(3, 4000);
		temp.put(4, 6000);
		temp.put(5, 8000);
		temp.put(6, 10000);
		temp.put(7, 13000);
		temp.put(8, 16000);
		temp.put(9, 19000);
		temp.put(10, 22000);
		temp.put(11, 25000);
		temp.put(12, 2800);
		temp.put(13, 32000);
		temp.put(14, 36000);
		temp.put(15, 40000);
		temp.put(16, 44000);
		temp.put(17, 48000);
		temp.put(18, 52000);
		temp.put(19, 56000);
		temp.put(20, 60000);
		temp.put(21, 65000);
		temp.put(22, 70000);
		return temp;
	}
}
