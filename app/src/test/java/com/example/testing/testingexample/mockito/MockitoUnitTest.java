package com.example.testing.testingexample.mockito;

import android.util.SparseArray;

import com.example.testing.testingexample.Constants;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * A sample if mock collection put, remove, clear interactions.
 * @author xieyue5
 * @date 2018/2/1
 */
public class MockitoUnitTest
{
    @Mock private SparseArray<String> stringSparseArray;
    private int size = 0;
    @Before
    public void setUp() throws Exception
    {
        // Init annotate mock class
        MockitoAnnotations.initMocks(this);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable
            {
                when(stringSparseArray.size()).thenReturn(++size);
                return 0;
            }
        }).when(stringSparseArray).put(anyInt(), anyString());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable
            {
                when(stringSparseArray.size()).thenReturn(--size);
                return 0;
            }
        }).when(stringSparseArray).remove(anyInt());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable
            {
                when(stringSparseArray.size()).thenReturn(0);
                return null;
            }
        }).when(stringSparseArray).clear();
        new MockitoUnit(stringSparseArray);
    }

    @Test
    public void init() throws Exception
    {
        verify(stringSparseArray, times(1)).put(1, Constants.ONE);
        verify(stringSparseArray, times(2)).put(anyInt(), anyString());
        assertEquals(stringSparseArray.size(), 2);
        stringSparseArray.remove(1);
        assertEquals(stringSparseArray.size(), 1);
        stringSparseArray.clear();
        assertEquals(stringSparseArray.size(), 0);
    }

}