package com.joy.programmer.joyce.util;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

public class MurmurHashFunctionImpl implements HashFunction {
    @Override
    public Integer hash(String key) {
        com.google.common.hash.HashFunction hf = Hashing.murmur3_32();
        HashCode hashCode = hf.newHasher().putBytes(key.getBytes()).hash();
        return Math.abs(hashCode.asInt());
    }
}
