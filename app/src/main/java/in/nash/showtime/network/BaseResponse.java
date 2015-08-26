package in.nash.showtime.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by avinash on 8/26/15.
 */
public class BaseResponse {

    public Integer page;

    @SerializedName("total_pages")
    public Integer totalPages;

    @SerializedName("total_results")
    public Integer totalResults;
}
