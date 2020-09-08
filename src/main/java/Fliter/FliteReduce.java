package Fliter;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/15.
 */
public class FliteReduce extends Reducer<LongWritable, Text, LongWritable, Text> {
    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Text text = new Text();
        String t = "\r\n";
        for (Text value : values) {
            text.set(value.toString() + t);
            context.write(key, text);
        }
    }
}
