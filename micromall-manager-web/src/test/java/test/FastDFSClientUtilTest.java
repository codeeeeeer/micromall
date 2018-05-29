package test;

import com.micromall.commonUtils.FastDFSClient;

/**
 * 〈the test class of fast dfs client util〉
 *
 * @author LewJay
 * @create 2018/5/28 7:21
 */
public class FastDFSClientUtilTest {
    public static void main(String[] args)throws Exception{
        FastDFSClient client = new FastDFSClient("classpath:FastDFS.conf");
        String file = client.uploadFile("G:\\wallpapers\\pexels-photo.jpg");
        System.out.println(file);
    }
}
