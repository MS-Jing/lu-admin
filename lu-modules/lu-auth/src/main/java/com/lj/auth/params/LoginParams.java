package com.lj.auth.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author luojing
 * @since 2024/10/29 13:58
 * 登录参数
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
}
