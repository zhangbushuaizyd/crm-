package com.shsxt.crm.exceptions;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.contains.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
       ModelAndView mv = createDefaultModelAndView(request,ex);
       //登录异常的处理

        if (ex instanceof LoginExcepiton){
            mv.addObject("errorMsg", CrmConstant.USER_NOT_LOGIN_MSG);
            mv.setViewName("login_error");
            return mv;
        }
        /**
         * 区分是什么异常,页面请求或者json请求
         */
        if (handler instanceof HandlerMethod)//逻辑上的安全,是该方法就出处理
        {
            /**
             * 解决:通过反射获取目标方法的注解,有就是json请求没有.普通请求
             */
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if (null == responseBody){
                //普通页面请求
                if (ex instanceof ParamsException){

                    ParamsException e = (ParamsException) ex;
                    mv.addObject("errorMsg",e.getMessage());

                }
            }else {
                //json请求
                ResultInfo info = new ResultInfo();
                info.setCode(300);
                info.setMsg("系统繁忙");
                if (ex instanceof ParamsException){
                    ParamsException e = (ParamsException) ex;
                    info.setMsg(e.getMsg());
                }
                //设置返回的响应编码
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                //用流的方式输出
                PrintWriter pw = null;

                try {
                    pw = response.getWriter();
                    pw.write(JSON.toJSONString(info));
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //确保流关闭
                    if (null!=pw){
                        pw.close();
                    }
                }
                return null;
            }
        }

        return mv;
    }

    private ModelAndView createDefaultModelAndView(HttpServletRequest request, Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error"); //跳转到错误页面
        mv.addObject("errorMsg","系统繁忙");   //错误信息
        mv.addObject("errorCode",300);      //错误码
        mv.addObject("uri",request.getRequestURI());        //请求路径
        mv.addObject("ctx",request.getContextPath());       //上下文路径
        mv.addObject("ctx",request.getContextPath());   //发生错误的时候,跳出Controller层 ctx是没有值的

        //System.out.println("==========================");
        //System.out.println("uri: "+request.getRequestURI());
        //System.out.println("==========================");
        return mv;
    }
}
