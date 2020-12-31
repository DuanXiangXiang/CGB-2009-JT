package com.jt.aop;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   //全局异常
public class SysResultException {

    /**
     * 如果我们的程序出了问题，应该返回什么样的数据 SysResult
     */
    @ExceptionHandler(RuntimeException.class)
    public SysResult fail(Exception e){
        e.printStackTrace();
        return SysResult.fail();
    }
}
