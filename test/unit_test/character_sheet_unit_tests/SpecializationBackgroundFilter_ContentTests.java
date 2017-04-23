package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.SpecializationClasses;

@RunWith(Parameterized.class)
public class SpecializationBackgroundFilter_ContentTests {

	// Description:
	//
	// This class tests background restriction contents

	@Parameters(name = "{index}: Specialization: ''{0}'' Restricted: ''{2}'' Expected backgrounds: ''{1}''")
	public static Collection<Object[]> data() {
		final HashMap<SpecializationClasses, HashSet<Background>> restrictedSpecializationClassesBackgrounds = new HashMap<>();
		restrictedSpecializationClassesBackgrounds.put(SpecializationClasses.KEEPER, Background.elfMages());
		restrictedSpecializationClassesBackgrounds.put(SpecializationClasses.LEGIONNAIRE_SCOUT, Background.dwarfRogues());
		restrictedSpecializationClassesBackgrounds.put(SpecializationClasses.LEGIONNAIRE_WARRIOR, Background.dwarfWarriors());
		restrictedSpecializationClassesBackgrounds.put(SpecializationClasses.LYRIUM_WARRIOR, Background.humanAndElfWarriors());
		restrictedSpecializationClassesBackgrounds.put(SpecializationClasses.SAAREBAS, Background.qunariMages());

		ArrayList<Object[]> parameters = new ArrayList<>();
		for (SpecializationClasses specializationClass : SpecializationClasses.values()) {
			if (restrictedSpecializationClassesBackgrounds.containsKey(specializationClass)) {
				parameters.add(new Object[] { specializationClass, restrictedSpecializationClassesBackgrounds.get(specializationClass), true });
			} else {
				parameters.add(new Object[] { specializationClass, new HashSet<Background>(), false });
			}
		}
		return parameters;
	}

	@Parameter(0)
	public SpecializationClasses specializationClass;

	@Parameter(1)
	public HashSet<Background> expectedBackgrounds;

	@Parameter(2)
	public boolean expectedRestriction;

	@Test
	public void testCheckRestrictedBackgroundsContent() {
		assertEquals(expectedBackgrounds, specializationClass.getRestrictedBackgrounds());
	}

	@Test
	public void testCheckIfSpecializationClassIsRestricted() {
		assertEquals(expectedBackgrounds, specializationClass.getRestrictedBackgrounds());
	}
}