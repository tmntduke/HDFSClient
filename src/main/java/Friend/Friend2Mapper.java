package Friend;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tmnt on 2020/7/17.
 */
public class Friend2Mapper extends Mapper<LongWritable, Text, Text, Text> {
    private String[] o2a = null;

    private HashMap<String, List<String>> map = new HashMap<>();
    private List<String> all = new ArrayList<>();
    private List<String> re = new ArrayList<>();

    private String[] split;
    private String user;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        URI[] uris = context.getCacheFiles();
        String name = uris[0].getPath().toString();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
        String line = null;
        while (StringUtils.isNotEmpty(line = bufferedReader.readLine())) {
            String[] strings = line.split(":");
            map.put(strings[0], Arrays.asList(strings[1].split(",")));
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        o2a = null;
        all.clear();
        re.clear();
        String v = value.toString();
        split = v.split("\t");
        user = split[0];
        getRelation();
        getCommon();
        System.out.println("re----->" + String.valueOf(re.size()));
        for (int i = 0; i < all.size(); i++) {
            context.write(new Text(user + "--" + all.get(i)), new Text(re.get(i)));
        }


    }

    private void getCommon() {
        List<String> friend = map.get(user);
        StringBuilder builder = new StringBuilder();
        for (String s : all) {
            List<String> commfri = map.get(s);
            for (String cf : commfri) {
                for (String f : friend) {
                    if (cf.equals(f)) {
                        builder.append(f + " ");
                    }
                }
            }
            if (builder.length() == 0) {
                re.add(" ");
            } else {
                re.add(builder.toString());
            }
            builder.setLength(0);
        }

    }

    private void getRelation() {
        o2a = split[1].split(",");
        List<String> list = map.get(user);
        for (String s : o2a) {
            for (String d : list) {
                if (s.equals(d)) {
                    all.add(s);
                }
            }

        }
    }

//    private String createText(List<String> l, int flag, String regex) {
//        StringBuilder builder;
//
//        if (flag == 1) {
//            builder = new StringBuilder(user + ":");
//        } else {
//            builder = new StringBuilder();
//        }
//
//        for (String s : l) {
//            builder.append(s + regex);
//        }
//        builder.deleteCharAt(builder.length() - 1);
//        return builder.toString();
//    }

}
