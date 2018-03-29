package com.chinapex.nexus.service;

import com.alibaba.fastjson.JSONObject;
import com.chinapex.nexus.dto.CreatePrismTagRequest;
import com.chinapex.nexus.dto.PrismBaseDto;
import com.chinapex.nexus.dto.PrismProject;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/** created by pengmingguo on 2/12/18 */
public interface PrismHttpService {

  @GET("nexus_api/?format=json")
  Call<List<PrismProject>> projects(@Header("token") String token);

  @GET("projects/{projectId}/tagcontainers/{containerId}/rules/?format=json")
  Call<List<PrismBaseDto>> triggers(
      @Header("token") String token,
      @Path("projectId") Integer pid,
      @Path("containerId") Integer cid);

  @Headers("Content-Type: application/json")
  @POST("projects/{projectId}/tagcontainers/{containerId}/nexus/create_tag/?format=json")
  Call<ResponseBody> createTag(
      @Header("token") String token,
      @Path("projectId") Integer pid,
      @Path("containerId") Integer cid,
      @Body CreatePrismTagRequest request);
}
