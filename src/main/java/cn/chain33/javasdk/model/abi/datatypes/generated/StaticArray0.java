package cn.chain33.javasdk.model.abi.datatypes.generated;

import cn.chain33.javasdk.model.abi.datatypes.StaticArray;
import cn.chain33.javasdk.model.abi.datatypes.Type;

import java.util.List;

/**
 * Auto generated code.
 * <p><strong>Do not modifiy!</strong>
 * <p>Please use cn.chain33.javasdk.model.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class StaticArray0<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray0(List<T> values) {
        super(0);
    }

    @Deprecated
    @SafeVarargs
    public StaticArray0(T... values) {
        super(0, values);
    }

    public StaticArray0(Class<T> type, List<T> values) {
        super(type, 0, values);
    }

    @SafeVarargs
    public StaticArray0(Class<T> type, T... values) {
        super(type, 0, values);
    }
}
