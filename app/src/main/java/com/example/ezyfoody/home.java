package com.example.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity implements View.OnClickListener{

    public CardView card_drinks, card_foods, card_snacks, card_topup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        card_drinks = (CardView) findViewById(R.id.card_drinks);
        card_foods = (CardView) findViewById(R.id.card_foods);
        card_snacks = (CardView) findViewById(R.id.card_snacks);
        card_topup = (CardView) findViewById(R.id.card_topup);

        card_drinks.setOnClickListener(this);
        card_foods.setOnClickListener(this);
        card_snacks.setOnClickListener(this);
        card_topup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

//        switch (v.getId()){
//            case R.id.card_drinks:
//                i = new Intent(this, DrinksActivity.class);
//                startActivity(i);
//                break;
//
//            case R.id.card_foods:
//                i = new Intent(this, FoodsActivity.class);
//                startActivity(i);
//                break;
//
//            case R.id.card_snacks:
//                i = new Intent(this, SnacksActivity.class);
//                startActivity(i);
//                break;
//
//            case R.id.card_topup:
//                i = new Intent(this, TopupActivity.class);
//                startActivity(i);
//                break;
//        }

    }
}