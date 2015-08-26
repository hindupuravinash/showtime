package in.nash.showtime.network;

import java.util.List;

import in.nash.showtime.model.Review;

/**
 * Created by avinash on 8/26/15.
 */
public class ReviewsResponse extends BaseResponse{

    public Integer id;
    public List<Review> results;
    public String url;

}
