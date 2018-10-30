package com.github.serianon.ppp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GridView gridView = findViewById(R.id.gridview);
        gridView.setNumColumns(3);

        String[] cardValues = getResources().getStringArray(R.array.fibonacci);
        CardsAdapter cardsAdapter = new CardsAdapter(this, cardValues);
        gridView.setAdapter(cardsAdapter);
    }

    public class CardsAdapter extends BaseAdapter {

        private final String[] mCardValues;

        private LayoutInflater mLayoutInflater;

        public CardsAdapter(Context context, String[] cardValues) {
            mCardValues = cardValues;
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return mCardValues.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return -1;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                view = mLayoutInflater.inflate(R.layout.layout_card, null);
            } else {
                view = convertView;
            }

            TextView textView = view.findViewById(R.id.number);
            textView.setText(mCardValues[position]);

            CardView cardView = view.findViewById(R.id.cardview);
            cardView.setOnClickListener(v -> {
                Intent intent = CardActivity.createIntent(OverviewActivity.this, position);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        OverviewActivity.this, cardView, ViewCompat.getTransitionName(cardView));
                startActivity(intent, options.toBundle());
            });

            return view;
        }
    }

}
