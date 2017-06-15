
package com.xn.uiframe.animation;

/**
 * 动画类型
 * Easing options.
 * @author Daniel Cohen Gindi
 */
public class Easing {

    public enum EasingAnimation { 
        Linear,
        EaseInQuad,
        EaseOutQuad,
        EaseInOutQuad,
        EaseInCubic,
        EaseOutCubic,//由快到慢
        EaseInOutCubic,
        EaseInQuart,
        EaseOutQuart,
        EaseInOutQuart,//可用
        EaseInSine,
        EaseOutSine,
        EaseInOutSine,//可用
        EaseInExpo,
        EaseOutExpo,
        EaseInOutExpo,
        EaseInCirc,  //可用
        EaseOutCirc, //下拉动画,可用于对话框
        EaseInOutCirc,//这个动画可以
        EaseInElastic,
        EaseOutElastic,
        EaseInOutElastic,//球一样弹跳动画
        EaseInBack,
        EaseOutBack, //越界回弹动画
        EaseInOutBack,//有回弹效果
        EaseInBounce,  //下拉弹框效果
        EaseOutBounce,//有回弹效果,不越界，布局内弹跳动画;
        EaseInOutBounce, //下拉弹框效果
    }

    public static UIFrameAnimatorInterpolator getEasingFunctionFromOption(EasingAnimation easing) {
        switch (easing) {
            default:
            case Linear:
                return Easing.EasingAnimatorInterpolator.Linear;
            case EaseInQuad:
                return Easing.EasingAnimatorInterpolator.EaseInQuad;
            case EaseOutQuad:
                return Easing.EasingAnimatorInterpolator.EaseOutQuad;
            case EaseInOutQuad:
                return Easing.EasingAnimatorInterpolator.EaseInOutQuad;
            case EaseInCubic:
                return Easing.EasingAnimatorInterpolator.EaseInCubic;
            case EaseOutCubic:
                return Easing.EasingAnimatorInterpolator.EaseOutCubic;
            case EaseInOutCubic:
                return Easing.EasingAnimatorInterpolator.EaseInOutCubic;
            case EaseInQuart:
                return Easing.EasingAnimatorInterpolator.EaseInQuart;
            case EaseOutQuart:
                return Easing.EasingAnimatorInterpolator.EaseOutQuart;
            case EaseInOutQuart:
                return Easing.EasingAnimatorInterpolator.EaseInOutQuart;
            case EaseInSine:
                return Easing.EasingAnimatorInterpolator.EaseInSine;
            case EaseOutSine:
                return Easing.EasingAnimatorInterpolator.EaseOutSine;
            case EaseInOutSine:
                return Easing.EasingAnimatorInterpolator.EaseInOutSine;
            case EaseInExpo:
                return Easing.EasingAnimatorInterpolator.EaseInExpo;
            case EaseOutExpo:
                return Easing.EasingAnimatorInterpolator.EaseOutExpo;
            case EaseInOutExpo:
                return Easing.EasingAnimatorInterpolator.EaseInOutExpo;
            case EaseInCirc:
                return Easing.EasingAnimatorInterpolator.EaseInCirc;
            case EaseOutCirc:
                return Easing.EasingAnimatorInterpolator.EaseOutCirc;
            case EaseInOutCirc:
                return Easing.EasingAnimatorInterpolator.EaseInOutCirc;
            case EaseInElastic:
                return Easing.EasingAnimatorInterpolator.EaseInElastic;
            case EaseOutElastic:
                return Easing.EasingAnimatorInterpolator.EaseOutElastic;
            case EaseInOutElastic:
                return Easing.EasingAnimatorInterpolator.EaseInOutElastic;
            case EaseInBack:
                return Easing.EasingAnimatorInterpolator.EaseInBack;
            case EaseOutBack:
                return Easing.EasingAnimatorInterpolator.EaseOutBack;
            case EaseInOutBack:
                return Easing.EasingAnimatorInterpolator.EaseInOutBack;
            case EaseInBounce:
                return Easing.EasingAnimatorInterpolator.EaseInBounce;
            case EaseOutBounce:
                return Easing.EasingAnimatorInterpolator.EaseOutBounce;
            case EaseInOutBounce:
                return Easing.EasingAnimatorInterpolator.EaseInOutBounce;
        }
    }
    
    private static class EasingAnimatorInterpolator {

