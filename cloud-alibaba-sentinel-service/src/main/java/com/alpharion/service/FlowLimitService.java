package com.alpharion.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 流控Service
 *
 * @author XieYT
 * @since 2024/04/21 下午10:11
 */
@Slf4j
@Service
public class FlowLimitService {

    @SentinelResource(value = "common")
    public void common() {
        log.info("common");
    }
}
