package com.aquafits.library.utils;

import com.aquafits.library.data.Mock;
import com.aquafits.library.data.model.BorrowStrategy;

import java.util.List;

public class StrategyFactory {
    private static StrategyFactory ourInstance = new StrategyFactory();

    public static StrategyFactory getInstance() {
        return ourInstance;
    }

    private List<BorrowStrategy> strategies = Mock.getInstance().strategies;

    private StrategyFactory() {
    }

    public BorrowStrategy getStrategy(String strategyName) {
        BorrowStrategy ret = null;
        for(BorrowStrategy s: strategies){
            if(s.getName().equals(strategyName)){
                return s;
            }
            if(s.getName().equals("Generic strategy")){
                ret = s;
            }
        }
        return ret;
    }
}
