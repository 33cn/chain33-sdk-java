package cn.chain33.javasdk.model.abi.datatypes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @authoer lhl
 * @date 2022/6/12 上午12:49
 */
public class AddressETHTest {

    String[] ethAddress = new String[]{
            "0x20fa54c061fe538b24dbe0f6dab18d4be0de36e4",
            "0xd4215189165742bf6585d1a5f7a35955ad006c93",
            "0xf39e69a8f2c1041edd7616cf079c7084bb7a5242"
    };
    String[] btcAddress = new String[]{
            "141NWSqwocrgUaXfnJouJfe2npgHLiV8JV",
            "1LLeAgKP18WNr8Xc9VmELWeqmTsjcD3PFi",
            "1PD8yKphczWERv8KnjQrdHjLxDWqXkBLgN"
    };

    @Test
    public void testToString() {
        for (int i = 0; i < ethAddress.length; i++) {
            assertEquals(ethAddress[i], new AddressETH(ethAddress[i]).toString());
        }
    }

    @Test
    public void testToBTCAddress() {
        for (int i = 0; i < ethAddress.length; i++) {
            assertEquals(btcAddress[i], new AddressETH(ethAddress[i]).toBTCAddress());
        }
    }
}
