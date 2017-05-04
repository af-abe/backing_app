package abe.appsfactory.nanodegree.bakingapp.persistence;

import java.util.ArrayList;
import java.util.List;

import abe.appsfactory.nanodegree.bakingapp.logic.models.IRecipe;
import abe.appsfactory.nanodegree.bakingapp.persistence.models.DbRecipe;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    public static List<? extends IRecipe> getRecipes() {
        Realm realm = Realm.getDefaultInstance();
        List<IRecipe> result = new ArrayList<>();
        RealmResults<DbRecipe> dbList = realm.where(DbRecipe.class).findAll();
        if (dbList != null) {
            result.addAll(realm.copyFromRealm(dbList));
        }
        realm.close();
        return result;
    }

    public static void persistRecipes(List<? extends IRecipe> recipes) {
        List<DbRecipe> insert = new ArrayList<>();
        for (IRecipe recipe : recipes) {
            insert.add(new DbRecipe(recipe));
        }
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(bgRealm -> bgRealm.copyToRealmOrUpdate(insert));
        realm.close();
    }

    public static IRecipe getRecipe(int parentId) {
        Realm realm = Realm.getDefaultInstance();
        IRecipe result = null;
        DbRecipe model = realm.where(DbRecipe.class).equalTo("id", parentId).findFirst();
        if (model != null) {
            result = realm.copyFromRealm(model);
        }
        realm.close();
        return result;
    }

//    public static void persistRecipes(List<? extends IRecipe> recipes) {
//        List<DbRecipe> insert = new ArrayList<>();
//        for (IRecipe recipe : recipes) {
//            insert.add(new DbRecipe(recipe));
//        }
//        Realm realm = Realm.getDefaultInstance();
//        realm.executeTransactionAsync(bgRealm -> bgRealm.copyToRealmOrUpdate(insert));
//        realm.close();
//    }
}
