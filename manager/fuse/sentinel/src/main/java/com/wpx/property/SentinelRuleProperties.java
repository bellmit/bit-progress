package com.wpx.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 不会飞的小鹏
 * Sentinel的规则配置
 */
@Configuration
@ConfigurationProperties(SentinelRuleProperties.PREFIX)
public class SentinelRuleProperties {

    public static final String PREFIX = "spring.cloud.sentinel.config.nacos.rule";

    private RuleProperties flow;

    private RuleProperties degrade;

    private RuleProperties system;

    private RuleProperties authority;

    public RuleProperties getFlow() {
        return flow;
    }

    public void setFlow(RuleProperties flow) {
        this.flow = flow;
    }

    public RuleProperties getDegrade() {
        return degrade;
    }

    public void setDegrade(RuleProperties degrade) {
        this.degrade = degrade;
    }

    public RuleProperties getSystem() {
        return system;
    }

    public void setSystem(RuleProperties system) {
        this.system = system;
    }

    public RuleProperties getAuthority() {
        return authority;
    }

    public void setAuthority(RuleProperties authority) {
        this.authority = authority;
    }
}
