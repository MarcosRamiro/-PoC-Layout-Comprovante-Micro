package com.ramiro.poclayoutcomprovantemicro.service;

import com.jayway.jsonpath.JsonPath;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ServiceBind {

		public String bind(String padrao, String json) {

			if(padrao == null || (!padrao.contains("$")) || json == null)
				return padrao;

			Object obj = JsonPath.read(json, padrao);

			if(obj instanceof List<?>)
				return ((List<?>) obj).stream().findFirst().isPresent() ? ((List<?>) obj).stream().findFirst().get().toString() : "";

			return obj == null ? "" : obj.toString();



		}
}
