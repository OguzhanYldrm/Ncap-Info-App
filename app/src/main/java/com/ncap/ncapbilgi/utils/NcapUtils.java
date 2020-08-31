package com.ncap.ncapbilgi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ncap.ncapbilgi.model.Ncap;
import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NcapUtils {

    public static CarouselItem imageValidator(String vehiclePicture, String vehicleDescription) {
        if (vehiclePicture != null && vehicleDescription != null){
            return new CarouselItem(vehiclePicture, vehicleDescription);
        }
        else if (vehiclePicture != null && vehicleDescription == null){
            return new CarouselItem(vehiclePicture, "");
        }
        else if (vehiclePicture == null && vehicleDescription != null){
            return new CarouselItem("https://www.clicpeugeot.com/back/PhotoHandler.ashx?id=524566&prop=photo1&format=default", vehicleDescription);
        }
        else{
            return new CarouselItem("https://www.clicpeugeot.com/back/PhotoHandler.ashx?id=524566&prop=photo1&format=default", "");
        }
    }

    public static String checkJson(String inputString){
        if (inputString.contains("No value") || inputString.equals(null)){
            return "-";
        }

        return inputString;
    }

    public static String checkRating(String inputString){
        if (inputString.contains("Not Rated")){
            return "0";
        }

        return inputString;
    }

    public static String checkImage(String inputString){
        if (inputString.contains("No value")){
            return "https://www.clicpeugeot.com/back/PhotoHandler.ashx?id=524566&prop=photo1&format=default";
        }

        return inputString;
    }

    public static void setFavourites(List<Ncap> favouriteList , Context context) {
        Gson gson = new Gson();
        String jsonResult = gson.toJson(favouriteList);

        SharedPreferences sharedPreferences = context.getSharedPreferences("FAV", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("FAVOURITES", jsonResult);
        editor.commit();

    }

    public static List<Ncap> getFavourites(Context context) {
        List<Ncap> favs;
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences("FAV",context.MODE_PRIVATE);
        String jsonResult = sharedPreferences.getString("FAVOURITES", "");
        Type type = new TypeToken<List<Ncap>>() {}.getType();
        favs = gson.fromJson(jsonResult, type);
        return favs != null ? favs: new ArrayList<Ncap>() ;
    }

    public static void removeFavourite(Ncap car,Context context){
        List<Ncap> newFavs = new ArrayList<>();
        List<Ncap> favs;
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences("FAV",context.MODE_PRIVATE);
        String jsonResult = sharedPreferences.getString("FAVOURITES", "");
        Type type = new TypeToken<List<Ncap>>() {}.getType();
        favs = gson.fromJson(jsonResult, type);


        for (Ncap favCar: favs
             ) {
            if (!favCar.getVehicleId().equals(car.getVehicleId())){
                newFavs.add(favCar);
            }
        }

        String json = gson.toJson(newFavs);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FAVOURITES", json);
        editor.apply();
    }

}
