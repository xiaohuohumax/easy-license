package top.xiaohuohu.license.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 命令行工具
 */
public class CommandUtil {
    /**
     * 运行 shell 命令
     *
     * @param shellStr 命令
     * @return 返回结果
     * @throws RuntimeException 命令调用错误/命令执行错误
     */
    public static String runShell(String shellStr) {
        String[] cmd = {"/bin/sh", "-c", shellStr};

        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(cmd);

            //0 表示线程正常终止。
            if (process.waitFor() == 0) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8);
                     BufferedReader ignore = new BufferedReader(inputStreamReader)) {
                    return byBufferedReaderGetContext(ignore);
                }
            }
            // 获取错误原因
            try (InputStreamReader errorStreamReader = new InputStreamReader(process.getErrorStream(), "GBK");
                 BufferedReader ignores = new BufferedReader(errorStreamReader)) {
                String errorResultStr = byBufferedReaderGetContext(ignores);
                throw new RuntimeException(errorResultStr);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 依据 BufferedReader 获取内容
     *
     * @param cmdResultReader reader
     * @return reader 字符串
     */
    private static String byBufferedReaderGetContext(BufferedReader cmdResultReader) throws IOException {
        String line;
        StringBuilder resStr = new StringBuilder();
        while ((line = cmdResultReader.readLine()) != null) {
            resStr.append(line);
        }
        return resStr.toString();
    }

}
