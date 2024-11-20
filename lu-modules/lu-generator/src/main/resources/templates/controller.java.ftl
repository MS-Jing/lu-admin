package ${packageInfo.controller};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lj.common_web.annotation.ResponseResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * <p>
 * ${tableInfo.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("/${moduleName}/${tableInfo.name}")
@ResponseResultVo
@Tag(name = "${tableInfo.comment!} 管理")
public class ${tableInfo.controllerName} {


}

