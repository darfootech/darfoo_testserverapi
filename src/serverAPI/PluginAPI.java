package serverAPI;

import model.PluginModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.testdarfooserverapi.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsmlby on 9/3/13.
 */
public class PluginAPI {
	public static List<PluginModel> getPluginList() {
		try {
			JSONArray json = new JSONArray(Util.httpGet("depot/pluginlist/"));
			System.out.println("json result===>" + json.toString());
			List<PluginModel> list = new ArrayList<PluginModel>(json.length());
			// for(int i=0; i<json.length(); i++){
			// System.out.println(json.getJSONObject(i).toString());
			// System.out.println(json.getJSONObject(i).getString("packageName"));
			// System.out.println(json.getJSONObject(i).getString("packageUrl"));
			// System.out.println(json.getJSONObject(i).getString("className"));
			// System.out.println(json.getJSONObject(i).getString("picUrl"));
			// }
			for (int i = 0; i < json.length(); i++) {
				PluginModel pm = new PluginModel();
				JSONObject jo = json.getJSONObject(i);
				pm.packageName = jo.getString("packageName");
				pm.url = jo.getString("packageUrl");
				pm.className = jo.getString("className");
				pm.picurl = jo.getString("picUrl");
				list.add(pm);
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
