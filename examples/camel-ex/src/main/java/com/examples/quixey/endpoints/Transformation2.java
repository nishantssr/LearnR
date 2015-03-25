package com.examples.quixey.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Transformation2 {

	double confidence = 0.0;

	public List<Object> get_token_parser(JSONObject query_parse_tree,
			double confidence1, List<JSONObject> jsonList) throws JSONException {
		List<JSONObject> tokens = new ArrayList<JSONObject>();
		;

		if (confidence1 == 0.0) {

			confidence = 1.0;
		} else {
			tokens = jsonList;
			confidence = confidence1;

		}

		confidence = confidence
				* Double.parseDouble(query_parse_tree
						.getString("edgeConfidence"));
		if (query_parse_tree.has("nodeValue")) {
			tokens.add(query_parse_tree.getJSONObject("nodeValue"));

		}

		if (query_parse_tree.getJSONArray("childrenNodes").isNull(0)) {
			List<Object> temp = new ArrayList<Object>();
			List<Object> temp1 = new ArrayList<Object>();
			temp.add(tokens);
			temp.add(confidence);
			temp1.add(temp);
			return temp1;
		}

		List<Object> token_parses = new ArrayList<Object>();
		for (int i = 0; i < query_parse_tree.getJSONArray("childrenNodes")
				.length(); i++) {
			List<JSONObject> temp_tokens = new ArrayList<JSONObject>();
			for (int j = 0; j < tokens.size(); j++) {
				temp_tokens.add(tokens.get(j));
			}
			token_parses.addAll(get_token_parser(
					query_parse_tree.getJSONArray("childrenNodes")
							.getJSONObject(i), confidence, temp_tokens));
		}
		return token_parses;

	}
	private List<Object> transform_parse(List<Object> token_parse) throws JSONException{
		
		for(int i=0;i<token_parse.size();i++){
			List<Object> temp = new ArrayList<Object>();
			temp = (List<Object>) token_parse.get(i);
			for(int j = 0;j<temp.size();j++){
				if (temp.get(j) instanceof List){
					List<JSONObject> tokens = (List<JSONObject>) temp.get(j);
					List<JSONObject> json_tokens = new ArrayList<JSONObject>();
					for(int k=0;k<tokens.size();k++){
						JSONObject jo =new JSONObject();
						JSONObject joTemp =new JSONObject();
						jo.put("lexema",tokens.get(k).get("queryToken"));
						jo.put("start",tokens.get(k).get("queryTokenStartIndex"));
						jo.put("end",tokens.get(k).get("queryTokenEndIndex"));
						//joTemp.put("@id",tokens.get(k).getJSONObject("parsedTokenData"))
						//jo.put("genericEntity")
					}
				}
				else{
					double confidence = (double) temp.get(j);
				}
					
			}
				
		}
		return null;
	}
}
