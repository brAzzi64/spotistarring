package net.brazzi64.spotistarring.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import net.brazzi64.spotistarring.data.model.Album;
import net.brazzi64.spotistarring.databinding.FragmentRecentAlbumsBinding;
import net.brazzi64.spotistarring.presenter.RecentAlbumsContract;
import net.brazzi64.spotistarring.presenter.RecentAlbumsPresenter;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class RecentAlbumsFragment extends Fragment implements RecentAlbumsContract.View {

  private FragmentRecentAlbumsBinding binding;
  private RecentAlbumsPresenter presenter;
  private RecentAlbumsAdapter adapter;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentRecentAlbumsBinding.inflate(inflater, container, false);
    adapter = new RecentAlbumsAdapter(getActivity());
    binding.recentAlbumsList.setAdapter(adapter);
    binding.recentAlbumsList.setLayoutManager(new LinearLayoutManager(getActivity()));
    binding.recentAlbumsList.setItemAnimator(createItemAnimator());
    return binding.getRoot();
  }

  @NonNull
  private RecyclerView.ItemAnimator createItemAnimator() {
    SlideInUpAnimator animator = new SlideInUpAnimator(new DecelerateInterpolator());
    animator.setAddDuration(1000);
    return animator;
  }

  @NonNull
  private RecentAlbumsPresenter getPresenter() {
    if (presenter == null) {
      presenter = new RecentAlbumsPresenter();
    }
    return presenter;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    getPresenter().attachView(this);
  }

  @Override
  public void onDetach() {
    getPresenter().clearView();
    super.onDetach();
  }

  @Override
  public void onStart() {
    super.onStart();
    getPresenter().start();
  }

  @Override
  public void showLoadingIndicator() {
    if (binding != null) {
      binding.setIsLoading(true);
    }
  }

  @Override
  public void hideLoadingIndicator() {
    if (binding != null) {
      binding.setIsLoading(false);
    }
  }

  @Override
  public void showRecentAlbums(@NonNull List<Album> recentAlbums) {
    if (adapter != null) {
      adapter.showItems(recentAlbums);
    }
  }
}
