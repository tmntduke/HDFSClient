package Compare;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class FlowReducer extends Reducer<FlowWritable, Text, Text, Text> {
    @Override
    protected void reduce(FlowWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(new Text(key.getPhoneNum()), new Text(String.valueOf(key.getSum())));
        }
    }
}
