package com.clogic.veslo.API;

import com.clogic.veslo.Model.Server.Info;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by clogic on 2016. 1. 9..
 */
public interface PostAPI {

    public static final String POST_MEETING = "/post/meeting";
    public static final String GET_ALL_POST_MEETING = "/post/meeting/all";

    @FormUrlEncoded
    @POST(POST_MEETING)
    public Call<Info> postMeeting(@Field("writer_id") String writerId,
                                  @Field("origin") String origin,
                                  @Field("origin_lat") double originLat,
                                  @Field("origin_long") double originLong,
                                  @Field("dest") String dest,
                                  @Field("dest_lat") double destLat,
                                  @Field("dest_long") double destLong,
                                  @Field("departure_time") long departureTime,
                                  @Field("arrival_time") long arrivalTime,
                                  @Field("describe") String describe);

    @FormUrlEncoded
    @POST(GET_ALL_POST_MEETING)
    public Call<Info> getAllMeeting(@Field("writer_id") String writerId);
}
