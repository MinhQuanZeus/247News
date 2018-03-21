package zeus.quantm.a247news.fragments;


import android.os.Bundle;
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

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setCurrentItem(this.categoryId);
        setCategoryImage(this.categoryId);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("main", tab.getPosition()+"");
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
        this.categories.add(new Category("Phap luat", "http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        this.categories.add(new Category("Suc khoe", "http://i.imgur.com/DvpvklR.png"));
        this.categories.add(new Category("Phap luat", "https://http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        this.categories.add(new Category("Phap luat", "https://http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        this.categories.add(new Category("Phap luat", "https://http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        this.categories.add(new Category("Phap luat", "https://http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        this.categories.add(new Category("Phap luat", "https://http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        this.categories.add(new Category("Phap luat", "https://http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        this.categories.add(new Category("Phap luat", "https://http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
        this.categories.add(new Category("Phap luat", "https://http://hotroontap.com/wp-content/uploads/2015/09/blog11.jpg"));
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
