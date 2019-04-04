package com.taikang.health.connect.comm.hospital.yilianzhong;

import com.taikang.health.connect.comm.hospital.yilianzhong.support.HosConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HosConnectorByYiLianZhong implements HosConnector {
    private static Logger logger = LoggerFactory.getLogger(HosConnectorByYiLianZhong.class);
    private RequestBuilder builder = new SimpleRequestBuilder();
    @Autowired
    private MongoTemplate MongoTemplate;
    @Autowired
    private ResourceConfigSupplier resourceConfigSupplier;

    /**
     *
     * @param groupId 机构编码
     * @param opcode 业务编码
     * @return 配置信息
     */
    protected ResConfig getResConfig(String groupId,String opcode){
        //入参校验
        //从配置信息中获取配置信息
        ResConfig config=  resourceConfigSupplier.getResConfig(groupId);
        switch(opcode){
            //获取平台医疗目录
            case 1 :
                //获取疾病医疗目录
            case 2 :
                //获取档案
            case 3 :
                //获取出院信息
            case 4 :
                //获取费用明细
            case 5 :
        }
    }
    //获取登录令牌
    protected String getToken(ResConfig config){

    }
    /**
     * 发起httpPost请求
     */
    protected String doHttpPost(ResConfig config,String param){
        Response post = builder.https(url)
                .requestbody(param)
                .contentType("application/json")//编码类型
                .setConnectTimeOut(18000)//超时时间
                .post();//请求方式
    }
    //获取平台医疗目录
    public List<HosMedicalSerMapping> getHosptialMdeicaSetlist( String groupId,String hospitalId){
        //入参校验
        //配置信息
        //功能验证
        config.isEnable（）
        //访问资源相应数据
        String responseData = getResourceResponse(config,ylzMdeicareListReq);
        //提取data字段报文
        String data = getDataFieldMessage(config,responseData);
        //解密data报文
        String decrypteData = getDecrypteData(config,getDataFieldMessage);
        //实例转换
       List<ylzMdeicareListRes> list= toJavaObjectList(config,decrypteData,ylzMdeicareListRes.class);
        //

    }
}
