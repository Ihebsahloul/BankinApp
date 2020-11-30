package com.bankin.data.utils;

import java.util.List;

public interface LoadTasksCallback<T> {

    void onTasksLoaded(List<T> tasks);

    void onDataNotAvailable();
}
