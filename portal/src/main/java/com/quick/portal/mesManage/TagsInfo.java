package com.quick.portal.mesManage;

import java.util.List;
import java.util.Map;

/**
 * Created by GaoPh on 2018/5/28.
 */
public class TagsInfo {
    public static String formatTagJoint(List<Map<String,Object>> map){
        StringBuffer sb = new StringBuffer();
        sb.append("<ul>");
        for(int i=0;i<map.size();i++){
            if(i==0){
                sb.append("<li><a class=\\\"active\\\" href='#' >" + map.get(0).get("tag_type_name")+"</a>");
            }else {
                sb.append("<li><a  href='#' >" + map.get(i).get("tag_type_name")+"</a>");
            }
         sb.append("<ul class=\\\"menu-body clearfloat\\\" style=\\\"width:90%\\\">");
        List<Map<String,Object>> tags = (List<Map<String,Object>>) map.get(i).get("tags");
         for(Map<String,Object> tag:tags){
             sb.append("<li style=\\\"line-height:20px \\\"><a href='#' onclick=\\\"click_event('"+tag.get("tag_id")+","+tag.get("tag_text")+"')\\\">"+ tag.get("tag_text")+"</a></li>");
         }
            sb.append("</ul>");
            sb.append("</li>");
        }
        sb.append("</ul>");
        String str = sb.toString();
        return  str;
    }
}
