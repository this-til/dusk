package com.til.dusk.util.math;

import com.til.dusk.util.gson.AcceptTypeJson;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 它封装了一个函数f(x)
 *
 * @author til
 */
@AcceptTypeJson
public interface INumberPack<N> {

    N ofValue(N x);

    N ofValue(Number x);

    interface ILongPack extends INumberPack<Long> {
        @Override
        default Long ofValue(Number x) {
            return ofValue(x.longValue());
        }

        /***
         * 常量
         */
        class Constant implements ILongPack {
            public long y;

            public Constant() {
            }

            public Constant(long y) {
                this.y = y;
            }


            @Override
            public Long ofValue(Long x) {
                return y;
            }
        }

        /***
         * 一次函数
         * f(x)=ax+b
         */
        class LinearFunction implements ILongPack {
            public INumberPack<Long> a;
            public INumberPack<Long> b;

            public LinearFunction() {
            }

            public LinearFunction(INumberPack<Long> a, INumberPack<Long> b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public Long ofValue(Long x) {
                return a.ofValue(x) * x + b.ofValue(x);
            }
        }

        /***
         * 二次函数
         * f(x)=ax^2+bx+c
         */
        class QuadraticFunction implements ILongPack {
            public INumberPack<Long> a;
            public INumberPack<Long> b;
            public INumberPack<Long> c;

            public QuadraticFunction() {
            }

            public QuadraticFunction(INumberPack<Long> a, INumberPack<Long> b, INumberPack<Long> c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }

            @Override
            public Long ofValue(Long x) {
                return (long) (a.ofValue(x) * Math.pow(x, 2) * b.ofValue(x) * x + c.ofValue(x));
            }
        }

        /***
         * 反比例函数
         * f(x)=a/b
         */
        class InverseProportionalFunction implements ILongPack {
            public INumberPack<Long> a;

            public InverseProportionalFunction() {
            }

            public InverseProportionalFunction(INumberPack<Long> a) {
                this.a = a;
            }

            @Override
            public Long ofValue(Long x) {
                return a.ofValue(x) / x;
            }
        }


        /***
         * 指数函数
         * f(x)=a^x
         */
        class ExponentialFunction implements ILongPack {
            public INumberPack<Long> a;

            public ExponentialFunction() {
            }

            public ExponentialFunction(INumberPack<Long> a) {
                this.a = a;
            }

            @Override
            public Long ofValue(Long x) {
                return (long) (Math.pow(a.ofValue(x), x));
            }
        }


        /***
         * 对数函数
         * f(x)=log a x
         */
        class LogarithmicFunction implements ILongPack {
            public INumberPack<Long> a;

            public LogarithmicFunction() {
            }

            public LogarithmicFunction(INumberPack<Long> a) {
                this.a = a;
            }

            @Override
            public Long ofValue(Long x) {
                return log(a.ofValue(x), x);
            }

            public static Long log(Long value, Long base) {
                return (long) (Math.log(value) / Math.log(base));
            }
        }


        /***
         * 幂函数
         * f(x)=x^a
         */
        class PowerFunction implements ILongPack {
            public INumberPack<Long> a;

            public PowerFunction() {
            }

            public PowerFunction(INumberPack<Long> a) {
                this.a = a;
            }

            @Override
            public Long ofValue(Long x) {
                return (long) (Math.pow(x, a.ofValue(x)));
            }
        }


        /***
         * 正弦函数
         */
        class SinFunction implements ILongPack {

            /***
             * 倍数
             */
            public INumberPack<Long> a;

            /***
             * 周期偏移
             */
            public INumberPack<Long> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Long> offset;

            public SinFunction(INumberPack<Long> a, INumberPack<Long> cycleOffset, INumberPack<Long> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Long ofValue(Long x) {
                return (long) (a.ofValue(x) * Math.sin(x + cycleOffset.ofValue(x)) + offset.ofValue(x));
            }
        }

        /***
         * 余弦函数
         */
        class CosFunction implements ILongPack {

            /***
             * 倍数
             */
            public INumberPack<Long> a;

            /***
             * 周期偏移
             */
            public INumberPack<Long> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Long> offset;

            public CosFunction(INumberPack<Long> a, INumberPack<Long> cycleOffset, INumberPack<Long> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Long ofValue(Long x) {
                return (long) (a.ofValue(x) * Math.cos(x + cycleOffset.ofValue(x)) + offset.ofValue(x));
            }
        }

