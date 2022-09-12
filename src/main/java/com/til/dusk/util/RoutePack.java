package com.til.dusk.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/***
 * 这是一个路径包
 * @author til
 * @param <T> 数据
 */
public class RoutePack<T> {

    public List<RouteCell<T>> routeCellList = new ArrayList<>();
    protected RoutePack<T> up;
    protected RoutePack<T> next;

    public RoutePack<T> getUp() {
        if (up == null) {
            up = new RoutePack<>();
            up.next = this;
        }
        return up;
    }

    public RoutePack<T> getNext() {
        if (next == null) {
            next = new RoutePack<>();
            next.up = this;
        }
        return next;
    }

    public void add(RouteCell<T> routeCell) {
        routeCellList.add(routeCell);
    }

    public List<Pos> getAllPos() {
        List<Pos> list = new ArrayList<>();
        for (RoutePack<T> tRoutePack : getAll()) {
            for (RouteCell<T> tRouteCell : tRoutePack.routeCellList) {
                list.add(tRouteCell.start);
                list.add(tRouteCell.end);
            }
        }
        return list;
    }

    public RoutePack<T> getFirst() {
        if (up == null) {
            return this;
        }
        return up.getFirst();
    }

    public RoutePack<T> getTail() {
        if (next == null) {
            return this;
        }
        return next.getTail();
    }

    public List<RoutePack<T>> getAll() {
        List<RoutePack<T>> routePackList = new ArrayList<>();
        RoutePack<T> cache = getFirst();
        while (cache != null) {
            routePackList.add(cache);
            cache = cache.next;
        }
        return routePackList;
    }

    public boolean isEmpty() {
        return routeCellList.isEmpty() && up == null  && next == null ;
    }

    public record RouteCell<T>(Pos start, Pos end, T data) {
    }

    public interface ISupportRoutePack<T> {

        /***
         * 设置路径
         * @param routePack 自身对应传递的路径
         */
        void set(RoutePack<T> routePack);

    }
}
