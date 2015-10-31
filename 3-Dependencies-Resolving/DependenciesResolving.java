import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.omg.Messaging.SyncScopeHelper;

public class DepedenciesResolving {

	public static void main(String[] args) {

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("src/files/dependencies.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray missingD = (JSONArray) jsonObject.get("dependencies");

			for (int i = 0; i < missingD.size(); i++) {
				String name = (String) missingD.get(i);

				File module = new File("src/installed_modules/" + name);

				if (module.exists()) {
					missingD.remove(name);
				}
			}
			if (missingD.size() == 0) {
				System.out.println("Everything is installed!");
			} else {
				Object obj2 = parser.parse(new FileReader("src/files/all_packages.json"));
				JSONObject jsonO2 = (JSONObject) obj2;
				Installment(missingD, jsonO2);
				System.out.println("All done.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Installment(JSONArray missingD, JSONObject obj) {
		for (int i = 0; i < missingD.size(); i++) {
			System.out.println("Installing " + missingD.get(i) + ".");

			File newPack = new File("src/installed_modules/" + missingD.get(i));
			newPack.mkdirs();

			JSONArray newPackInstalls = (JSONArray) obj.get(missingD.get(i));

			if (newPackInstalls.size() != 0) {
				System.out.println("In order to install " + missingD.get(i) + ", we need to install "
						+ toString(newPackInstalls) + ".");
			}
			for (int k = 0; k < newPackInstalls.size(); k++) {
				File newModule = new File("src/installed_modules/" + newPackInstalls.get(k));
				if (newModule.exists()) {
					System.out.println(newPackInstalls.get(k) + " is already installed.");
					newPackInstalls.remove(k);

				}
			}
			Installment(newPackInstalls, obj);
		}
	}

	public static String toString(JSONArray arr) {
		String str = "";

		for (int i = 0; i < arr.size() - 1; i++) {
			str += arr.get(i) + " and ";
		}
		str += arr.get(arr.size() - 1);
		return str;
	}
}
