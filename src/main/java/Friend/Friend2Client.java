package Friend;

import JobBuilder.JobBuilder;
import org.apache.hadoop.io.Text;

/**
 * Created by tmnt on 2020/7/17.
 */
public class Friend2Client {
    public static void main(String[] args) {

        JobBuilder builder = JobBuilder.newInstance();
        builder.setJarClass(Friend2Client.class)
                .setMapOutputClass(Text.class, Text.class)
                .setOutputClass(Text.class, Text.class)
                .setMapReduceClass(Friend2Mapper.class, FriendReducer.class)
                .setMapreduceNum(0)
                .setDistributeCacheFile("file:/G:/test/friends.txt")
                .setFormatPath("G:\\test\\f", "G:\\test\\friend2")
                .execute();
    }
}
