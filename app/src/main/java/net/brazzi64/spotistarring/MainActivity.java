package net.brazzi64.spotistarring;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import net.brazzi64.spotistarring.databinding.ActivityMainBinding;
import net.brazzi64.spotistarring.di.ApplicationComponent;
import net.brazzi64.spotistarring.di.ApplicationModule;
import net.brazzi64.spotistarring.di.DaggerApplicationComponent;
import net.brazzi64.spotistarring.ui.RecentAlbumsFragment;
import net.brazzi64.spotistarring.ui.StarredAlbumsFragment;


public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  private static ApplicationComponent appComponent;

  @NonNull
  public ApplicationComponent getApplicationComponent() {
    if (appComponent == null) {
      appComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(getApplication()))
          .build();
    }
    return appComponent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    setupTabs(binding);
  }

  private void setupTabs(@NonNull ActivityMainBinding binding) {
    binding.viewPager.setAdapter(new BottomTabsAdapter());
    binding.tabLayout.setupWithViewPager(binding.viewPager);
  }


  private class BottomTabsAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;
    private int tabIcons[] = new int[]{R.drawable.ic_recent_albums, R.drawable.ic_starred_items};

    BottomTabsAdapter() {
      super(getSupportFragmentManager());
    }

    @Override
    public int getCount() {
      return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
      if (position == 0) {
        return new RecentAlbumsFragment();
      } else if (position == 1) {
        return new StarredAlbumsFragment();
      }
      return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      Drawable image = ContextCompat.getDrawable(MainActivity.this, tabIcons[position]);
      image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
      SpannableString sb = new SpannableString(" ");
      ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
      sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      return sb;
    }
  }
}
