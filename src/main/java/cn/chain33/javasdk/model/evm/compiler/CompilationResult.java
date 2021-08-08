package cn.chain33.javasdk.model.evm.compiler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CompilationResult {

    private Map<String, ContractMetadata> contracts = Collections.emptyMap();

    public String version = "";

    public static CompilationResult parse(String rawJson) throws IOException {
        System.out.println(rawJson);
        if (rawJson == null || rawJson.isEmpty()) {
            CompilationResult empty = new CompilationResult();
            empty.contracts = Collections.emptyMap();
            empty.version = "";

            return empty;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(rawJson, CompilationResult.class);
        }
    }

    /**
     * @param contractName The contract name
     * @return the first contract found for a given contract name; use {@link #getContract(Path,
     *     String)} if this compilation result contains more than one contract with the same name
     */
    public ContractMetadata getContract(String contractName) {

        for (Map.Entry<String, ContractMetadata> entry : contracts.entrySet()) {
            String key = entry.getKey();
            String name = key.substring(key.lastIndexOf(':') + 1);
            if (contractName.equals(name)) {
                return entry.getValue();
            }
        }
        throw new UnsupportedOperationException(
                "No contract found with name '"
                        + contractName
                        + "'. Please specify a valid contract name. Available keys ("
                        + getContractKeys()
                        + ").");
    }

    /**
     * @param contractPath The contract path
     * @param contractName The contract name
     * @return the contract with key {@code contractPath:contractName} if it exists; {@code null}
     *     otherwise
     */
    public ContractMetadata getContract(Path contractPath, String contractName) {
        return contracts.get(contractPath.toAbsolutePath().toString() + ':' + contractName);
    }

    /** @return all contracts from this compilation result */
    public List<ContractMetadata> getContracts() {
        return new ArrayList<>(contracts.values());
    }

    /** @return all keys from this compilation result */
    public List<String> getContractKeys() {
        return new ArrayList<>(contracts.keySet());
    }

    public static class ContractMetadata {
        public Object abi;
        public String bin;
        public String metadata;

        @Override
        public String toString() {
            return "ContractMetadata [abi=" + abi + ", bin=" + bin + ", metadata=" + metadata + "]";
        }
    }
}
