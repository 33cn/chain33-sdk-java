package cn.chain33.javasdk.model.abi.datatypes.generated;

import cn.chain33.javasdk.model.abi.datatypes.Uint;

import java.math.BigInteger;

/**
 * Auto generated code.
 * <p><strong>Do not modifiy!</strong>
 * <p>Please use cn.chain33.javasdk.model.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Uint48 extends Uint {
    public static final Uint48 DEFAULT = new Uint48(BigInteger.ZERO);

    public Uint48(BigInteger value) {
        super(48, value);
    }

    public Uint48(long value) {
        this(BigInteger.valueOf(value));
    }
}
