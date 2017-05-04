package abe.appsfactory.nanodegree.bakingapp.logic;

import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.network.APIHelper;
import abe.appsfactory.nanodegree.bakingapp.persistence.RealmHelper;

public class DataLogic {

    public static List<? extends IRecipe> getRecipes() throws Exception {
        List<? extends IRecipe> result = RealmHelper.getRecipes();
        if (result.isEmpty()) {
            result = APIHelper.getRecipes();
            RealmHelper.persistRecipes(result);
        }
        return result;
    }
}
