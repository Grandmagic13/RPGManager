package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.BaseClasses;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;
import rpg_database.character_sheet.Shields;
import rpg_database.character_sheet.exceptions.InvalidCharacterClassException;

public class ShieldsUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void expectException_SetArmorsMalformedInput() {
		expectExceptionWithMessage(InvalidParameterException.class,
				"class java.lang.String value is not an instance of class rpg_database.character_sheet.Shields");
		final String malformedInput = "MALFORMED INPUT";
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.SHIELDS, malformedInput);
	}

	private void expectExceptionWithMessage(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
		thrown.expectMessage(message);
	}

	@Test
	public void expectException_SetShieldForMage() {
		thrown.expect(InvalidCharacterClassException.class);
		thrown.expectMessage(String.format("%s is not a valid BaseClass for Shields.", BaseClasses.MAGE.toString()));
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.MAGE);
		characterSheet.setData(Fields.SHIELDS, Shields.MEDIUM_SHIELD);
	}

	@Test
	public void testSetLightShield() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		characterSheet.setData(Fields.SHIELDS, Shields.LIGHT_SHIELD);
		Shields actualValue = characterSheet.getData(Fields.SHIELDS);
		assertEquals(Shields.LIGHT_SHIELD, actualValue);
	}

	@Test
	public void testGetCostLightShield() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		characterSheet.setData(Fields.SHIELDS, Shields.LIGHT_SHIELD);
		Shields actualValue = characterSheet.getData(Fields.SHIELDS);
		assertEquals(15, actualValue.getCost());
	}

	@Test
	public void testGetShieldBonusLightShield() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.BASECLASS, BaseClasses.WARRIOR);
		characterSheet.setData(Fields.SHIELDS, Shields.LIGHT_SHIELD);
		Shields actualValue = characterSheet.getData(Fields.SHIELDS);
		assertEquals(1, actualValue.getShieldBonus());
	}

	@Test
	public void testGetDefaultDefenseAnderSurvivor() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_PLATE);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(10, actualArmorValue + Armors.class.cast(characterSheet.getData(Fields.ARMOR_TYPE)).getArmorRating() + Shields.class.cast(
				characterSheet.getData(Fields.SHIELDS)).getShieldBonus());
	}

	@Test
	public void testGetDefaultDefenseAnderSurvivorWithShield() {
		CharacterSheet characterSheet = new CharacterSheet("characterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_PLATE);
		characterSheet.setData(Fields.SHIELDS, Shields.LIGHT_SHIELD);
		int actualArmorValue = characterSheet.getData(Fields.ARMOR_RATING);
		assertEquals(11, actualArmorValue + Armors.class.cast(characterSheet.getData(Fields.ARMOR_TYPE)).getArmorRating() + Shields.class.cast(
				characterSheet.getData(Fields.SHIELDS)).getShieldBonus());
	}
}