        /***
         * 正切函数
         */
        class TanFunction implements ILongPack {

            /***
             * 倍数
             */
            public INumberPack<Long> a;

            /***
             * 周期偏移
             */
            public INumberPack<Long> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Long> offset;

            public TanFunction(INumberPack<Long> a, INumberPack<Long> cycleOffset, INumberPack<Long> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Long ofValue(Long x) {
                return (long) (a.ofValue(x) * Math.tan(x + cycleOffset.ofValue(x)) + offset.ofValue(x));
            }
        }


        /***
         * 范围
         */
        class Range implements ILongPack {
            @Nullable
            public INumberPack<Long> max;
            @Nullable
            public INumberPack<Long> min;

            public Range(@Nullable INumberPack<Long> min, @Nullable INumberPack<Long> max) {
                this.max = max;
                this.min = min;
            }

            @Override
            public Long ofValue(Long x) {
                if (max != null) {
                    x = Math.min(x, max.ofValue(x));
                }
                if (min != null) {
                    x = Math.max(x, min.ofValue(x));
                }
                return x;
            }
        }

        class PackList implements ILongPack {
            public List<INumberPack<Long>> iNumberPacks;

            public PackList(List<INumberPack<Long>> iNumberPacks) {
                this.iNumberPacks = iNumberPacks;
            }

            @Override
            public Long ofValue(Long x) {
                for (INumberPack<Long> iNumberPack : iNumberPacks) {
                    x = iNumberPack.ofValue(x);
                }
                return x;
            }
        }

    }

    interface IIntPack extends INumberPack<Integer> {

        @Override
        default Integer ofValue(Number x) {
            return ofValue(x.intValue());
        }

        /***
         * 常量
         */
        class Constant implements IIntPack {
            public int y;

            public Constant() {
            }

            public Constant(int y) {
                this.y = y;
            }


            @Override
            public Integer ofValue(Integer x) {
                return y;
            }
        }

        /***
         * 一次函数
         * f(x)=ax+b
         */
        class LinearFunction implements IIntPack {
            public INumberPack<Integer> a;
            public INumberPack<Integer> b;

            public LinearFunction() {
            }

            public LinearFunction(INumberPack<Integer> a, INumberPack<Integer> b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public Integer ofValue(Integer x) {
                return a.ofValue(x) * x + b.ofValue(x);
            }
        }

        /***
         * 二次函数
         * f(x)=ax^2+bx+c
         */
        class QuadraticFunction implements IIntPack {
            public INumberPack<Integer> a;
            public INumberPack<Integer> b;
            public INumberPack<Integer> c;

            public QuadraticFunction() {
            }

            public QuadraticFunction(INumberPack<Integer> a, INumberPack<Integer> b, INumberPack<Integer> c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }

            @Override
            public Integer ofValue(Integer x) {
                return (int) (a.ofValue(x) * Math.pow(x, 2) * b.ofValue(x) * x + c.ofValue(x));
            }
        }

        /***
         * 反比例函数
         * f(x)=a/b
         */
        class InverseProportionalFunction implements IIntPack {
            public INumberPack<Integer> a;

            public InverseProportionalFunction() {
            }

            public InverseProportionalFunction(INumberPack<Integer> a) {
                this.a = a;
            }

            @Override
            public Integer ofValue(Integer x) {
                return a.ofValue(x) / x;
            }
        }


        /***
         * 指数函数
         * f(x)=a^x
         */
        class ExponentialFunction implements IIntPack {
            public INumberPack<Integer> a;

            public ExponentialFunction() {
            }

            public ExponentialFunction(INumberPack<Integer> a) {
                this.a = a;
            }

            @Override
            public Integer ofValue(Integer x) {
                return (int) (Math.pow(a.ofValue(x), x));
            }
        }


        /***
         * 对数函数
         * f(x)=log a x
         */
        class LogarithmicFunction implements IIntPack {
            public INumberPack<Integer> a;

            public LogarithmicFunction() {
            }

            public LogarithmicFunction(INumberPack<Integer> a) {
                this.a = a;
            }

            @Override
            public Integer ofValue(Integer x) {
                return log(a.ofValue(x), x);
            }

            public static Integer log(Integer value, Integer base) {
                return (int) (Math.log(value) / Math.log(base));
            }
        }


        /***
         * 幂函数
         * f(x)=x^a
         */
        class PowerFunction implements IIntPack {
            public INumberPack<Integer> a;

