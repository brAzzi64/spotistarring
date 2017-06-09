package net.brazzi64.spotistarring.di;

import net.brazzi64.spotistarring.data.AlbumRepository;
import net.brazzi64.spotistarring.data.StarredAlbumsStore;
import net.brazzi64.spotistarring.ui.RecentAlbumsFragment;
import net.brazzi64.spotistarring.ui.StarredAlbumsFragment;
import net.brazzi64.spotistarring.viewmodels.RecentAlbumsViewModel;
import net.brazzi64.spotistarring.viewmodels.StarredAlbumsViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

  RecentAlbumsViewModel recentAlbumsViewModel();

  StarredAlbumsViewModel starredAlbumsViewModel();

  AlbumRepository albumRepository();

  StarredAlbumsStore starredAlbumsStore();


  void inject(RecentAlbumsFragment fragment);

  void inject(StarredAlbumsFragment fragment);
}