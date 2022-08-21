package com.til.dusk.util;

public class Extension {

    public interface Action {

        Action empty = () -> {
        };

        void action();
    }

    public interface Action_1V<A> {
        void action(A a);
    }

    public interface Action_2V<A, B> {
        void action(A a, B b);
    }

    public interface Action_3V<A, B, C> {
        void action(A a, B b, C c);
    }

    public interface Action_4V<A, B, C, D> {
        void action(A a, B b, C c, D d);
    }

    public interface Action_5V<A, B, C, D, E> {
        void action(A a, B b, C c, D d, E e);
    }

    public interface Action_6V<A, B, C, D, E, F> {
        void action(A a, B b, C c, D d, E e, F f);
    }

    public interface Func<O> {
        O func();
    }

    public interface Func_1I<I, O> {
        O func(I i);
    }

    public interface Func_2I<I1, I2, O> {
        O func(I1 i1, I2 i2);
    }

    public static class Data<D> {
        public D a;

        public Data(D a) {
            this.a = a;
        }
    }

    public static class Data_2<D1, D2> {
        public D1 a;
        public D2 b;

        public Data_2(D1 a, D2 b) {
            this.a = a;
            this.b = b;
        }
    }

    public static int recursionDivision(int basic, int r) {
        for (int i = 0; i < r; i++) {
            basic /= 2;
        }
        return basic;
    }

}
