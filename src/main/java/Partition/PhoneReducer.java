package Partition;

import Phone.PhoneWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class PhoneReducer extends Reducer<Text, PhoneWritable, Text, PhoneWritable> {
    @Override
    protected void reduce(Text key, Iterable<PhoneWritable> values, Context context) throws IOException, InterruptedException {
        PhoneWritable writable = new PhoneWritable();
        int sum = 0;
        writable.setPhoneNum(key.toString());
        for (PhoneWritable value : values) {
            writable.setUpload(value.getUpload());
            writable.setDownload(value.getDownload());
            sum += value.getSum();
            writable.setSum(sum);
        }

        context.write(key, writable);
    }
}
