package WordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/10.
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text text = new Text();
    IntWritable intWritable = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String lines = value.toString();//每执行一次获取一行
        String[] words = lines.split(" ");//将一行拆分

        for (String w : words) {
            text.set(w);
            intWritable.set(1);
            context.write(text, intWritable);
        }
    }
}
