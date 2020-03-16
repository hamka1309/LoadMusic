package com.mia.loadmusic.main.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mia.loadmusic.R;
import com.mia.loadmusic.adapter.HomeAdapter;
import com.mia.loadmusic.main.presenter.MainContract;
import com.mia.loadmusic.main.presenter.impl.MainPresenterImp;
import com.mia.loadmusic.model.Music;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    private HomeAdapter homeAdapter;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImp(this, this);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void initView() {
        homeAdapter = new HomeAdapter(this);
        if (this.rvMain != null) {
            this.rvMain.setAdapter(homeAdapter);
        }
        presenter.loadData();
    }

    @Override
    public void showData(List<Music> listMusics) {
        if (homeAdapter != null) {
            homeAdapter.setListMusics(listMusics);
        }
    }

    @Override
    public void showEmpty() {
        Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
