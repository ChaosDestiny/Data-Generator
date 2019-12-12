package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import generatedata.utilites.ParseData;

public class InsertionUtilities<T> {
	final Class<T> typeParameterClass;
	public InsertionUtilities(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}
	public List<T> readJson(String path) {
		List<T> list = new ArrayList<T>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
		ParseData pd = new ParseData();
		JsonArray jsonArray = pd.getDataFromJson(path);
		Iterator<JsonElement> it = jsonArray.iterator();
		JsonElement jsonElement;
		while (it.hasNext()) {
			jsonElement = it.next();
			T tmp = gson.fromJson(jsonElement, typeParameterClass);
			list.add(tmp);
		}
		return list;
	}
}
