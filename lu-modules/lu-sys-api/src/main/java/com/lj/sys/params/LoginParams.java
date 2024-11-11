package com.lj.sys.params;

import com.lj.sys.entity.dto.LoginDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/10/29 11:33
 * 登录接口需要的参数
 */
@Data
@Schema(description = "登录接口参数")
public class LoginParams {

    @Schema(description = "验证码的uuid")
    private String uuid;

    @Schema(description = "验证码")
    private String captcha;

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空！")
    private String userName;

    @Schema(description = "密码")
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
