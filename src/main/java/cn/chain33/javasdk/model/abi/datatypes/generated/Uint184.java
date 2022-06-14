package cn.chain33.javasdk.model.abi.datatypes.generated;

import cn.chain33.javasdk.model.abi.datatypes.Uint;

import java.math.BigInteger;

/**
 * Auto generated code.
 * <p><strong>Do not modifiy!</strong>
 * <p>Please use cn.chain33.javasdk.model.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Uint184 extends Uint {
    public static final Uint184 DEFAULT = new Uint184(BigInteger.ZERO);

    public Uint184(BigInteger value) {
        super(184, value);
    }

    public Uint184(long value) {
        this(BigInteger.valueOf(value));
    }
}
