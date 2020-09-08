package Friend;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmnt on 2020/7/17.
 */
public class FriendMapper extends Mapper<LongWritable, Text, Text, Text> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split(":");
        String user = split[0];
        String[] friends = split[1].split(",");

        for (String friend : friends) {
            context.write(new Text(friend), new Text(user));
        }


    }

}
