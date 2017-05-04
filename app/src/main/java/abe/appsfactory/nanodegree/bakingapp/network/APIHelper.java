package abe.appsfactory.nanodegree.bakingapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.network.models.APIRecipe;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    public static List<? extends IRecipe> getRecipes() throws Exception {
        Gson gson = new GsonBuilder()
                .create();

        APIBaking service = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .addConverterFactory((GsonConverterFactory.create(gson)))
                .build()
                .create(APIBaking.class);

        Response<List<APIRecipe>> response = service.getRecieps().execute();
        return response.body();
    }
}
