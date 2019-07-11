package ggze.esp.controller;


import ggze.esp.dao.inter.ApiDao;
import ggze.esp.dao.inter.DataRecordDao;
import ggze.esp.dao.inter.LookRecordDao;
import ggze.esp.entity.Api;
import ggze.esp.entity.DataRecord;
import ggze.esp.entity.LookRecord;
import ggze.loader.Storage;

import ggze.tool.BeanToJson;
import ggze.tool.ParHttpRequest;
import ggze.tool.ReturnBody;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class GouController{
    @Autowired
    private ApiDao apiDao;
    @Autowired
    private DataRecordDao dataRecordDao;
    @Autowired
    private LookRecordDao lookRecordDao;
    @Autowired
    private HttpServletRequest request;
    @RequestMapping("/vsp")
    public String esp(HttpServletResponse response) throws Exception {
        String originHeader = this.getClientIP(request);
        Api api=new Api();
        if(originHeader==null){
            api=null;
        }else{
            api=apiDao.isSelect("a_ip",originHeader);
        }
        if(api==null||api.getA_is().equals("0")){
            response.setHeader("Access-Control-Allow-Origin", originHeader);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        }
        String webName= Storage.getObj().getConfig().get("mirrorName");
        String esp=request.getParameter("esp");
        String code=request.getParameter("code");
        String view=Storage.getObj().getConfig().get("mirrorView");
        LookRecord lookRecord = lookRecordDao.isSelect("uuid",code);
        if(lookRecord.getStatus().equals("0")){
            if(view.equals("2")){
                return  lookRecord.getPageView();
            }else{
                response.sendRedirect(lookRecord.getPageView());
            }
        }else{
            return null;
        }
        return null;
    }
    @RequestMapping("dsp")
    @ResponseBody
    public String dsp(HttpServletResponse response){
        String originHeader = this.getClientIP(request);
        Api api=new Api();
        if(originHeader==null){
            api=null;
        }else{
            api=apiDao.isSelect("a_ip",originHeader);
        }
        if(api==null||api.getA_is().equals("0")){
            response.setHeader("Access-Control-Allow-Origin", originHeader);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        }
        String webName= Storage.getObj().getConfig().get("mirrorName");
        String code=request.getParameter("code");
        DataRecord dataRecord = dataRecordDao.isSelect("uuid",code);
        if(dataRecord.getStatus().equals("0")){
            String jon=request.getParameter("jon");
            String xp="http://localhost"+webName+"/"+dataRecord.getUri();
            String req=ParHttpRequest.sendPost("http://localhost"+webName+"/"+dataRecord.getUri(),jon);
            return req;
        }else{
            ReturnBody returnBody = new ReturnBody();
            returnBody.setCode("1");
            returnBody.setTitle("此api已经被禁用！请联系开发者");
            return BeanToJson.toJsonObjectString(returnBody);
        }
    }


    private String getClientIP(HttpServletRequest request){
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}
