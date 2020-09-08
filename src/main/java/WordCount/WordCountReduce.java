package WordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/10.
 */
public class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        IntWritable intWritable = new IntWritable();
        for (IntWritable i : values) {
            sum += i.get();
        }
        intWritable.set(sum);
        context.write(key, intWritable);
    }
}
