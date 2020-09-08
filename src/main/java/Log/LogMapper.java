package Log;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/16.
 */
public class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        boolean result = parseLog(s, context);
        if (result) {
            context.write(value, NullWritable.get());
        }
    }

    private boolean parseLog(String s, Context context) {

        String[] split = s.split(" ");
        for (String s1 : split) {
            if (s1.length() > 11) {
                context.getCounter("map", "true").increment(1);
                return true;
            } else {
                context.getCounter("map", "false").increment(1);
                return false;
            }
        }

        return false;
    }
}
