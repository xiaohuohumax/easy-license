package top.xiaohuohu.license.starter.events;

/**
 * 许可证解析事件触发顺序
 */
public interface EventOrder {

    default int getOrder() {
        return Integer.MAX_VALUE;
    }
}
