package cn.chain33.javasdk.model.abi.datatypes.generated;

import cn.chain33.javasdk.model.abi.datatypes.Uint;

import java.math.BigInteger;

/**
 * Auto generated code.
 * <p><strong>Do not modifiy!</strong>
 * <p>Please use cn.chain33.javasdk.model.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Uint144 extends Uint {
    public static final Uint144 DEFAULT = new Uint144(BigInteger.ZERO);

    public Uint144(BigInteger value) {
        super(144, value);
    }

    public Uint144(long value) {
        this(BigInteger.valueOf(value));
    }
}
