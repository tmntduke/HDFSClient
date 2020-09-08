package Friend;

import Index.Index2Mapper;
import Index.Index2Reducer;
import JobBuilder.JobBuilder;
import org.apache.hadoop.io.Text;

/**
 * Created by tmnt on 2020/7/17.
 */
public class FriendClient {
    public static void main(String[] args) {

        JobBuilder builder = JobBuilder.newInstance();
        builder.setJarClass(FriendClient.class)
                .setMapOutputClass(Text.class, Text.class)
                .setOutputClass(Text.class, Text.class)
                .setMapReduceClass(FriendMapper.class, FriendReducer.class)
                .setFormatPath("G:\\test\\friends.txt", "G:\\test\\friend")
                .execute();
    }
}
