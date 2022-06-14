package cn.chain33.javasdk.utils;

import org.web3j.crypto.Hash;

/**
 * @authoer lhl
 * @date 2022/6/12 下午7:56
 */
public class HashUtil {
    private HashUtil() {}

    /**
     * Generates a digest for the given {@code input}.
     *
     * @param input The input to digest
     * @param algorithm The hash algorithm to use
     * @return The hash value for the given input
     * @throws RuntimeException If we couldn't find any provider for the given algorithm
     */
    public static byte[] hash(byte[] input, String algorithm) {
        return Hash.hash(input,algorithm);
    }

    /**
     * Keccak-256 hash function.
     *
     * @param hexInput hex encoded input data with optional 0x prefix
     * @return hash value as hex encoded string
     */
    public static String sha3(String hexInput) {
        return Hash.sha3(hexInput);
    }

    /**
     * Keccak-256 hash function.
     *
     * @param input binary encoded input data
     * @param offset of start of data
     * @param length of data
     * @return hash value
     */
    public static byte[] sha3(byte[] input, int offset, int length) {
       return Hash.sha3(input, offset, length);
    }

    /**
     * Keccak-256 hash function.
     *
     * @param input binary encoded input data
     * @return hash value
     */
    public static byte[] sha3(byte[] input) {
        return sha3(input, 0, input.length);
    }

    /**
     * Keccak-256 hash function that operates on a UTF-8 encoded String.
     *
     * @param utf8String UTF-8 encoded string
     * @return hash value as hex encoded string
     */
    public static String sha3String(String utf8String) {
        return Hash.sha3String(utf8String);
    }

    /**
     * Generates SHA-256 digest for the given {@code input}.
     *
     * @param input The input to digest
     * @return The hash value for the given input
     * @throws RuntimeException If we couldn't find any SHA-256 provider
     */
    public static byte[] sha256(byte[] input) {
        return Hash.sha256(input);
    }

    public static byte[] hmacSha512(byte[] key, byte[] input) {
       return Hash.hmacSha512(key, input);
    }

    public static byte[] sha256hash160(byte[] input) {
       return Hash.sha256hash160(input);
    }

    /**
     * Blake2-256 hash function.
     *
     * @param input binary encoded input data
     * @return hash value
     */
    public static byte[] blake2b256(byte[] input) {
        return Hash.blake2b256(input);
    }
}

