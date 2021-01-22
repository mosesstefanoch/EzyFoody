package com.example.ezyfoody.Utils;

import com.example.ezyfoody.Retrofit.IEzyFoodAPI;
import com.example.ezyfoody.Retrofit.RetrofitClient;

public class Common {
    //In emulator localhost = 10.0.2.2
    private static final String BASE_URL = "http://10.0.2.2/ezyfoody/";

    public static IEzyFoodAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IEzyFoodAPI.class);
    }
}
