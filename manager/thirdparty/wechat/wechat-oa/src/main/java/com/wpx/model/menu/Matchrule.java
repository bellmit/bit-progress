package com.wpx.model.menu;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 */
public class Matchrule {

    /**
     * 用户标签的id，可通过用户标签管理接口获取
     */
    @JSONField(name = "tag_id")
    private String tagId;
    @JSONField(name = "group_id")
    private Integer groupId;
    //性别：男（1）女（2），不填则不做匹配
    private Integer sex;
    // 国家信息，是用户在微信中设置的地区，具体请参考地区信息表
    private String country;
    // 省份信息，是用户在微信中设置的地区，具体请参考地区信息表
    private String province;
    // 城市信息，是用户在微信中设置的地区，具体请参考地区信息表
    private String city;
    //客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)，不填则不做匹配
    @JSONField(name = "client_platform_type")
    private String clientPlatformType;
    //语言信息
    private String language;
}
