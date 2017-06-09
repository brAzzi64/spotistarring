package net.brazzi64.spotistarring.infra;


import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.brazzi64.spotistarring.data.model.Image;

import java.util.List;

public class DataBindingAdapters {

  private DataBindingAdapters() { }

  @BindingAdapter("albumImage")
  public static void setAlbumImage(@NonNull ImageView imageView, @NonNull List<Image> images) {
    Image firstImage = images.size() > 0 ? images.get(0) : null;
    if (firstImage == null) {
      imageView.setImageBitmap(null);
    } else {
      Picasso.with(imageView.getContext())
          .load(firstImage.url)
          .into(imageView);
    }
  }
}
