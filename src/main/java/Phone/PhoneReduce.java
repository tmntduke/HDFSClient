package Phone;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/11.
 */
public class PhoneReduce extends Reducer<Text, PhoneWritable, Text, PhoneWritable> {
    @Override
    protected void reduce(Text key, Iterable<PhoneWritable> values, Context context) throws IOException, InterruptedException {
        PhoneWritable phoneWritable = new PhoneWritable();
        long up = 0;
        long dowm = 0;
        long sum = 0;
        for (PhoneWritable value : values) {
            up += value.getUpload();
            dowm += value.getDownload();
            sum += value.getSum();
        }
        phoneWritable.setPhoneNum(key.toString());
        phoneWritable.setDownload(dowm);
        phoneWritable.setUpload(up);
        phoneWritable.setSum(sum);
        context.write(key, phoneWritable);
    }
}
