package net.brazzi64.spotistarring.ui;

import android.databinding.Observable;
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

import net.brazzi64.spotistarring.BR;
import net.brazzi64.spotistarring.MainActivity;
import net.brazzi64.spotistarring.data.model.Album;
import net.brazzi64.spotistarring.databinding.FragmentRecentAlbumsBinding;
import net.brazzi64.spotistarring.viewmodels.RecentAlbumsViewModel;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class RecentAlbumsFragment extends Fragment implements AlbumsAdapter.AlbumStarClickedCallback {

  @Inject
  RecentAlbumsViewModel viewModel;

  private AlbumsAdapter adapter;

  private final AlbumsChangedCallback albumsCallback = new AlbumsChangedCallback();

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((MainActivity) getActivity()).getApplicationComponent().inject(this);
    viewModel.addOnPropertyChangedCallback(albumsCallback);
  }

  @Override
  public void onDestroy() {
    viewModel.destroy();
    viewModel.removeOnPropertyChangedCallback(albumsCallback);
    super.onDestroy();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    FragmentRecentAlbumsBinding binding = FragmentRecentAlbumsBinding.inflate(inflater, container, false);
    binding.setViewModel(viewModel);
    adapter = new AlbumsAdapter(getActivity(), this);
    binding.recentAlbumsList.setAdapter(adapter);
    binding.recentAlbumsList.setLayoutManager(new LinearLayoutManager(getActivity()));
    binding.recentAlbumsList.setItemAnimator(createItemAnimator());
    return binding.getRoot();
  }

  @NonNull
  private RecyclerView.ItemAnimator createItemAnimator() {
    SlideInUpAnimator animator = new SlideInUpAnimator(new DecelerateInterpolator());
    animator.setAddDuration(500);
    return animator;
  }

  @Override
  public void onClick(@NonNull Album album) {
    viewModel.onAlbumStarClicked(album);
  }

  private class AlbumsChangedCallback extends Observable.OnPropertyChangedCallback {

    @Override
    public void onPropertyChanged(@NonNull Observable sender, int propertyId) {
      if (propertyId == BR.albums) {
        adapter.showItems(viewModel.getAlbums());
      }
    }
  }
}
