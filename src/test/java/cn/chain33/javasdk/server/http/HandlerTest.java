package cn.chain33.javasdk.server.http;

import cn.chain33.javasdk.model.protobuf.BlockchainProtobuf;
import cn.chain33.javasdk.model.protobuf.EvmEventProtobuf;
import cn.chain33.javasdk.model.protobuf.EvmEventProtobuf.EVMTxLogsInBlks;
import cn.chain33.javasdk.model.protobuf.EvmEventProtobuf.EVMTxLogPerBlk;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.protobuf.format.JsonFormat;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * @authoer lhl
 * @date 2022/6/16 下午3:30
 */
public class HandlerTest {

    @Test
    public void testJsonToProto() {

        String ajson="{\"seqs\":[{\"num\":\"769\",\"seq\":{\"Hash\":\"0xe51b635350b32496da8c92cf83be28858360d065bfbb3040637c037a33d2058c\",\"Type\":\"1\"},\"detail\":{\"block\":{\"version\":\"0\",\"parentHash\":\"0x1797059d0236f2ca1b52accaecd6d315a40a7d1a654f10f82e336038021b8541\",\"txHash\":\"0x37922b2b2c7d4b5ade851e8bb306a9108f2c4b8b7447633235285c17f6bc152a\",\"stateHash\":\"0xe6af137b7886957ee0e0ba22c392d7d9c63cb395f81136553431432d16d541ef\",\"height\":\"769\",\"blockTime\":\"1655733774\",\"difficulty\":520159231,\"mainHash\":\"0xe51b635350b32496da8c92cf83be28858360d065bfbb3040637c037a33d2058c\",\"mainHeight\":\"769\",\"signature\":null,\"txs\":[{\"execer\":\"0x636f696e73\",\"payload\":\"0x18010a291080c2d72f2222314e554d7157506b796a6e3658466354587a506f34655a6a6d396e3646636f673557\",\"signature\":{\"ty\":1,\"pubkey\":\"0x02504fa1c28caaf1d5a20fefb87c50a49724ff401043420cb3ba271997eb5a4387\",\"signature\":\"0x304502210089fa1328ee8a1df2a017f7a71ff0599f2788315b1f6593afc68828ac162c9aad02200b7b9884d1751a2c73ccdc1b981c0e9b1240b395c4a64bdb2410ade9383ce93e\"},\"fee\":\"100000\",\"expire\":\"1655733893\",\"nonce\":\"3342105679304883261\",\"to\":\"1NUMqWPkyjn6XFcTXzPo4eZjm9n6Fcog5W\",\"groupCount\":0,\"header\":null,\"next\":null,\"chainID\":0}]},\"receipts\":[{\"ty\":2,\"logs\":[{\"ty\":2,\"log\":\"0x0a2d10e094edec96d6e111222231344b454b6259744b4b516d34774d7468534b394a344c61346e41696964476f7a74122d10c087e7ec96d6e111222231344b454b6259744b4b516d34774d7468534b394a344c61346e41696964476f7a74\"},{\"ty\":3,\"log\":\"0x0a2d10c087e7ec96d6e111222231344b454b6259744b4b516d34774d7468534b394a344c61346e41696964476f7a74122d10c0c58fbd96d6e111222231344b454b6259744b4b516d34774d7468534b394a344c61346e41696964476f7a74\"},{\"ty\":3,\"log\":\"0x0a2a1080f6ba870a2222314e554d7157506b796a6e3658466354587a506f34655a6a6d396e3646636f673557122a1080b892b70a2222314e554d7157506b796a6e3658466354587a506f34655a6a6d396e3646636f673557\"}]}],\"KV\":[],\"prevStatusHash\":null}}]}";

       JSONObject jsonObject= JSONObject.parseObject(ajson);
       System.out.println(jsonObject.toJSONString());

        byte[] bytes = jsonObject.toJSONString().getBytes();
        try {
            ByteArrayInputStream inputStream= new ByteArrayInputStream(bytes);
            JsonFormat jsonFormat=new JsonFormat();
            BlockchainProtobuf.BlockSeqs.Builder builder =   BlockchainProtobuf.BlockSeqs.newBuilder();
            jsonFormat.merge(inputStream,builder);
            BlockchainProtobuf.BlockSeqs  blockSeqs  = builder.build();
            System.out.println(blockSeqs);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
