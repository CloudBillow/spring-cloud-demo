package com.alpharion.controller;

import com.alpharion.result.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos Config Client Controller
 *
 * @author XieYT
 * @since 2024/04/20 下午10:26
 */
@RefreshScope
@RestController
public class NacosConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public R<String> getConfigInfo() {
        return R.success(configInfo);
    }
}
