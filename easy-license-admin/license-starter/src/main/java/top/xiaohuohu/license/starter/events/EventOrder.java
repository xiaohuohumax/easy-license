package top.xiaohuohu.license.starter.events;

public interface EventOrder {

    default int getOrder() {
        return Integer.MAX_VALUE;
    }
}
