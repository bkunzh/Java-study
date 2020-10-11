1. done 想控制台值打印某些日志，其实 **指定包信息打印到控制台更好一点** ，因为调试信息会有很多，我控制台只想显示那么几句
```xml
    <root level="debug">
        <appender-ref ref="FILE" />
    </root>

    <logger name="com.bkunzh.console_log">
        <appender-ref ref="STDOUT" />
    </logger>
```