            public PowerFunction() {
            }

            public PowerFunction(INumberPack<Integer> a) {
                this.a = a;
            }

            @Override
            public Integer ofValue(Integer x) {
                return (int) (Math.pow(x, a.ofValue(x)));
            }
        }


        /***
         * 正弦函数
         */
        class SinFunction implements IIntPack {

            /***
             * 倍数
             */
            public INumberPack<Integer> a;

            /***
             * 周期偏移
             */
            public INumberPack<Integer> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Integer> offset;

            public SinFunction(INumberPack<Integer> a, INumberPack<Integer> cycleOffset, INumberPack<Integer> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Integer ofValue(Integer x) {
                return (int) (a.ofValue(x) * Math.sin(x + cycleOffset.ofValue(x)) + offset.ofValue(x));
            }
        }

        /***
         * 余弦函数
         */
        class CosFunction implements IIntPack {

            /***
             * 倍数
             */
            public INumberPack<Integer> a;

            /***
             * 周期偏移
             */
            public INumberPack<Integer> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Integer> offset;

            public CosFunction(INumberPack<Integer> a, INumberPack<Integer> cycleOffset, INumberPack<Integer> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Integer ofValue(Integer x) {
                return (int) (a.ofValue(x) * Math.cos(x + cycleOffset.ofValue(x)) + offset.ofValue(x));
            }
        }

        /***
         * 正切函数
         */
        class TanFunction implements IIntPack {

            /***
             * 倍数
             */
            public INumberPack<Integer> a;

            /***
             * 周期偏移
             */
            public INumberPack<Integer> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Integer> offset;

            public TanFunction(INumberPack<Integer> a, INumberPack<Integer> cycleOffset, INumberPack<Integer> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Integer ofValue(Integer x) {
                return (int) (a.ofValue(x) * Math.tan(x + cycleOffset.ofValue(x)) + offset.ofValue(x));
            }
        }


        /***
         * 范围
         */
        class Range implements IIntPack {
            @Nullable
            public INumberPack<Integer> max;
            @Nullable
            public INumberPack<Integer> min;

            public Range(@Nullable INumberPack<Integer> min, @Nullable INumberPack<Integer> max) {
                this.max = max;
                this.min = min;
            }

            @Override
            public Integer ofValue(Integer x) {
                if (max != null) {
                    x = Math.min(x, max.ofValue(x));
                }
                if (min != null) {
                    x = Math.max(x, min.ofValue(x));
                }
                return x;
            }
        }

        class PackList implements IIntPack {
            public List<INumberPack<Integer>> iNumberPacks;

            public PackList(List<INumberPack<Integer>> iNumberPacks) {
                this.iNumberPacks = iNumberPacks;
            }

            @Override
            public Integer ofValue(Integer x) {
                for (INumberPack<Integer> iNumberPack : iNumberPacks) {
                    x = iNumberPack.ofValue(x);
                }
                return x;
            }
        }

    }

    interface IDoublePack extends INumberPack<Double> {

        @Override
        default Double ofValue(Number x) {
            return ofValue(x.doubleValue());
        }

        /***
         * 常量
         */
        class Constant implements IDoublePack {
            public double y;

            public Constant() {
            }

            public Constant(double y) {
                this.y = y;
            }


            @Override
            public Double ofValue(Double x) {
                return y;
            }
        }

        /***
         * 一次函数
         * f(x)=ax+b
         */
        class LinearFunction implements IDoublePack {
            public INumberPack<Double> a;
            public INumberPack<Double> b;

            public LinearFunction() {
            }

            public LinearFunction(INumberPack<Double> a, INumberPack<Double> b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public Double ofValue(Double x) {
                return a.ofValue(x) * x + b.ofValue(x);
            }
        }

        /***
         * 二次函数
         * f(x)=ax^2+bx+c
         */
        class QuadraticFunction implements IDoublePack {
            public INumberPack<Double> a;
            public INumberPack<Double> b;
            public INumberPack<Double> c;

            public QuadraticFunction() {
            }

            public QuadraticFunction(INumberPack<Double> a, INumberPack<Double> b, INumberPack<Double> c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }

            @Override
            public Double ofValue(Double x) {
                return a.ofValue(x) * Math.pow(x, 2) * b.ofValue(x) * x + c.ofValue(x);
            }
        }

