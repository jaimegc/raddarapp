package com.raddarapp.presentation.android.di.module;

import android.content.Context;

import com.karumi.rosie.domain.usecase.error.ErrorHandler;
import com.karumi.rosie.repository.datasource.CacheDataSource;
import com.karumi.rosie.repository.datasource.InMemoryCacheDataSource;
import com.karumi.rosie.repository.datasource.paginated.InMemoryPaginatedCacheDataSource;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.karumi.rosie.time.TimeProvider;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.CoinMining;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.domain.model.Territory;
import com.raddarapp.domain.model.TerritoryMain;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.presentation.general.application.RaddarApplicationWrapper;
import com.raddarapp.presentation.android.RaddarApplication;
import com.raddarapp.presentation.general.application.RaddarApplicationWrapperContract;
import com.raddarapp.presentation.android.error.remote.factory.RaddarErrorFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static java.util.concurrent.TimeUnit.MINUTES;

@Module(library = true,
        complete = false,
        injects = {
            RaddarApplication.class
        })
public class RaddarApplicationModule {

    private static final long MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL = MINUTES.toMillis(Integer.MAX_VALUE);
    private static final long FOOTPRINTS_MAIN_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long MY_FOOTPRINTS_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long MY_FOOTPRINTS_COLLECTION_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long FOOTPRINTS_RANKING_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long COMMENTS_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long TERRITORIES_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long USER_PROFILE_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long USER_FOOTPRINTS_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long MY_USERS_RANKING_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long TERRITORY_MAIN_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private static final long COIN_MINING_IN_MEMORY_CACHE_TTL = MINUTES_DEFAULT_IN_MEMORY_CACHE_TTL;
    private final Context context;

    public RaddarApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    public Context providesContext() {
        return context;
    }

    @Provides @Singleton
    public PaginatedCacheDataSource<String, FootprintMain> provideFootprintsMainPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), FOOTPRINTS_MAIN_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public PaginatedCacheDataSource<String, MyFootprint> provideMyFootprintsPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), MY_FOOTPRINTS_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public PaginatedCacheDataSource<String, MyFootprintCollection> provideMyFootprintsCollectionPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), MY_FOOTPRINTS_COLLECTION_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public PaginatedCacheDataSource<String, FootprintRanking> provideFootprintsRankingPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), FOOTPRINTS_RANKING_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public PaginatedCacheDataSource<String, Comment> provideCommentsPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), COMMENTS_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public PaginatedCacheDataSource<String, Territory> provideTerritoriesPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), TERRITORIES_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public CacheDataSource<String, MyUserProfile> provideUserProfileMemoryCache() {
        return new InMemoryCacheDataSource<>(new TimeProvider(), USER_PROFILE_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public PaginatedCacheDataSource<String, MyUserRanking> provideMyUsersRankingPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), MY_USERS_RANKING_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public PaginatedCacheDataSource<String, UserFootprint> provideUserFootprintsPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), USER_FOOTPRINTS_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public CacheDataSource<String, TerritoryMain> provideTerritoryMainMemoryCache() {
        return new InMemoryCacheDataSource<>(new TimeProvider(), TERRITORY_MAIN_IN_MEMORY_CACHE_TTL);
    }

    @Provides @Singleton
    public CacheDataSource<String, CoinMining> provideCoinMiningMemoryCache() {
        return new InMemoryCacheDataSource<>(new TimeProvider(), COIN_MINING_IN_MEMORY_CACHE_TTL);
    }

    @Provides public ErrorHandler providesErrorHandler(RaddarErrorFactory raddarErrorFactory) {
        return new ErrorHandler(raddarErrorFactory);
    }

    @Provides @Singleton
    public MyUserProfilePreferencesDataSourceContract providesUserProfilePreferencesDataSource() {
        return new MyUserProfilePreferencesDataSource(context);
    }

    @Provides @Singleton
    public RaddarApplicationWrapperContract providesRaddarApplicationWrapper() {
        // FIXME: This is code smell :[
        return new RaddarApplicationWrapper(RaddarApplication.getFootprintMainActivity());
    }
}
