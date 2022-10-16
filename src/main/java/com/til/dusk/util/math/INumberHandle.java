package com.til.dusk.util.math;

import com.til.dusk.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public interface INumberHandle<N> {
    /***
     * 相加
     */
    N add(N n1, N n2);

    /***
     * 相乘
     */
    N multiple(N n1, N n2);

    /***
     * 对数
     */
    N log(N n1, N n2);

    /***
     * 幂
     */
    N pow(N n1, N n2);


    class NumberHandles {
        public static final Map<Class<?>, INumberHandle<?>> MAP = new HashMap<>();
        public static final INumberHandle<Integer> INTEGER_NUMBER_HANDLE = new INumberHandle<>() {
            @Override
            public Integer add(Integer n1, Integer n2) {
                return n1 + n2;
            }

            @Override
            public Integer multiple(Integer n1, Integer n2) {
                return n1 + n2;
            }

            @Override
            public Integer log(Integer n1, Integer n2) {
                return (int) NumberHandles.log(n1.doubleValue(), n2.doubleValue());
            }

            @Override
            public Integer pow(Integer n1, Integer n2) {
                return (int) Math.pow(n1, n2);
            }
        };
        public static final INumberHandle<Double> DOUBLE_NUMBER_HANDLE = new INumberHandle<>() {
            @Override
            public Double add(Double n1, Double n2) {
                return n1 + n2;
            }

            @Override
            public Double multiple(Double n1, Double n2) {
                return n1 * n2;
            }

            @Override
            public Double log(Double n1, Double n2) {
                return NumberHandles.log(n1, n2);
            }

            @Override
            public Double pow(Double n1, Double n2) {
                return Math.pow(n1, n2);
            }
        };
        public static final INumberHandle<Long> LONG_NUMBER_HANDLE = new INumberHandle<>() {
            @Override
            public Long add(Long n1, Long n2) {
                return n1 + n2;
            }

            @Override
            public Long multiple(Long n1, Long n2) {
                return n1 * n2;
            }

            @Override
            public Long log(Long n1, Long n2) {
                return (long) NumberHandles.log(n1.doubleValue(), n2.doubleValue());
            }

            @Override
            public Long pow(Long n1, Long n2) {
                return (long) Math.pow(n1, n2);
            }
        };

        static {
            register(Integer.class, INTEGER_NUMBER_HANDLE);
            register(int.class, INTEGER_NUMBER_HANDLE);
            register(Double.class, DOUBLE_NUMBER_HANDLE);
            register(double.class, DOUBLE_NUMBER_HANDLE);
            register(Long.class, LONG_NUMBER_HANDLE);
            register(long.class, LONG_NUMBER_HANDLE);
        }

        /***
         *
         * 获取处理方法
         */
        public static <N> INumberHandle<N> getHandle(Class<N> hClass) {
            return Util.forcedConversion(MAP.get(hClass));
        }

        public static <N> INumberHandle<N> register(Class<N> nClass, INumberHandle<N> INumberHandle) {
            MAP.put(nClass, INumberHandle);
            return INumberHandle;
        }

        public static double log(double value, double base) {
            return Math.log(value) / Math.log(base);
        }
    }


}
