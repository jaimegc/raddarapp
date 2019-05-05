/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raddarapp.presentation.android.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends android.support.v13.app.FragmentPagerAdapter {

  private final List<ViewPagerFragmentHolder> fragments = new ArrayList<>();

  public FragmentAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @Override
  public Fragment getItem(int position) {
    ViewPagerFragmentHolder fragmentHolder = fragments.get(position);
    return fragmentHolder.fragment;
  }

  @Override
  public int getCount() {
    return fragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    ViewPagerFragmentHolder fragmentHolder = fragments.get(position);
    return fragmentHolder.pageTitle;
  }

  public void addFragment(Fragment fragment, String pageTitle) {
    fragments.add(new ViewPagerFragmentHolder(fragment, pageTitle));
  }

  public void addFragment(Fragment fragment) {
    fragments.add(new ViewPagerFragmentHolder(fragment, ""));
  }

  private static class ViewPagerFragmentHolder {
    public final Fragment fragment;
    public final String pageTitle;

    public ViewPagerFragmentHolder(Fragment fragment, String pageTitle) {
      this.fragment = fragment;
      this.pageTitle = pageTitle;
    }
  }
}
