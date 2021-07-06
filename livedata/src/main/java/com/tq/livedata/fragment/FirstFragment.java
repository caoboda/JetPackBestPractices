package com.tq.livedata.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.tq.livedata.R;


public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root= inflater.inflate(R.layout.fragment_first, container, false);
        final SeekBar seekBar= root.findViewById(R.id.seekBar);
        //new ViewModelProvider(this，xxxx）；这里不能使用this 必须使用getActivity()否则不能共享数据
        final SeekBarViewModel  seekBarViewModel=  new ViewModelProvider(getActivity(),new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(SeekBarViewModel.class);

        seekBarViewModel.getmProgress().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                seekBar.setProgress(integer);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarViewModel.getmProgress().setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return root;
    }
}