package com.lj.sys.params;

import com.lj.sys.dto.LoginDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author luojing
 * @since 2024/10/29 11:33
 * 登录接口需要的参数
 */
@Data
@ApiModel("登录接口参数")
public class LoginParams {

    @ApiModelProperty("验证码的uuid")
    private String uuid;

    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空！")
    private String userName;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空！")
    private String password;

    public LoginDto of() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUuid(uuid);
        loginDto.setCaptcha(captcha);
        loginDto.setUserName(userName);
        loginDto.setPassword(password);
        return loginDto;
    }
}
