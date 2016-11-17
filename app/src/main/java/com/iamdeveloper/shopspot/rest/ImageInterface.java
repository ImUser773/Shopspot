package com.iamdeveloper.shopspot.rest;

import com.iamdeveloper.shopspot.model.ImageModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by IamDeveloper on 11/16/2016.
 */

public interface ImageInterface {
    @GET("1")
    Call<ImageModel> getPosts();

}
