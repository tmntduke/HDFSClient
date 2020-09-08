package Join;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

/**
 * Created by tmnt on 2020/7/15.
 */
public class DistributeMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    private HashMap<String, String> pdMap = new HashMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //缓存小表
        URI[] cacheFiles = context.getCacheFiles();
        String s = cacheFiles[0].getPath().toString();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(s), "utf-8"));

        String line;
        while (StringUtils.isNotEmpty(line = bufferedReader.readLine())) {
            //1.切割
            String[] split = line.split("\t");
            pdMap.put(split[0], split[1]);
        }

        IOUtils.closeStream(bufferedReader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split("\t");
        String name = pdMap.get(split[1]);
//        ProductWritale writale = new ProductWritale();
//        writale.setOrderId(split[0]);
//        writale.setpId(split[1]);
//        writale.setCount(Integer.parseInt(split[2]));
//        writale.setName(name);
        Text text = new Text();
        text.set(s + "\t" + name);
        context.write(text, NullWritable.get());

    }
}
