package com.til.dusk.util.math;

import com.til.dusk.util.gson.AcceptTypeJson;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 它封装了一个函数f(x)
 *
 * @author til
 */
@AcceptTypeJson
public interface INumberPack {

    double ofValue(double x);

    /***
     * 常量
     */
    class Constant implements INumberPack {
        public double y;

        public Constant() {
        }

        public Constant(double y) {
            this.y = y;
        }


        @Override
        public double ofValue(double x) {
            return y;
        }
    }

    /***
     * 一次函数
     * f(x)=ax+b
     */
    class LinearFunction implements INumberPack {
        public INumberPack a;
        public INumberPack b;

        public LinearFunction() {
        }

        public LinearFunction(INumberPack a, INumberPack b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public double ofValue(double x) {
            return a.ofValue(x) * x + b.ofValue(x);
        }
    }

    /***
     * 二次函数
     * f(x)=ax^2+bx+c
     */
    class QuadraticFunction implements INumberPack {
        public INumberPack a;
        public INumberPack b;
        public INumberPack c;

        public QuadraticFunction() {
        }

        public QuadraticFunction(INumberPack a, INumberPack b, INumberPack c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public double ofValue(double x) {
            return a.ofValue(x) * Math.pow(x, 2) * b.ofValue(x) * x + c.ofValue(x);
        }
    }

    /***
     * 幂函数
     * f(x)=x^a
     */
    class PowerFunction implements INumberPack {
        public INumberPack a;

        public PowerFunction() {
        }

        public PowerFunction(INumberPack a) {
            this.a = a;
        }

        @Override
        public double ofValue(double x) {
            return Math.pow(x, a.ofValue(x));
        }
    }

    /***
     * 指数函数
     * f(x)=a^x
     */
    class ExponentialFunction implements INumberPack {
        public INumberPack a;

        public ExponentialFunction() {
        }

        public ExponentialFunction(INumberPack a) {
            this.a = a;
        }

        @Override
        public double ofValue(double x) {
            return Math.pow(a.ofValue(x), x);
        }
    }

    /***
     * 对数函数
     * f(x)=log a x
     */
    class LogarithmicFunction implements INumberPack {
        public INumberPack a;

        public LogarithmicFunction() {
        }

        public LogarithmicFunction(INumberPack a) {
            this.a = a;
        }

        @Override
        public double ofValue(double x) {
            return log(a.ofValue(x), x);
        }

        public static double log(double value, double base) {
            return Math.log(value) / Math.log(base);
        }
    }

    /***
     * 反比例函数
     * f(x)=a/b
     */
    class InverseProportionalFunction implements INumberPack {
        public INumberPack a;

        public InverseProportionalFunction() {
        }

        public InverseProportionalFunction(INumberPack a) {
            this.a = a;
        }

        @Override
        public double ofValue(double x) {
            return a.ofValue(x) / x;
        }
    }

    /***
     * 正弦函数
     */
    class SinFunction implements INumberPack {

        /***
         * 倍数
         */
        public INumberPack a;

        /***
         * 周期偏移
         */
        public INumberPack cycleOffset;

        /***
         * 偏移
         */
        public INumberPack offset;

        public SinFunction(INumberPack a, INumberPack cycleOffset, INumberPack offset) {
            this.a = a;
            this.cycleOffset = cycleOffset;
            this.offset = offset;
        }

        @Override
        public double ofValue(double x) {
            return a.ofValue(x) * Math.sin(x + cycleOffset.ofValue(x)) + offset.ofValue(x);
        }
    }

    /***
     * 余弦函数
     */
    class CosFunction implements INumberPack {

        /***
         * 倍数
         */
        public INumberPack a;

        /***
         * 周期偏移
         */
        public INumberPack cycleOffset;

        /***
         * 偏移
         */
        public INumberPack offset;

        public CosFunction(INumberPack a, INumberPack cycleOffset, INumberPack offset) {
            this.a = a;
            this.cycleOffset = cycleOffset;
            this.offset = offset;
        }

        @Override
        public double ofValue(double x) {
            return a.ofValue(x) * Math.cos(x + cycleOffset.ofValue(x)) + offset.ofValue(x);
        }
    }

    /***
     * 正切函数
     */
    class TanFunction implements INumberPack {

        /***
         * 倍数
         */
        public INumberPack a;

        /***
         * 周期偏移
         */
        public INumberPack cycleOffset;

        /***
         * 偏移
         */
        public INumberPack offset;

        public TanFunction(INumberPack a, INumberPack cycleOffset, INumberPack offset) {
            this.a = a;
            this.cycleOffset = cycleOffset;
            this.offset = offset;
        }

        @Override
        public double ofValue(double x) {
            return a.ofValue(x) * Math.tan(x + cycleOffset.ofValue(x)) + offset.ofValue(x);
        }
    }

    /***
     * 范围
     */
    class Range implements INumberPack {
        @Nullable
        public INumberPack max;
        @Nullable
        public INumberPack min;

        public Range(@Nullable INumberPack min, @Nullable INumberPack max) {
            this.max = max;
            this.min = min;
        }

        @Override
        public double ofValue(double x) {
            if (max != null) {
                x = Math.min(x, max.ofValue(x));
            }
            if (min != null) {
                x = Math.max(x, min.ofValue(x));
            }
            return x;
        }
    }

    class PackList implements INumberPack {
        public List<INumberPack> iNumberPacks;

        public PackList(List<INumberPack> iNumberPacks) {
            this.iNumberPacks = iNumberPacks;
        }

        @Override
        public double ofValue(double x) {
            for (INumberPack iNumberPack : iNumberPacks) {
                x = iNumberPack.ofValue(x);
            }
            return x;
        }
    }
}
