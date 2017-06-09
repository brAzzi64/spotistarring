package net.brazzi64.spotistarring.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.brazzi64.spotistarring.data.model.Album;
import net.brazzi64.spotistarring.databinding.LayoutItemAlbumBinding;

import java.util.ArrayList;
import java.util.List;


public class RecentAlbumsAdapter extends RecyclerView.Adapter<RecentAlbumsAdapter.AlbumItemBindingHolder> {

  private final LayoutInflater inflater;
  private List<Album> items = new ArrayList<>();

  public RecentAlbumsAdapter(@NonNull Context context) {
    this.inflater = LayoutInflater.from(context);
  }

  public void showItems(@NonNull List<Album> items) {
    this.items = items;
    notifyItemRangeInserted(0, items.size());
  }

  @Override
  public AlbumItemBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new AlbumItemBindingHolder(LayoutItemAlbumBinding.inflate(inflater, parent, false));
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
}
