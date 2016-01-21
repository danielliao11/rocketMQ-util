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

    /**
     * 查看Region列表
     *
     * <p>
     *      测试通过
     * </p>
     *
     * 返回
     * <pre>
     *      {"channelName":"","createTime":1411623866000,"id":1,"onsRegionId":"publictest","regionName":"公网测试","status":1,"updateTime":1411623866000}
     *      {"channelName":"","createTime":1411623866000,"id":3,"onsRegionId":"qingdao","regionName":"青岛","status":1,"updateTime":1411623866000}
     *      {"channelName":"","createTime":1411623866000,"id":4,"onsRegionId":"shenzhen","regionName":"深圳","status":1,"updateTime":1411623866000}
     *      {"channelName":"","createTime":1413877180000,"id":6,"onsRegionId":"hangzhou","regionName":"杭州","status":1,"updateTime":1413877180000}
     *      {"channelName":"","createTime":1413008259000,"id":8,"onsRegionId":"beijing","regionName":"北京 ","status":1,"updateTime":1413008259000}
     *      {"channelName":"","createTime":1452840680000,"id":9,"onsRegionId":"shanghai","regionName":"上海","status":1,"updateTime":1452840688000}
     * </pre>
     */
    @Test
    public void testShowAllRegions() throws Exception {
        Region region = new Region();
        for (OnsRegionListResponse.RegionDo regionDo : region.showAll(getParam())) {
            System.out.println(JSON.toJSONString(regionDo));
        }
    }

}
