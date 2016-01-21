package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.ons.model.v20151214.OnsClusterListResponse;
import com.aliyuncs.ons.model.v20151214.OnsClusterNamesResponse;
import com.saintdan.util.rocketmq.ons.cluster.Cluster;
import com.saintdan.util.rocketmq.param.BaseMsgParam;
import com.saintdan.util.rocketmq.param.BaseParam;
import org.junit.Test;

import java.util.List;

/**
 * 集群信息获取测试
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/19/16
 * @since JDK1.8
 */
public class ClusterTest extends BaseTest {

    /**
     * 获取当前Region下的集群
     * <p>
     *     测试通过
     * </p>
     *
     * 返回
     * <pre>
     *      {"brokerAddr":"10.153.137.73:8080","brokerId":0,"brokerIp":"10.153.137.73","brokerName":"hzshare-01","clusterName":"hzshare","inTPS":3456.425,"inTotalToday":8.8051072E7,"inTotalYest":2.54808176E8,"outTPS":2889.6033,"outTotalToday":6.170536E7,"outTotalYest":1.98733808E8,"version":"V3_4_5_SNAPSHOT"}
     *      {"brokerAddr":"10.153.137.74:8080","brokerId":0,"brokerIp":"10.153.137.74","brokerName":"hzshare-02","clusterName":"hzshare","inTPS":3408.7366,"inTotalToday":8.8013168E7,"inTotalYest":2.54757968E8,"outTPS":3097.461,"outTotalToday":6.8717984E7,"outTotalYest":2.11404272E8,"version":"V3_4_5_SNAPSHOT"
     * </pre>
     */
    @Test
    public void testShow() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setOnsRegionId("hangzhou");
        OnsClusterListResponse response = cluster.show(param);
        List<OnsClusterListResponse.ClusterInfoDataDo> clusterInfoDataDoList=response.getData();
        for(OnsClusterListResponse.ClusterInfoDataDo clusterInfoDataDo:clusterInfoDataDoList){
            List<OnsClusterListResponse.ClusterInfoDataDo.BrokerInfoDataDo> brokerList = clusterInfoDataDo.getBrokerInfoList();
            for (OnsClusterListResponse.ClusterInfoDataDo.BrokerInfoDataDo brokerInfoDataDo:brokerList){
                System.out.println(JSON.toJSONString(brokerInfoDataDo));
            }
        }
    }

    /**
     * 查询当前Region下集群名列表
     * <p>
     *     测试通过
     * </p>
     *
     * 返回
     * <pre>
     *     {"data":["hzshare"],"requestId":"5104752A-BE04-4F18-9438-2BD56A82E1A4"}
     * </pre>
     *
     * @throws Exception
     */
    @Test
    public void testShowNames() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setOnsRegionId("hangzhou");
        OnsClusterNamesResponse response = cluster.showNames(param);
        System.out.println(JSON.toJSONString(response));
    }

    private Cluster cluster = new Cluster();
}
