package com.lj.utils.query.condition;

import com.lj.utils.query.annotation.*;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luojing
 * @date 2023/8/11
 * 处理条件注解的管理器
 */
public class ConditionManager {
    /**
     * 条件注解和对应处理器的映射
     */
    private static final Map<Class<? extends Annotation>, AbstractConditionHandler<? extends Annotation>> CONDITION_ANNOTATION_HANDLER_MAPPING = new HashMap<>();

    @Getter
    private static final BetweenConditionHandler betweenConditionHandler = new BetweenConditionHandler();

    static {
        CONDITION_ANNOTATION_HANDLER_MAPPING.put(Eq.class, new EqConditionHandler());
        CONDITION_ANNOTATION_HANDLER_MAPPING.put(Like.class, new LikeConditionHandler());
        CONDITION_ANNOTATION_HANDLER_MAPPING.put(AllEq.class, new AllEqConditionHandler());
        CONDITION_ANNOTATION_HANDLER_MAPPING.put(Gt.class, new GtConditionHandler());
        CONDITION_ANNOTATION_HANDLER_MAPPING.put(Lt.class, new LtConditionHandler());
    }

    public static AbstractConditionHandler<? extends Annotation> getHandler(Class<? extends Annotation> annotationClass) {
        return CONDITION_ANNOTATION_HANDLER_MAPPING.get(annotationClass);
    }
}
