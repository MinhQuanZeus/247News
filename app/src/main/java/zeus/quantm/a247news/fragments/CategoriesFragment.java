package zeus.quantm.a247news.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import zeus.quantm.a247news.R;
import zeus.quantm.a247news.activities.FavoritesActivity;
import zeus.quantm.a247news.models.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private ImageView ivCategory;
    private List<Category> categories = new ArrayList<>();
    private int categoryId;
    public CategoriesFragment() {
        // Required empty public constructor
    }

    public CategoriesFragment setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        setUpCategories();
        ivCategory = (ImageView) view.findViewById(R.id.iv_category);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FavoritesActivity.class);
                getActivity().startActivity(intent);
            }
        });


        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setCurrentItem(this.categoryId);
        setCategoryImage(this.categoryId);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCategoryImage(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private void setCategoryImage(int position) {
        Picasso.get().load(this.categories.get(position).getImage()).into(this.ivCategory);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(this.categories.get(position).getName());
    }

    private void setUpCategories(){
        this.categories = new ArrayList<>();

        this.categories.add(new Category("Trang Chủ", "https://duyanhweb.com/wp-content/uploads/trang-chu-la-gi.jpg"));
        this.categories.add(new Category("Thời Sự", "https://i.ytimg.com/vi/VD7kuSX_lXw/maxresdefault.jpg"));
        this.categories.add(new Category("Kinh Doanh", "http://tslethamduong.com/public/files/editor/images/kd.jpg"));
        this.categories.add(new Category("Giải Trí", "https://pbs.twimg.com/profile_images/777845924403949568/OvHMdybN_400x400.jpg"));
        this.categories.add(new Category("Thể Thao", "http://review.siu.edu.vn/Upload/Siu17/top%205%20su%20kien%20the%20thao%20lon%20nhat%20hanh%20tinh%201.png"));
        this.categories.add(new Category("Pháp Luật", "http://thcsdichvong.edu.vn/uploads/news/2018_01/phap-luat-2014-webmoi.jpg"));
        this.categories.add(new Category("Giáo  Dục", "http://gddt.daklak.gov.vn/wp-content/uploads/Thumbcache/no-thumb-ne45vzwrrm6guvzoda1jh1ez0j6wbk6uu8to1f81v2.png"));
        this.categories.add(new Category("Sức Khỏe", "http://xuatkhaulaodongs3.vn/wp-content/uploads/2015/06/dieu-kien-suc-khoe-di-xuat-khau-lao-dong-nhat-ban.jpg"));
        this.categories.add(new Category("Gia Đình", "http://kivi.vn/upload_images/images/suc-khoe(1).jpg"));
        this.categories.add(new Category("Cười", "http://thcs.daytot.vn/uploads/thcs/2016_11/chuyen-cuoi.jpg"));
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager());
        for (int i = 0; i < this.categories.size(); i++) {
            adapter.addFrag(new NewsListFragment().setCategoryId(i), this.categories.get(i).getName());
        }
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
