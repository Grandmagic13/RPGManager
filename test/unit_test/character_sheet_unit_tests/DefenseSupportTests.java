package unit_test.character_sheet_unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rpg_database.character_sheet.Armors;
import rpg_database.character_sheet.CharacterSheet;
import rpg_database.character_sheet.Fields;

public class DefenseSupportTests {

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
