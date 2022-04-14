package top.xiaohuohu.license.core.enums;

import lombok.Getter;
import top.xiaohuohu.license.core.exceptions.LicenseException;
import top.xiaohuohu.license.core.utils.CommandUtil;
import top.xiaohuohu.license.core.utils.MD5Util;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 机器注册码获取
 */
@Getter
public enum MachineRegistration {
    /**
     * window环境暂时注册码固定
     */
    WINDOW() {
        @Override
        public String getCode() {
            // 暂时固定
            return "1234567890987654321";
        }
    },
    LINUX() {
        /**
         * <b>获取本机的注册码</b>
         * <pre>
         * window 环境 默认测试 注册码固定
         * linux 环境 通过挂载 /dev/mem 目录 dmidecode 命令解析 获取主机硬件信息
         *      目前 获取 system-uuid + system-version + bios-version
         * </pre>
         */
        @Override
        public String getCode() {
            List<String> cmdList = Arrays.asList(
                    "dmidecode -s system-uuid",
                    "dmidecode -s system-version",
                    "dmidecode -s bios-version");
            try {
                List<String> cmdResult = cmdList.stream().map(CommandUtil::runShell).collect(Collectors.toList());

                StringJoiner stringJoiner = new StringJoiner("-");

                for (String cmdRes : cmdResult) {
                    stringJoiner.add(cmdRes);
                }
                return MD5Util.enMd(stringJoiner.toString());
            } catch (Exception e) {
                throw new LicenseException(String.format("获取机器注册码错误 原因:%s", e.getMessage()));
            }
        }
    },
    AUTO() {
        /**
         * 简单自动识别环境,切换处理方式
         * */
        @Override
        public String getCode() {
            boolean isWindow = System.getProperty("os.name").toLowerCase().contains("win");
            return isWindow
                    ? MachineRegistration.WINDOW.getCode()
                    : MachineRegistration.LINUX.getCode();
        }
    };

    public abstract String getCode();
}
