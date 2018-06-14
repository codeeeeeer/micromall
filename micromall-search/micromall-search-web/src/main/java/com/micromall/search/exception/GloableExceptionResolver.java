package com.micromall.search.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈the gloable exception resolver〉
 *
 * @author LewJay
 * @create 2018/6/12 20:51
 */
public class GloableExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        ModelAndView view = new ModelAndView();
        view.addObject("message", "系统发生异常 请稍后重试！");
        view.setViewName("error/exception");
        return view;
    }
}
