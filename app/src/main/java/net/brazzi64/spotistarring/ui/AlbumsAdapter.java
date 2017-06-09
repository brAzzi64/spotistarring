package net.brazzi64.spotistarring.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.brazzi64.spotistarring.BR;
import net.brazzi64.spotistarring.data.model.Album;
import net.brazzi64.spotistarring.databinding.LayoutItemAlbumBinding;

import java.util.ArrayList;
import java.util.List;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumItemBindingHolder> {

  private final LayoutInflater inflater;
  private final AlbumStarClickedCallback albumStarClickedCallback;
  private List<Album> items = new ArrayList<>();


  public AlbumsAdapter(@NonNull Context context, @Nullable AlbumStarClickedCallback albumStarClickedCallback) {
    this.inflater = LayoutInflater.from(context);
    this.albumStarClickedCallback = albumStarClickedCallback;
  }

  public void showItems(@NonNull List<Album> items) {
    if (!this.items.isEmpty()) {
      notifyItemRangeRemoved(0, this.items.size());
    }
    this.items = items;
    notifyItemRangeInserted(0, items.size());
  }

  @Override
  public AlbumItemBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final AlbumItemBindingHolder bindingHolder = new AlbumItemBindingHolder(LayoutItemAlbumBinding.inflate(inflater, parent, false));
    bindingHolder.binding.starButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (albumStarClickedCallback != null) {
          albumStarClickedCallback.onClick(bindingHolder.binding.getAlbum());
        }
        bindingHolder.binding.notifyPropertyChanged(BR.album);
      }
    });
    bindingHolder.binding.starButton.setVisibility(albumStarClickedCallback != null ? View.VISIBLE : View.INVISIBLE);
    return bindingHolder;
  }

  @Override
  public void onBindViewHolder(AlbumItemBindingHolder holder, int position) {
    holder.binding.setAlbum(items.get(position));
  }

  @Override
  public int getItemCount() {
    return items.size();
  }


  public static class AlbumItemBindingHolder extends RecyclerView.ViewHolder {

    final LayoutItemAlbumBinding binding;

    AlbumItemBindingHolder(@NonNull LayoutItemAlbumBinding itemBinding) {
      super(itemBinding.getRoot());
      this.binding = itemBinding;
    }
  }

  public interface AlbumStarClickedCallback {
    void onClick(@NonNull Album album);
  }
}