        public static final UIFrameAnimatorInterpolator Linear = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInQuad = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input * input;
            }
        };

        public static final UIFrameAnimatorInterpolator EaseOutQuad = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return -input * (input - 2f);
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInOutQuad = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {

                float position = input / 0.5f;

                if (position < 1.f) {
                    return 0.5f * position * position;
                }

                return -0.5f * ((--position) * (position - 2.f) - 1.f);
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInCubic = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input * input * input;
            }
        };

        public static final UIFrameAnimatorInterpolator EaseOutCubic = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        input--;
                        return (input * input * input + 1.f);
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInOutCubic = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {

                        float position = input / 0.5f;
                        if (position < 1.f) {
                            return 0.5f * position * position * position;
                        }
                        position -= 2.f;
                        return 0.5f * (position * position * position + 2.f);
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInQuart = new UIFrameAnimatorInterpolator() {

            public float getInterpolation(float input) {
                return input * input * input * input;
            }
        };

        public static final UIFrameAnimatorInterpolator EaseOutQuart = new UIFrameAnimatorInterpolator() {

            public float getInterpolation(float input) {
                input--;
                return -(input * input * input * input - 1f);
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInOutQuart = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        float position = input / 0.5f;
                        if (position < 1.f) {
                            return 0.5f * position * position * position * position;
                        }
                        position -= 2.f;
                        return -0.5f * (position * position * position * position - 2.f);
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInSine = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return -(float) Math.cos(input * (Math.PI / 2.f)) + 1.f;
            }
        };

        public static final UIFrameAnimatorInterpolator EaseOutSine = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return (float) Math.sin(input * (Math.PI / 2.f));
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInOutSine = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return -0.5f * ((float) Math.cos(Math.PI * input) - 1.f);
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInExpo = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return (input == 0) ? 0.f : (float) Math.pow(2.f, 10.f * (input - 1.f));
            }
        };

        public static final UIFrameAnimatorInterpolator EaseOutExpo = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return (input == 1f) ? 1.f : (-(float) Math.pow(2.f, -10.f * (input + 1.f)));
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInOutExpo = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        if (input == 0)
                        {
                            return 0.f;
                        }
                        if (input == 1f)
                        {
                            return 1.f;
                        }

                        float position = input / 0.5f;
                        if (position < 1.f)
                        {
                            return 0.5f * (float) Math.pow(2.f, 10.f * (position - 1.f));
                        }
                        return 0.5f * (-(float) Math.pow(2.f, -10.f * --position) + 2.f);
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInCirc = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return -((float) Math.sqrt(1.f - input * input) - 1.f);
            }
        };

        public static final UIFrameAnimatorInterpolator EaseOutCirc = new UIFrameAnimatorInterpolator() {
            @Override
            public float getInterpolation(float input) {
                input--;
                return (float) Math.sqrt(1.f - input * input);
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInOutCirc = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        float position = input / 0.5f;
                        if (position < 1.f)
                        {
                            return -0.5f * ((float) Math.sqrt(1.f - position * position) - 1.f);
                        }
                        return 0.5f * ((float) Math.sqrt(1.f - (position -= 2.f) * position)
                        + 1.f);
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInElastic = new
                UIFrameAnimatorInterpolator() {

                    @Override
                    public float getInterpolation(float input) {
                        if (input == 0)
                        {
                            return 0.f;
                        }

                        float position = input;
                        if (position == 1)
                        {
                            return 1.f;
                        }

                        float p = .3f;
                        float s = p / (2.f * (float) Math.PI) * (float) Math.asin(1.f);
                        return -((float) Math.pow(2.f, 10.f * (position -= 1.f)) * (float)
                        Math
                                .sin((position - s) * (2.f * Math.PI) / p));
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseOutElastic = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        if (input == 0)
                        {
                            return 0.f;
                        }

                        float position = input;
                        if (position == 1)
                        {
                            return 1.f;
                        }

                        float p = .3f;
                        float s = p / (2 * (float) Math.PI) * (float) Math.asin(1.f);
                        return (float) Math.pow(2, -10 * position)
                                * (float) Math.sin((position - s) * (2.f * Math.PI) / p) +
                                1.f;
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInOutElastic = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        if (input == 0)
                        {
                            return 0.f;
                        }

                        float position = input / 0.5f;
                        if (position == 2)
                        {
                            return 1.f;
                        }

                        float p = (.3f * 1.5f);
                        float s = p / (2.f * (float) Math.PI) * (float) Math.asin(1.f);
                        if (position < 1.f)
                        {
                            return -.5f
                                    * ((float) Math.pow(2.f, 10.f * (position -= 1.f)) * (float) Math
                                            .sin((position * 1f - s) * (2.f * Math.PI) / p));
                        }
                        return (float) Math.pow(2.f, -10.f * (position -= 1.f))
                                * (float) Math.sin((position * 1f - s) * (2.f * Math.PI) / p) *
                                .5f
                                + 1.f;
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInBack = new UIFrameAnimatorInterpolator()
        {
            @Override
            public float getInterpolation(float input) {
                final float s = 1.70158f;
                float position = input;
                return position * position * ((s + 1.f) * position - s);
            }
        };

        public static final UIFrameAnimatorInterpolator EaseOutBack = new UIFrameAnimatorInterpolator()
        {
            @Override
            public float getInterpolation(float input) {
                final float s = 1.70158f;
                float position = input;
                position--;
                return (position * position * ((s + 1.f) * position + s) + 1.f);
            }
        };

        public static final UIFrameAnimatorInterpolator EaseInOutBack = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        float s = 1.70158f;
                        float position = input / 0.5f;
                        if (position < 1.f)
                        {
                            return 0.5f * (position * position * (((s *= (1.525f)) + 1.f) *
                                    position - s));
                        }
                        return 0.5f * ((position -= 2.f) * position
                                * (((s *= (1.525f)) + 1.f) * position + s) + 2.f);
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInBounce = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        return 1.f - EaseOutBounce.getInterpolation(1f - input);
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseOutBounce = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        float position = input;
                        if (position < (1.f / 2.75f))
                        {
                            return (7.5625f * position * position);
                        }
                        else if (position < (2.f / 2.75f))
                        {
                            return (7.5625f * (position -= (1.5f / 2.75f)) * position + .75f);
                        }
                        else if (position < (2.5f / 2.75f))
                        {
                            return (7.5625f * (position -= (2.25f / 2.75f)) * position + .9375f);
                        }
                        else
                        {
                            return (7.5625f * (position -= (2.625f / 2.75f)) * position +
                            .984375f);
                        }
                    }
                };

        public static final UIFrameAnimatorInterpolator EaseInOutBounce = new
                UIFrameAnimatorInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        if (input < 0.5f)
                        {
                            return EaseInBounce.getInterpolation(input * 2) * .5f;
                        }
                        return EaseOutBounce.getInterpolation(input * 2 - 1f) * .5f +
                        .5f;
                    }
                };

    }
}
