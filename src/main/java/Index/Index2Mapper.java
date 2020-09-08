package Index;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/16.
 */
public class Index2Mapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split("--");
        Text k = new Text();
        k.set(split[0]);

        Text v = new Text();
        v.set(split[1].replace("\t", "-->"));
        context.write(k, v);

    }
}
