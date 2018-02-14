package com.chinapex.nexus.service;

import com.chinapex.nexus.dto.PrismBaseDto;
import com.chinapex.nexus.dto.PrismProject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.List;

/**
 * created by pengmingguo on 2/12/18
 */
public interface PrismHttpService {
    String TOKEN_PREFIX = "token ";

    @GET("nexus_api/?format=json")
    Call<List<PrismProject>> projects(@Header("Authorization") String token);

    @GET("projects/{projectId}/tagcontainers/{containerId}/rules/?format=json")
    Call<List<PrismBaseDto>> triggers(@Header("Authorization") String token);
}
