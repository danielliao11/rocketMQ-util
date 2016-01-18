package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.ons.model.v20151214.OnsRegionListResponse;
import com.saintdan.util.rocketmq.ons.region.Region;
import org.junit.Test;

/**
 * 地区测试
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/25/15
 * @since JDK1.8
 */
public class RegionTest extends BaseTest {

    @Test
    public void testShowAllRegions() throws Exception {
        Region region = new Region();
        for (OnsRegionListResponse.RegionDo regionDo : region.showAll(getParam())) {
            System.out.println(JSON.toJSONString(regionDo));
        }
    }

}
