//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dorm.handler;

import com.dorm.common.R;
import com.dorm.exception.BusinessException;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R<String> handleValidException(MethodArgumentNotValidException e) {
        String msg = (String)e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        return R.error("参数校验失败: " + msg);
    }

    @ExceptionHandler({BusinessException.class})
    public R<String> handleBusinessException(BusinessException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public R<String> handleException(Exception e) {
        return R.error("系统繁忙，请稍后重试");
    }
}
