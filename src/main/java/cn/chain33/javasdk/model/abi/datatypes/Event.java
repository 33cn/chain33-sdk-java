/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package cn.chain33.javasdk.model.abi.datatypes;

import cn.chain33.javasdk.model.abi.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.chain33.javasdk.model.abi.Utils.convert;

/**
 * Event wrapper type.
 */
public class Event {
    private String name;
    private List<TypeReference<Type>> parameters;

    public Event(String name, List<TypeReference<?>> parameters) {
        this.name = name;
        this.parameters = convert(parameters);
    }

    public String getName() {
        return name;
    }

    public List<TypeReference<Type>> getParameters() {
        return parameters;
    }

    public List<TypeReference<Type>> getIndexedParameters() {
        return parameters.stream().filter(TypeReference::isIndexed).collect(Collectors.toList());
    }

    public List<TypeReference<Type>> getNonIndexedParameters() {
        return parameters.stream().filter(p -> !p.isIndexed()).collect(Collectors.toList());
    }

    /**
     * 参数解析结果是否需要重新排序
     *
     * @return
     */
    public boolean isRecordered() {
        if (getIndexedParameters().size() == 0) {
            return false;
        }
        for (int i = 0; i < getIndexedParameters().size(); i++) {
            if (!getParameters().get(i).isIndexed()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取参数位置索引list
     *
     * @return
     */
    public List<Integer> getParsedParametersIndex() {
        ArrayList<Integer> indexedList = new ArrayList<Integer>();
        ArrayList<Integer> noIndexedList = new ArrayList<Integer>();
        ArrayList<Integer> parsedParametersIndex = new ArrayList<Integer>();
        for (int i = 0; i < getParameters().size(); i++) {
            if (getParameters().get(i).isIndexed()) {
                indexedList.add(i);
                continue;
            }
            noIndexedList.add(i);
        }
        parsedParametersIndex.addAll(indexedList);
        parsedParametersIndex.addAll(noIndexedList);
        return parsedParametersIndex;
    }

    /**
     * 获取排序后的event 参数列表
     *
     * @return
     */
    public List<TypeReference<Type>> getParsedParameters() {
        List<TypeReference<Type>> paramters = getIndexedParameters();
        paramters.addAll(getNonIndexedParameters());
        return paramters;
    }
}
