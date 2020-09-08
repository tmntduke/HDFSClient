package Friend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/17.
 */
public class FriendReducer extends Reducer<Text, Text, Text, Text> {
    StringBuilder builder = new StringBuilder();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        builder.setLength(0);
        for (Text value : values) {
            builder.append(value.toString() + ",");
        }
        builder.deleteCharAt(builder.length() - 1);
        context.write(key, new Text(builder.toString()));
    }
}
