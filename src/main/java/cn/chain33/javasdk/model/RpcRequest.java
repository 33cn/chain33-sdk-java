package cn.chain33.javasdk.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.chain33.javasdk.model.enums.RpcMethod;

public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jsonrpc;

    private Integer id;

    private String method;

    private JSONArray params;

    private transient String privateKey;

    public RpcRequest() {
        this.jsonrpc = "2.0";
        this.id = 2;
        params = new JSONArray();
    }

    public JSONArray getParams() {
        return params;
    }

    public void setParams(JSONArray params) {
        this.params = params;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(RpcMethod method) {
        this.method = method.toString();
    }

    public void addParams(String data) {
        JSONObject json = new JSONObject();
        json.put("data", data);
        this.params.add(json);
    }

    public void addJsonParams(JSONObject jsonParams) {
        this.params.add(jsonParams);
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

}
