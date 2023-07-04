package com.ordersystem.demo.service.impl;

import com.ordersystem.demo.service.GoogleApiService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

//@Service
public class GoogleApiServiceImpl implements GoogleApiService {

    @Value("${api.key}")
    private String API_KEY;
    OkHttpClient client = new OkHttpClient();
    public int getRealDistance(List<String> origin, List<String> destination) throws IOException {
        String originParam = origin.get(0)+origin.get(1);
        String destinationParam = destination.get(0)+destination.get(1);
        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/distancematrix/json")
                .queryParam("key", API_KEY)
                .queryParam("origins", originParam)
                .queryParam("destinations", destinationParam)
                .build().encode().toString();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        JSONParser parser = new JSONParser();
        float realDistance = 0;
        try {
            Object object = parser.parse(response.toString());
            JSONObject jsonObject=(JSONObject)object;
            JSONArray row=(JSONArray)jsonObject.get("rows");
            JSONObject obj2 = (JSONObject)row.get(0);
            JSONArray elements=(JSONArray)obj2.get("elements");
            JSONObject obj3 = (JSONObject)elements.get(0);
            JSONObject distance=(JSONObject)obj3.get("distance");
            realDistance = ((Long)distance.get("value")).floatValue();
            return (int) Math.round(realDistance/100);

        }catch(ParseException e){
            throw new IllegalArgumentException("Cannot parse json response object from Google API.");
        }
    }

}
