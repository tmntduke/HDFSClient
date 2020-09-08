package Partition;

import Phone.PhoneWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class PhoneMapper extends Mapper<LongWritable, Text, Text, PhoneWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        PhoneWritable writable = new PhoneWritable();
        String phone = value.toString();
        String[] split = phone.split("\t");
        int size = split.length;
        long sum = 0;
        writable.setPhoneNum(split[1]);
        writable.setUpload(Long.parseLong(split[size - 3]));
        writable.setDownload(Long.parseLong(split[size - 2]));
        sum = Long.parseLong(split[size - 3]) + Long.parseLong(split[size - 2]);
        writable.setSum(sum);
        context.write(new Text(split[1]), writable);

    }
}
