package Phone;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by tmnt on 2020/7/11.
 */
public class PhoneWritable implements Writable{

    private String phoneNum;
    private long upload;
    private long download;
    private long sum;

    public PhoneWritable() {
        super();
    }

    public PhoneWritable(String phoneNum, long upload, long download, long sum) {
        super();
        this.phoneNum = phoneNum;
        this.upload = upload;
        this.download = download;
        this.sum = sum;
    }


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(phoneNum);//string
        dataOutput.writeLong(upload);
        dataOutput.writeLong(download);
        dataOutput.writeLong(sum);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.phoneNum = dataInput.readUTF();
        this.upload = dataInput.readLong();
        this.download = dataInput.readLong();
        this.sum = dataInput.readLong();
    }

    @Override
    public String toString() {
        return upload + "\t" + download + "\t" + sum + "\t";
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


}
