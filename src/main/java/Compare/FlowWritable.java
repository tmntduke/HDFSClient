package Compare;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class FlowWritable implements WritableComparable<FlowWritable> {

    private String phoneNum;
    private long upload;
    private long download;
    private long sum;

    public FlowWritable() {
        super();
    }

    public FlowWritable(String phoneNum, long upload, long download, long sum) {
        this.phoneNum = phoneNum;
        this.upload = upload;
        this.download = download;
        this.sum = sum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public long getUpload() {
        return upload;
    }

    public void setUpload(long upload) {
        this.upload = upload;
    }

    public long getDownload() {
        return download;
    }

    public void setDownload(long download) {
        this.download = download;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public int compareTo(FlowWritable o) {
        int result;
        if (sum > o.getSum()) {
            result = -1;
        } else if (sum < o.getSum()) {
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.phoneNum);
        dataOutput.writeLong(this.upload);
        dataOutput.writeLong(this.download);
        dataOutput.writeLong(this.sum);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        phoneNum = dataInput.readUTF();
        upload = dataInput.readLong();
        download = dataInput.readLong();
        sum = dataInput.readLong();
    }
}
