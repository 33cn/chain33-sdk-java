package cn.chain33.javasdk.model.abi.datatypes;

import cn.chain33.javasdk.utils.AddressUtil;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

/**
 * @authoer lhl
 * @date 2022/6/10 上午11:54
 * btc类型地址定义
 */
public class AddressBTC implements Type<String> {

    public static final String TYPE_NAME = "address";
    public static final int DEFAULT_LENGTH = 160;
    public static final Address DEFAULT = new Address(BigInteger.ZERO);

    private final Uint value;

    public AddressBTC(Uint value) {
        this.value = value;
    }

    public AddressBTC(BigInteger value) {
        this(DEFAULT_LENGTH, value);
    }

    public AddressBTC(int bitSize, BigInteger value) {
        this(new Uint(bitSize, value));
    }

    public AddressBTC(String address) {
        String hexValue = AddressUtil.convertBTCToETH(address);
        new Address(DEFAULT_LENGTH, hexValue);
        this.value = new Uint(DEFAULT_LENGTH, Numeric.toBigInt(hexValue));
    }

    public AddressBTC(int bitSize, String hexValue) {
        this(bitSize, Numeric.toBigInt(hexValue));
    }


    public Uint toUint() {
        return value;
    }

    public String toETHAddress() {
        return AddressUtil.convertBTCToETH(toString());
    }

    @Override
    public String getTypeAsString() {
        return TYPE_NAME;
    }

    @Override
    public String toString() {
        return AddressUtil.convertETHToBTC(Numeric.toHexStringWithPrefixZeroPadded(value.getValue(), value.getBitSize() >> 2));
    }

    @Override
    public String getValue() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressBTC address = (AddressBTC) o;

        return value != null ? value.value.equals(address.value.value) : address.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
