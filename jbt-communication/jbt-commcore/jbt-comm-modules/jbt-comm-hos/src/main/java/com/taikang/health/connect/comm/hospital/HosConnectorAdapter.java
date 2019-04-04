package com.taikang.health.connect.comm.hospital;

import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * 连接适配器
 */
public class HosConnectorAdapter {
    private static Logger logger = (Logger) LoggerFactory.getLogger(HosConnectorAdapter.class);
    private static Map<String,Class> connectors = new ConcurrentHashMap<>();
    static{
        connectors.put("groudId","对应的类对象".getClass());
    }
    }
