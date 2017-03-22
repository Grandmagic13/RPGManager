package rpg_database.character_sheet;

import java.security.InvalidParameterException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rpg_database.character_sheet.interfaces.CustomSetter;
import rpg_database.character_sheet.interfaces.MultipleFieldsGetterSetter;

public enum Languages {
	ANDER("Ander"), ANTIVAN("Antivan"), CIRIANE("Ciriane"), TRADE_TONGUE("Trade tongue"), DWARVEN_LANGUAGES("Dwarven languages"),
	ELVISH("Elvish"), QUNLAT("Qunlat"), ORLESIAN("Orlesian"), RIVAINI("Rivaini"), TEVENE("Tevene"), ANCIENT_TEVENE("Ancient Tevene");
	
	private final String text;
	public static Languages languages;
	private Languages(final String text){
		this.text = text;
	}

}
