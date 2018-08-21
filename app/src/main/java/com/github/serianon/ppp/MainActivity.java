package com.github.serianon.ppp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static Intent createIntent(Context context, int cardIndex) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(CURRENT_ITEM_INDEX_KEY, cardIndex);
        return intent;
    }

    private static final String CURRENT_ITEM_INDEX_KEY = "CURRENT_ITEM_INDEX_KEY";

    private CardPagerAdapter mCardPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.postponeEnterTransition(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCardPagerAdapter = new CardPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(mCardPagerAdapter);

        enableViewPagerPreviews();

        loadCurrentItemFromIntentExtra();
    }

    private void enableViewPagerPreviews() {
        // Left and right card preview
        mViewPager.setClipToPadding(false);
        mViewPager.setPadding(100, 0, 100, 0);
    }

    private void loadCurrentItemFromIntentExtra() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(CURRENT_ITEM_INDEX_KEY)) {
            mViewPager.setCurrentItem(intent.getIntExtra(CURRENT_ITEM_INDEX_KEY, 0));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.qq:
                Intent intent = new Intent(this, OverviewActivity.class);
                /*CardView currentCardView = mCardPagerAdapter.getItem(mViewPager.getCurrentItem())
                        .getView().findViewById(R.id.cardview);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, currentCardView, ViewCompat.getTransitionName(currentCardView));
                startActivity(intent, options.toBundle());*/
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class CardPagerAdapter extends FragmentPagerAdapter {

        public CardPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            String[] cardValues = getResources().getStringArray(R.array.fibonacci);
            return CardFragment.newInstance(cardValues[position]);
        }

        @Override
        public int getCount() {
            return getResources().getIntArray(R.array.fibonacci).length;
        }
    }

    public static class CardFragment extends Fragment {

        private static final String CARD_VALUE_KEY = "CARD_VALUE";

        public CardFragment() {
        }

        public static CardFragment newInstance(String cardValue) {
            CardFragment fragment = new CardFragment();
            Bundle args = new Bundle();
            args.putString(CARD_VALUE_KEY, cardValue);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.layout_card, container, false);
            TextView textView = rootView.findViewById(R.id.number);
            textView.setText(getArguments().getString(CARD_VALUE_KEY));
            return rootView;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ActivityCompat.startPostponedEnterTransition(getActivity());
        }

    }

}
