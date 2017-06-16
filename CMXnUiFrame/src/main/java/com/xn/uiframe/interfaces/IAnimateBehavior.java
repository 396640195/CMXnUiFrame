package com.xn.uiframe.interfaces;

import com.xn.uiframe.ViewElement;
import com.xn.uiframe.animation.Easing;

/**
 * 定义界面元素的动画能力接口
 * Created by 陈真 on 2017/6/16.
 */

public interface IAnimateBehavior {
    /**
     * 针对该视图进行Y轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param duration
     */
    void animateY(ViewElement elementCategory, long duration);

    /**
     * 针对该视图进行X轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param duration
     */
    void animateX(ViewElement elementCategory, long duration);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param xDuration        x轴动画时间
     * @param yDuration        y轴动画时间
     */
    void animateXY(ViewElement elementCategory, long xDuration, long yDuration);

    /**
     * 针对该视图进行Y轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param easing          动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param duration
     */
    void animateY(ViewElement elementCategory, Easing.EasingAnimation easing, long duration);

    /**
     * 针对该视图进行X轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param easing          动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param duration
     */
    void animateX(ViewElement elementCategory, Easing.EasingAnimation easing, long duration);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param easing          动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param xDuration        x轴动画时间
     * @param yDuration        y轴动画时间
     */
    void animateXY(ViewElement elementCategory, Easing.EasingAnimation easing, long xDuration, long yDuration);
}
