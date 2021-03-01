package cn.chain33.javasdk.client;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;

import java.net.SocketAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultipleResolverProvider extends NameResolverProvider {

    final List<EquivalentAddressGroup> addresses;

    MultipleResolverProvider(List<EquivalentAddressGroup> addresses) {
//        this.addresses = Arrays.stream(addresses)
//                .map(EquivalentAddressGroup::new)
//                .collect(Collectors.toList());
        this.addresses = addresses;
    }

    public NameResolver newNameResolver(URI notUsedUri, NameResolver.Args args) {
        return new NameResolver() {
            @Override
            public String getServiceAuthority() {
                return "mideaChain";
            }
            public void start(Listener2 listener) {
                listener.onResult(ResolutionResult.newBuilder().setAddresses(addresses).setAttributes(Attributes.EMPTY).build());
            }
            public void shutdown() {
            }
        };
    }

    @Override
    public String getDefaultScheme() {
        return "multiple";
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 0;
    }
}
