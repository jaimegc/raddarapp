package com.raddarapp.presentation.general.presenter;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedFootprintsMainTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.usecase.AddVote;
import com.raddarapp.domain.usecase.GetFootprintsMain;
import com.raddarapp.domain.usecase.GetMyUserProfile;
import com.raddarapp.presentation.android.listener.OnMoveMapAndAddMarkerForUpdateFootprintMainListener;
import com.raddarapp.presentation.general.application.RaddarApplicationWrapperContract;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.FootprintInsigniaMainViewModel;
import com.raddarapp.presentation.general.viewmodel.FootprintMainViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;
import com.raddarapp.presentation.general.viewmodel.mapper.FootprintMainToFootprintMainViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.FootprintsMainToFootprintsMainViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class FootprintsMainPresenter extends BasePresenterRefreshWithLoading<FootprintsMainPresenter.View>  {

    private static final int NUMBER_OF_FOOTPRINTS_MAIN_PER_PAGE = 20;
    private final FootprintsMainToFootprintsMainViewModelMapper mapperFootprintsMain;
    private final FootprintMainToFootprintMainViewModelMapper mapperFootprintMain;
    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private final GetFootprintsMain getFootprintsMain;
    private final AddVote addVote;
    private final RaddarApplicationWrapperContract raddarApplicationWrapper;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferences;
    private List<String> keyPositions = new LinkedList<>();
    private Integer offset = 0, pageNumber = 0;
    private Location lastLocation;
    private long totalFootprints = 0;
    private boolean hasMore = false;
    private final GetMyUserProfile getUserProfile;

    @Inject
    public FootprintsMainPresenter(UseCaseHandler useCaseHandler, FootprintsMainToFootprintsMainViewModelMapper mapperFootprintsMain,
            GetFootprintsMain getFootprintsMain, AddVote addVote, FootprintMainToFootprintMainViewModelMapper mapperFootprintMain,
            RaddarApplicationWrapperContract raddarApplicationWrapper, MyUserProfileToUserProfileViewModelMapper mapperUserProfile,
            MyUserProfilePreferencesDataSourceContract userProfilePreferences, GetMyUserProfile getUserProfile) {
        super(useCaseHandler);
        this.mapperFootprintsMain = mapperFootprintsMain;
        this.getFootprintsMain = getFootprintsMain;
        this.addVote = addVote;
        this.mapperFootprintMain = mapperFootprintMain;
        this.raddarApplicationWrapper = raddarApplicationWrapper;
        this.mapperUserProfile = mapperUserProfile;
        this.userProfilePreferences = userProfilePreferences;
        this.getUserProfile = getUserProfile;
    }

    @Override
    public void initialize() {
        super.initialize();
        // Only first time because sometimes fails from the Activity
        loadUserProfile();
    }

    private void loadUserProfile() {

        createUseCaseCall(getUserProfile)
                .useCaseName(getUserProfile.USE_CASE_GET_USER_PROFILE_PREFERENCES)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getUserProfilePreferences(MyUserProfile userProfilePreferences) {
                        try {
                            getView().showUserProfilePreferences(mapperUserProfile.map(userProfilePreferences));
                        } catch (Exception e) {}
                    }
                })
                .onError(error -> {
                    showError();

                    return false;
                }).execute();
    }

    public void loadData(Location lastLocation) {
        try {
            this.lastLocation = lastLocation;
            getView().hideFootprints();
            showLoading();

            PaginatedCollection<FootprintMain> allFootprintsMainInCache = getFootprintsMain.getAllFootprintsMainInCacheOrderByDate();

            if (allFootprintsMainInCache.getPage().getLimit() == 0) {
                loadFootprintsMain(lastLocation);
            } else {
                getView().clearFootprints();
                showFootprintsMain(allFootprintsMainInCache);
                moveMapAndAddMarker(allFootprintsMainInCache);
                offset = allFootprintsMainInCache.getItems().size();
            }
        } catch (Exception e) {}
    }

    @Override
    public void forceRefreshing() {
        if (!isForceRefreshing) {
            getFootprintsMain.deleteCache();
            offset = 0;
            totalFootprints = 0;
            pageNumber = 0;
            loadFootprintsMain(lastLocation);
        }
    }

    private void loadFootprintsMain(Location location) {

        if (location != null) {
            RaddarLocation raddarLocation = new RaddarLocationBuilder()
                    .withLatitude(location.getLatitude())
                    .withLongitude(location.getLongitude())
                    .build();

            try {
                showLoading();
            } catch (Exception e) {}

            createUseCaseCall(getFootprintsMain)
                    .args(raddarLocation, pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_FOOTPRINTS_MAIN_PER_PAGE, NUMBER_OF_FOOTPRINTS_MAIN_PER_PAGE))
                    .useCaseName(GetFootprintsMain.USE_CASE_GET_FOOTPRINTS_MAIN)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void getFootprintsMain(PaginatedFootprintsMainTotalCollection<FootprintMain> footprintsMain) {
                            showFootprintsMain(footprintsMain);
                        }
                    })
                    .onError(error -> {
                        showError();
                        return false;
                    }).execute();
        }
    }

    private void showFootprintsMain(PaginatedFootprintsMainTotalCollection<FootprintMain> footprintsMain) {
        try {

            MyUserProfile myUserProfileUpdated = footprintsMain.getMyUserProfile();
            myUserProfileUpdated.setRangeMinedLocalAccumulated(userProfilePreferences.getRangeMinedLocalAccumulated());

            userProfilePreferences.setUserProfilePreferences(myUserProfileUpdated);
            MyUserProfileViewModel myUserProfileViewModel = mapperUserProfile.map(footprintsMain.getMyUserProfile());
            getView().showUserProfilePreferences(myUserProfileViewModel);

            if (!footprintsMain.getPaginatedCollection().getItems().isEmpty()) {
                getFootprintsMain.setHasMore(footprintsMain.getPaginatedCollection().hasMore());
                showFootprintsMain(footprintsMain.getPaginatedCollection());
                moveMapAndAddMarker(footprintsMain.getPaginatedCollection());
                offset = footprintsMain.getPaginatedCollection().getPage().getOffset() + NUMBER_OF_FOOTPRINTS_MAIN_PER_PAGE;
                hasMore = footprintsMain.getPaginatedCollection().hasMore();

                if (totalFootprints == 0) {
                    totalFootprints = footprintsMain.getTotalItems();
                    getView().showTotalFootprints(String.valueOf(totalFootprints));
                }

                pageNumber++;
            } else {
                getView().removeAllMarkers();
                getView().showEmptyFootprints();
            }
        } catch (Exception e) {}
    }

    private void showFootprintsMain(PaginatedCollection<FootprintMain> footprintsMain) {
        try {
            getView().clearFootprints();
            getView().removeAllMarkers();
            List<FootprintMainViewModelContract> footprintsMainViewModels = mapperFootprintsMain.map(footprintsMain);
            getView().showFootprintsMain(footprintsMainViewModels);
            getView().showHasMore(footprintsMain.hasMore());

            hideLoading();
        } catch (Exception e) {}
    }

    private void showError() {
        hideLoading();
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    private void moveMapAndAddMarker(PaginatedCollection<FootprintMain> footprintsMain) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        List<LatLng> listLatLngs = new ArrayList<>();
        List<Integer> typeFootprints = new ArrayList<>();
        List<Integer> usersRelationship = new ArrayList<>();
        List<Boolean> visibleFootprints = new ArrayList<>();
        keyPositions = new LinkedList<>();
        double latitude, longitude;
        LatLng latLng;
        int position = 0;

        for (FootprintMain footprintMain : footprintsMain.getItems()) {
            latitude = footprintMain.getRaddarLocation().getLatitude();
            longitude = footprintMain.getRaddarLocation().getLongitude();
            latLng = new LatLng(latitude, longitude);
            builder.include(latLng);
            listLatLngs.add(latLng);
            typeFootprints.add(footprintMain.getType());
            usersRelationship.add(footprintMain.getUser().getUserRelationship());
            visibleFootprints.add(footprintMain.isVisible());
            keyPositions.add(footprintMain.getKey());
            position++;
        }

        try {
            getView().moveMapAndAddMaker(builder.build(), listLatLngs, typeFootprints, usersRelationship, visibleFootprints);
        } catch (Exception e) {}
    }

    // This method is because when we update footprint main getView() throws an Exception
    public void moveMapAndAddMarkerForUpdateFootprintMain(PaginatedCollection<FootprintMain> footprintsMain,
            OnMoveMapAndAddMarkerForUpdateFootprintMainListener listener) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        List<LatLng> listLatLngs = new ArrayList<>();
        List<Integer> typeFootprints = new ArrayList<>();
        List<Integer> usersRelationship = new ArrayList<>();
        List<Boolean> visibleFootprints = new ArrayList<>();
        keyPositions = new LinkedList<>();
        double latitude, longitude;
        LatLng latLng;
        int position = 0;
        boolean visibleFootprint;

        for (FootprintMain footprintMain : footprintsMain.getItems()) {

            visibleFootprint = footprintMain.isVisible();

            if (visibleFootprint) {
                latitude = footprintMain.getRaddarLocation().getLatitude();
                longitude = footprintMain.getRaddarLocation().getLongitude();
            } else {
                latitude = lastLocation.getLatitude();
                longitude = lastLocation.getLongitude();
            }

            latLng = new LatLng(latitude, longitude);
            builder.include(latLng);
            listLatLngs.add(latLng);
            typeFootprints.add(footprintMain.getMediaType());
            usersRelationship.add(footprintMain.getUser().getUserRelationship());
            visibleFootprints.add(visibleFootprint);
            keyPositions.add(footprintMain.getKey());
            position++;
        }

        listener.onMoveMapAndAddMarkerSuccess(builder.build(), listLatLngs, typeFootprints, usersRelationship, visibleFootprints);
    }

    public void onLoadMore(boolean loadMoreByScrolled) {
        try {
            getView().loadMoreByScrolled(loadMoreByScrolled);
            loadFootprintsMain(lastLocation);
        } catch (Exception e) {}
    }

    public void onFootprintMainClicked(FootprintMainViewModel footprintMain, int position) {
        String footprintMainKey = footprintMain.getKey();
        long comments = footprintMain.getComments();
        getView().openFootprintMainDetails(footprintMainKey, comments, position);
    }

    public void onFootprintInsigniaMainClicked(FootprintInsigniaMainViewModel footprintInsigniaMain, int position) {
        String footprintMainKey = footprintInsigniaMain.getKey();
        long comments = footprintInsigniaMain.getComments();
        getView().openFootprintMainDetails(footprintMainKey, comments, position);
    }

    public void onUserClicked(FootprintMainViewModel footprintMain, int position) {
        String footprintMainKey = footprintMain.getKey();
        String userKey = footprintMain.getUserKey();
        getView().openUserProfile(footprintMainKey, userKey, position);
    }

    public void addVote(String userFootprintKey, String footprintKey, int addVoteType) {
        createUseCaseCall(addVote)
                .args(footprintKey, addVoteType)
                .useCaseName(AddVote.USE_CASE_ADD_VOTE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void addVote(double actualScope) {
                        try {
                            if (userProfilePreferences.getUserKey().equals(userFootprintKey)) {
                                getView().updateAfterVoteMyOwnFootprint();
                            }

                            if ((keyPositions.isEmpty() && !hasMore)) {
                                getView().lastVoteRadarScanClicked();
                            }
                        } catch (Exception e) {}
                    }
                })
                .onError(error -> false).execute();

    }

    public void setUserProfilePreferencesRange(long range) {
        userProfilePreferences.setRange(range);
    }

    public void removeFootprintKeyFromMap(String footprintKey) {
        keyPositions.remove(footprintKey);
        getFootprintsMain.deleteCacheByKey(footprintKey);

        if (keyPositions.isEmpty()) {
            try {
                if (hasMore) {
                    onLoadMore(false);
                } else {
                    getView().showNoMoreFootprints();
                }
            } catch (Exception e) {}
        }
    }

    public void onClickedFootprintAnimationSelected(String footprintKey) {
        try {
            getView().onClickedFootprintAnimationSelected(keyPositions.indexOf(footprintKey));
        } catch (Exception e) {}
    }

    public boolean isRaddarViewRefreshing() {
        return isForceRefreshing;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public MyUserProfileViewModel getUserProfilePreferences() {
        return mapperUserProfile.map(userProfilePreferences.getUserProfilePreferences());
    }

    public String myUserProfileKey() {
        return userProfilePreferences.getUserKey();
    }

    public void addFootprintMainInCache(FootprintMain footprintMain) {
        PaginatedCollection<FootprintMain> footprintsMainCollection = getFootprintsMain.addFootprintMainInCache(footprintMain);
        updateFootprintsMain(footprintsMainCollection);
    }

    public void removeFootprintMainInCache(String footprintMainKey) {
        if (getFootprintsMain.footprintMainExists(footprintMainKey)) {
            PaginatedCollection<FootprintMain> footprintsMainCollection = getFootprintsMain.removeFootprintMainInCache(footprintMainKey);
            updateFootprintsMain(footprintsMainCollection);
        } else {
        }
    }

    private void updateFootprintsMain(PaginatedCollection<FootprintMain> footprintsMainCollection) {
        List<FootprintMainViewModelContract> footprintsMainViewModel = mapperFootprintsMain.map(footprintsMainCollection);

        try {
            // FIXME: This is code smell :[
            raddarApplicationWrapper.updateFootprintsMain(footprintsMainCollection, footprintsMainViewModel);
        } catch (Exception e) {}
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void hideFootprints();

        void showFootprintsMain(List<FootprintMainViewModelContract> footprintsMain);

        void showHasMore(boolean hasMore);

        void clearFootprints();

        void openFootprintMainDetails(String footprintMainKey, long comments, int position);

        void moveMapAndAddMaker(LatLngBounds latLngBounds, List<LatLng> listLatLng, List<Integer> typeFootprints,
            List<Integer> usersRelationship, List<Boolean> visibleFootprints);

        void removeAllMarkers();

        void showEmptyFootprints();

        void onClickedFootprintAnimationSelected(int position);

        void showTotalFootprints(String totalFootprints);

        void hideTotalFootprints();

        void showNoMoreFootprints();

        void loadMoreByScrolled(boolean loadMoreByScrolled);

        void openUserProfile(String footprintMainKey, String userKey, int position);

        void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel);

        void lastVoteRadarScanClicked();

        void updateAfterVoteMyOwnFootprint();
    }
}
