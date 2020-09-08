package WordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * Created by tmnt on 2020/7/10.
 */
public class WordCountClient {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();
            configuration.setBoolean("mapreduce.map.output.compress", true);
            configuration.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);

            Job job = Job.getInstance(configuration);

            job.setJarByClass(WordCountClient.class);
            job.setMapperClass(WordCountMapper.class);
            job.setReducerClass(WordCountReduce.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);


            //默认FileInputFormat为TextInputFormat
            job.setInputFormatClass(CombineTextInputFormat.class);//整合小文件 减少分片
            CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);

            FileOutputFormat.setCompressOutput(job, true);
            FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            boolean result = job.waitForCompletion(true);
            System.out.println("word" + result);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
