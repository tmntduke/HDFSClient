package Partition;

import Phone.PhoneWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by tmnt on 2020/7/14.
 * part中的类型是mapper的输出 获取到的是map的输出
 */
public class PhonePartition extends Partitioner<Text, PhoneWritable> {
    @Override
    public int getPartition(Text text, PhoneWritable phoneWritable, int i) {
        String l = text.toString();
        String num = l.substring(0, 3);
        int partition = 0;
        switch (Integer.parseInt(num.trim())) {
            case 136:
                partition = 0;
                break;
            case 137:
                partition = 1;
                break;
            case 138:
                partition = 2;
                break;
            case 139:
                partition = 3;
                break;
            default:
                partition = 4;
                break;

        }
        return partition;
    }
}
