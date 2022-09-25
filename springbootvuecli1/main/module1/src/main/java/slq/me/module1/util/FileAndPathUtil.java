package slq.me.module1.util;

import java.io.IOException;
import java.util.Random;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileAndPathUtil {
    public static Random random = new Random();

    public static String getModulePath() throws IOException {
        Resource resource = new ClassPathResource("");
        String modulePath = resource.getFile().getParentFile().getParentFile().getAbsolutePath();
        // System.out.println(modulePath);
        return modulePath;
    }
    
    public static String getRandomFilename() throws IOException {
        return System.currentTimeMillis() + "-" + random.nextInt(100);
    }
}
