package com.example.testing.testingexample.mockito;

import android.util.SparseArray;

import com.example.testing.testingexample.Constants;

/**
 * @author xieyue5
 * @date 2018/2/1
 */

public class MockitoUnit
{
    private final SparseArray<String> mSparseStringArray;
    public MockitoUnit(SparseArray<String> stringSparseArray)
    {
        mSparseStringArray = stringSparseArray;
        init();
    }

    public final void init()
    {
        mSparseStringArray.put(1, Constants.ONE);
        onInit();
    }

    protected void onInit()
    {
        mSparseStringArray.put(2, Constants.THREE);
    }
}