        /***
         * 反比例函数
         * f(x)=a/b
         */
        class InverseProportionalFunction implements IDoublePack {
            public INumberPack<Double> a;

            public InverseProportionalFunction() {
            }

            public InverseProportionalFunction(INumberPack<Double> a) {
                this.a = a;
            }

            @Override
            public Double ofValue(Double x) {
                return a.ofValue(x) / x;
            }
        }


        /***
         * 指数函数
         * f(x)=a^x
         */
        class ExponentialFunction implements IDoublePack {
            public INumberPack<Double> a;

            public ExponentialFunction() {
            }

            public ExponentialFunction(INumberPack<Double> a) {
                this.a = a;
            }

            @Override
            public Double ofValue(Double x) {
                return Math.pow(a.ofValue(x), x);
            }
        }


        /***
         * 对数函数
         * f(x)=log a x
         */
        class LogarithmicFunction implements IDoublePack {
            public INumberPack<Double> a;

            public LogarithmicFunction() {
            }

            public LogarithmicFunction(INumberPack<Double> a) {
                this.a = a;
            }

            @Override
            public Double ofValue(Double x) {
                return log(a.ofValue(x), x);
            }

            public static double log(double value, double base) {
                return Math.log(value) / Math.log(base);
            }
        }


        /***
         * 幂函数
         * f(x)=x^a
         */
        class PowerFunction implements IDoublePack {
            public INumberPack<Double> a;

            public PowerFunction() {
            }

            public PowerFunction(INumberPack<Double> a) {
                this.a = a;
            }

            @Override
            public Double ofValue(Double x) {
                return Math.pow(x, a.ofValue(x));
            }
        }


        /***
         * 正弦函数
         */
        class SinFunction implements IDoublePack {

            /***
             * 倍数
             */
            public INumberPack<Double> a;

            /***
             * 周期偏移
             */
            public INumberPack<Double> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Double> offset;

            public SinFunction(INumberPack<Double> a, INumberPack<Double> cycleOffset, INumberPack<Double> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Double ofValue(Double x) {
                return a.ofValue(x) * Math.sin(x + cycleOffset.ofValue(x)) + offset.ofValue(x);
            }
        }

        /***
         * 余弦函数
         */
        class CosFunction implements IDoublePack {

            /***
             * 倍数
             */
            public INumberPack<Double> a;

            /***
             * 周期偏移
             */
            public INumberPack<Double> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Double> offset;

            public CosFunction(INumberPack<Double> a, INumberPack<Double> cycleOffset, INumberPack<Double> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Double ofValue(Double x) {
                return a.ofValue(x) * Math.cos(x + cycleOffset.ofValue(x)) + offset.ofValue(x);
            }
        }

        /***
         * 正切函数
         */
        class TanFunction implements IDoublePack {

            /***
             * 倍数
             */
            public INumberPack<Double> a;

            /***
             * 周期偏移
             */
            public INumberPack<Double> cycleOffset;

            /***
             * 偏移
             */
            public INumberPack<Double> offset;

            public TanFunction(INumberPack<Double> a, INumberPack<Double> cycleOffset, INumberPack<Double> offset) {
                this.a = a;
                this.cycleOffset = cycleOffset;
                this.offset = offset;
            }

            @Override
            public Double ofValue(Double x) {
                return a.ofValue(x) * Math.tan(x + cycleOffset.ofValue(x)) + offset.ofValue(x);
            }
        }


        /***
         * 范围
         */
        class Range implements IDoublePack {
            @Nullable
            public INumberPack<Double> max;
            @Nullable
            public INumberPack<Double> min;

            public Range(@Nullable INumberPack<Double> min, @Nullable INumberPack<Double> max) {
                this.max = max;
                this.min = min;
            }

            @Override
            public Double ofValue(Double x) {
                if (max != null) {
                    x = Math.min(x, max.ofValue(x));
                }
                if (min != null) {
                    x = Math.max(x, min.ofValue(x));
                }
                return x;
            }
        }

        class PackList implements IDoublePack {
            public List<INumberPack<Double>> iNumberPacks;

            public PackList(List<INumberPack<Double>> iNumberPacks) {
                this.iNumberPacks = iNumberPacks;
            }

            @Override
            public Double ofValue(Double x) {
                for (INumberPack<Double> iNumberPack : iNumberPacks) {
                    x = iNumberPack.ofValue(x);
                }
                return x;
            }
        }

    }
}
