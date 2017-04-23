package rpg_database.character_sheet.common;

import java.util.ArrayList;

public class CharacterSheetCommon {

	public static String generateEnumText(String enumName) {
		ArrayList<String> nameWords = new ArrayList<>();
		for (String substring : enumName.split("_")) {
			nameWords.add(toProperCase(substring));
		}
		return String.join(" ", nameWords);
	}

	private static String toProperCase(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}
}
