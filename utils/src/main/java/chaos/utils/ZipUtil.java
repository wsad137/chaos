package chaos.utils;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ZipUtil {

    /**
     * Extract all files from Tar into the specified directory
     *
     * @param tarFile
     * @param directory
     * @return the list of extracted filenames
     * @throws IOException
     */
    public static List<String> unZip(File tarFile, File directory) {
        List<String> result = new ArrayList<String>();

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(tarFile);
            ZipArchiveInputStream in = new ZipArchiveInputStream(inputStream);

            ZipArchiveEntry entry = in.getNextZipEntry();
            while (entry != null) {
                if (entry.isDirectory()) {
                    entry = in.getNextZipEntry();
                    continue;
                }
                File curfile = new File(directory, entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                OutputStream out = new FileOutputStream(curfile);
                IOUtils.copy(in, out);
                out.close();
                result.add(entry.getName());
                entry = in.getNextZipEntry();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return result;
    }

    /**
     * 解压 zip 文件
     *
     * @param zipfile zip 压缩文件的路径
     * @param destDir zip 压缩文件解压后保存的目录
     * @return 返回 zip 压缩文件里的文件名的 list
     * @throws Exception
     */
    public static List<String> unZip(String zipfile, String destDir) {
        File zipFile = new File(zipfile);
        return unZip(zipFile, new File(destDir));
    }

    public static void main(String[] args) {
//        List<String> names = unZip("F:\\MyProject\\github\\chaos\\api\\target\\classes\\chaosApi.zip", "F:\\MyProject\\github\\chaos\\api\\target\\classes");
        List<String> names = null;
        names = unZip(new File("F:\\MyProject\\github\\chaos\\api\\target\\classes\\chaosApi.zip"), new File("F:\\MyProject\\github\\chaos\\api\\target\\classes"));
        System.out.println(names);
    }
}