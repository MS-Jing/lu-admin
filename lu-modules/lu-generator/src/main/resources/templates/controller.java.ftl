package ${controller.packagePath};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lj.common_web.annotation.ResponseResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * <p>
 * ${tableComment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("/${moduleName}/${controller.url}")
@ResponseResultVo
@Tag(name = "${tableComment!} 管理")
public class ${controller.className} {


}

