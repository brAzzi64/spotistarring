package net.brazzi64.spotistarring.data;

import android.support.annotation.NonNull;


public interface RepositoryCallback<T> {

  void onFetchReady(@NonNull T data);

  void onFetchFailed(@NonNull Throwable throwable);
}
