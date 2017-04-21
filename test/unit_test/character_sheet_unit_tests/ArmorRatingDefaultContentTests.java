package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import rpg_database.character_sheet.Armors;

@RunWith(Parameterized.class)
public class ArmorRatingDefaultContentTests {

	@Parameters(name = "Amror : ''{0}'' DefaultArmorRating :''{1}'' DefaultArmorPenalty :''{2}''")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { Armors.LIGHT_LEATHER, 3, 0, 1 }, { Armors.HEAVY_LEATHER, 4, -1, 2 }, { Armors.LIGHT_MAIL, 5, -2, 3 },
				{ Armors.HEAVY_MAIL, 7, -3, 4 }, { Armors.LIGHT_PLATE, 8, -4, 5 }, { Armors.HEAVY_PLATE, 10, -5, 6 }, { Armors.LIGHT_LEATHER_DUSTER,
						3, 0, 1 }, { Armors.TAILORED_LEATHER_DUSTER, 4, 0, 2 },

				{ Armors.ROBE, 0, 0, 0 } });
	}

	@Parameter(0)
	public Armors armor;

	@Parameter(1)
	public int defaultArmorRating;

	@Parameter(2)
	public int defaultArmorPenalty;

	@Parameter(3)
	public int defaultStrain;

	@Test
	public void testGetDefaultArmorRating() {
		int actualValue = armor.getArmorRating();
		assertEquals(defaultArmorRating, actualValue);
	}

	@Test
	public void testGetDefaultAmorPenalty() {
		int actualValue = armor.getArmorPenalty();
		assertEquals(defaultArmorPenalty, actualValue);
	}

	@Test
	public void testGetDefaultStrain() {
		int actualValue = armor.getStrain();
		assertEquals(defaultStrain, actualValue);
	}
}
