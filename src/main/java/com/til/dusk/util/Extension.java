package com.til.dusk.util;

import com.til.dusk.util.gson.AcceptTypeJson;

import java.util.Map;
import java.util.function.Supplier;

public class Extension {

    @AcceptTypeJson
    public interface Action {

        Action empty = () -> {
        };

        void action();
    }

    @AcceptTypeJson
    public interface Action_1V<A> {
        void action(A a);
    }

    @AcceptTypeJson
    public interface Action_2V<A, B> {
        void action(A a, B b);
    }

    @AcceptTypeJson
    public interface Action_3V<A, B, C> {
        void action(A a, B b, C c);
    }

    @AcceptTypeJson
    public interface Action_4V<A, B, C, D> {
        void action(A a, B b, C c, D d);
    }

    @AcceptTypeJson
    public interface Action_5V<A, B, C, D, E> {
        void action(A a, B b, C c, D d, E e);
    }

    @AcceptTypeJson
    public interface Action_6V<A, B, C, D, E, F> {
        void action(A a, B b, C c, D d, E e, F f);
    }

    @AcceptTypeJson
    public interface Func<O> {
        O func();
    }

    @AcceptTypeJson
    public interface Func_1I<I, O> {
        O func(I i);
    }

    @AcceptTypeJson
    public interface Func_2I<I1, I2, O> {
        O func(I1 i1, I2 i2);
    }

    @AcceptTypeJson
    public interface Func_3I<I1, I2, I3, O> {
        O func(I1 i1, I2 i2, I3 i3);
    }

    @AcceptTypeJson
    public interface Func_4I<I1, I2, I3, I4, O> {
        O func(I1 i1, I2 i2, I3 i3, I4 i4);
    }

    public record Data<D>(D d1) {
    }


    public record Data_2<D1, D2>(D1 d1, D2 d2) {
    }

    public record Data_3<D1, D2, D3>(D1 d1, D2 d2, D3 d3) {
    }

    public record Data_4<D1, D2, D3, D4>(D1 d1, D2 d2, D3 d3, D4 d4) {
    }

    public record Data_5<D1, D2, D3, D4, D5>(D1 d1, D2 d2, D3 d3, D4 d4, D5 d5) {
    }

    public record Data_6<D1, D2, D3, D4, D5, D6>(D1 d1, D2 d2, D3 d3, D4 d4, D5 d5, D6 d6) {
    }

    public static class VariableData<D> implements Supplier<D> {
        public D d1;

        public VariableData(D d1) {
            this.d1 = d1;
        }

        @Override
        public D get() {
            return d1;
        }
    }

    public static class VariableData_2<D1, D2> implements Map.Entry<D1, D2> {
        public D1 k;
        public D2 v;

        public VariableData_2(D1 k, D2 v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public D1 getKey() {
            return k;
        }

        @Override
        public D2 getValue() {
            return v;
        }

        @Override
        public D2 setValue(D2 value) {
            D2 d2 = this.v;
            this.v = value;
            return d2;
        }
    }

    public static class VariableData_3<D1, D2, D3> {
        public D1 d1;
        public D2 d2;
        public D3 d3;

        public VariableData_3(D1 d1, D2 d2) {
            this.d1 = d1;
            this.d2 = d2;
        }
    }

    public static int recursionDivision(int basic, int r) {
        for (int i = 0; i < r; i++) {
            basic /= 2;
        }
        return basic;
    }

}
