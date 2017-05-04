package abe.appsfactory.nanodegree.bakingapp.network;

import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.network.models.APIRecipe;
import retrofit2.Call;
import retrofit2.http.GET;


public interface APIBaking {
    @GET("/topher/2017/May/5907926b_baking/baking.json")
    Call<List<APIRecipe>> getRecieps();
}
