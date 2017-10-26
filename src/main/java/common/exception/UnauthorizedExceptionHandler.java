package common.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/** 无权限跳转页面
 * Created by paul on 2017/10/24.
 */
public class UnauthorizedExceptionHandler {

    @ExceptionHandler(value ={UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String processUnauthorizedException(UnauthorizedException e, Model model){
        model.addAttribute("exception", e.getMessage());
        return "redirect:/login";
    }

}
