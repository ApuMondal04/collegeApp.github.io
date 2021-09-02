package com.example.imed.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.imed.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private CourseAdapter adapter;
    private List<CoursesModel> list;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about, container, false);

        list=new ArrayList<>();
        list.add(new CoursesModel(R.drawable.ic_comp,"BCA","Bachelor in Computer Application (BCA) is an undergraduate degree course in computer applications. ... The duration of the course is 3 years and divided into 6 semesters. It comprises of the subjects like database, networking, data structure, core programming languages like 'C' and 'java'."));
        list.add(new CoursesModel(R.drawable.ic_comp,"MCA","MCA (Master of Computer Applications) is a professional master's degree in computer science. It focuses on providing a theoretical as well as practical training to students in the related field.” Computer Applications is a use of computer to solve a problem or to accomplish a particular job."));
        list.add(new CoursesModel(R.drawable.ic_bag,"BBA","Bachelor of Business Administration or BBA is one of the most popular bachelor degree programmes.The BBA course offers knowledge and training in management and leadership skills to prepare them for managerial roles and entrepreneurship."));
        list.add(new CoursesModel(R.drawable.ic_bag,"MBA","An MBA, also known as a Master of Business Administration, is a post-secondary degree that provides in-depth knowledge and training from the perspective of business management."));

        adapter=new CourseAdapter(getContext(),list);

        viewPager=view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        ImageView imageView=view.findViewById(R.id.college_image);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/imed-pune.appspot.com/o/images%2Fimed_pune.jpg?alt=media&token=73efc295-a8bd-4e58-babf-ee6a8654345a")
                .into(imageView);

        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }
    private void openMap() {
        Uri uri=Uri.parse("geo:0, 0?q=Institute of Management and Entrepreneurship Development,Pune");
        Intent intent=new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}