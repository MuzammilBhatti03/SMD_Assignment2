package com.example.assignement2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ButtonFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String buttonText;
    private View.OnClickListener buttonClickListener;
    private Integer Height, width, HorizontalMargin=50;
    String bgcolor, textcolor;
    Button button ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ButtonFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ButtonFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ButtonFrag newInstance(String param1, String param2) {
        ButtonFrag fragment = new ButtonFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_button, container, false);
        button = view.findViewById(R.id.btn);

        if (buttonText != null) {
            button.setText(buttonText);
        }

        if (buttonClickListener != null) {
            button.setOnClickListener(buttonClickListener);
        }

        // Apply configurations only if they were set explicitly
        if (Height != null && width != null) {
            ViewGroup.LayoutParams params = button.getLayoutParams();
            params.width = width;
            params.height = Height;
            button.setLayoutParams(params);
        }

        if (bgcolor != null) {
            button.setBackgroundColor(Color.parseColor(bgcolor));
        }

        if (textcolor != null) {
            button.setTextColor(Color.parseColor(textcolor));
        }
        Height=-2;
        width=-1;
        textcolor="#000000";
        bgcolor="#FFFFFF";
        //match partent -1 and wrap content -2
        setconfiguration(Height,width,bgcolor,textcolor,HorizontalMargin);
        return view;
    }
    public void setButtonText(String text) {
        this.buttonText = text;
        if (getView() != null) {
            Button button = getView().findViewById(R.id.btn);
            button.setText(text);
        }
    }

    public void setButtonClickListener(View.OnClickListener listener) {
        this.buttonClickListener = listener;
        if (getView() != null) {
            button.setOnClickListener(listener);
        }
    }

    public void setconfiguration(int Height, int width, String bgcolor, String textcolor,int horizontalMargin){
        System.out.println("callled set configuration");
        this.Height = Height;
        this.width = width;
        this.bgcolor = bgcolor;
        this.textcolor = textcolor;

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) button.getLayoutParams();

// Set width and height
        params.width = width;  // Example: ViewGroup.LayoutParams.MATCH_PARENT or WRAP_CONTENT
        params.height = Height;
        params.setMargins(horizontalMargin, params.topMargin, horizontalMargin, params.bottomMargin);

// Apply the updated layout parameters
        button.setLayoutParams(params);

// Set background color and text color
        button.setBackgroundColor(Color.parseColor(bgcolor));
        button.setTextColor(Color.parseColor(textcolor));
    }


}