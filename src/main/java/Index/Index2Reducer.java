package Index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/16.
 */
public class Index2Reducer extends Reducer<Text, Text, Text, Text> {
    StringBuilder stringBuilder = new StringBuilder();



    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        stringBuilder.setLength(0);
        for (Text value : values) {
            stringBuilder.append(value.toString() + " ");
        }
        context.write(key, new Text(stringBuilder.toString()));
    }
}
