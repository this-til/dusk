package com.til.dusk.util;

public class Extension {

    public interface Action {

        Action empty = () -> {};

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
        D d;

        public Data(D d) {
            this.d = d;
        }
    }

    public static class Data_2<D1, D2> {
        D1 d1;
        D2 d2;

        public Data_2(D1 d1, D2 d2) {
            this.d1 = d1;
            this.d2 = d2;
        }
    }

    public static int recursionDivision(int basic, int r){
        for (int i = 0; i < r; i++) {
            basic /= 2;
        }
        return basic;
    }

}
