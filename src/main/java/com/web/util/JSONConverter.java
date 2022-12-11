package com.web.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.web.cart.CartDetail;

public class JSONConverter {

	public static String convertList(List<CartDetail> list) {
		Gson gson = new GsonBuilder().serializeNulls()
				.excludeFieldsWithoutExposeAnnotation().create();
			
		try {
			return gson.toJson(list);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("can't convert to json");
		}

	}
}
