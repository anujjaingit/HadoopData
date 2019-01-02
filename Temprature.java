import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Temprature {
    public static void main(String[] args) throws Exception 
    {
        if(args.length != 2) 
        {
            System.err.println("Usage: Temperature<input path> <output path>");
            System.exit(-1);
        }
        Configuration conf =new Configuration();
    	Job job=Job.getInstance(conf,"Temprature");
        job.setJarByClass(Temprature.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(TempratureMapper.class);
        job.setReducerClass(TempratureReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}