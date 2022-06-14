package cn.chain33.javasdk.model.abi;

import java.util.Arrays;
import java.util.List;

import cn.chain33.javasdk.model.abi.datatypes.AddressETH;
import cn.chain33.javasdk.model.abi.datatypes.Type;
import org.junit.jupiter.api.Test;

import cn.chain33.javasdk.model.abi.datatypes.Event;
import cn.chain33.javasdk.model.abi.datatypes.generated.Uint256;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @authoer lhl
 * @date 2022/6/12 上午3:19
 */
public class EventLogDecoderTest {
    @Test
    public void testDecodeEventParameters() {
        //event log 解析
        String eventLog = "000000000000000000000000f39e69a8f2c1041edd7616cf079c7084bb7a524200000000000000000000000000000000000000000000000000000000075bcd1500000000000000000000000000000000000000000000000000000000075bcd15";

        Event event = new Event(
                "SetValue",
                Arrays.asList(
                        new TypeReference<AddressETH>() {},
                        new TypeReference<Uint256>() {},
                        new TypeReference<Uint256>() {}
                )
        );

        List<Type> result= Arrays.asList(
                new AddressETH("0xf39e69a8f2c1041edd7616cf079c7084bb7a5242"),
                new Uint256(123456789L),
                new Uint256(123456789L));
        //解析evmLog
        assertEquals(
                result,
                EventLogDecoder.decodeEventParameters(eventLog,event));
    }
}
