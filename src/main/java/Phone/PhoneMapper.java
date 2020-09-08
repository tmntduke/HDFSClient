package Phone;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/11.
 */
public class PhoneMapper extends Mapper<LongWritable, Text, Text, PhoneWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] phoneInfo = s.split("\t");
        int len = phoneInfo.length;
        PhoneWritable phoneWritable = new PhoneWritable();
        phoneWritable.setPhoneNum(phoneInfo[1]);
        phoneWritable.setUpload(Long.parseLong(phoneInfo[len - 3]));
        phoneWritable.setDownload(Long.parseLong(phoneInfo[len - 2]));
        long sum = Long.parseLong(phoneInfo[len - 2]) + Long.parseLong(phoneInfo[len - 3]);
        phoneWritable.setSum(sum);
        Text text = new Text(phoneInfo[1]);
        context.write(text, phoneWritable);
    }
}
