package com.ncap.ncapbilgi.service;

import android.app.Activity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.ncap.ncapbilgi.model.Brand;
import com.ncap.ncapbilgi.model.Model;
import com.ncap.ncapbilgi.model.Ncap;
import com.ncap.ncapbilgi.model.Vehicle;
import com.ncap.ncapbilgi.model.Year;
import com.ncap.ncapbilgi.utils.NcapUtils;
import com.ncap.ncapbilgi.view.ActivityBrand;
import com.ncap.ncapbilgi.view.ActivityMain;
import com.ncap.ncapbilgi.view.ActivityModel;
import com.ncap.ncapbilgi.view.ActivityNcap;
import com.ncap.ncapbilgi.view.ActivityVehicle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NcapApi {

    private static final String BASE = "https://one.nhtsa.gov/webapi/api/SafetyRatings?format=json";
    private static final String ERROR = "";
    private static final String NO_IMAGE_FOUND = "https://www.clicpeugeot.com/back/PhotoHandler.ashx?id=524566&prop=photo1&format=default";

    public static void getYears(final Activity activity) {
        AndroidNetworking.initialize(activity);
        AndroidNetworking.get(BASE)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        List<Year> result = new ArrayList<>();
                        JSONArray resultArray = null;

                        try {
                            resultArray = response.getJSONArray("Results");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < ((resultArray != null) ? resultArray.length() : 0); i++) {
                            try {
                                Year currentYear = new Year();
                                currentYear.setYear(resultArray.getJSONObject(i).getString("ModelYear"));
                                result.add(currentYear);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ActivityMain.setYears(activity, result);
                    }

                    @Override
                    public void onError(ANError anError) {
                        
                    }
                });
    }

    public static void getBrands(final Activity activity, String modelYear) {

        AndroidNetworking.initialize(activity);
        AndroidNetworking.get(BASE + "/{modelyear}")
                .addQueryParameter("modelyear", modelYear)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Brand> result = new ArrayList<>();
                        JSONArray resultArray = null;

                        try {
                            resultArray = response.getJSONArray("Results");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < ((resultArray != null) ? resultArray.length() : 0); i++) {
                            try {
                                Brand currentBrand = new Brand();
                                currentBrand.setBrand(resultArray.getJSONObject(i).getString("Make"));
                                result.add(currentBrand);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ActivityBrand.setBrands(activity, result);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
    }

    public static void getModels(final Activity activity, String modelYear, String make) {

        AndroidNetworking.initialize(activity);
        AndroidNetworking.get(BASE + "/{modelyear}/{make}")
                .addQueryParameter("modelyear", modelYear)
                .addQueryParameter("make", make)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Model> result = new ArrayList<>();
                        JSONArray resultArray = null;

                        try {
                            resultArray = response.getJSONArray("Results");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < ((resultArray != null) ? resultArray.length() : 0); i++) {
                            try {
                                Model currentModel = new Model();
                                currentModel.setModel(resultArray.getJSONObject(i).getString("Model"));
                                result.add(currentModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ActivityModel.setModels(activity, result);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
    }

    public static void getVehicleId(final Activity activity, String modelYear, String make, String model) {

        AndroidNetworking.initialize(activity);
        AndroidNetworking.get(BASE + "/{modelyear}/{make}/{model}")
                .addQueryParameter("modelyear", modelYear)
                .addQueryParameter("make", make)
                .addQueryParameter("model", model)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Vehicle> result = new ArrayList<>();
                        JSONArray resultArray = null;

                        try {
                            resultArray = response.getJSONArray("Results");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < ((resultArray != null) ? resultArray.length() : 0); i++) {
                            try {
                                Vehicle currentVehicle = new Vehicle();
                                currentVehicle.setVehicleDescription(resultArray.getJSONObject(i).getString("VehicleDescription"));
                                currentVehicle.setVehicleId(resultArray.getJSONObject(i).getString("VehicleId"));
                                result.add(currentVehicle);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ActivityVehicle.setVehicles(activity, result);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
    }

    public static void getNcapData(final Activity activity, String VehicleId) {
        AndroidNetworking.initialize(activity);
        AndroidNetworking.get(BASE + "/{VehicleId}")
                .addQueryParameter("VehicleId", VehicleId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Ncap ncap = new Ncap();
                        JSONArray resultArray = null;

                        try {
                            resultArray = response.getJSONArray("Results");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < ((resultArray != null) ? resultArray.length() : 0); i++) {

                            JSONObject jsonObject = null;
                            try {
                                jsonObject = resultArray.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            //General
                            try {
                                ncap.setVehicleId(NcapUtils.checkJson(jsonObject.getString("VehicleId")));
                            } catch (JSONException e) {
                                ncap.setVehiclePicture("9403");
                                e.printStackTrace();
                            }
                            try {
                                assert jsonObject != null;
                                ncap.setVehicleDescription(NcapUtils.checkJson(jsonObject.getString("VehicleDescription")));
                            } catch (JSONException e) {
                                ncap.setVehicleDescription("-");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setVehiclePicture(NcapUtils.checkImage(jsonObject.getString("VehiclePicture")));
                            } catch (JSONException e) {
                                ncap.setVehiclePicture(NO_IMAGE_FOUND);
                                e.printStackTrace();
                            }
                            try {
                                ncap.setOverallRating(NcapUtils.checkRating(jsonObject.getString("OverallRating")));
                            } catch (JSONException e) {
                                ncap.setOverallRating("0");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setModelYear(NcapUtils.checkJson(jsonObject.getString("ModelYear")));
                            } catch (JSONException e) {
                                ncap.setModelYear("-");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setMake(NcapUtils.checkJson(jsonObject.getString("Make")));
                            } catch (JSONException e) {
                                ncap.setMake("-");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setModel(NcapUtils.checkJson(jsonObject.getString("Model")));
                            } catch (JSONException e) {
                                ncap.setModel("-");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setComplaintsCount(NcapUtils.checkJson(jsonObject.getString("ComplaintsCount")));
                            } catch (JSONException e) {
                                ncap.setComplaintsCount("-");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setRecallsCount(NcapUtils.checkJson(jsonObject.getString("RecallsCount")));
                            } catch (JSONException e) {
                                ncap.setRecallsCount("-");
                                e.printStackTrace();
                            }
                            //Front
                            try {
                                ncap.setFrontCrashDriversideRating(NcapUtils.checkRating(jsonObject.getString("FrontCrashDriversideRating")));
                            } catch (JSONException e) {
                                ncap.setFrontCrashDriversideRating("0");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setFrontCrashPicture(NcapUtils.checkImage(jsonObject.getString("FrontCrashPicture")));
                            } catch (JSONException e) {
                                ncap.setFrontCrashPicture(NO_IMAGE_FOUND);
                                e.printStackTrace();
                            }
                            try {
                                ncap.setFrontCrashVideo(NcapUtils.checkJson(jsonObject.getString("FrontCrashVideo")));
                            } catch (JSONException e) {
                                ncap.setFrontCrashVideo(ERROR);
                                e.printStackTrace();
                            }
                            try {
                                ncap.setFrontCrashPassengersideRating(NcapUtils.checkRating(jsonObject.getString("FrontCrashPassengersideRating")));
                            } catch (JSONException e) {
                                ncap.setFrontCrashPassengersideRating("0");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setOverallFrontCrashRating(NcapUtils.checkRating(jsonObject.getString("OverallFrontCrashRating")));
                            } catch (JSONException e) {
                                ncap.setOverallFrontCrashRating("0");
                                e.printStackTrace();
                            }
                            //Side
                            try {
                                ncap.setOverallSideCrashRating(NcapUtils.checkRating(jsonObject.getString("OverallSideCrashRating")));
                            } catch (JSONException e) {
                                ncap.setOverallSideCrashRating("0");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setSideCrashDriversideRating(NcapUtils.checkRating(jsonObject.getString("SideCrashDriversideRating")));
                            } catch (JSONException e) {
                                ncap.setSideCrashDriversideRating("0");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setSideCrashPassengersideRating(NcapUtils.checkRating(jsonObject.getString("SideCrashPassengersideRating")));
                            } catch (JSONException e) {
                                ncap.setSideCrashPassengersideRating("0");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setSideCrashPicture(NcapUtils.checkImage(jsonObject.getString("SideCrashPicture")));
                            } catch (JSONException e) {
                                ncap.setSideCrashPicture(NO_IMAGE_FOUND);
                                e.printStackTrace();
                            }
                            try {
                                ncap.setSideCrashVideo(NcapUtils.checkJson(jsonObject.getString("SideCrashVideo")));
                            } catch (JSONException e) {
                                ncap.setSideCrashVideo(ERROR);
                                e.printStackTrace();
                            }
                            //Side Pole
                            try {
                                ncap.setSidePoleCrashRating(NcapUtils.checkRating(jsonObject.getString("SidePoleCrashRating")));
                            } catch (JSONException e) {
                                ncap.setSidePoleCrashRating("0");
                                e.printStackTrace();
                            }
                            try {
                                ncap.setSidePolePicture(NcapUtils.checkImage(jsonObject.getString("SidePolePicture")));
                            } catch (JSONException e) {
                                ncap.setSidePolePicture(NO_IMAGE_FOUND);
                                e.printStackTrace();
                            }
                            try {
                                ncap.setSidePoleVideo(NcapUtils.checkJson(jsonObject.getString("SidePoleVideo")));
                            } catch (JSONException e) {
                                ncap.setSidePoleVideo(ERROR);
                                e.printStackTrace();
                            }

                        }
                        ActivityNcap.setNcaps(ncap);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
    }
}
