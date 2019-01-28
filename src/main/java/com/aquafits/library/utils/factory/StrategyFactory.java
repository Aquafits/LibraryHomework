package com.aquafits.library.utils.factory;

import com.aquafits.library.data.Mock;
import com.aquafits.library.data.model.strategies.BorrowStrategy;
import com.aquafits.library.data.model.strategies.GenericStrategy;

import java.util.ArrayList;
import java.util.List;

public class StrategyFactory {
    private static StrategyFactory ourInstance = new StrategyFactory();

    public static StrategyFactory getInstance() {
        return ourInstance;
    }

    private List<BorrowStrategy> strategies = Mock.getInstance().strategies;

    private StrategyFactory() {
    }

    public BorrowStrategy getStrategy(String strategy) {
        switch (strategy) {
            case "Student":
                return new GenericStrategy("" + (strategies.size() + 1), "Student strategy",
                        10, 30, new ArrayList<>());
            case "Teacher":
                return new GenericStrategy("" + (strategies.size() + 1), "Teacher strategy",
                        40, 60, new ArrayList<>());
            default:
                return new GenericStrategy("" + (strategies.size() + 1), "Default strategy",
                        0, 0, new ArrayList<>());
        }
    }
}
