package com.raddarapp.presentation.android.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.raddarapp.R;
import com.vanniktech.emoji.EmojiTextView;

import java.util.ArrayList;
import java.util.List;

import io.ghyeok.stickyswitch.widget.StickySwitch;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class TutorialUtils {

    private Activity activity;
    private Typeface font;

    // Tutorial Map
    private ImageView topRankingsView;
    private ImageView myTerritoryPositionView;
    private RelativeLayout viewScanComplete;
    private ImageView githubView;
    private ImageView createFootprintImageView;

    // Tutorial My Profile
    private ImageView menuSettingsView;
    private ImageView audioProfileView;
    private LinearLayout linearFootprints;
    private LinearLayout linearDeaths;
    private LinearLayout linearVotes;
    private LinearLayout linearCompliments;
    private LinearLayout linearStars;
    private ImageView myProfileIconView;
    private ImageView icCoinMining;
    private ImageView icPromoCode;
    private int extraMeters;
    private int winExtraMeters;
    private TextView userRaddarRangeView;

    // Tutorial Create Flag
    private ImageView previsualizationFlagView;
    private EmojiTextView emojiCategoryView;
    private StickySwitch footprintVisibleView;

    // Tutorial My Ranking
    private RelativeLayout relativeTerritoryView;
    private ImageView influencersSettingsView;
    private ImageView showTerritoryMapView;
    private ImageView showTerritoryParentView;

    public TutorialUtils(Activity activity, String fontName, ImageView topRankingsView,
            ImageView myTerritoryPositionView, RelativeLayout viewScanComplete, ImageView githubView,
            ImageView createFootprintImageView) {
        this.activity = activity;
        this.font = TypefaceUtils.load(activity.getAssets(), "fonts/" + fontName);
        this.topRankingsView = topRankingsView;
        this.githubView = githubView;
        this.myTerritoryPositionView = myTerritoryPositionView;
        this.viewScanComplete = viewScanComplete;
        this.createFootprintImageView = createFootprintImageView;
    }

    public TutorialUtils(Activity activity, String fontName, ImageView menuSettingsView,
            ImageView audioProfileView, LinearLayout linearFootprints, LinearLayout linearDeaths,
            LinearLayout linearVotes, LinearLayout linearCompliments, LinearLayout linearStars,
            TextView userRaddarRangeView, ImageView myProfileIconView,
            ImageView icCoinMining, ImageView icPromoCode, int extraMeters, int winExtraMeters) {
        this.activity = activity;
        this.font = TypefaceUtils.load(activity.getAssets(), "fonts/" + fontName);
        this.menuSettingsView = menuSettingsView;
        this.audioProfileView = audioProfileView;
        this.linearFootprints = linearFootprints;
        this.linearDeaths = linearDeaths;
        this.linearVotes = linearVotes;
        this.linearCompliments = linearCompliments;
        this.linearStars = linearStars;
        this.myProfileIconView = myProfileIconView;
        this.icCoinMining = icCoinMining;
        this.icPromoCode = icPromoCode;
        this.extraMeters = extraMeters;
        this.winExtraMeters = winExtraMeters;
        this.userRaddarRangeView = userRaddarRangeView;
    }

    public TutorialUtils(Activity activity, String fontName, ImageView previsualizationFlagView,
            EmojiTextView emojiCategoryView, StickySwitch footprintVisibleView) {
        this.activity = activity;
        this.font = TypefaceUtils.load(activity.getAssets(), "fonts/" + fontName);
        this.previsualizationFlagView = previsualizationFlagView;
        this.emojiCategoryView = emojiCategoryView;
        this.footprintVisibleView = footprintVisibleView;
    }

    public TutorialUtils(Activity activity, String fontName, RelativeLayout relativeTerritoryView,
            ImageView influencersSettingsView, ImageView showTerritoryMapView, ImageView showTerritoryParentView) {
        this.activity = activity;
        this.font = TypefaceUtils.load(activity.getAssets(), "fonts/" + fontName);
        this.relativeTerritoryView = relativeTerritoryView;
        this.influencersSettingsView = influencersSettingsView;
        this.showTerritoryMapView = showTerritoryMapView;
        this.showTerritoryParentView = showTerritoryParentView;
    }

    public TutorialUtils(Activity activity, String fontName) {
        this.activity = activity;
        this.font = TypefaceUtils.load(activity.getAssets(), "fonts/" + fontName);
    }

    public void initializeTutorialMap() {
        initializeSequenceTutorialMap();
    }

    public void initializeTutorialMyProfile() {
        initializeSequenceTutorialMyProfile();
    }

    public void initializeTutorialCreateFlag() {
        initializeSequenceTutorialCreateFlag();
    }

    private void initializeSequenceTutorialMap() {
        List<TapTarget> listTargets = new ArrayList<>();
        listTargets.add(raddarScan());
        listTargets.add(showTerritory());
        listTargets.add(topRankings());
        listTargets.add(github());
        listTargets.add(createFootprint());

        final TapTargetSequence sequence = new TapTargetSequence(activity).targets(listTargets).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {
                new PreferencesUtils(activity).setShowTutorialMap(false);
            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {}

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {}
        });
        sequence.continueOnCancel(true);
        sequence.considerOuterCircleCanceled(true);
        sequence.start();
    }

    private TapTarget topRankings() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_top_rankings_description));

        return TapTarget.forView(topRankingsView, activity.getString(R.string.tutorial_screen_top_rankings_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget github() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_github_description));

        return TapTarget.forView(githubView, activity.getString(R.string.tutorial_screen_github_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget showTerritory() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_show_territory_description));

        return TapTarget.forView(myTerritoryPositionView, activity.getString(R.string.tutorial_screen_show_territory_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget raddarScan() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_scan_description));

        return TapTarget.forView(viewScanComplete, activity.getString(R.string.tutorial_screen_scan_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(3);
    }

    private TapTarget createFootprint() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_create_footprint_description));

        return TapTarget.forView(createFootprintImageView, activity.getString(R.string.tutorial_screen_create_footprint_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.marker_me)
                .tintTarget(false)
                .id(4);
    }

    private void initializeSequenceTutorialMyProfile() {
        List<TapTarget> listTargets = new ArrayList<>();
        listTargets.add(menuSettingsProfile());
        listTargets.add(audioProfile());
        listTargets.add(starsProfile());
        listTargets.add(rangeProfile());
        //listTargets.add(footprintsProfile());
        //listTargets.add(deathsProfile());
        //listTargets.add(votesProfile());
        listTargets.add(complimentsProfile());
        listTargets.add(myProfileIcon());

        final TapTargetSequence sequence = new TapTargetSequence(activity).targets(listTargets).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {
                new PreferencesUtils(activity).setShowTutorialMyProfile(false);
            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {}

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {}
        });
        sequence.continueOnCancel(true);
        sequence.considerOuterCircleCanceled(true);
        sequence.start();
    }

    private TapTarget menuSettingsProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_menu_settings_description));

        return TapTarget.forView(menuSettingsView, activity.getString(R.string.tutorial_screen_menu_settings_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget audioProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_audio_profile_description));

        return TapTarget.forView(audioProfileView, activity.getString(R.string.tutorial_screen_audio_profile_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget rangeMinedProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_coin_mined_description));

        return TapTarget.forView(icCoinMining, activity.getString(R.string.tutorial_screen_coin_mined_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget promoCodeProfile() {
        NumberUtils numberUtils = new NumberUtils();
        String extraMeters = numberUtils.rangeOrScopeToStringWithoutDecimals(this.extraMeters);
        String winExtraMeters = numberUtils.rangeOrScopeToStringWithoutDecimals(this.winExtraMeters);

        final SpannableString spannedDesc = new SpannableString(activity.getString(
                R.string.tutorial_screen_promo_code_description, extraMeters, winExtraMeters));
        return TapTarget.forView(icPromoCode, activity.getString(R.string.tutorial_screen_promo_code_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget complimentsProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_compliments_description));

        return TapTarget.forView(linearCompliments, activity.getString(R.string.tutorial_screen_compliments_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget footprintsProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_footprints_description));

        return TapTarget.forView(linearFootprints, activity.getString(R.string.tutorial_screen_footprints_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget deathsProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_deaths_description));

        return TapTarget.forView(linearDeaths, activity.getString(R.string.tutorial_screen_deaths_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget votesProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_votes_description));

        return TapTarget.forView(linearVotes, activity.getString(R.string.tutorial_screen_votes_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget starsProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_stars_description));

        return TapTarget.forView(linearStars, activity.getString(R.string.tutorial_screen_stars_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget rangeProfile() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_range_description));

        return TapTarget.forView(userRaddarRangeView, activity.getString(R.string.tutorial_screen_range_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(2);
    }

    private TapTarget myProfileIcon() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_icon_profile_description));

        return TapTarget.forView(myProfileIconView, activity.getString(R.string.tutorial_screen_icon_profile_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.white)
                .tintTarget(false)
                .id(2);
    }

    private void initializeSequenceTutorialCreateFlag() {
        List<TapTarget> listTargets = new ArrayList<>();
        listTargets.add(classificateFlag());
        listTargets.add(previsualizationFlag());
        listTargets.add(footprintVisibleFlag());

        final TapTargetSequence sequence = new TapTargetSequence(activity).targets(listTargets).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {
                new PreferencesUtils(activity).setShowTutorialCreateFootprint(false);
            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {}

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {}
        });
        sequence.continueOnCancel(true);
        sequence.considerOuterCircleCanceled(true);
        sequence.start();
    }

    private TapTarget previsualizationFlag() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_previsualization_description));

        return TapTarget.forView(previsualizationFlagView, activity.getString(R.string.tutorial_screen_previsualization_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(1);
    }

    private TapTarget classificateFlag() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_classificate_description));

        return TapTarget.forView(emojiCategoryView, activity.getString(R.string.tutorial_screen_classificate_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(1);
    }

    private TapTarget footprintVisibleFlag() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_footprint_visible_description));

        return TapTarget.forView(footprintVisibleView, activity.getString(R.string.tutorial_screen_footprint_visible_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(1);
    }

    public void initializeTutorialUserRanking() {
        initializeSequenceTutorialUserRanking();
    }

    private void initializeSequenceTutorialUserRanking() {
        List<TapTarget> listTargets = new ArrayList<>();
        listTargets.add(userRanking());
        listTargets.add(territoryNames());
        listTargets.add(influencersSettings());
        listTargets.add(territoryMap());

        if (showTerritoryParentView.getVisibility() == View.VISIBLE) {
            listTargets.add(territoryParent());
        }

        final TapTargetSequence sequence = new TapTargetSequence(activity).targets(listTargets).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {
                new PreferencesUtils(activity).setShowTutorialMyRanking(false);
            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {}

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {}
        });
        sequence.continueOnCancel(true);
        sequence.considerOuterCircleCanceled(true);
        sequence.start();
    }

    private TapTarget userRanking() {
        final Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final Drawable droid = ContextCompat.getDrawable(activity, R.drawable.tutorial_influencer);
        final Rect droidTarget = new Rect(0, 0, 0, 0);
        droidTarget.offset(size.x / 2, size.y / 2);

        return TapTarget.forBounds(droidTarget, activity.getString(R.string.tutorial_screen_influencer_title),
                activity.getString(R.string.tutorial_screen_influencer_description))
                .icon(droid)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .targetRadius(150)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.black)
                .tintTarget(false)
                .id(1);
    }

    private TapTarget territoryNames() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_territory_name_description));

        return TapTarget.forView(relativeTerritoryView, activity.getString(R.string.tutorial_screen_territory_name_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .tintTarget(false)
                .targetRadius(30)
                .dimColor(R.color.colorPrimaryDark)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.black)
                .targetCircleColorInt(Color.GREEN)
                .outerCircleColorInt(Color.GRAY)
                .id(2);
    }

    private TapTarget influencersSettings() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_influencer_settings_description));

        return TapTarget.forView(influencersSettingsView, activity.getString(R.string.tutorial_screen_influencer_settings_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .tintTarget(false)
                .id(3);
    }

    private TapTarget territoryMap() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_territory_map_description));

        return TapTarget.forView(showTerritoryMapView, activity.getString(R.string.tutorial_screen_territory_map_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .tintTarget(false)
                .targetRadius(30)
                .dimColor(R.color.black)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .targetCircleColorInt(Color.GREEN)
                .outerCircleColorInt(Color.GRAY)
                .id(4);
    }

    private TapTarget territoryParent() {
        final SpannableString spannedDesc = new SpannableString(activity.getString(R.string.tutorial_screen_territory_parent_description));

        return TapTarget.forView(showTerritoryParentView, activity.getString(R.string.tutorial_screen_territory_parent_title), spannedDesc)
                .cancelable(true)
                .drawShadow(true)
                .textTypeface(font)
                .descriptionTypeface(font)
                .titleTypeface(font)
                .titleTextDimen(R.dimen.textsize_big1)
                .descriptionTextDimen(R.dimen.textsize_medium)
                .tintTarget(false)
                .targetRadius(30)
                .dimColor(R.color.black)
                .outerCircleColor(R.color.black)
                .targetCircleColor(R.color.blue_dark_tutorial)
                .targetCircleColorInt(Color.GREEN)
                .outerCircleColorInt(Color.GRAY)
                .id(5);
    }
}
