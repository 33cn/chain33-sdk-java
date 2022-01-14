package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.List;

public class ListPushesResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<PushSubscribeReq> pushes;

    public List<PushSubscribeReq> getPushes() {
        return pushes;
    }

    public void setPushes(List<PushSubscribeReq> pushes) {
        this.pushes = pushes;
    }

    @Override
    public String toString() {
        return "ListPushesResult{" + "pushes=" + pushes + '}';
    }
}
