package cn.chain33.javasdk.model.abi.datatypes;

import cn.chain33.javasdk.utils.AddressUtil;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

/**
 * @authoer lhl
 * @date 2022/6/10 上午11:54
 * eth类型地址定义,默认原生就是eth地址格式
 */
public class AddressETH implements Type<String> {
    public static final String TYPE_NAME = "address";
    public static final int DEFAULT_LENGTH = 160;
    public static final AddressETH DEFAULT = new AddressETH(BigInteger.ZERO);

    private final Uint value;

    public AddressETH(Uint value) {
        this.value = value;
    }

    public AddressETH(BigInteger value) {
        this(DEFAULT_LENGTH, value);
    }

    public AddressETH(int bitSize, BigInteger value) {
        this(new Uint(bitSize, value));
    }

    public AddressETH(String hexValue) {
        this(DEFAULT_LENGTH, hexValue);
    }

    public AddressETH(int bitSize, String hexValue) {
        this(bitSize, Numeric.toBigInt(hexValue));
    }

    public Uint toUint() {
        return value;
    }

    public String toBTCAddress() {
        return AddressUtil.convertETHToBTC(toString());
    }

    @Override
    public String getTypeAsString() {
        return TYPE_NAME;
    }

    @Override
    public String toString() {
        return Numeric.toHexStringWithPrefixZeroPadded(value.getValue(), value.getBitSize() >> 2);
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

        AddressETH address = (AddressETH) o;

        return value != null ? value.value.equals(address.value.value) : address.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
