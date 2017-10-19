package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.Background;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

public class SpeedAndDefenseSupportTests {

	@Test
	public void SetDexterity3GetSpeed13() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.ROBE);
		characterSheet.setData(Fields.DEXTERITY_VALUE, 3);
		assertEquals(13, (int) characterSheet.getData(Fields.SPEED));
	}

	@Test
	public void SetDexterityMinus1GetSpeed9() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.ROBE);
		characterSheet.setData(Fields.DEXTERITY_VALUE, -1);
		assertEquals(9, (int) characterSheet.getData(Fields.SPEED));
	}

	@Test
	public void SetDexterityMinus15GetSpeed0() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.ROBE);
		characterSheet.setData(Fields.DEXTERITY_VALUE, -15);
		assertEquals(0, (int) characterSheet.getData(Fields.SPEED));
	}

	@Test
	public void SetMagic5GetSpeedDefault() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.ROBE);
		characterSheet.setData(Fields.MAGIC_VALUE, 5);
		assertEquals(10, (int) characterSheet.getData(Fields.SPEED));
	}

	@Test
	public void SetBackgroundToCityElfGetSpeed12() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.ROBE);
		characterSheet.setData(Fields.BACKGROUND, Background.CITY_ELF);
		assertEquals(12, (int) characterSheet.getData(Fields.SPEED));
	}

	@Test
	public void SetLightPlateGetSpeed6() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.LIGHT_PLATE);
		assertEquals(6, (int) characterSheet.getData(Fields.SPEED));
	}

	@Test
	public void SetBackgroundToCityElfSetArmorHeavyLeatherGetSpeed11() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BACKGROUND, Background.CITY_ELF);
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_LEATHER);
		assertEquals(11, (int) characterSheet.getData(Fields.SPEED));
	}

	@Test
	public void SetBackgroundToCityElfSetDexterity3SetArmorHeavyPlateGetSpeed10() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.BACKGROUND, Background.CITY_ELF);
		characterSheet.setData(Fields.DEXTERITY_VALUE, 3);
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.HEAVY_PLATE);
		assertEquals(10, (int) characterSheet.getData(Fields.SPEED));
	}

	@Test
	public void SetDexterity3GetDefense13() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.ROBE);
		characterSheet.setData(Fields.DEXTERITY_VALUE, 3);
		assertEquals(13, (int) characterSheet.getData(Fields.DEFENSE));
	}

	@Test
	public void SetDexterityMinus11GetDefense0() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.ROBE);
		characterSheet.setData(Fields.DEXTERITY_VALUE, -11);
		assertEquals(0, (int) characterSheet.getData(Fields.DEFENSE));
	}

	@Test
	public void SetMagic5GetDefenseDefault() {
		CharacterSheet characterSheet = new CharacterSheet("CharacterSheet");
		characterSheet.setData(Fields.ARMOR_TYPE, Armors.ROBE);
		characterSheet.setData(Fields.MAGIC_VALUE, 5);
		assertEquals(10, (int) characterSheet.getData(Fields.DEFENSE));
	}
}
