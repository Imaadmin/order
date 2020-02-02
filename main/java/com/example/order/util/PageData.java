package com.example.order.util;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

public class PageData extends HashMap implements Map {

    private static final long serialVersionUID = 1L;

    Map map = null;
    HttpServletRequest request;

    public PageData(HttpServletRequest request) {
        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            if (value != null) {
                //推荐使用ESAPI库来避免脚本攻击,value = ESAPI.encoder().canonicalize(value);
                // 避免空字符串
                value = value.replaceAll(" ", "");
                // 避免script 标签
                Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                // 避免src形式的表达式
                scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
                        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
                        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                // 删除单个的 </script> 标签
                scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                // 删除单个的<script ...> 标签
                scriptPattern = Pattern.compile("<script(.*?)>",
                        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                // 避免 eval(...) 形式表达式
                scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                // 避免 e­xpression(...) 表达式
                scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)",
                        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                // 避免 javascript: 表达式
                scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                // 避免 vbscript:表达式
                scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                // 避免 οnlοad= 表达式
                scriptPattern = Pattern.compile("onload(.*?)=",
                        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
            }
            returnMap.put(name, value);
        }
        map = returnMap;
    }

    public PageData() {
        map = new HashMap();
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if (map.get(key) instanceof Object[]) {
            Object[] arr = (Object[]) map.get(key);
            obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    public String getString(Object key) {
        return (String) get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set keySet() {
        return map.keySet();
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public void putAll(Map t) {
        map.putAll(t);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection values() {
        return map.values();
    }

    @Override
    public String toString() {
        return "PageData [map=" + map + ", request=" + request + "]";
    }


}
