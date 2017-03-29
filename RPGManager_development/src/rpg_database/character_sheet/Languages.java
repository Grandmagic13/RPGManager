package rpg_database.character_sheet;

import java.util.ArrayList;
import java.util.Arrays;


public enum Languages {
	ANDER("Ander", speakAnder()), ANTIVAN("Antivan", speakAntivan()), TRADE_TONGUE("Trade tongue", speakTradeTongue()),
	DWARVEN_LANGUAGE("Dwarven language", speakDwarven()), ELVEN("Elven", speakElvish()), 
	QUNLAT("Qunlat", speakQunlat()), ORLESIAN("Orlesian", speakOrlesian()), RIVAINI("Rivaini", speakRivaini()), 
	TEVENE("Tevene", speakTevene()), ANCIENT_TEVENE("Ancient Tevene", speakAncientTevene());
	
	private final String text;
	private final ArrayList<Background> backgrounds;
	
	private Languages(final String text, Background[] backgrounds){
		this.text = text;
		this.backgrounds = new ArrayList<>(Arrays.asList(backgrounds));
	}
	private static Background[] speakAncientTevene() {
		return new Background[] {Background.CIRCLE_MAGE };
	}
	
	private static Background[] speakTevene() {
		return new Background[] {Background.ESCAPED_ELVEN_SLAVE, Background.SEHERON_CONVERT, Background.TEVINTER_ALTUS, Background.TEVINTER_LAETAN, Background.TEVINTER_SOPORATI };
	}
	
	private static Background[] speakRivaini() {
		return new Background[] {Background.RIVAINI_MERCHANT };
	}
	
	private static Background[] speakOrlesian() {
		return new Background[] {Background.NEVARRAN_ADVENTURER, Background.ORLESIAN_COMMONER, Background.ORLESIAN_EXILE, Background.ORLESIAN_NOBLE, Background.ORLESIAN_STUDENT };
	}
	
	private static Background[] speakQunlat() {
		return new Background[] {Background.QUNARI_BERESAAD, Background.SEHERON_CONVERT, Background.TAL_VASHOTH };
	}
	
	private static Background[] speakElvish() {
		return new Background[] {Background.DALISH_ELF};
	}
	
	private static Background[] speakDwarven() {
		return new Background[] {Background.DWARF_DUSTER, Background.HIGH_BORN_DWARF, Background.LOW_BORN_DWARF, Background.SURFACE_DWARF };
	}
	
	private static Background[] speakTradeTongue() {
		return new Background[] {Background.ANDER_SURVIVOR, Background.APOSTATE };
	}
	
	private static Background[] speakAntivan() {
		return new Background[] {Background.ANTIVAN_WAYFARER, Background.WAKING_SEA_RAIDER };
	}
	
	private static Background[] speakAnder() {
		return new Background[] {Background.ANDER_SURVIVOR, };
	}
	
	//TODO WAKING_SEA_RAIDER' default languages depends on the land where he is from, can be Antivan, Rivaine or Orlesian
}
