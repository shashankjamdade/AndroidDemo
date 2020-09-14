package com.matrimony.demo.ui;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.rule.ActivityTestRule;

import com.matrimony.demo.R;

import org.junit.After;
import org.junit.Before;

import org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

class UserListActivityTest {

    private UserListActivity userListActivity;
    private RecyclerView mRecycleView;
    @Rule
    public ActivityTestRule<UserListActivity> activityTestRule = new ActivityTestRule<UserListActivity>(UserListActivity.class);

    @Before
    public void setUp() {
        userListActivity = activityTestRule.getActivity();
        mRecycleView = userListActivity.findViewById(R.id.userListRecyclerView);
    }

    @Test
    public void testAct(){
        assertNotNull(mRecycleView.getContext());
    }

    @Test
    public void ensureListViewIsPresent() throws Exception {
        UserListActivity activity = activityTestRule.getActivity();
        View viewByIdw = activity.findViewById(R.id.userListRecyclerView);
        assertThat(viewByIdw,notNullValue());
        assertThat(viewByIdw, instanceOf(RecyclerView.class));
        RecyclerView userListRecyclerView = (RecyclerView) viewByIdw;
        RecyclerView.Adapter adapter = userListRecyclerView.getAdapter();
        assertThat(adapter, instanceOf(UserListAdapter.class));
    }

    @After
    public void tearDown() {
        userListActivity = null;
    }
}