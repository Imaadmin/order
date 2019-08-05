package com.example.order.controller.Base;


import java.util.HashMap;
import java.util.Map;


/**
 * 接口返回信息
 *
 * @author ZhouXiaobing
 * @version 1.0
 * @date 2018年3月24日 上午8:36:41
 */
public class InterfaceResult {


    /**
     * 接口返回状态码
     */
    public static final int SUCCESS = 0;//成功

    public static final int FAILURE = 1;//失败


    /**
     * 接口返回提示信息
     */
    public static final String Msg_success = "操作成功";

    public static final String Msg_failure = "操作失败"; //其实就是系统异常


    /**
     * 返回成功结果
     *
     * @param data
     * @return
     * @author ZhouXiaobing
     * @date 2018年3月24日 上午8:36:59
     */
    public static Map<String, Object> returnSuccess(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", SUCCESS);
        map.put("resmsg", Msg_success);
        map.put("data", data);
        return map;
    }

    /**
     * dataTable的成功结果返回
     *
     * @param data
     * @param total: dataTable分页需要的总条数
     * @param draw：  dataTable需要的调用次数
     * @return
     * @author ZhouXiaobing
     * @date 2018年3月24日 上午8:37:07
     */
    public static Map<String, Object> returnTableSuccess(Object data, Long total, Object draw) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", SUCCESS);
        map.put("resmsg", Msg_success);
        map.put("data", data);
        map.put("recordsTotal", total);   //dataTable分页需要
        map.put("recordsFiltered", total);//dataTable分页需要
        map.put("draw", draw);//dataTable分页需要
        return map;
    }


    /**
     * 返回成功结果  和  自定义成功信息
     *
     * @param data
     * @param msg
     * @return
     * @author ZhouXiaobing
     * @date 2018年3月24日 上午8:37:53
     */
    public static Map<String, Object> returnSuccessWithMsgAndData(Map<String, Object> data, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", SUCCESS);
        map.put("resmsg", msg);
        map.put("data", data);
        return map;
    }

    /**
     * 返回失败结果
     *
     * @return
     * @author ZhouXiaobing
     * @date 2018年3月24日 上午8:38:24
     */
    public static Map<String, Object> returnFailure() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", FAILURE);
        map.put("resmsg", Msg_failure);
        map.put("data", new HashMap<String, Object>());
        return map;
    }


    /**
     * 返回失败结果   提示信息自己定义
     *
     * @param msg
     * @return
     * @author ZhouXiaobing
     * @date 2018年3月24日 上午8:38:31
     */
    public static Map<String, Object> returnFailureWithMsg(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", FAILURE);
        map.put("resmsg", msg);
        map.put("data", new HashMap<String, Object>());
        return map;
    }


    /**
     * 返回结果自定义
     *
     * @param status
     * @param Msg
     * @param data
     * @return
     * @author ZhouXiaobing
     * @date 2018年3月24日 上午8:38:53
     */
    public static Map<String, Object> returnResult(Integer status, String Msg, Map<String, Object> data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("resmsg", Msg);
        map.put("data", data);
        return map;
    }


}
