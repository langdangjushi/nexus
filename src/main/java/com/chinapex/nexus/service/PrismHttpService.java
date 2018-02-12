package com.chinapex.nexus.service;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * created by pengmingguo on 2/12/18
 */
public interface PrismHttpService {
    String TOKEN_PREFIX = "token";

    @GET("nexus_api/?format=json")
    Call<List<>>
}
